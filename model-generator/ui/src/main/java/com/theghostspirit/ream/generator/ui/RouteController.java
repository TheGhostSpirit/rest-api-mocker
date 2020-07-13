package com.theghostspirit.ream.generator.ui;

import com.theghostspirit.ream.generator.core.Api;
import com.theghostspirit.ream.generator.core.Contact;
import com.theghostspirit.ream.generator.core.Response;
import com.theghostspirit.ream.generator.core.Route;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;

public class RouteController {

    private Route newRoute = new Route();

    private Api api;

    public void setApi(Api api){
        this.api = api;
    }

    @FXML
    private TextField routeUrl;

    @FXML
    private TextField operationId;

    @FXML
    private TextField routeDescription;

    @FXML
    private ComboBox<String> methodList;

    @FXML
    private Label apiPath;


    @FXML
    public void initialize() {

        //this.api = api;

        ObservableList<String>listOfMethod = FXCollections.observableArrayList("GET","POST","PUT");

        methodList.setItems(listOfMethod);






    }


    @FXML
    void AddRoute(ActionEvent event){

        if(routeUrl.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, (Stage) ((Node)event.getSource()).getScene().getWindow(), "Form Error!", "Please enter your description of API");
            return;
        }
        if(operationId.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR,(Stage) ((Node)event.getSource()).getScene().getWindow(), "Form Error!", "Please enter your email");
            return;
        }
        if(routeDescription.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, (Stage) ((Node)event.getSource()).getScene().getWindow(), "Form Error!", "Please enter a name");
            return;
        }

        showAlert(Alert.AlertType.CONFIRMATION, (Stage) ((Node)event.getSource()).getScene().getWindow(), "Registration Successful!", "Vos informations ont bien été enregistré");

        newRoute.setPath(routeUrl.getText());
        //newRoute.set(operationId.getText());
        newRoute.setDescription(routeDescription.getText());
        newRoute.setMethod(methodList.getSelectionModel().getSelectedItem());

        //api.addRoute(newRoute);

        System.out.println("Verificiation objet api dans route controller : " + api.toString());

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
    void loadQueryView(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/QueryView.fxml"));
        Parent apiView = (Parent) loader.load();
        Scene apiScene = new Scene(apiView);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();

        QueryController controlR = loader.getController();
        controlR.setApi(api);

        window.setScene(apiScene);
        window.show();
    }

    @FXML
    void loadResponseView(ActionEvent event)throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ResponseView.fxml"));
        Parent apiView = (Parent) loader.load();
        Scene apiScene = new Scene(apiView);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();

        ResponseController controlR = loader.getController();
        controlR.setApi(api);

        window.setScene(apiScene);
        window.show();

    }

    @FXML
    void loadPreviousView(ActionEvent event)throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ApiView.fxml"));
        Parent apiView = (Parent) loader.load();
        Scene apiScene = new Scene(apiView);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();


        // BOUGER LE RESPONSE
        ApiController controlR = loader.getController();
        controlR.setApi(api);

        window.setScene(apiScene);
        window.show();
    }

}
