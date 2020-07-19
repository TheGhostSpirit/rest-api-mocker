package com.theghostspirit.ream.generator.ui;

import com.theghostspirit.ream.generator.core.Api;
import com.theghostspirit.ream.generator.core.FieldParameter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class ObjectResponseController {
    private Api api;
    private int indexOfRoute;
    private int indexResponse;
    private int indexObject;
    private FieldParameter newObject = new FieldParameter();
    private boolean loadObject = false;

    public void setApi(Api api){
        this.api = api;
    }

    public void setIndexObject(int indexObject) {
        this.indexObject = indexObject;
    }


    public void setIndexResponse(int indexResponse) {
        this.indexResponse = indexResponse;
    }

    public int getIndexOfRoute() {
        return indexOfRoute;
    }

    public void setTextData(){
        nameObject.setText(this.api.getRoutes().get(indexOfRoute).getResponse().get(indexResponse).getBody().get(indexObject).getName());
        selectTypeObject.getSelectionModel().select(this.api.getRoutes().get(this.indexOfRoute).getResponse().get(indexResponse).getBody().get(indexObject).getType());
        if(this.api.getRoutes().get(this.indexOfRoute).getResponse().get(indexResponse).getBody().get(indexObject).getRequired()){
            requiredObject.setSelected(true);
        }

    }

    public void setIndexOfRoute(int indexOfRoute) {
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
    }

    @FXML
    void addObject(ActionEvent event)throws IOException {


        if(this.loadObject == false){
            newObject.setName(nameObject.getText());
            newObject.setType(selectTypeObject.getSelectionModel().getSelectedItem());
            if(requiredObject.isSelected()){
                newObject.setRequired(true);
            }else{
                newObject.setRequired(false);
            }

            System.out.println("Index of route " + indexOfRoute);

            System.out.println("Size of query parameter list : " +  this.api.getRoutes().get(indexOfRoute).getResponse().get(indexResponse).getBody().size());

            this.api.getRoutes().get(indexOfRoute).getResponse().get(indexResponse).getBody().add(newObject);

            this.loadObject = true;
        }else{
            this.api.getRoutes().get(indexOfRoute).getResponse().get(indexResponse).getBody().get(this.indexObject).setName(nameObject.getText());
            this.api.getRoutes().get(indexOfRoute).getResponse().get(indexResponse).getBody().get(this.indexObject).setType(selectTypeObject.getSelectionModel().getSelectedItem());
            if(requiredObject.isSelected()) {
                this.api.getRoutes().get(indexOfRoute).getResponse().get(indexResponse).getBody().get(this.indexObject).setRequired(true);
            }else{
                this.api.getRoutes().get(indexOfRoute).getResponse().get(indexResponse).getBody().get(this.indexObject).setRequired(false);
            }
        }


    }

    @FXML
    void loadPreviousScene (ActionEvent event)throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ResponseView.fxml"));
        Parent responseView = (Parent) loader.load();
        Scene responseScene = new Scene(responseView);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        ResponseController controlR = loader.getController();
        controlR.setApi(api);
        controlR.setIndexOfRoute(this.indexOfRoute);
        controlR.setIndexResponse(this.indexResponse);
        controlR.setTextData();
        window.setScene(responseScene);
        window.show();
    }
}
