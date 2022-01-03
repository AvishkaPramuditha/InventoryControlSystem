package entity;

public class Product {
    private String productID;
    private String productName;
    private double unitPrice;
    private String manufacturer;
    private String categoryCode;

    public Product(String productID, String productName, double unitPrice, String manufacturer, String categoryCode) {
        this.productID = productID;
        this.productName = productName;
        this.unitPrice = unitPrice;
        this.manufacturer = manufacturer;
        this.categoryCode = categoryCode;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }
}
