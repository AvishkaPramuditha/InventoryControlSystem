package dao.custom;

import dao.CrudDAO;
import entity.Category;
import entity.Product;

import java.sql.SQLException;

public interface CategoryDAO extends CrudDAO<Category,String> {
    public Category searchByCode(String code) throws SQLException, ClassNotFoundException;
    public Category searchByName(String name) throws SQLException, ClassNotFoundException;
}
