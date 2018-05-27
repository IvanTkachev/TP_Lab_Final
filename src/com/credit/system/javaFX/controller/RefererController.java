package com.credit.system.javaFX.controller;

import com.credit.system.entity.Request;
import com.credit.system.javaFX.view.Main;
import com.credit.system.service.RequestService;
import com.credit.system.service.impl.RequestServiceImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import javax.naming.Context;
import javax.naming.InitialContext;
import java.io.IOException;

public class RefererController {

    public TableView requestTableView;
    private Stage dialogStage;
    public TableColumn idColumn;
    public TableColumn nameColumn;
    public TableColumn userTypeColumn;
    public TableColumn amountColumn;
    private RequestService requestService;

    public static ObservableList<Request> requests = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        try {
            Context context = new InitialContext();
            requestService = new RequestServiceImpl();
            requests.addAll(requestService.getCreatedRequests());

            idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
            nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
            userTypeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
            amountColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));

            requestTableView.setItems(requests);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void logoutAction(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setRoot(getClass().getResource("login.fxml"));
            loader.setLocation(Main.class.getResource("login.fxml"));
            Main.rootStage.setScene(new Scene(loader.load(), 700, 500));
            Main.rootStage.setTitle("Login page");
            Main.rootStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void showRequestDialog(MouseEvent mouseEvent) {
        if(mouseEvent.getClickCount() == 2){
            errorMessage("Hello");
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
