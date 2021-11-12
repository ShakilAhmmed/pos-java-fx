//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.example.quicklookapplication.Category;

import com.example.quicklookapplication.HelloApplication;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class HandleProduct implements Initializable {
    @FXML
    private TextField productId;
    @FXML
    private TextField productName;
    @FXML
    private TextField productDescription;
    @FXML
    private TextField categoryId;
    @FXML
    private TextField brandId;
    @FXML
    private TextField productPurchasePrice;
    @FXML
    private TextField productSalePrice;
    @FXML
    private TextField productStatus;
    @FXML
    private TableView<Product> productTableView;
    @FXML
    private TableColumn<Product, Integer> tableId;
    @FXML
    private TableColumn<Product, String> tableProductName;
    @FXML
    private TableColumn<Product, String> tableProductDesc;
    @FXML
    private TableColumn<Product, String> tableProductPurchasePrice;
    @FXML
    private TableColumn<Product, String> tableProductSalePrice;
    @FXML
    private TableColumn<Product, String> tableProductStatus;
    @FXML
    private Button addProduct;
    @FXML
    private Button editProduct;
    @FXML
    private Button deleteProduct;
    @FXML
    private Button goBackToHomePage;

    public HandleProduct() {
    }

    @FXML
    protected void handleButtonAction(ActionEvent event) throws IOException {
        if (event.getSource() == this.addProduct) {
            this.insertProduct();
        } else if (event.getSource() == this.editProduct) {
            this.updateProduct();
        } else if (event.getSource() == this.deleteProduct) {
            this.destroyProduct();
        } else if (event.getSource() == this.goBackToHomePage) {
            this.goBackToHomePage(event);
        }

    }

    public void goBackToHomePage(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("home-view.fxml"));
        Scene scene = new Scene((Parent) fxmlLoader.load(), 800.0D, 600.0D);
        Stage primaryStage = (Stage) this.goBackToHomePage.getScene().getWindow();
        primaryStage.setTitle("Product");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void handleMouseAction(MouseEvent mouseEvent) {

        Product product = productTableView.getSelectionModel().getSelectedItem();
        if (product != null) {
            productId.setText("" + product.getId());
            categoryId.setText("" + product.getCategoryId());
            brandId.setText(product.getBrandId());
            productName.setText(product.getProductName());
            productDescription.setText(product.getProductDescription());
            productPurchasePrice.setText(product.getProductPurchasePrice());
            productSalePrice.setText(product.getProductSalePrice());
            productStatus.setText(product.getProductStatus());
        }
    }

    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.showProducts();
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

    public ObservableList<Product> getProductList() {
        ObservableList<Product> productList = FXCollections.observableArrayList();
        Connection connection = this.getConnection();
        String query = "SELECT * FROM products";

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                Product product = new Product(resultSet.getInt("id"), resultSet.getString("category_id"), resultSet.getString("brand_id"), resultSet.getString("name"), resultSet.getString("description"), resultSet.getString("purchase_price"), resultSet.getString("sale_price"), resultSet.getString("status"));
                productList.add(product);
            }
        } catch (Exception var7) {
            System.out.println("Error" + var7.getMessage());
            var7.printStackTrace();
        }

        return productList;
    }

    public void showProducts() {
        ObservableList<Product> list = this.getProductList();
        this.tableId.setCellValueFactory(new PropertyValueFactory("Id"));
        this.tableProductName.setCellValueFactory(new PropertyValueFactory("ProductName"));
        this.tableProductDesc.setCellValueFactory(new PropertyValueFactory("ProductDescription"));
        this.tableProductPurchasePrice.setCellValueFactory(new PropertyValueFactory("ProductPurchasePrice"));
        this.tableProductSalePrice.setCellValueFactory(new PropertyValueFactory("ProductSalePrice"));
        this.tableProductStatus.setCellValueFactory(new PropertyValueFactory("ProductStatus"));
        this.productTableView.setItems((ObservableList) null);
        this.productTableView.setItems(list);
    }

    private void insertProduct() {
        String query = "INSERT INTO `products` (`name`, `description`, `category_id`, `brand_id`, `purchase_price`, `sale_price`, `status`) VALUES (" +
                "'" + productName.getText() + "','" + productDescription.getText() + "','" + categoryId.getText() + "','" + brandId.getText()  + "','" + productPurchasePrice.getText() + "','" + productSalePrice.getText() + "','" + productStatus.getText() + "'" + ")";

        System.out.println("query " + query);
        this.executeQuery(query);
        this.clearForm();
        this.showProducts();
    }

    private void updateProduct() {
        String query = "UPDATE `products` SET `name`='" + this.productName.getText() + "', `category_id`='" + this.categoryId.getText() + "',`brand_id`='" + this.brandId.getText() + "',`description`='" + this.productDescription.getText() + "',`purchase_price`='" + this.productPurchasePrice.getText() + "',`sale_price`='" + this.productSalePrice.getText() + "',`status`='" + this.productStatus.getText() + "' WHERE id=" + this.productId.getText();
        this.executeQuery(query);
        this.clearForm();
        this.showProducts();
    }

    private void destroyProduct() {
        String query = "DELETE FROM products WHERE id=" + this.productId.getText();
        this.executeQuery(query);
        this.clearForm();
        this.showProducts();
    }

    private void clearForm() {
        this.productId.setText((String) null);
        this.categoryId.setText((String) null);
        this.brandId.setText((String) null);
        this.productName.setText((String) null);
        this.productDescription.setText((String) null);
        this.productSalePrice.setText((String) null);
        this.productPurchasePrice.setText((String) null);
        this.productStatus.setText((String) null);
    }

    private void executeQuery(String query) {
        Connection connection = this.getConnection();

        try {
            Statement statement = connection.createStatement();
            System.out.println("dd ");
            statement.executeUpdate(query);
        } catch (Exception var5) {
            System.out.println("Error " + var5.getMessage());
            var5.printStackTrace();
        }

    }
}
