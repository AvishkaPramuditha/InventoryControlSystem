package bo.custom;

import bo.SuperBO;
import javafx.scene.Parent;

import java.io.IOException;
import java.sql.SQLException;

public interface LoginBO  extends SuperBO {
    public boolean checkLoginCredentials(String username ,String password) throws SQLException, ClassNotFoundException;
    public boolean isItDefaultLogin(String username ,String password);
    public Parent setUser(String userName) throws IOException;
}
