//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.example.quicklookapplication.Category;

public class SaleReport {

    private int id;
    private String productName;
    private String category;
    private String brand;
    private String productPurchasePrice;
    private String productSalePrice;
    private String productQuantity;
    private String date;

    public SaleReport(int id,
                          String productName,
                          String category,
                          String brand,
                          String productPurchasePrice,
                          String productSalePrice,
                          String productQuantity,
                          String date) {
        this.id = id;
        this.category = category;
        this.brand = brand;
        this.productName = productName;
        this.productPurchasePrice = productPurchasePrice;
        this.productSalePrice = productSalePrice;
        this.productQuantity = productQuantity;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getProductPurchasePrice() {
        return productPurchasePrice;
    }

    public void setProductPurchasePrice(String productPurchasePrice) {
        this.productPurchasePrice = productPurchasePrice;
    }

    public String getProductSalePrice() {
        return productSalePrice;
    }

    public void setProductSalePrice(String productSalePrice) {
        this.productSalePrice = productSalePrice;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(String productQuantity) {
        this.productQuantity = productQuantity;
    }

}
