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
    private Button login;
    @FXML
    private Button register;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to QUICK LOOK Application!");
    }

    @FXML
    public void handleButtonAction(ActionEvent event) throws IOException {
        if (event.getSource() == login) {
            login(event);
        }
    }

    public void login(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 600);
        Stage primaryStage = (Stage) login.getScene().getWindow();
        primaryStage.setTitle("Login");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void registerPage(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainController.class.getResource("reg-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 600);
        Stage primaryStage = (Stage) login.getScene().getWindow();
        primaryStage.setTitle("Register");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("login-reg-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 600);
        stage.setTitle("QUICK LOOK!");
        stage.setScene(scene);
        stage.show();
    }
}