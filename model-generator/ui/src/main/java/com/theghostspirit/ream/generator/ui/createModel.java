package com.theghostspirit.ream.generator.ui;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import com.theghostspirit.ream.generator.core.*;

public class createModel extends Application{

    @Override
    public void start(final Stage stage){
        final var resultLabel = new Label("Effectuer vos modifications et cliqué sur sauvegarder");
        final var labelName = "Name";
        final var inputName = new TextField("Name");
        final var labelDescription = "Description";
        final var inputDescription = new TextField("Description");
        final var goButton = new Button("SAVE");
        final var contactLabel = new Label("Contact : ");
        final var labelContactName = "nom";
        final var inputContactName = new TextField("Nom");
        final var labelEmail= "email";
        final var inputEmail = new TextField("Email");
        final var newRoute = new Button("+Route");


        final var labelPath = new Label("Path : ");
        final var inputPath = new TextField("path");

        final var labelQuery = new Label("Query : ");
        final var inputBodyName = new TextField("Name");
        final var inputBodyType = new TextField("Type");
        final var inputBodyRequired = new TextField("Required");

        final var labelResponse = new Label("Response : ");
        final var inputBodyNameResponse = new TextField("Name");
        final var inputBodyTypeResponse = new TextField("Type");
        final var inputBodyRequiredResponse = new TextField("Required");


        final var exportButton = new Button("Export");


        ChoiceBox<String> method = new ChoiceBox<>();
        method.getItems().add("GET");
        method.getItems().add("POST");
        method.getItems().add("Update");


        goButton.setOnMouseClicked(event ->{
            final var ToPrint = "Hello, " + inputName.getText().trim() + " " + inputDescription.getText().trim();

            resultLabel.setText(ToPrint);
        });

        exportButton.setOnMouseClicked(event ->{
            //TEST
            Api ap = new Api();
            ap.setName("Ma première API");
            ap.setDescription("Description de mon api");
          //  ap.getContact().setEmail("mathis1703@hotmail.fr");

            ExportModel exportModel = new ExportModel();
            //exportModel.getJsonModel(ap);

            //END
        });

        final var hBox = new HBox();
        hBox.getChildren().setAll(inputName, inputDescription, goButton);

        final var contactBox = new HBox();
        contactBox.getChildren().setAll(inputContactName, inputEmail);

        final var pathBox = new HBox();
        pathBox.getChildren().setAll(inputPath);

        final var routeBox = new VBox();
        routeBox.getChildren().setAll(pathBox,method);

        final var queryBox = new VBox();
        queryBox.getChildren().setAll(labelQuery,inputBodyName,inputBodyType,inputBodyRequired);

        final var responseBox = new VBox();
        responseBox.getChildren().setAll(labelResponse,inputBodyNameResponse,inputBodyTypeResponse,inputBodyRequiredResponse);

        final var responseQueryBox = new HBox();
        responseQueryBox.getChildren().setAll(queryBox,responseBox);

        final var vBox = new VBox();
        vBox.getChildren().setAll(resultLabel, hBox,contactLabel ,contactBox, newRoute,routeBox,responseQueryBox,exportButton);



        final var scene = new Scene(vBox);



        stage.setTitle("Votre nouvelle Api");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args){
        launch(args);
    }


}
