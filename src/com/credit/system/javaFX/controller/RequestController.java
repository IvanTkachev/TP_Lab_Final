package com.credit.system.javaFX.controller;

import com.credit.system.entity.Request;
import com.credit.system.entity.UserType;
import com.credit.system.service.RequestService;
import com.credit.system.service.impl.RequestServiceImpl;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javax.naming.Context;
import javax.naming.InitialContext;

public class RequestController {

    public Text costText;
    private Stage dialogStage;
    public TextField nameFiled;
    public Slider costField;
    public ChoiceBox clientType;
    private RequestService requestService;

    @FXML
    private void initialize() {
        try {
            Context context = new InitialContext();
            requestService = new RequestServiceImpl();
            costField.valueProperty().addListener(new ChangeListener<Number>() {
                @Override
                public void changed(ObservableValue<? extends Number> observable,
                                    Number oldValue, Number newValue) {

                    costText.setText("Cost: " + newValue.intValue() + "$");
                }
            });

            clientType.setItems(FXCollections.observableArrayList(
                    "Legal entity", "Physical person ")
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
            requestService.create(
                    new Request(0, nameFiled.getText(), UserType.valueOf(result), (int)costField.getValue(), null)
            );
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
}
