package com.theghostspirit.ream.generator.ui;

import javafx.application.Application;
import javafx.stage.Stage;

public class MainApp extends Application
{
    public static void main(String[] args)
    {
        Application.launch(args);
    }

    private Router router;

    @Override
    public void start(final Stage stage) {

        try{
            this.router = new Router(stage);
            System.out.println("Ok");
            router.<MenuController>goTo("Menu", controller -> controller.setRouter(router));
            System.out.println("Ok");


            stage.setTitle("API Generator");
            stage.show();

        }catch(Exception e){
            System.out.println("Erreur Perso : " + e);
        }

    }
}
