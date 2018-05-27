package com.credit.system.javaFX.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.stage.Stage;

import javax.naming.Context;
import javax.naming.InitialContext;

public class RefererController {

    private Stage dialogStage;
    public TableColumn idColumn;
    public TableColumn nameColumn;
    public TableColumn userTypeColumn;
    public TableColumn amountColumn;

    @FXML
    private void initialize() {
        try {
            Context context = new InitialContext();
        }catch (Exception e){
            e.printStackTrace();
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
