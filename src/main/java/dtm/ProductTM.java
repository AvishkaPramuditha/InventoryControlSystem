package dtm;

public class ProductTM {
    private String productID;
    private String productName;
    private double unitPrice;
    private String manufacturer;
    private String categoryName;

    public ProductTM(String productID, String productName, double unitPrice, String manufacturer, String categoryCode) {
        this.productID = productID;
        this.productName = productName;
        this.unitPrice = unitPrice;
        this.manufacturer = manufacturer;
        this.categoryName = categoryCode;
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

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryCode) {
        this.categoryName = categoryCode;
    }
}
