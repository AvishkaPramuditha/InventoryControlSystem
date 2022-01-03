package dao.custom;

import dao.SuperDAO;
import dto.ProductDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface QueryDAO extends SuperDAO {
    public ProductDTO searchProductDetail(String ID, String name) throws SQLException, ClassNotFoundException;
    public ProductDTO searchStockDetail(String IDName) throws SQLException, ClassNotFoundException;
    public ArrayList<ProductDTO> getStockDetail() throws SQLException, ClassNotFoundException;

}
