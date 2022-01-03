package dao;

import db.DBConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CrudUtil {
    private static PreparedStatement getPreparedStatement(String sql,Object...objects) throws SQLException, ClassNotFoundException {
        PreparedStatement preparedStatement = DBConnection.getDbConnection().getConnection().prepareStatement(sql);
        for (int i = 0; i < objects.length; i++) {
            preparedStatement.setObject(i+1,objects[i]);
        }
        return preparedStatement;
    }

    public static boolean executeUpdate(String sql,Object...object) throws SQLException, ClassNotFoundException {
        return getPreparedStatement(sql, object).executeUpdate()>0;
    }

    public static ResultSet executeQuery(String sql,Object...object) throws SQLException, ClassNotFoundException {
        return getPreparedStatement(sql, object).executeQuery();
    }

}
