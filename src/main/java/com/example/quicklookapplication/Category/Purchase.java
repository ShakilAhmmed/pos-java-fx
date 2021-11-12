package com.example.quicklookapplication.Category;

public class Purchase {
    private int id;
    private String productId;
    private String productQuantity;


    private String productDate;

    public Purchase(int id, String productId, String productQuantity, String productDate) {
        this.id = id;
        this.productId = productId;
        this.productQuantity = productQuantity;
        this.productDate = productDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(String productQuantity) {
        this.productQuantity = productQuantity;
    }


    public String getProductDate() {
        return productDate;
    }

    public void setProductDate(String productDate) {
        this.productDate = productDate;
    }

}
