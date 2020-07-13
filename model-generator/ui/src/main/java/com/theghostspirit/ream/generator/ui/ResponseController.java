package com.theghostspirit.ream.generator.ui;

import com.theghostspirit.ream.generator.core.Api;
import com.theghostspirit.ream.generator.core.Response;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import javafx.stage.Window;

import javafx.scene.control.TextField;
import java.io.IOException;

public class ResponseController {

    private Api api;

    public void setApi(Api api){
        this.api = api;
    }

    private Response response = new Response();

    @FXML
    private TextField statusCode;

    @FXML
    private TextField descriptionResponse;

    @FXML
    public void initialize() {
    }

    @FXML
    void addResponse(ActionEvent event)throws IOException {

        if(statusCode.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, (Stage) ((Node)event.getSource()).getScene().getWindow(), "Form Error!", "Please enter a status code");
            return;
        }
        if(descriptionResponse.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR,(Stage) ((Node)event.getSource()).getScene().getWindow(), "Form Error!", "Please enter a description");
            return;
        }

        showAlert(Alert.AlertType.CONFIRMATION, (Stage) ((Node)event.getSource()).getScene().getWindow(), "Registration Successful!", "Vos informations ont bien été enregistré");

        response.setStatus(statusCode.getText());
        response.setDescription(descriptionResponse.getText());




    }

    private void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }

    @FXML
    void loadObjectView(ActionEvent event)throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ObjectView.fxml"));
        Parent apiView = (Parent) loader.load();
        Scene apiScene = new Scene(apiView);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        ObjectController controlR = loader.getController();
        controlR.setApi(api);

        window.setScene(apiScene);
        window.show();
    }

    @FXML
    void loadPreviousRouteView(ActionEvent event)throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/RouteView.fxml"));
        Parent apiView = (Parent) loader.load();
        Scene apiScene = new Scene(apiView);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        RouteController controlR = loader.getController();
        controlR.setApi(api);
        window.setScene(apiScene);
        window.show();
    }

}
