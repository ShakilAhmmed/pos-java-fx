//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.example.quicklookapplication.Category;

public class Product {
    private int id;
    private String categoryId;
    private String brandId;
    private String productName;
    private String productDescription;
    private String productPurchasePrice;
    private String productSalePrice;
    private String productStatus;

    public Product(int id, String categoryId, String brandId, String productName, String productDescription, String productPurchasePrice, String productSalePrice, String productStatus) {
        this.id = id;
        this.categoryId = categoryId;
        this.brandId = brandId;
        this.productName = productName;
        this.productDescription = productDescription;
        this.productPurchasePrice = productPurchasePrice;
        this.productSalePrice = productSalePrice;
        this.productStatus = productStatus;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategoryId() {
        return this.categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getBrandId() {
        return this.brandId;
    }

    public void setBrandId(String brandId) {
        this.brandId = brandId;
    }

    public String getProductName() {
        return this.productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return this.productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getProductPurchasePrice() {
        return this.productPurchasePrice;
    }

    public void setProductPurchasePrice(String productPurchasePrice) {
        this.productPurchasePrice = productPurchasePrice;
    }

    public String getProductSalePrice() {
        return this.productSalePrice;
    }

    public void setProductSalePrice(String productSalePrice) {
        this.productSalePrice = productSalePrice;
    }

    public String getProductStatus() {
        return this.productStatus;
    }

    public void setProductStatus(String productStatus) {
        this.productStatus = productStatus;
    }
}
