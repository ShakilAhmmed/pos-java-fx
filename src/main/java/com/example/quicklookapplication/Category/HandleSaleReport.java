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

public class HandleSaleReport implements Initializable {
    @FXML
    private TableView<SaleReport> saleReportView;
    @FXML
    private TableColumn<SaleReport, Integer> tableId;
    @FXML
    private TableColumn<SaleReport, String> tableProductName;
    @FXML
    private TableColumn<SaleReport, String> tableProductCategory;
    @FXML
    private TableColumn<SaleReport, String> tableProductBrand;
    @FXML
    private TableColumn<SaleReport, String> tableProductPurchasePrice;
    @FXML
    private TableColumn<SaleReport, String> tableProductSalePrice;
    @FXML
    private TableColumn<SaleReport, String> tableProductSaleQuantity;
    @FXML
    private TableColumn<SaleReport, String> tableProductDate;

    @FXML
    private Button goBackToHomePage;

    public HandleSaleReport() {
    }

    @FXML
    protected void handleButtonAction(ActionEvent event) throws IOException {
        if (event.getSource() == this.goBackToHomePage) {
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

    public ObservableList<SaleReport> getProductList() {
        ObservableList<SaleReport> productList = FXCollections.observableArrayList();
        Connection connection = this.getConnection();
        String query = "SELECT * FROM `products` INNER JOIN categories ON categories.id = products.category_id INNER JOIN brands ON brands.id = products.brand_id INNER JOIN sales ON sales.product_id = products.id";

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                SaleReport product = new SaleReport(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("category_name"),
                        resultSet.getString("brand_name"),
                        resultSet.getString("purchase_price"),
                        resultSet.getString("sale_price"),
                        resultSet.getString("quantity"),
                        resultSet.getString("date")
                );
                productList.add(product);
            }
        } catch (Exception var7) {
            System.out.println("Error" + var7.getMessage());
            var7.printStackTrace();
        }

        return productList;
    }

    public void showProducts() {
        ObservableList<SaleReport> list = this.getProductList();
        this.tableId.setCellValueFactory(new PropertyValueFactory("Id"));
        this.tableProductName.setCellValueFactory(new PropertyValueFactory("ProductName"));
        this.tableProductCategory.setCellValueFactory(new PropertyValueFactory("Category"));
        this.tableProductBrand.setCellValueFactory(new PropertyValueFactory("Brand"));
        this.tableProductPurchasePrice.setCellValueFactory(new PropertyValueFactory("ProductPurchasePrice"));
        this.tableProductSalePrice.setCellValueFactory(new PropertyValueFactory("ProductSalePrice"));
        this.tableProductSaleQuantity.setCellValueFactory(new PropertyValueFactory("ProductQuantity"));
        this.tableProductDate.setCellValueFactory(new PropertyValueFactory("Date"));
        this.saleReportView.setItems((ObservableList) null);
        this.saleReportView.setItems(list);
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
