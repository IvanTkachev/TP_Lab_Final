package com.credit.system.javaFX.controller;

import com.credit.system.entity.User;
import com.credit.system.javaFX.view.Main;
import com.credit.system.service.UserService;
import com.credit.system.service.impl.UserServiceImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
    private UserService userService;

    @FXML
    private void initialize() {
        try {
            userService = new UserServiceImpl();
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
            User user = userService.getUser(usernameFiled.getText(), passwordField.getText());
            if(user != null){
                if(dialogStage!= null)
                    dialogStage.close();
                FXMLLoader loader = new FXMLLoader();
                switch (user.getRole().getName()){
                    case "Clerk":
                        loader.setRoot(getClass().getResource("clerkForm.fxml"));
                        loader.setLocation(Main.class.getResource("clerkForm.fxml"));
                        break;
                    case "HR":
                        loader.setRoot(getClass().getResource("hrForm.fxml"));
                        loader.setLocation(Main.class.getResource("hrForm.fxml"));
                        break;
                    case "Referer":
                        loader.setRoot(getClass().getResource("refererForm.fxml"));
                        loader.setLocation(Main.class.getResource("refererForm.fxml"));
                        break;
                    case "Servant":
                        loader.setRoot(getClass().getResource("servantForm.fxml"));
                        loader.setLocation(Main.class.getResource("servantForm.fxml"));
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
