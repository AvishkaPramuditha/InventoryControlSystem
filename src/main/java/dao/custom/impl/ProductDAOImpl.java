package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.ProductDAO;
import entity.Product;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductDAOImpl implements ProductDAO {
    @Override
    public boolean add(Product product) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate("INSERT INTO  Product VALUES(?,?,?,?,?)",product.getProductID(),product.getProductName(),product.getUnitPrice(),product.getManufacturer(),product.getCategoryCode());
    }

    @Override
    public boolean delete(String ID) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate("DELETE FROM Product WHERE ProductID=?",ID);
    }

    @Override
    public boolean update(Product product) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate("Update Product SET ProductName=?,UnitPrice=?,Manufacturer=?,CategoryCode=? WHERE ProductID=?",product.getProductName(),product.getUnitPrice(),product.getManufacturer(),product.getCategoryCode(),product.getProductID());
    }

    @Override
    public ArrayList<Product> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Product> products=new ArrayList<>();
        ResultSet resultSet = CrudUtil.executeQuery("SELECT * FROM Product");
        while (resultSet.next()){
            products.add(new Product(resultSet.getString("ProductID"),resultSet.getString("ProductName"),resultSet.getDouble("UnitPrice"),resultSet.getString("Manufacturer"),resultSet.getString("CategoryCode")));
        }
        return products;
    }
}
