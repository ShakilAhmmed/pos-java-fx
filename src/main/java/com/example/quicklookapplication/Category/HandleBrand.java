package com.example.quicklookapplication.Category;

import com.example.quicklookapplication.HelloApplication;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class HandleBrand implements Initializable {
    @FXML
    private TextField brandId;
    @FXML
    private TextField brandName;
    @FXML
    private TextField brandDescription;
    @FXML
    private TextField brandStatus;
    @FXML
    private TableView<Brand> brandTableView;
    @FXML
    private TableColumn<Brand, Integer> tableId;
    @FXML
    private TableColumn<Brand, String> tableBrandName;
    @FXML
    private TableColumn<Brand, String> tableBrandDesc;
    @FXML
    private TableColumn<Brand, String> tableBrandStatus;
    @FXML
    private Button addBrand;
    @FXML
    private Button editBrand;
    @FXML
    private Button deleteBrand;
    @FXML
    private Button goBackToHomePage;

    @FXML
    protected void handleButtonAction(ActionEvent event) throws IOException {
        if (event.getSource() == addBrand) {
            insertBrand();
        } else if (event.getSource() == editBrand) {
            updateBrand();
        } else if (event.getSource() == deleteBrand) {
            destroyBrand();
        } else if (event.getSource() == goBackToHomePage) {
            goBackToHomePage(event);
        }
    }

    public void goBackToHomePage(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("home-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        Stage primaryStage = (Stage) goBackToHomePage.getScene().getWindow();
        primaryStage.setTitle("brand");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void handleMouseAction(javafx.scene.input.MouseEvent mouseEvent) {
        Brand brand = brandTableView.getSelectionModel().getSelectedItem();
        if (brand != null) {
            brandId.setText("" + brand.getId());
            brandName.setText(brand.getBrandName());
            brandDescription.setText(brand.getBrandDescription());
            brandStatus.setText(brand.getBrandStatus());
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showBrands();
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

    public ObservableList<Brand> getBrandList() {

        ObservableList<Brand> brandList = FXCollections.observableArrayList();
        Connection connection = getConnection();
        String query = "SELECT * FROM brands";
        Statement statement;
        ResultSet resultSet;


        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Brand brand = new Brand(
                        resultSet.getInt("id"),
                        resultSet.getString("brand_name"),
                        resultSet.getString("brand_description"),
                        resultSet.getString("brand_status")
                );
                brandList.add(brand);
            }

        } catch (Exception exception) {
            System.out.println("Error" + exception.getMessage());
            exception.printStackTrace();
        }
        return brandList;
    }

    public void showBrands() {
        ObservableList<Brand> list = getBrandList();
        tableId.setCellValueFactory(new PropertyValueFactory<Brand, Integer>("Id"));
        tableBrandName.setCellValueFactory(new PropertyValueFactory<Brand, String>("brandName"));
        tableBrandDesc.setCellValueFactory(new PropertyValueFactory<Brand, String>("brandDescription"));
        tableBrandStatus.setCellValueFactory(new PropertyValueFactory<Brand, String>("brandStatus"));
        brandTableView.setItems(null);
        brandTableView.setItems(list);
    }

    private void insertBrand() {
        String query = "INSERT INTO `brands`(`brand_name`, `brand_description`, `brand_status`) VALUES (" +
                "'" + brandName.getText() + "','" + brandDescription.getText() + "','" + brandStatus.getText() + "'" +
                ")";
        System.out.println("query" + query);
        executeQuery(query);
        clearForm();
        showBrands();
    }

    private void updateBrand() {
        String query = "UPDATE `brands` SET `brand_name`='" + brandName.getText() +
                "',`brand_description`='" + brandDescription.getText() + "',`brand_status`='" + brandStatus.getText() +
                "' WHERE id=" + brandId.getText();
        executeQuery(query);
        clearForm();
        showBrands();
    }

    private void destroyBrand() {
        String query = "DELETE FROM brands WHERE id=" + brandId.getText();
        executeQuery(query);
        clearForm();
        showBrands();
    }

    private void clearForm() {
        brandId.setText(null);
        brandName.setText(null);
        brandDescription.setText(null);
        brandStatus.setText(null);
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


}