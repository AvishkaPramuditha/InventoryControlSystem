package bo.custom;

import bo.SuperBO;
import dto.ProductDTO;
import dto.StockDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface StockBO extends SuperBO {
    public ProductDTO searchStockByIDName(String IDName) throws SQLException, ClassNotFoundException;
    public ArrayList<ProductDTO>  getAllStockDetail() throws SQLException, ClassNotFoundException;
    public boolean addProductToStock(StockDTO stockDTO) throws SQLException, ClassNotFoundException;
    public boolean removeProductFromStock(String productID) throws SQLException, ClassNotFoundException;
    public boolean UpdateProductInStock(StockDTO stockDTO) throws SQLException, ClassNotFoundException;

}
