package dao.custom;

import dao.SuperDAO;

import java.sql.SQLException;

public interface LoginDAO extends SuperDAO {
    public int getPassword(String userName) throws SQLException, ClassNotFoundException;
}
