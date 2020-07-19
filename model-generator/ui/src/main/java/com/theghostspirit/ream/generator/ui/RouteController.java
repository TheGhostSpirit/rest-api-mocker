package com.theghostspirit.ream.generator.ui;

import com.theghostspirit.ream.generator.core.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.util.ArrayList;

public class RouteController {

    private Route newRoute = new Route();

    private Api api;
    private int indexOfRoute;
    private boolean loadRoute = false;

    public void setApi(Api api){
        this.api = api;
    }

    public Api getApi(){
        return this.api;
    }

    public int getIndexOfRoute() {
        return indexOfRoute;
    }

    public void setIndexOfRoute(int indexOfRoute) {
        this.indexOfRoute = indexOfRoute;
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
    private ComboBox<String> responsesRouteList;

    @FXML
    private HBox hboxResponseList;

    @FXML
    private Button AddQueryButton;

    @FXML
    private Button AddResponseButton;

    @FXML
    private HBox hboxBodyList;

    @FXML
    private ComboBox<String> responsesBodyList;

    @FXML
    public void initialize() {
        ObservableList<String>listOfMethod = FXCollections.observableArrayList("GET","POST","PUT","PATCH","DELETE");
        methodList.setItems(listOfMethod);
        methodList.getSelectionModel().selectFirst();
        hboxResponseList.setVisible(false);
        hboxBodyList.setVisible(false);
        AddQueryButton.setVisible(false);
        AddResponseButton.setVisible(false);
    }

    public void setTextData(Boolean loadRouteCheck){
        apiPath.setText(this.api.getServerPath() + " : ");
        if(loadRouteCheck == true){
            AddQueryButton.setVisible(true);
            AddResponseButton.setVisible(true);
            routeUrl.setText(this.api.getRoutes().get(this.indexOfRoute).getPath());
            operationId.setText(this.api.getRoutes().get(this.indexOfRoute).getOperationId());
            routeDescription.setText(this.api.getRoutes().get(this.indexOfRoute).getDescription());
            methodList.getSelectionModel().select(this.api.getRoutes().get(this.indexOfRoute).getMethod());
            if(this.api.getRoutes().get(indexOfRoute).getResponse().isEmpty() == false){
                loadListOfResponsesSelect();
            }else{
                hboxResponseList.setVisible(false);
            }
            if(this.api.getRoutes().get(indexOfRoute).getBody().isEmpty() == false){
                loadListOfBodySelect();
            }else{
                hboxBodyList.setVisible(false);
            }
            this.loadRoute = true;
        }
    }

    public void loadListOfResponsesSelect(){
        hboxResponseList.setVisible(true);
        ArrayList<String> listResponseView = new ArrayList<String>();
        for(int i = 0 ; i < this.api.getRoutes().get(indexOfRoute).getResponse().size() ; i++){
            String AddResponseToList = this.api.getRoutes().get(indexOfRoute).getResponse().get(i).getStatus() + "   :   " + this.api.getRoutes().get(indexOfRoute).getResponse().get(i).getDescription();
            listResponseView.add(AddResponseToList);
        }
        ObservableList<String> listOfRoutes = FXCollections.observableArrayList(listResponseView);
        responsesRouteList.setItems(listOfRoutes);
        responsesRouteList.getSelectionModel().selectFirst();
    }

    public void loadListOfBodySelect(){
        hboxBodyList.setVisible(true);
        ArrayList<String> listBodyView = new ArrayList<String>();

        for(int i = 0 ; i < this.api.getRoutes().get(indexOfRoute).getBody().size() ; i++){
            String AddBodyToList = this.api.getRoutes().get(indexOfRoute).getBody().get(i).getType() + "   :   " + this.api.getRoutes().get(indexOfRoute).getBody().get(i).getName();
            listBodyView.add(AddBodyToList);
        }

        ObservableList<String> listOfBody = FXCollections.observableArrayList(listBodyView);
        responsesBodyList.setItems(listOfBody);
        responsesBodyList.getSelectionModel().selectFirst();
    }


    @FXML
    void AddRoute(ActionEvent event){
        if(routeUrl.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, (Stage) ((Node)event.getSource()).getScene().getWindow(), "Form Error!", "Please enter a route path");
            return;
        }
        showAlert(Alert.AlertType.CONFIRMATION, (Stage) ((Node)event.getSource()).getScene().getWindow(), "Successful!", "Vos informations ont bien été enregistré");
        if(loadRoute == false){
            newRoute.setPath(routeUrl.getText());
            newRoute.setOperationId(operationId.getText());
            newRoute.setDescription(routeDescription.getText());
            newRoute.setMethod(methodList.getSelectionModel().getSelectedItem());
            this.api.getRoutes().add(newRoute);
            this.setIndexOfRoute(api.getRoutes().indexOf(newRoute));
            this.loadRoute = true;
            AddQueryButton.setVisible(true);
            AddResponseButton.setVisible(true);
        }else{
            this.api.getRoutes().get(indexOfRoute).setPath(routeUrl.getText());
            this.api.getRoutes().get(indexOfRoute).setOperationId(operationId.getText());
            this.api.getRoutes().get(indexOfRoute).setDescription(routeDescription.getText());
            this.api.getRoutes().get(indexOfRoute).setMethod(methodList.getSelectionModel().getSelectedItem());
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
    void loadQueryView(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/QueryView.fxml"));
        Parent apiView = (Parent) loader.load();
        Scene apiScene = new Scene(apiView);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        QueryController controlR = loader.getController();
        controlR.setApi(api);
        controlR.setIndexOfRoute(this.getIndexOfRoute());
        controlR.setTextData();
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
        controlR.setIndexOfRoute(this.getIndexOfRoute());
        window.setScene(apiScene);
        window.show();
    }

    @FXML
    void loadBodyView(ActionEvent event)throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ObjectBodyView.fxml"));
        Parent bodyView = (Parent) loader.load();
        Scene bodyScene = new Scene(bodyView);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        ObjectBodyController controlR = loader.getController();
        controlR.setApi(api);
        controlR.setIndexOfRoute(this.getIndexOfRoute());
        window.setScene(bodyScene);
        window.show();
    }

    @FXML
    void loadPreviousView(ActionEvent event)throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ApiView.fxml"));
        Parent apiView = (Parent) loader.load();
        Scene apiScene = new Scene(apiView);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        ApiController controlR = loader.getController();
        controlR.setApi(api);
        controlR.setTextData();
        window.setScene(apiScene);
        window.show();

    }

