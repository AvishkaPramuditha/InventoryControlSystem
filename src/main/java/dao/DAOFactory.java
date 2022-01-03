package dao;

import dao.custom.impl.*;

public class DAOFactory {
    private static DAOFactory daoFactory;

    public enum DAOTypes{
        CATEGORY,PRODUCT,STOCK,LOGIN,Query
    }
    private DAOFactory(){
    }
  public static DAOFactory getDaoFactory(){
        return daoFactory==null?(daoFactory=new DAOFactory()):daoFactory;
  }
    public SuperDAO getDAO(DAOTypes types){
        switch (types) {
            case CATEGORY:
                return new CategoryDAOImpl();
            case PRODUCT:
                return new ProductDAOImpl();
            case STOCK:
                return new StockDAOImpl();
            case LOGIN:
                return new LoginDAOImpl();
            case Query:
                return new QueryDAOImpl();
            default:return null;
        }
    }
}
