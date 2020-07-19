package com.theghostspirit.ream.generator.ui;

import com.theghostspirit.ream.generator.core.Api;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class QueryController {

    private Api api;
    private int indexOfRoute;

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


    @FXML
    private ComboBox<String> listObjectCombo;

    @FXML
    private HBox HboxListObjectQuery;


    @FXML
    public void initialize() {
        HboxListObjectQuery.setVisible(false);
    }

    public void setTextData(){
        if(this.api.getRoutes().get(indexOfRoute).getQuery().isEmpty() == false){

            HboxListObjectQuery.setVisible(true);
            ArrayList<String> listObjectView = new ArrayList<String>();

            for(int i = 0 ; i < this.api.getRoutes().get(indexOfRoute).getQuery().size() ; i++){
                String AddObjectToList = this.api.getRoutes().get(indexOfRoute).getQuery().get(i).getType() + "   :   " + this.api.getRoutes().get(indexOfRoute).getQuery().get(i).getName();
                listObjectView.add(AddObjectToList);
            }

            ObservableList<String> listOfObjects = FXCollections.observableArrayList(listObjectView);
            listObjectCombo.setItems(listOfObjects);
            listObjectCombo.getSelectionModel().selectFirst();
        }else{
            HboxListObjectQuery.setVisible(false);
        }

    }



    @FXML
    void loadObjectScene(ActionEvent event)throws IOException {

        System.out.println("Verification du path de la route créer dans le query controller : " + this.api.getRoutes().get(indexOfRoute).getPath());

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ObjectView.fxml"));
        Parent ObjectView = (Parent) loader.load();
        Scene ObjectScene = new Scene(ObjectView);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        ObjectController controlR = loader.getController();
        controlR.setApi(api);
        controlR.setIndexOfRoute(this.getIndexOfRoute());
        window.setScene(ObjectScene);
        window.show();
    }

    @FXML
    void loadPreviousScene (ActionEvent event)throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/RouteView.fxml"));
        Parent routeView = (Parent) loader.load();
        Scene routeScene = new Scene(routeView);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        RouteController controlR = loader.getController();
        controlR.setApi(api);
        controlR.setIndexOfRoute(this.indexOfRoute);
        controlR.setTextData(true);
        window.setScene(routeScene);
        window.show();
    }

    @FXML
    void deleteObject (ActionEvent event)throws IOException {

        int selectedIndex = listObjectCombo.getSelectionModel().getSelectedIndex();

        System.out.println("Selected INDEX : " + selectedIndex);

        this.api.getRoutes().get(indexOfRoute).getQuery().remove(selectedIndex);

        setTextData();

    }

    @FXML
    void editObject (ActionEvent event)throws IOException {

        int selectedIndex = listObjectCombo.getSelectionModel().getSelectedIndex();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ObjectView.fxml"));
        Parent apiView = (Parent) loader.load();
        Scene apiScene = new Scene(apiView);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();

        ObjectController controlR = loader.getController();
        controlR.setApi(api);
        controlR.setIndexOfRoute(indexOfRoute);
        controlR.setIndexObject(selectedIndex);
        controlR.setTextData();

        window.setScene(apiScene);
        window.show();

    }





}
