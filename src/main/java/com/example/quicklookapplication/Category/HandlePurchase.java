//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.example.quicklookapplication.Category;

import com.example.quicklookapplication.HelloApplication;
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

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class HandlePurchase implements Initializable {
    @FXML
    private TextField productId;
    @FXML
    private TextField productQuantity;


    @FXML
    private TableView<Purchase> purchaseTableView;
    @FXML
    private TableColumn<Purchase, Integer> tableId;
    @FXML
    private TableColumn<Purchase, String> tableProductName;
    @FXML
    private TableColumn<Purchase, String> tableProductQuantity;
    @FXML
    private TableColumn<Purchase, String> tableProductDate;
    @FXML
    private Button addPurchase;
    @FXML
    private Button goBackToHomePage;

    public HandlePurchase() {
    }

    @FXML
    protected void handleButtonAction(ActionEvent event) throws IOException {
        if (event.getSource() == this.addPurchase) {
            this.insertProduct();
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

        Purchase product = purchaseTableView.getSelectionModel().getSelectedItem();
        if (product != null) {
            productId.setText("" + product.getProductId());
            productQuantity.setText("" + product.getProductQuantity());
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

    public ObservableList<Purchase> getProductList() {
        ObservableList<Purchase> productList = FXCollections.observableArrayList();
        Connection connection = this.getConnection();
        String query = "SELECT * FROM `products` inner join purchases ON products.id = purchases.product_id";

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                Purchase purchase = new Purchase(resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("quantity"),
                        resultSet.getString("date")
                );
                productList.add(purchase);
            }
        } catch (Exception var7) {
            System.out.println("Error" + var7.getMessage());
            var7.printStackTrace();
        }

        return productList;
    }

    public void showProducts() {
        ObservableList<Purchase> list = this.getProductList();
        this.tableId.setCellValueFactory(new PropertyValueFactory("Id"));
        this.tableProductName.setCellValueFactory(new PropertyValueFactory("ProductId"));
        this.tableProductQuantity.setCellValueFactory(new PropertyValueFactory("ProductQuantity"));
        this.tableProductDate.setCellValueFactory(new PropertyValueFactory("ProductDate"));
        this.purchaseTableView.setItems((ObservableList) null);
        this.purchaseTableView.setItems(list);
    }

    private void insertProduct() {
        String query = "INSERT INTO `purchases`(`product_id`, `quantity`) VALUES (" +
                "'" + productId.getText() + "','"
                + productQuantity.getText() + "')";
        System.out.println("query " + query);
        this.executeQuery(query);
        this.clearForm();
        this.showProducts();
    }

    private void clearForm() {
        this.productId.setText((String) null);
        this.productQuantity.setText((String) null);
    }

    private void executeQuery(String query) {
        Connection connection = this.getConnection();

        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
        } catch (Exception var5) {
            System.out.println("Error " + var5.getMessage());
            var5.printStackTrace();
        }

    }
}
