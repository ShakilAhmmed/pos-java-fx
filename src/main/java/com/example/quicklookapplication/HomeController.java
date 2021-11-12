package com.example.quicklookapplication;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable {
    @FXML
    private Label welcomeText;
    @FXML
    private Button categoryMenu;
    @FXML
    private Button brandMenu;
    @FXML
    private Button productMenu;
    @FXML
    private Button purchaseMenu;
    @FXML
    private Button productReport;


    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to QUICK LOOK Application!");
    }

    @FXML
    public void handleButtonAction(ActionEvent event) throws IOException {
        if (event.getSource() == categoryMenu) {
            categoryMenu(event);
        } else if (event.getSource() == brandMenu) {
            brandMenu(event);
        } else if (event.getSource() == productMenu) {
            productMenu(event);
        } else if (event.getSource() == purchaseMenu) {
            purchaseMenu(event);
        } else if (event.getSource() == productReport) {
            productReport(event);
        }
    }

    public void categoryMenu(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("category-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        Stage primaryStage = (Stage) categoryMenu.getScene().getWindow();
        primaryStage.setTitle("Category");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void brandMenu(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HomeController.class.getResource("brand-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        Stage primaryStage = (Stage) brandMenu.getScene().getWindow();
        primaryStage.setTitle("Brands");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void productMenu(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("product-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        Stage primaryStage = (Stage) categoryMenu.getScene().getWindow();
        primaryStage.setTitle("Category");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void purchaseMenu(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("purchase-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        Stage primaryStage = (Stage) purchaseMenu.getScene().getWindow();
        primaryStage.setTitle("Purchase");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void productReport(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("product-report-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        Stage primaryStage = (Stage) productReport.getScene().getWindow();
        primaryStage.setTitle("Product Report");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}