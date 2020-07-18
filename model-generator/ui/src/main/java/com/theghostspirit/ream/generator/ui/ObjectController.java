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


public class ObjectController {

    private Api api;
    private int indexOfRoute;
    private FieldParameter newObject = new FieldParameter();
    private boolean loadObject = false;
    private int indexObject;

    public void setApi(Api api){
        this.api = api;
    }

    public void setIndexObject(int indexObject) {
        this.indexObject = indexObject;
    }



    public int getIndexOfRoute() {
        return indexOfRoute;
    }

    public void setTextData(){
        nameObject.setText(this.api.getRoutes().get(indexOfRoute).getQuery().getFieldParameterList().get(indexObject).getName());
        selectTypeObject.getSelectionModel().select(this.api.getRoutes().get(this.indexOfRoute).getQuery().getFieldParameterList().get(indexObject).getType());
        if(this.api.getRoutes().get(this.indexOfRoute).getQuery().getFieldParameterList().get(indexObject).getRequired()){
            requiredObject.setSelected(true);
        }
        this.loadObject = true;

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
    //requiredObject

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

            System.out.println("Size of query parameter list : " +  this.api.getRoutes().get(indexOfRoute).getQuery().getFieldParameterList().size());

            this.api.getRoutes().get(indexOfRoute).getQuery().getFieldParameterList().add(newObject);

            System.out.println("Size of query parameter list : " +  this.api.getRoutes().get(indexOfRoute).getQuery().getFieldParameterList().size());

            System.out.println("Index de la route ajouté : " + api.getRoutes().get(indexOfRoute).getQuery().getFieldParameterList().indexOf(newObject));

            this.loadObject = true;
        }else{
            this.api.getRoutes().get(indexOfRoute).getQuery().getFieldParameterList().get(this.indexObject).setName(nameObject.getText());
            this.api.getRoutes().get(indexOfRoute).getQuery().getFieldParameterList().get(this.indexObject).setType(selectTypeObject.getSelectionModel().getSelectedItem());
            if(requiredObject.isSelected()) {
                this.api.getRoutes().get(indexOfRoute).getQuery().getFieldParameterList().get(this.indexObject).setRequired(true);
            }else{
                this.api.getRoutes().get(indexOfRoute).getQuery().getFieldParameterList().get(this.indexObject).setRequired(false);
            }
        }


    }

    @FXML
    void loadPreviousScene (ActionEvent event)throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/QueryView.fxml"));
        Parent queryView = (Parent) loader.load();
        Scene queryScene = new Scene(queryView);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        QueryController controlR = loader.getController();
        controlR.setApi(api);
        controlR.setIndexOfRoute(this.indexOfRoute);
        controlR.setTextData();
        window.setScene(queryScene);
        window.show();
    }


}
