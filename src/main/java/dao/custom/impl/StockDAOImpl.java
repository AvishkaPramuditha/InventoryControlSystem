package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.StockDAO;
import entity.Stock;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StockDAOImpl implements StockDAO {

    @Override
    public boolean add(Stock stock) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate("INSERT INTO  Stock VALUES(?,?)",stock.getProductID(),stock.getQuantityOnHand());
    }

    @Override
    public boolean delete(String ID) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate("DELETE FROM Stock WHERE ProductID=?",ID);
    }

    @Override
    public boolean update(Stock stock) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate("Update Stock SET QuantityOnHand=? WHERE ProductID=?",stock.getQuantityOnHand(),stock.getProductID());
    }

    @Override
    public ArrayList<Stock> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Stock> categories=new ArrayList<>();
        ResultSet resultSet = CrudUtil.executeQuery("SELECT * FROM Stock");
        while (resultSet.next()){
            categories.add(new Stock(resultSet.getString("ProductID"),resultSet.getInt("QuantityOnHand")));
        }
        return categories;
    }
}
