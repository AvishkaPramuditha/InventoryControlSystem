package entity;

public class Stock {
    private String ProductID ;
    private int QuantityOnHand ;

    public Stock(String productID, int quantityOnHand) {
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
