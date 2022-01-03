package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.LoginDAO;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDAOImpl implements LoginDAO {

    @Override
    public int getPassword(String userName) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.executeQuery("SELECT Password FROM Login WHERE UserName=?", userName);
        resultSet.next();
        return resultSet.getInt(1);
    }
}
