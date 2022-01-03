package bo.custom.impl;

import bo.custom.StockBO;
import dao.DAOFactory;
import dao.SuperDAO;
import dao.custom.QueryDAO;
import dao.custom.StockDAO;
import dto.ProductDTO;
import dto.StockDTO;
import entity.Stock;

import java.sql.SQLException;
import java.util.ArrayList;

public class StockBOImpl implements StockBO {
    QueryDAO queryDAO = (QueryDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.Query);
    StockDAO stockDAO = (StockDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.STOCK);

    @Override
    public ProductDTO searchStockByIDName(String IDName) throws SQLException, ClassNotFoundException {
        return queryDAO.searchStockDetail(IDName);
    }

    public ArrayList<ProductDTO> getAllStockDetail() throws SQLException, ClassNotFoundException {
        return queryDAO.getStockDetail();
    }

    @Override
    public boolean addProductToStock(StockDTO stockDTO) throws SQLException, ClassNotFoundException {
      return stockDAO.add(new Stock(stockDTO.getProductID(),stockDTO.getQuantityOnHand()));
    }

    @Override
    public boolean removeProductFromStock(String productID) throws SQLException, ClassNotFoundException {
        return stockDAO.delete(productID);
    }

    @Override
    public boolean UpdateProductInStock(StockDTO stockDTO) throws SQLException, ClassNotFoundException {
        return stockDAO.update(new Stock(stockDTO.getProductID(),stockDTO.getQuantityOnHand()));
    }
}
