package com.credit.system.javaFX.controller;

import com.credit.system.javaFX.view.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.naming.Context;
import javax.naming.InitialContext;
import java.io.IOException;

public class ClerkController {
    public Button newRequestButton;
    public Button logoutButton;
    private Stage dialogStage;

    @FXML
    private void initialize() {
        try {
            Context context = new InitialContext();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void showRequestDialog(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("requestForm.fxml"));
            AnchorPane page = (AnchorPane)loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Request page");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(Main.rootStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            RequestController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            dialogStage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void logoutAction(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setRoot(getClass().getResource("login.fxml"));
            loader.setLocation(Main.class.getResource("login.fxml"));
            Main.rootStage.setScene(new Scene(loader.load(), 700, 500));
            Main.rootStage.setTitle("Login page");
            Main.rootStage.show();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Logout!");
            alert.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void errorMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(message);
        alert.show();
    }
}
