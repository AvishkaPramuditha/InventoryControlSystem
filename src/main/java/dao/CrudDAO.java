package dao;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CrudDAO<T1,T2> extends SuperDAO {
    public boolean add(T1 t1) throws SQLException, ClassNotFoundException;
    public  boolean delete(T2 t2) throws SQLException, ClassNotFoundException;
    public boolean update(T1 t1) throws SQLException, ClassNotFoundException;
    public ArrayList<T1> getAll() throws SQLException, ClassNotFoundException;
}
