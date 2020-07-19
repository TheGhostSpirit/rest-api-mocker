package com.theghostspirit.ream.generator.ui;

import com.theghostspirit.ream.generator.core.Api;
import com.theghostspirit.ream.generator.core.Response;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.scene.control.TextField;
import java.io.IOException;
import java.util.ArrayList;

public class ResponseController {

    private Api api;
    private int indexOfRoute;
    private int indexResponse;
    private boolean loadResponse = false;

    public void setApi(Api api){
        this.api = api;
    }

    public Api getApi() {
        return api;
    }

    public int getIndexOfRoute() {
        return indexOfRoute;
    }

    public void setIndexOfRoute(int indexOfRoute) {
        this.indexOfRoute = indexOfRoute;
    }

    public void setIndexResponse(int indexResponse) {
        this.indexResponse = indexResponse;
    }

    private Response response = new Response();

    @FXML
    private TextField statusCode;

    @FXML
    private TextField descriptionResponse;

    @FXML
    private HBox HboxListObjectResponse;

    @FXML
    private ComboBox<String> listObjectCombo;

    @FXML
    private Button AddObjectButton;

    @FXML
    public void initialize() {
        HboxListObjectResponse.setVisible(false);
        AddObjectButton.setVisible(false);
    }

    @FXML
    void addResponse(ActionEvent event)throws IOException {
        if(statusCode.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, (Stage) ((Node)event.getSource()).getScene().getWindow(), "Form Error!", "Please enter a status code");
            return;
        }
        if(checkStatusCode(statusCode.getText())){
            showAlert(Alert.AlertType.ERROR, (Stage) ((Node)event.getSource()).getScene().getWindow(), "Form Error!", "Please enter a valid status code");
            return;
        }
        showAlert(Alert.AlertType.CONFIRMATION, (Stage) ((Node)event.getSource()).getScene().getWindow(), "Registration Successful!", "Vos informations ont bien été enregistré");
        if(loadResponse == false){
            response.setStatus(statusCode.getText());
            response.setDescription(descriptionResponse.getText());
            api.getRoutes().get(indexOfRoute).getResponse().add(response);
            indexResponse = api.getRoutes().get(indexOfRoute).getResponse().indexOf(response);
            loadResponse = true;
            AddObjectButton.setVisible(true);
        }else{
            this.api.getRoutes().get(indexOfRoute).getResponse().get(indexResponse).setStatus(statusCode.getText());
            this.api.getRoutes().get(indexOfRoute).getResponse().get(indexResponse).setDescription(descriptionResponse.getText());
        }
    }
    public void setTextData(){

        loadResponse = true;
        AddObjectButton.setVisible(true);
        statusCode.setText(this.api.getRoutes().get(this.indexOfRoute).getResponse().get(this.indexResponse).getStatus());
        descriptionResponse.setText(this.api.getRoutes().get(this.indexOfRoute).getResponse().get(this.indexResponse).getDescription());
        if(this.api.getRoutes().get(indexOfRoute).getResponse().get(indexResponse).getBody().isEmpty() == false){
            HboxListObjectResponse.setVisible(true);
            ArrayList<String> listObjectView = new ArrayList<String>();
            for(int i = 0 ; i < this.api.getRoutes().get(indexOfRoute).getResponse().get(indexResponse).getBody().size() ; i++){
                String AddObjectToList = this.api.getRoutes().get(indexOfRoute).getResponse().get(indexResponse).getBody().get(i).getType() + "   :   " + this.api.getRoutes().get(indexOfRoute).getResponse().get(indexResponse).getBody().get(i).getName();
                listObjectView.add(AddObjectToList);
            }
            ObservableList<String> listOfObjects = FXCollections.observableArrayList(listObjectView);
            listObjectCombo.setItems(listOfObjects);
            listObjectCombo.getSelectionModel().selectFirst();
        }else{
            HboxListObjectResponse.setVisible(false);
        }
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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ObjectResponseView.fxml"));
        Parent apiView = (Parent) loader.load();
        Scene apiScene = new Scene(apiView);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        ObjectResponseController controlR = loader.getController();
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
        controlR.setIndexOfRoute(indexOfRoute);
        controlR.setTextData(true);
        window.setScene(apiScene);
        window.show();
    }

    @FXML
    void deleteObject (ActionEvent event)throws IOException {
        int selectedIndex = listObjectCombo.getSelectionModel().getSelectedIndex();
        this.api.getRoutes().get(indexOfRoute).getResponse().get(indexResponse).getBody().remove(selectedIndex);
        setTextData();
    }

    @FXML
    void editObject (ActionEvent event)throws IOException {
        int selectedIndex = listObjectCombo.getSelectionModel().getSelectedIndex();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ObjectResponseView.fxml"));
        Parent apiView = (Parent) loader.load();
        Scene apiScene = new Scene(apiView);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        ObjectResponseController controlR = loader.getController();
        controlR.setApi(api);
        controlR.setIndexOfRoute(indexOfRoute);
        controlR.setIndexResponse(indexResponse);
        controlR.setIndexObject(selectedIndex);
        controlR.setTextData();
        window.setScene(apiScene);
        window.show();
    }

    private boolean checkStatusCode(String check){
        try {
            Integer.parseInt(check);
        } catch(NumberFormatException e) {
            return true;
        }
        return false;
    }
}
