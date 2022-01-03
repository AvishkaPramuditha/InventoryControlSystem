package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.QueryDAO;
import dto.ProductDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class QueryDAOImpl implements QueryDAO {
    @Override
    public ProductDTO searchProductDetail(String ID, String name) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.executeQuery("SELECT p.ProductID,p.ProductName,p.UnitPrice,p.Manufacturer, c.CategoryCode,c.CategoryName FROM Product p INNER JOIN Category c  ON p.CategoryCode=c.CategoryCode  WHERE  p.ProductID=? OR p.ProductName=?",ID,name);
        return resultSet.next()? new ProductDTO(resultSet.getString("ProductID"),resultSet.getString("ProductName"),resultSet.getDouble("UnitPrice"),resultSet.getString("Manufacturer"),resultSet.getString("CategoryCode"),resultSet.getString("CategoryName")):null;
    }

    @Override
    public ProductDTO searchStockDetail(String IDName) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.executeQuery("SELECT p.ProductID,p.ProductName,p.UnitPrice,p.Manufacturer,c.CategoryName,s.QuantityOnHand FROM Category c INNER JOIN Product p LEFT JOIN Stock s ON p.CategoryCode=c.CategoryCode AND p.ProductID=s.ProductID WHERE  p.ProductID=? OR p.ProductName=?", IDName,IDName);
        return resultSet.next()?new ProductDTO(resultSet.getString("ProductID"),resultSet.getString("ProductName"),resultSet.getDouble("UnitPrice"),resultSet.getString("Manufacturer"),resultSet.getString("CategoryName"),resultSet.getInt("QuantityOnHand")):null;
    }

    @Override
    public ArrayList<ProductDTO> getStockDetail() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.executeQuery("SELECT p.ProductID,p.ProductName,p.UnitPrice,p.Manufacturer,c.CategoryName,s.QuantityOnHand FROM Product p INNER JOIN Category c INNER JOIN Stock s ON p.CategoryCode=c.CategoryCode AND p.ProductID=s.ProductID");
        ArrayList<ProductDTO> stocks = new ArrayList<>();
        while (resultSet.next()){
            stocks.add(new ProductDTO(resultSet.getString("ProductID"),resultSet.getString("ProductName"),resultSet.getDouble("UnitPrice"),resultSet.getString("Manufacturer"),resultSet.getString("CategoryName"),resultSet.getInt("QuantityOnHand")));
        }
        return stocks;
    }
}





