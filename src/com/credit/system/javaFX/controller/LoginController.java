package com.credit.system.javaFX.controller;

import com.credit.system.dao.UserDao;
import com.credit.system.dao.impl.UserDaoImpl;
import com.credit.system.entity.User;
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
            User user = userDao.getUser(usernameFiled.getText(), passwordField.getText());
            if(user != null){
                dialogStage.close();
                FXMLLoader loader = new FXMLLoader();
                switch (user.getRole().getName()){
                    case "Clerk":
                        loader.setRoot(getClass().getResource("mainForm.fxml"));
                        loader.setLocation(Main.class.getResource("mainForm.fxml"));
                        break;
                    case "HR":
                        loader.setRoot(getClass().getResource("mainForm.fxml"));
                        loader.setLocation(Main.class.getResource("mainForm.fxml"));
                        break;
                    case "Referer":
                        loader.setRoot(getClass().getResource("mainForm.fxml"));
                        loader.setLocation(Main.class.getResource("mainForm.fxml"));
                        break;
                    case "Servant":
                        loader.setRoot(getClass().getResource("mainForm.fxml"));
                        loader.setLocation(Main.class.getResource("mainForm.fxml"));
                        break;
                    default:
                        loader.setRoot(getClass().getResource("mainForm.fxml"));
                        loader.setLocation(Main.class.getResource("mainForm.fxml"));
                        break;
                }
                Main.rootStage.setScene(new Scene(loader.load(), 700, 500));
                Main.rootStage.setTitle("Hello, " + user.getUsername());
                Main.rootStage.show();
            }
            else {
                errorMessage("Username or password incorrect. Please try again.");
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