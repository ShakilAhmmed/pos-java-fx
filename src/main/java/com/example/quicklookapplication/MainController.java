package com.example.quicklookapplication;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController extends Application {
    @FXML
    private Label welcomeText;
    @FXML
    private Button categoryMenu;
    @FXML
    private Button brandMenu;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to QUICK LOOK Application!");
    }

    public void handleCategoryButtonAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("category-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 600);
        Stage primaryStage = (Stage) categoryMenu.getScene().getWindow();
        primaryStage.setTitle("Category");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("home-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 600);
        stage.setTitle("QUICK LOOK!");
        stage.setScene(scene);
        stage.show();
    }
}