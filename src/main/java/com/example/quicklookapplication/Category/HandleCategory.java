package com.example.quicklookapplication.Category;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.w3c.dom.events.MouseEvent;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.sql.Connection;
import java.sql.DriverManager;

public class HandleCategory implements Initializable {
    @FXML
    private TextField categoryId;
    @FXML
    private TextField categoryName;
    @FXML
    private TextField categoryDescription;
    @FXML
    private TextField categoryStatus;
    @FXML
    private TableView<Category> categoryTableView;
    @FXML
    private TableColumn<Category, Integer> tableId;
    @FXML
    private TableColumn<Category, String> tableCategoryName;
    @FXML
    private TableColumn<Category, String> tableCategoryDesc;
    @FXML
    private TableColumn<Category, String> tableCategoryStatus;
    @FXML
    private Button addCategory;
    @FXML
    private Button editCategory;
    @FXML
    private Button deleteCategory;

    @FXML
    protected void handleButtonAction(ActionEvent event) {
        if (event.getSource() == addCategory) {
            insertCategory();
        } else if (event.getSource() == editCategory) {
            updateCategory();
        } else if (event.getSource() == deleteCategory) {
            destroyCategory();
        }
    }

    public void handleMouseAction(javafx.scene.input.MouseEvent mouseEvent) {
        Category category = categoryTableView.getSelectionModel().getSelectedItem();
        if (category != null) {
            categoryId.setText("" + category.getId());
            categoryName.setText(category.getCategoryName());
            categoryDescription.setText(category.getCategoryDescription());
            categoryStatus.setText(category.getCategoryStatus());
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showCategories();
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

    public ObservableList<Category> getCategoryList() {

        ObservableList<Category> categoryList = FXCollections.observableArrayList();
        Connection connection = getConnection();
        String query = "SELECT * FROM categories";
        Statement statement;
        ResultSet resultSet;


        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Category category = new Category(
                        resultSet.getInt("id"),
                        resultSet.getString("category_name"),
                        resultSet.getString("category_description"),
                        resultSet.getString("category_status")
                );
                categoryList.add(category);
            }

        } catch (Exception exception) {
            System.out.println("Error" + exception.getMessage());
            exception.printStackTrace();
        }
        return categoryList;
    }

    public void showCategories() {
        ObservableList<Category> list = getCategoryList();
        tableId.setCellValueFactory(new PropertyValueFactory<Category, Integer>("Id"));
        tableCategoryName.setCellValueFactory(new PropertyValueFactory<Category, String>("CategoryName"));
        tableCategoryDesc.setCellValueFactory(new PropertyValueFactory<Category, String>("CategoryDescription"));
        tableCategoryStatus.setCellValueFactory(new PropertyValueFactory<Category, String>("CategoryStatus"));
        categoryTableView.setItems(null);
        categoryTableView.setItems(list);
    }

    private void insertCategory() {
        String query = "INSERT INTO `categories`(`category_name`, `category_description`, `category_status`) VALUES (" +
                "'" + categoryName.getText() + "','" + categoryDescription.getText() + "','" + categoryStatus.getText() + "'" +
                ")";
        System.out.println("query" + query);
        executeQuery(query);
        clearForm();
        showCategories();
    }

    private void updateCategory() {
        String query = "UPDATE `categories` SET `category_name`='" + categoryName.getText() +
                "',`category_description`='" + categoryDescription.getText() + "',`category_status`='" + categoryStatus.getText() +
                "' WHERE id=" + categoryId.getText();
        executeQuery(query);
        clearForm();
        showCategories();
    }

    private void destroyCategory() {
        String query = "DELETE FROM categories WHERE id=" + categoryId.getText();
        executeQuery(query);
        clearForm();
        showCategories();
    }

    private void clearForm() {
        categoryId.setText(null);
        categoryName.setText(null);
        categoryDescription.setText(null);
        categoryStatus.setText(null);
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