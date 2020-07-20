package com.theghostspirit.ream.generator.ui;

import com.theghostspirit.ream.generator.core.Api;
import com.theghostspirit.ream.generator.core.ExportModel;
import com.theghostspirit.ream.generator.core.PluginLoader;
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
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class ApiController {

    private Api api;
    private final FileChooser fileChooser = new FileChooser();

    public void setApi(Api api){
        this.api = api;
    }

    public Api getApi(){
        return this.api;
    }

    @FXML
    private TextField apiName;

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
    private TextField version;

    @FXML
    private HBox HboxListRoutes;

    @FXML
    private ComboBox<String> listRoutesApi;

    @FXML
    private Button newRoutesButton;

    @FXML
    public void initialize() {
        HboxListRoutes.setVisible(false);
        newRoutesButton.setVisible(false);
    }


    void setTextData(){
        newRoutesButton.setVisible(true);
        apiName.setText(this.api.getName());
        apiDescription.setText(this.api.getDescription());
        serverAddress.setText(this.api.getServerPath());
        contactName.setText(this.api.getContact().getName());
        contactEmail.setText(this.api.getContact().getEmail());
        licenseUrl.setText(this.api.getLicense());
        version.setText(this.api.getVersion());
        if(!this.api.getRoutes().isEmpty()){
            loadListOfRoutesSelect();
        }else{
            HboxListRoutes.setVisible(false);
        }
    }

    private void loadListOfRoutesSelect(){
        HboxListRoutes.setVisible(true);
        ArrayList<String> listRouteView = new ArrayList<>();
        for(int i = 0 ; i < this.api.getRoutes().size() ; i++){
            String AddRouteToList = this.api.getRoutes().get(i).getMethod() + "   : " + this.api.getRoutes().get(i).getPath();
            listRouteView.add(AddRouteToList);
        }
        ObservableList<String> listOfRoutes = FXCollections.observableArrayList(listRouteView);
        listRoutesApi.setItems(listOfRoutes);
        listRoutesApi.getSelectionModel().selectFirst();
    }

    @FXML
    void saveApi(ActionEvent event){
        if(apiDescription.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR,((Node)event.getSource()).getScene().getWindow(), "Form Error!", "Please enter a name Api");
            return;
        }
        if(apiDescription.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR,((Node)event.getSource()).getScene().getWindow(), "Form Error!", "Please enter your description of API");
            return;
        }
        if(serverAddress.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR,((Node)event.getSource()).getScene().getWindow(), "Form Error!", "Please enter a server address");
            return;
        }
        if(contactName.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR,((Node)event.getSource()).getScene().getWindow(), "Form Error!", "Please enter a name");
            return;
        }
        if(contactEmail.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR,((Node)event.getSource()).getScene().getWindow(), "Form Error!", "Please enter your email");
            return;
        }
        if(!isValidEmailAddress(contactEmail.getText())){
            showAlert(Alert.AlertType.ERROR,((Node)event.getSource()).getScene().getWindow(), "Form Error!", "Please enter a valid email");
            return;
        }
        if(licenseUrl.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR,((Node)event.getSource()).getScene().getWindow(), "Form Error!", "Please enter a license");
            return;
        }
        if(version.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR,((Node)event.getSource()).getScene().getWindow(), "Form Error!", "Please enter a version");
            return;
        }
        showAlert(Alert.AlertType.CONFIRMATION,((Node)event.getSource()).getScene().getWindow(), "Registration Successful!", "Vos informations ont bien été enregistré");

        api.setName(apiName.getText());
        api.setDescription(apiDescription.getText());
        api.setServerPath(serverAddress.getText());
        api.getContact().setName(contactName.getText());
        api.getContact().setEmail(contactEmail.getText());
        api.setLicense(licenseUrl.getText());
        api.setVersion(version.getText());
        newRoutesButton.setVisible(true);

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
        Parent apiView = loader.load();
        Scene apiScene = new Scene(apiView);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        RouteController controlR = loader.getController();
        controlR.setApi(api);
        controlR.setTextData(false);
        window.setScene(apiScene);
        window.show();

    }

    @FXML
    void exportThisApiJSON(ActionEvent event){
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        DirectoryChooser directoryChooser = new DirectoryChooser();
        File selectedDirectory = directoryChooser.showDialog(window);
        String directoryPath = selectedDirectory.getAbsolutePath() + "/" + this.api.getName() + ".json";
        ExportModel exportModel = new ExportModel();
        exportModel.getJsonModel(this.api,directoryPath);
    }

    @FXML
    void exportThisApiYAML(ActionEvent event){
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        DirectoryChooser directoryChooser = new DirectoryChooser();
        File selectedDirectory = directoryChooser.showDialog(window);
        String directoryPath = selectedDirectory.getAbsolutePath() + "/" + this.api.getName() + ".yaml";
        ExportModel exportModel = new ExportModel();
        exportModel.getYamlModel(this.api,directoryPath);
    }

    @FXML
    void loadPreviousScene (ActionEvent event)throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/MenuView.fxml"));
        Parent menuView = loader.load();
        Scene menuScene = new Scene(menuView);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(menuScene);
        window.show();
    }

    @FXML
    void deleteRoute (ActionEvent event){
        int selectedIndex = listRoutesApi.getSelectionModel().getSelectedIndex();
        this.api.getRoutes().remove(this.api.getRoutes().get(selectedIndex));
        setTextData();
    }

    @FXML
    void editRoute (ActionEvent event)throws IOException {
        int selectedIndex = listRoutesApi.getSelectionModel().getSelectedIndex();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/RouteView.fxml"));
        Parent apiView = loader.load();
        Scene apiScene = new Scene(apiView);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        RouteController controlR = loader.getController();
        controlR.setApi(api);
        controlR.setIndexOfRoute(selectedIndex);
        controlR.setTextData(true);
        window.setScene(apiScene);
        window.show();
    }

    private static boolean isValidEmailAddress(String email) {
        boolean result = true;
        try {
            InternetAddress emailAddr = new InternetAddress(email);
            emailAddr.validate();
        } catch (AddressException ex) {
            result = false;
        }
        return result;
    }

    @FXML
    void callPluginFunction(ActionEvent event)throws IOException{
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        File file = fileChooser.showOpenDialog(window);
        String path = file.getAbsolutePath();
        var loader = new PluginLoader(path);
        loader.loadPlugin(this.api);
    }
}
