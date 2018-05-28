package com.credit.system.javaFX.controller;

import com.credit.system.entity.Request;
import com.credit.system.entity.RequestType;
import com.credit.system.service.RequestService;
import com.credit.system.service.impl.RequestServiceImpl;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javax.naming.Context;
import javax.naming.InitialContext;
import java.awt.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class ServantRequestController {
    public ListView attachmentList;
    public Button attachmentButton;
    private RequestService requestService;
    public Text costText;
    public TextField nameFiled;
    public Slider costField;
    public ChoiceBox clientType;
    private Stage dialogStage;
    public static ObservableList<String> attachments = FXCollections.observableArrayList();

    private Desktop desktop = Desktop.getDesktop();


    @FXML
    private void initialize() {
        try {
            Context context = new InitialContext();
            requestService = new RequestServiceImpl();
            attachments.addAll(requestService.getRequestById(ServantController.servantRequest.getId()).getAttachments());

            if(costField != null){
                costField.valueProperty().addListener(new ChangeListener<Number>() {
                    @Override
                    public void changed(ObservableValue<? extends Number> observable,
                                        Number oldValue, Number newValue) {

                        costText.setText("Cost: " + newValue.intValue() + "$");
                    }
                });

                clientType.setItems(FXCollections.observableArrayList(
                        "Legal entity", "Physical person")
                );
                if("LEGAL".equals(ServantController.servantRequest.getType().name())){
                    clientType.setValue("Legal entity");
                }
                else clientType.setValue("Physical person");
            }
            costField.setValue(ServantController.servantRequest.getAmount());
            costText.setText("Cost: " + (int)costField.getValue() + "$");
            nameFiled.setText(ServantController.servantRequest.getName());

            attachmentList.setItems(attachments);


        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }


    public void saveServantRequest(ActionEvent actionEvent) {
        ServantController.requests.remove(ServantController.servantRequest);

        List<String> attachment = new ArrayList<>(attachments);

        Request updateRequest = new Request(
                ServantController.servantRequest.getId(),
                nameFiled.getText(),
                ServantController.servantRequest.getType(),
                (int)costField.getValue(),
                new RequestType(3, "Staffed"),
                attachment
        );
        requestService.update(updateRequest);
        dialogStage.close();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Request approved!");
        alert.show();
    }

    public void openFileAction(MouseEvent mouseEvent) {
        if(mouseEvent.getClickCount() == 2){
            try {
                desktop.open(new File(attachmentList.getSelectionModel().getSelectedItems().get(0).toString()));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void checkAccountStatus(ActionEvent actionEvent) {
        PrintWriter printWriter = null;
        try {
            printWriter = new PrintWriter("D:\\BSU\\TP\\New folder\\TP_Lab_Final\\src\\resources\\request\\"+ ServantController.servantRequest.getId() +"_account.txt");
            BufferedWriter bw = new BufferedWriter(printWriter);
            bw.write(ServantController.servantRequest.getName() + ":");
            bw.newLine();
            bw.write("PriorBank - " +(int)((Math.random()+1)*1000) + "$");
            bw.newLine();
            bw.write( "TechnoBank - " + +(int)((Math.random()+1)*1000) + "$" );
            bw.newLine();
            bw.write( "BelInvestBank - " + +(int)((Math.random()+1)*1000) + "$" );
            bw.close();
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(!attachments.contains("D:\\BSU\\TP\\New folder\\TP_Lab_Final\\src\\resources\\request\\"+ ServantController.servantRequest.getId() +"_account.txt"))
            attachments.add("D:\\BSU\\TP\\New folder\\TP_Lab_Final\\src\\resources\\request\\"+ ServantController.servantRequest.getId() +"_account.txt");
    }
}
