package bo.custom;

import bo.SuperBO;
import dto.CategoryDTO;
import dto.ProductDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ProductBO extends SuperBO {
    public boolean addProduct(ProductDTO productDTO) throws SQLException, ClassNotFoundException;
    public boolean updateProduct(ProductDTO productDTO) throws SQLException, ClassNotFoundException;
    public boolean deleteProduct(String ID) throws SQLException, ClassNotFoundException;
    public ArrayList<ProductDTO> getAllProduct() throws SQLException, ClassNotFoundException;
    public ArrayList<CategoryDTO> getAllCategory() throws SQLException, ClassNotFoundException;
    public ProductDTO SearchProduct(String ID, String name) throws SQLException, ClassNotFoundException;
    public String getCategoryName(String code) throws SQLException, ClassNotFoundException;
    public String getCategoryCode(String name) throws SQLException, ClassNotFoundException;
}
