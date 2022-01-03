package dto;

public class StockDTO {
    private String ProductID ;
    private int QuantityOnHand ;

    public StockDTO(String productID, int quantityOnHand) {
        ProductID = productID;
        QuantityOnHand = quantityOnHand;
    }

    public String getProductID() {
        return ProductID;
    }

    public void setProductID(String productID) {
        ProductID = productID;
    }

    public int getQuantityOnHand() {
        return QuantityOnHand;
    }

    public void setQuantityOnHand(int quantityOnHand) {
        QuantityOnHand = quantityOnHand;
    }
}
