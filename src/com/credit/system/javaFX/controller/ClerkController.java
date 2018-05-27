package com.credit.system.javaFX.controller;

import com.credit.system.javaFX.view.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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

    @FXML
    private void initialize() {
        try {
//            userDao = new UserDaoImpl();
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
            dialogStage.setTitle("Delete product");
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

    private void errorMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(message);
        alert.show();
    }
}
