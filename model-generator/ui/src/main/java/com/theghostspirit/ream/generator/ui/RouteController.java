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

    public void setTextData(Boolean loadRouteCheck){
        apiPath.setText(this.api.getServerPath() + " : ");
        if(loadRouteCheck == true){
            AddQueryButton.setVisible(true);
            AddResponseButton.setVisible(true);
            routeUrl.setText(this.api.getRoutes().get(this.indexOfRoute).getPath());
            operationId.setText(this.api.getRoutes().get(this.indexOfRoute).getOperationId());
            routeDescription.setText(this.api.getRoutes().get(this.indexOfRoute).getDescription());
            methodList.getSelectionModel().select(this.api.getRoutes().get(this.indexOfRoute).getMethod());
            if(this.api.getRoutes().get(indexOfRoute).getResponses().isEmpty() == false){
                loadListOfResponsesSelect();
            }else{
                hboxResponseList.setVisible(false);
            }

                this.loadRoute = true;
            // Rajouter le champ combo préselectionné
        }
    }

    public void loadListOfResponsesSelect(){
        hboxResponseList.setVisible(true);
        ArrayList<String> listResponseView = new ArrayList<String>();

        for(int i = 0 ; i < this.api.getRoutes().get(indexOfRoute).getResponses().size() ; i++){
            String AddResponseToList = this.api.getRoutes().get(indexOfRoute).getResponses().get(i).getStatus() + "   :   " + this.api.getRoutes().get(indexOfRoute).getResponses().get(i).getDescription();
            listResponseView.add(AddResponseToList);
        }

        ObservableList<String> listOfRoutes = FXCollections.observableArrayList(listResponseView);
        responsesRouteList.setItems(listOfRoutes);
        responsesRouteList.getSelectionModel().selectFirst();
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
    public void initialize() {

        //this.api = api;
        ObservableList<String>listOfMethod = FXCollections.observableArrayList("GET","POST","PUT");
        methodList.setItems(listOfMethod);
        hboxResponseList.setVisible(false);
        AddQueryButton.setVisible(false);
        AddResponseButton.setVisible(false);
    }


    @FXML
    void AddRoute(ActionEvent event){

        System.out.println("Verification ADD ou LOAD : " + loadRoute);

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

        if(loadRoute == false){
            newRoute.setPath(routeUrl.getText());
            newRoute.setOperationId(operationId.getText());
            newRoute.setDescription(routeDescription.getText());
            newRoute.setMethod(methodList.getSelectionModel().getSelectedItem());

            System.out.println("Ok route");
            this.api.getRoutes().add(newRoute);

            System.out.println("Index de la route ajouté : " + api.getRoutes().indexOf(newRoute));
            this.setIndexOfRoute(api.getRoutes().indexOf(newRoute));

            //trouver une methode pour connaitre l'index de la route ajouté
            this.loadRoute = true;
            AddQueryButton.setVisible(true);
            AddResponseButton.setVisible(true);

            System.out.println("Fin save api  route : ");
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
    void loadPreviousView(ActionEvent event)throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ApiView.fxml"));
        Parent apiView = (Parent) loader.load();
        Scene apiScene = new Scene(apiView);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();

        System.out.println("Verificiation objet api dans Route controller : " + api.toString());


        ApiController controlR = loader.getController();
        controlR.setApi(api);

        System.out.println("check data previous view " + controlR.getApi().toString());
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

        System.out.println("Selected INDEX : " + selectedIndex);

        this.api.getRoutes().get(indexOfRoute).getResponses().remove(selectedIndex);

        setTextData(true);
    }

}
