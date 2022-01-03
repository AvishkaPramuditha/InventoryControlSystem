package dtm;

public class StockTM {
    private String productID;
    private String productName;
    private String categoryName;
    private String manufacturer;
    private double unitPrice;
    private int quantityOnHand;

    public StockTM(String productID, String productName, String categoryName, String manufacturer, double unitPrice, int quantityOnHand) {
        this.productID = productID;
        this.productName = productName;
        this.categoryName = categoryName;
        this.manufacturer = manufacturer;
        this.unitPrice = unitPrice;
        this.quantityOnHand = quantityOnHand;
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

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getQuantityOnHand() {
        return quantityOnHand;
    }

    public void setQuantityOnHand(int quantityOnHand) {
        this.quantityOnHand = quantityOnHand;
    }
}
