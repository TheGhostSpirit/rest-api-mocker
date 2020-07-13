package com.theghostspirit.ream.generator.ui;

import com.theghostspirit.ream.generator.core.Api;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;

import javafx.scene.control.TextField;

import javafx.scene.control.CheckBox;

import java.awt.*;


public class ObjectController {

    private Api api;

    public void setApi(Api api){
        this.api = api;
    }

    @FXML
    private TextField nameObject;

    @FXML
    private ComboBox<String> selectTypeObject;

    @FXML
    private Checkbox requiredObject;

}
