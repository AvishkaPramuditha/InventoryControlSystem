package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.CategoryDAO;
import entity.Category;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CategoryDAOImpl implements CategoryDAO {

    @Override
    public boolean add(Category category) throws SQLException, ClassNotFoundException {
       return CrudUtil.executeUpdate("INSERT INTO  Category VALUES(?,?)",category.getCategoryCode(),category.getCategoryName());
    }

    @Override
    public boolean delete(String code) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate("DELETE FROM Category WHERE CategoryCode=?",code);
    }

    @Override
    public boolean update(Category category) throws SQLException, ClassNotFoundException {
      return CrudUtil.executeUpdate("Update Category SET CategoryName=? WHERE CategoryCode=?",category.getCategoryName(),category.getCategoryCode());
    }

    @Override
    public Category searchByCode(String code) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.executeQuery("SELECT * FROM Category WHERE CategoryCode=?", code);
        return resultSet.next()? new Category(resultSet.getString("CategoryCode"),resultSet.getString("CategoryName")):null;
    }

    @Override
    public Category searchByName(String name) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.executeQuery("SELECT * FROM Category WHERE CategoryName=?", name);
        return resultSet.next()? new Category(resultSet.getString("CategoryCode"),resultSet.getString("CategoryName")):null;
    }

    @Override
    public ArrayList<Category> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Category> categories=new ArrayList<>();
        ResultSet resultSet = CrudUtil.executeQuery("SELECT * FROM Category");
        while (resultSet.next()){
            categories.add(new Category(resultSet.getString("CategoryCode"),resultSet.getString("CategoryName")));
        }
        return categories;
    }
}
