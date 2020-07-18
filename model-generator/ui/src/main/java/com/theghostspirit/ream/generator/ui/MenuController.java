package com.theghostspirit.ream.generator.ui;

import java.awt.Desktop;

import com.theghostspirit.ream.generator.core.Api;
import com.theghostspirit.ream.generator.core.ImportModel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class MenuController {

    private Api api;

    private Desktop desktop = Desktop.getDesktop();
    private Router router;
    int counter = 0;
    final FileChooser fileChooser = new FileChooser();

    @FXML
    private Button apiButton;

    @FXML
    public void initialize() {

    }


    @FXML
    void loadApiScene(ActionEvent event)throws IOException {
        /*
        Parent apiView = FXMLLoader.load(getClass().getResource("/ApiView.fxml"));
        Scene apiScene = new Scene(apiView);

        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();

        window.setScene(apiScene);
        window.show();
        */
        this.api = new Api();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ApiView.fxml"));
        Parent apiView = (Parent) loader.load();
        Scene apiScene = new Scene(apiView);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        ApiController controlR = loader.getController();
        controlR.setApi(api);
        window.setScene(apiScene);
        window.show();

    }

    @FXML
    void loadModelScene(ActionEvent event)throws IOException {
        Parent apiView = FXMLLoader.load(getClass().getResource("/ModelView.fxml"));
        Scene apiScene = new Scene(apiView);

        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();

        window.setScene(apiScene);
        window.show();
    }

    @FXML
    void selectFile(ActionEvent event)throws IOException{

        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        File file = fileChooser.showOpenDialog(window);
        String path = file.getAbsolutePath();

        ImportModel importModel = new ImportModel();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ApiView.fxml"));
        Parent apiView = (Parent) loader.load();
        Scene apiScene = new Scene(apiView);
        window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        ApiController controlR = loader.getController();
        controlR.setApi(importModel.ImportJsonFile(path));
        controlR.setTextData();
        window.setScene(apiScene);
        window.show();

    }

    void setRouter(final Router router) {
        this.router = router;
    }

    private void openFile(File file) {
        try {
            this.desktop.open(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
