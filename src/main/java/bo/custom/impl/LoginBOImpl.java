package bo.custom.impl;

import bo.custom.LoginBO;
import controller.HomeFormController;
import dao.DAOFactory;
import dao.SuperDAO;
import dao.custom.LoginDAO;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;
import java.sql.SQLException;

public class LoginBOImpl implements LoginBO {
    private static final int defaultUserName=-244613732;
    private static final int defaultPassword=1403730359;
    private final LoginDAO loginDAO = (LoginDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.LOGIN);
    @Override
    public boolean checkLoginCredentials(String username ,String password) throws SQLException, ClassNotFoundException {
        return getPasswordByUserName(username)==password.hashCode();
    }

    private int getPasswordByUserName(String userName) throws SQLException, ClassNotFoundException {
        return loginDAO.getPassword(userName);
    }

    @Override
    public boolean isItDefaultLogin(String username ,String password){
       return username.hashCode()==defaultUserName&&password.hashCode()==defaultPassword;
    }
    @Override
    public Parent setUser(String userName) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/homeForm.fxml"));
        Parent load = loader.load();
        HomeFormController homeFormController=loader.getController();
        homeFormController.setUser(userName);
        return load;
    }

}
