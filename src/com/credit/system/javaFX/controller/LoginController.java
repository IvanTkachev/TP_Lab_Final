package com.credit.system.javaFX.controller;

import com.credit.system.javaFX.view.Main;
import com.credit.system.javaFX.view.MainController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.naming.Context;
import javax.naming.InitialContext;

public class LoginController {

    public PasswordField passwordField;
    public TextField usernameFiled;
    public Button loginButton;
    private Stage dialogStage;

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

    public void loginAction(ActionEvent actionEvent) {
        try {
            dialogStage.close();
            FXMLLoader loader = new FXMLLoader();
            loader.setRoot(getClass().getResource("mainForm.fxml"));
            loader.setLocation(Main.class.getResource("mainForm.fxml"));
            Main.rootStage.setScene(new Scene(loader.load(), 700, 500));
            Main.rootStage.setTitle("Hello!");
            Main.rootStage.show();
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
}
