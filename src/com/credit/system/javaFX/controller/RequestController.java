package com.credit.system.javaFX.controller;

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
    private String errorMessage;

    @FXML
    private void initialize() {
        try {
//            userDao = new UserDaoImpl();
            Context context = new InitialContext();
            costField.valueProperty().addListener(new ChangeListener<Number>() {
                @Override
                public void changed(ObservableValue<? extends Number> observable,
                                    Number oldValue, Number newValue) {

                    costText.setText("Cost: " + newValue.intValue() + "$");
                }
            });

            clientType.setItems(FXCollections.observableArrayList(
                    "Legal entity", "Private person ")
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
