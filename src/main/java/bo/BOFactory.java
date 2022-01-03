package bo;

import bo.custom.impl.CategoryBoImpl;
import bo.custom.impl.LoginBOImpl;
import bo.custom.impl.ProductBOImpl;
import bo.custom.impl.StockBOImpl;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory(){}

    public enum BOTypes{
        CATEGORY,PRODUCT,STOCK,LOGIN
    }

    public static BOFactory getBoFactory(){
        return boFactory==null?(boFactory=new BOFactory()):boFactory;
    }

    public SuperBO getBO(BOTypes types){
        switch (types) {
            case CATEGORY:
                return new CategoryBoImpl();
            case PRODUCT:
                return new ProductBOImpl();
            case STOCK:
                return new StockBOImpl();
            case LOGIN:
                return new LoginBOImpl();
            default:return null;
        }
    }
}
