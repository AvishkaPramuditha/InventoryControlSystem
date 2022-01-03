package dto;

public class ProductDTO {
    private String productID;
    private String productName;
    private double unitPrice;
    private String manufacturer;
    private String categoryCode;
    private String categoryName;

    public int getQuantityOnHand() {
        return quantityOnHand;
    }

    public void setQuantityOnHand(int quantityOnHand) {
        this.quantityOnHand = quantityOnHand;
    }

    private int quantityOnHand;

    public ProductDTO(String productID, String productName, double unitPrice, String manufacturer, String categoryName, int quantityOnHand) {
        this.productID = productID;
        this.productName = productName;
        this.unitPrice = unitPrice;
        this.manufacturer = manufacturer;
        this.categoryName = categoryName;
        this.quantityOnHand = quantityOnHand;
    }

    public ProductDTO(String productID, String productName, double unitPrice, String manufacturer, String categoryCode) {
        this.productID = productID;
        this.productName = productName;
        this.unitPrice = unitPrice;
        this.manufacturer = manufacturer;
        this.categoryCode = categoryCode;
    }

    public ProductDTO(String productID, String productName, double unitPrice, String manufacturer, String categoryCode, String categoryName) {
        this.productID = productID;
        this.productName = productName;
        this.unitPrice = unitPrice;
        this.manufacturer = manufacturer;
        this.categoryCode = categoryCode;
        this.categoryName = categoryName;
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

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
