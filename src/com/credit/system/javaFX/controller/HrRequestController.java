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

public class HrRequestController {
    public Slider rateField;
    public Text rateText;
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
            if(rateField != null){
                rateField.valueProperty().addListener(new ChangeListener<Number>() {
                    @Override
                    public void changed(ObservableValue<? extends Number> observable,
                                        Number oldValue, Number newValue) {

                        rateText.setText("Rate: " + newValue.intValue() + "%");
                    }
                });
            }

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
                if("LEGAL".equals(HrController.hrRequest.getType().name())){
                    clientType.setValue("Legal entity");
                }
                else clientType.setValue("Physical person");
            }
            costField.setValue(HrController.hrRequest.getAmount());
            costText.setText("Cost: " + (int)costField.getValue() + "$");
            nameFiled.setText(HrController.hrRequest.getName());



        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }


    public void acceptHrRequest(ActionEvent actionEvent) {
        HrController.requests.remove(HrController.hrRequest);
        Request updateRequest = new Request(
                HrController.hrRequest.getId(),
                nameFiled.getText(),
                HrController.hrRequest.getType(),
                (int)costField.getValue(),
                new RequestType(4, "Confirmed"),
                null
        );
        requestService.update(updateRequest);
        dialogStage.close();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Request accepted!");
        alert.show();
    }

    public void refuseHrRequest(ActionEvent actionEvent) {
        HrController.requests.remove(HrController.hrRequest);
        requestService.delete(HrController.hrRequest.getId());
        dialogStage.close();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Request refused!");
        alert.show();
    }
}
