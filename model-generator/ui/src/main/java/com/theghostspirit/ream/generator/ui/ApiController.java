package com.theghostspirit.ream.generator.ui;

import com.theghostspirit.ream.generator.core.Api;
import com.theghostspirit.ream.generator.core.Contact;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;

public class ApiController {

    private Api api;

    public void setApi(Api api){
        this.api = api;
    }

    private Contact contact;

    @FXML
    private TextField apiDescription;

    @FXML
    private TextField serverAddress;

    @FXML
    private TextField contactName;

    @FXML
    private TextField contactEmail;

    @FXML
    private TextField licenseUrl;

    @FXML
    public void initialize() {
        api = new Api();
        contact = new Contact();
    }

    @FXML
    void saveApi(ActionEvent event){


        if(apiDescription.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, (Stage) ((Node)event.getSource()).getScene().getWindow(), "Form Error!", "Please enter your description of API");
            return;
        }
        if(contactEmail.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR,(Stage) ((Node)event.getSource()).getScene().getWindow(), "Form Error!", "Please enter your email");
            return;
        }
        if(contactName.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, (Stage) ((Node)event.getSource()).getScene().getWindow(), "Form Error!", "Please enter a name");
            return;
        }

        showAlert(Alert.AlertType.CONFIRMATION, (Stage) ((Node)event.getSource()).getScene().getWindow(), "Registration Successful!", "Vos informations ont bien été enregistré");

        api.setDescription(apiDescription.getText());
        contact.setName(contactName.getText());
        contact.setEmail(contactEmail.getText());
        api.setContact(contact);
        //api.setLicense(licenseUrl.getText());

        System.out.println(api.toString());

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
    void loadRouteView(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/RouteView.fxml"));
        Parent apiView = (Parent) loader.load();
        Scene apiScene = new Scene(apiView);
        System.out.println("Ok dans le loadRouteView");
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();

        RouteController controlR = loader.getController();
        controlR.setApi(api);

        window.setScene(apiScene);
        window.show();
    }

}
