package com.example.quicklookapplication.Category;

import com.example.quicklookapplication.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class HandleLogin implements Initializable {
    @FXML
    private TextField id;
    @FXML
    private TextField email;
    @FXML
    private TextField password;
    @FXML
    private Button login;
    @FXML
    private Button goBackToHomePage;

    @FXML
    protected void handleButtonAction(ActionEvent event) throws IOException {
        if (event.getSource() == login) {
            if (login()) {
                clearForm(event);
            }
        } else if (event.getSource() == goBackToHomePage) {
            goBackToHomePage(event);
        }
    }

    public void goBackToHomePage(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("login-reg-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        Stage primaryStage = (Stage) goBackToHomePage.getScene().getWindow();
        primaryStage.setTitle("Home");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public Connection getConnection() {
        Connection connection;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/point_of_sale_fx", "root", "");
            System.out.println("Connected");
            return connection;
        } catch (Exception exception) {
            System.out.println("Error" + exception.getMessage());
            return null;
        }
    }

    private boolean login() {
        String query = "SELECT *  FROM users WHERE email='" + email.getText() + "'and password='" + password.getText() + "'";
        System.out.println("query" + query);
        Connection connection = getConnection();
        Statement statement;
        ResultSet resultSet;
        boolean result = false;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                result = true;
            }
        } catch (Exception exception) {
            System.out.println("Error" + exception.getMessage());
            exception.printStackTrace();
        }
        System.out.println(result);
        return result;

    }

    private void clearForm(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("home-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        Stage primaryStage = (Stage) goBackToHomePage.getScene().getWindow();
        primaryStage.setTitle("Category");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void executeQuery(String query) {
        Connection connection = getConnection();
        Statement statement;
        try {
            statement = connection.createStatement();
            statement.executeUpdate(query);
        } catch (Exception exception) {
            System.out.println("Error" + exception.getMessage());
            exception.printStackTrace();
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void handleMouseAction(MouseEvent mouseEvent) {
    }
}