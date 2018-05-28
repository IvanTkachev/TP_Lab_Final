package com.credit.system.javaFX.controller;

import com.credit.system.entity.Request;
import com.credit.system.entity.UserType;
import com.credit.system.service.RequestService;
import com.credit.system.service.impl.RequestServiceImpl;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.naming.Context;
import javax.naming.InitialContext;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RequestController {

    public Text costText;
    public ListView attachmentList;
    public Button attachmentButton;
    private Stage dialogStage;
    public TextField nameFiled;
    public Slider costField;
    public ChoiceBox clientType;
    private RequestService requestService;

    public static ObservableList<String> attachments = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        try {

            Context context = new InitialContext();
            attachmentList.setItems(attachments);
            requestService = new RequestServiceImpl();
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
            clientType.setValue("Legal entity");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void newRequestAction(ActionEvent actionEvent) {
        if("".equals(nameFiled.getText())){
            errorMessage("Name is empty. Try again please");
        }
        else {
            dialogStage.close();
            String result = "";
            if("Legal entity".equals(clientType.getValue().toString())){
                result = "LEGAL";
            }
            else result = "PHYSICAL";
            List<String> attachment = new ArrayList<>(attachments);
            requestService.create(
                    new Request(0, nameFiled.getText(), UserType.valueOf(result), (int)costField.getValue(), attachment)
            );
            attachments.clear();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Request add to analyse");
            alert.show();
        }
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }


    private void errorMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(message);
        alert.show();
    }

    public void addAttachmentAction(ActionEvent actionEvent) {
        final FileChooser fileChooser = new FileChooser();
        configureFileChooser(fileChooser);
        File file = fileChooser.showOpenDialog(dialogStage);
        if (file != null) {
            attachments.add(file.getPath());
        }
    }
    private static void configureFileChooser(final FileChooser fileChooser) {
        fileChooser.setTitle("View Pictures");
        fileChooser.setInitialDirectory(
                new File(System.getProperty("user.home"))
        );
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text file", "*.txt")
        );
    }
}
