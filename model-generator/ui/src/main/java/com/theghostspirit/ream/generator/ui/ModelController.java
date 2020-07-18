package com.theghostspirit.ream.generator.ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javafx.scene.control.TextField;
import java.io.IOException;

public class ModelController {

    @FXML
    private TextField nameModel;

    @FXML
    private TextField descriptionModel;

    @FXML
    void loadObjectView(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ObjectView.fxml"));
        Parent objectView = (Parent) loader.load();
        Scene objectScene = new Scene(objectView);
        System.out.println("Ok dans le loadRouteView");
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();

        ObjectController controlR = loader.getController();
        //controlR.setApi(api);

        window.setScene(objectScene);
        window.show();
    }

    @FXML
    void addModel(ActionEvent event) throws IOException {

    }


}
