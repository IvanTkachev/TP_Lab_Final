package com.credit.system.javaFX.view;

import com.credit.system.javaFX.controller.LoginController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.naming.Context;
import javax.naming.InitialContext;
import java.io.IOException;

public class MainController {
    public Button loginButton;

    public void showLoginDialog(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("login.fxml"));
            AnchorPane page = (AnchorPane)loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Login page");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(Main.rootStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            LoginController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            dialogStage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    @FXML
    private void initialize() {
        try {
            Context context = new InitialContext();

//            products.addAll(xmlService.getAllProducts(nameStore));
//            stores.addAll(xmlService.getAllStores());

//            deleteItem.disableProperty().bind(Bindings.isEmpty(productTableView.getSelectionModel().getSelectedItems()));

            // устанавливаем тип и значение которое должно хранится в колонке
//            idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
//            nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
//            countColumn.setCellValueFactory(new PropertyValueFactory<>("count"));
//            storeColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

//            // заполняем таблицу данными
//            productTableView.setItems(products);
//            storeTableView.setItems(stores);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
