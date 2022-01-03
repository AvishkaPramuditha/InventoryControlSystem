package bo.custom;

import bo.SuperBO;
import dto.CategoryDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CategoryBO extends SuperBO {
    public boolean addCategory(CategoryDTO categoryDTO) throws SQLException, ClassNotFoundException;
    public boolean updateCategory(CategoryDTO categoryDTO) throws SQLException, ClassNotFoundException;
    public boolean deleteCategory(String code) throws SQLException, ClassNotFoundException;
    public ArrayList<CategoryDTO> getAllCategory() throws SQLException, ClassNotFoundException;
}
