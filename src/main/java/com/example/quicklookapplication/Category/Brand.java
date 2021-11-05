package com.example.quicklookapplication.Category;

public class Brand {
    private int id;
    private String brandName;
    private String brandDescription;
    private String brandStatus;

    public Brand(int id, String brandName, String brandDescription, String brandStatus) {
        this.id = id;
        this.brandName = brandName;
        this.brandDescription = brandDescription;
        this.brandStatus = brandStatus;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getBrandDescription() {
        return brandDescription;
    }

    public void setBrandDescription(String brandDescription) {
        this.brandDescription = brandDescription;
    }

    public String getBrandStatus() {
        return brandStatus;
    }

    public void setBrandStatus(String brandStatus) {
        this.brandStatus = brandStatus;
    }
}
