package com.theghostspirit.ream.generator.ui;

import com.theghostspirit.ream.generator.core.Api;
import com.theghostspirit.ream.generator.core.Query;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;

import javafx.scene.control.TextField;

import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;


public class ObjectController {

    private Api api;
    private int indexOfRoute;
    private Query newObject = new Query();
    private boolean loadObject = false;
    private int indexObject;

    public void setApi(Api api){
        this.api = api;
    }

    void setIndexObject(int indexObject) {
        this.indexObject = indexObject;
    }

    void setIndexOfRoute(int indexOfRoute) {
        this.indexOfRoute = indexOfRoute;
    }

    @FXML
    private TextField nameObject;

    @FXML
    private ComboBox<String> selectTypeObject;

    @FXML
    private CheckBox requiredObject;

    @FXML
    public void initialize() {
        ObservableList<String> listOfType = FXCollections.observableArrayList("String","Int","Boolean","Float");
        selectTypeObject.setItems(listOfType);
        selectTypeObject.getSelectionModel().selectFirst();
    }

    void setTextData(){
        nameObject.setText(this.api.getRoutes().get(indexOfRoute).getQuery().get(indexObject).getName());
        selectTypeObject.getSelectionModel().select(this.api.getRoutes().get(this.indexOfRoute).getQuery().get(indexObject).getType());
        if(this.api.getRoutes().get(this.indexOfRoute).getQuery().get(indexObject).getRequired()){
            requiredObject.setSelected(true);
        }
        this.loadObject = true;
    }

    @FXML
    void addObject(ActionEvent event){
        if(nameObject.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR,((Node)event.getSource()).getScene().getWindow(), "Form Error!", "Please enter a name");
            return;
        }
        showAlert(Alert.AlertType.CONFIRMATION,((Node)event.getSource()).getScene().getWindow(), "Successful!", "Vos informations ont bien été enregistré");
        if(!this.loadObject){
            newObject.setName(nameObject.getText());
            newObject.setType(selectTypeObject.getSelectionModel().getSelectedItem());
            if(requiredObject.isSelected()){
                newObject.setRequired(true);
            }else{
                newObject.setRequired(false);
            }
            this.api.getRoutes().get(indexOfRoute).getQuery().add(newObject);
            this.loadObject = true;
        }else{
            this.api.getRoutes().get(indexOfRoute).getQuery().get(this.indexObject).setName(nameObject.getText());
            this.api.getRoutes().get(indexOfRoute).getQuery().get(this.indexObject).setType(selectTypeObject.getSelectionModel().getSelectedItem());
            if(requiredObject.isSelected()) {
                this.api.getRoutes().get(indexOfRoute).getQuery().get(this.indexObject).setRequired(true);
            }else{
                this.api.getRoutes().get(indexOfRoute).getQuery().get(this.indexObject).setRequired(false);
            }
        }
    }

    @FXML
    void loadPreviousScene (ActionEvent event)throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/QueryView.fxml"));
        Parent queryView = loader.load();
        Scene queryScene = new Scene(queryView);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        QueryController controlR = loader.getController();
        controlR.setApi(api);
        controlR.setIndexOfRoute(this.indexOfRoute);
        controlR.setTextData();
        window.setScene(queryScene);
        window.show();
    }

    private void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }


}
