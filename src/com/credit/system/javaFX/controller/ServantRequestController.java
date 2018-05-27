package com.credit.system.javaFX.controller;

import com.credit.system.entity.Request;
import com.credit.system.entity.RequestType;
import com.credit.system.service.RequestService;
import com.credit.system.service.impl.RequestServiceImpl;
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

public class ServantRequestController {
    private RequestService requestService;
    public Text costText;
    public TextField nameFiled;
    public Slider costField;
    public ChoiceBox clientType;
    private Stage dialogStage;

    @FXML
    private void initialize() {
        try {
            Context context = new InitialContext();
            requestService = new RequestServiceImpl();

            if(costField != null){
                costField.valueProperty().addListener(new ChangeListener<Number>() {
                    @Override
                    public void changed(ObservableValue<? extends Number> observable,
                                        Number oldValue, Number newValue) {

                        costText.setText("Cost: " + newValue.intValue() + "$");
                    }
                });

                clientType.setItems(FXCollections.observableArrayList(
                        "Legal entity", "Physical person")
                );
                if("LEGAL".equals(ServantController.servantRequest.getType().name())){
                    clientType.setValue("Legal entity");
                }
                else clientType.setValue("Physical person");
            }
            costField.setValue(ServantController.servantRequest.getAmount());
            costText.setText("Cost: " + (int)costField.getValue() + "$");
            nameFiled.setText(ServantController.servantRequest.getName());



        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }


    public void saveServantRequest(ActionEvent actionEvent) {
        ServantController.requests.remove(ServantController.servantRequest);
        Request updateRequest = new Request(
                ServantController.servantRequest.getId(),
                nameFiled.getText(),
                ServantController.servantRequest.getType(),
                (int)costField.getValue(),
                new RequestType(3, "Staffed"),
                null
        );
        requestService.update(updateRequest);
        dialogStage.close();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Request approved!");
        alert.show();
    }
}
