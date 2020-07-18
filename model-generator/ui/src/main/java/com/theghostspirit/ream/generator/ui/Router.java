package com.theghostspirit.ream.generator.ui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.function.Consumer;

class Router {

    private final Stage stage;

    Router(final Stage stage) {
        this.stage = stage;
    }

    void goTo(final String viewName) {
        goTo(viewName, __ -> {});
    }

    <T> void goTo(final String viewName, final Consumer<T> controllerConsumer) {

        System.out.println("Dans le go to : " + controllerConsumer);
        final var view = loadView(viewName, controllerConsumer);
        stage.setScene(new Scene(view));
    }

    private <T> Parent loadView(final String viewName, final Consumer<T> controllerConsumer) {
        final var viewPath = String.format("/%sView.fxml", viewName);
        System.out.println("viewPath : " + viewPath);

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(viewPath));
            System.out.println("FXML Loader : ok ");
            Parent view = (Parent) fxmlLoader.load();
            System.out.println("View ok");
            System.out.println("Test 1 : " + fxmlLoader.getController());
            controllerConsumer.accept(fxmlLoader.getController());
            System.out.println("Accept");

            return view;
        } catch (IOException e) {
            throw new IllegalStateException(String.format("Unable to load view: %s", viewPath), e);
        }
    }
}
