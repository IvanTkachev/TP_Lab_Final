package com.credit.system.javaFX.controller;

import com.credit.system.dao.UserDao;
import com.credit.system.dao.impl.UserDaoImpl;
import com.credit.system.javaFX.view.Main;
import com.credit.system.javaFX.view.MainController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
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
    private UserDao userDao;

    @FXML
    private void initialize() {
        try {
            userDao = new UserDaoImpl();
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
            if(userDao.getUser(usernameFiled.getText(), passwordField.getText()) != null){
                dialogStage.close();
                FXMLLoader loader = new FXMLLoader();
                loader.setRoot(getClass().getResource("mainForm.fxml"));
                loader.setLocation(Main.class.getResource("mainForm.fxml"));
                Main.rootStage.setScene(new Scene(loader.load(), 700, 500));
                Main.rootStage.setTitle("Hello!");
                Main.rootStage.show();
            }
            else {
                errorMessage("User not found. Please try again.");
            }

        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

    private void errorMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(message);
        alert.show();
    }
}