    @FXML
    void editResponse(ActionEvent event)throws IOException{
        int selectedIndexResponse = responsesRouteList.getSelectionModel().getSelectedIndex();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ResponseView.fxml"));
        Parent responseView = (Parent) loader.load();
        Scene responseScene = new Scene(responseView);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        ResponseController controlR = loader.getController();
        controlR.setApi(api);
        controlR.setIndexOfRoute(indexOfRoute);
        controlR.setIndexResponse(selectedIndexResponse);
        controlR.setTextData();
        window.setScene(responseScene);
        window.show();
    }

    @FXML
    void deleteResponse(ActionEvent event)throws IOException{
        int selectedIndex = responsesRouteList.getSelectionModel().getSelectedIndex();
        this.api.getRoutes().get(indexOfRoute).getResponse().remove(selectedIndex);
        setTextData(true);
    }

    @FXML
    void deleteBody(ActionEvent event)throws IOException{
        int selectedIndex = responsesBodyList.getSelectionModel().getSelectedIndex();
        this.api.getRoutes().get(indexOfRoute).getBody().remove(selectedIndex);
        setTextData(true);
    }

    @FXML
    void editBody(ActionEvent event)throws IOException{
        int selectedIndexResponse = responsesBodyList.getSelectionModel().getSelectedIndex();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ObjectBodyView.fxml"));
        Parent objectView = (Parent) loader.load();
        Scene objectScene = new Scene(objectView);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        ObjectBodyController controlR = loader.getController();
        controlR.setApi(api);
        controlR.setIndexOfRoute(indexOfRoute);
        controlR.setIndexObject(selectedIndexResponse);
        controlR.setTextData();
        window.setScene(objectScene);
        window.show();
    }



}
