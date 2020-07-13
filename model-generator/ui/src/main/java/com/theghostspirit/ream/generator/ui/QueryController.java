package com.theghostspirit.ream.generator.ui;

import com.theghostspirit.ream.generator.core.Api;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class QueryController {

    private Api api;

    public void setApi(Api api){
        this.api = api;
    }

    @FXML
    public void initialize() {
        
    }

    @FXML
    void loadObjectScene(ActionEvent event)throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ObjectView.fxml"));
        Parent apiView = (Parent) loader.load();
        Scene apiScene = new Scene(apiView);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        ObjectController controlR = loader.getController();
        controlR.setApi(api);

        window.setScene(apiScene);
        window.show();
    }



}
