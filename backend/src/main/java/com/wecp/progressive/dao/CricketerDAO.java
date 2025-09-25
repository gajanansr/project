package com.wecp.progressive.dao;
 
import com.wecp.progressive.entity.Cricketer;
import java.sql.SQLException;
import java.util.List;
 
public interface CricketerDAO {
 
    int addCricketer(Cricketer cricketer) throws SQLException;
 
    Cricketer getCricketerById(int cricketerId) throws SQLException;
 
    void updateCricketer(Cricketer cricketer) throws SQLException;
 
    void deleteCricketer(int cricketerId) throws SQLException;
 
    List<Cricketer> getAllCricketers() throws SQLException;
}