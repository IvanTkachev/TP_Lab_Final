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

public class RefererRequestController {

    public ListView attachmentList;
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

            attachments.addAll(requestService.getRequestById(RefererController.refererRequest.getId()).getAttachments());

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
                if("LEGAL".equals(RefererController.refererRequest.getType().name())){
                    clientType.setValue("Legal entity");
                }
                else clientType.setValue("Physical person");
            }
            costField.setValue(RefererController.refererRequest.getAmount());
            costText.setText("Cost: " + (int)costField.getValue() + "$");
            nameFiled.setText(RefererController.refererRequest.getName());
            attachmentList.setItems(attachments);



        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }


    public void approveRefererRequest(ActionEvent actionEvent) {
        RefererController.requests.remove(RefererController.refererRequest);

        List<String> attachment = new ArrayList<>(attachments);

        Request updateRequest = new Request(
                RefererController.refererRequest.getId(),
                nameFiled.getText(),
                RefererController.refererRequest.getType(),
                (int)costField.getValue(),
                new RequestType(2, "Analysed"),
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

    public void checkFinancialAction(ActionEvent actionEvent) {
        PrintWriter printWriter = null;
        try {
            printWriter = new PrintWriter("D:\\BSU\\TP\\New folder\\TP_Lab_Final\\src\\resources\\request\\"+ RefererController.refererRequest.getId() +"_fin.txt");
            BufferedWriter bw = new BufferedWriter(printWriter);
            bw.write(RefererController.refererRequest.getName() + ":");
            bw.newLine();
            bw.write("Financial position - " +(int)((Math.random()+1)*1000) + "$");
            bw.newLine();
            bw.write( "Income - " + +(int)((Math.random()+1)*300) + "$" );
            bw.close();
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(!attachments.contains("D:\\BSU\\TP\\New folder\\TP_Lab_Final\\src\\resources\\request\\"+ RefererController.refererRequest.getId() +"_fin.txt"))
            attachments.add("D:\\BSU\\TP\\New folder\\TP_Lab_Final\\src\\resources\\request\\"+ RefererController.refererRequest.getId() +"_fin.txt");
    }
}
