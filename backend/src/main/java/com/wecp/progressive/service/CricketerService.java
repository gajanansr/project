package com.wecp.progressive.service;
import com.wecp.progressive.entity.Cricketer;
import com.wecp.progressive.exception.TeamCricketerLimitExceededException;

import java.sql.SQLException;
import java.util.List;
public interface CricketerService {
    List<Cricketer> getAllCricketers() throws SQLException;
    Integer addCricketer(Cricketer cricketer) throws SQLException;
    List<Cricketer> getAllCricketersSortedByExperience() throws SQLException;
    default void emptyArrayList() {
    }
    //Do not implement these methods in CricketerServiceImplArraylist.java class
    default void updateCricketer(Cricketer cricketer) throws SQLException {}
    default void deleteCricketer(int cricketerId) throws SQLException {}
    default Cricketer getCricketerById(int cricketerId) throws SQLException {
        return null;
    }
    //Do not implement these methods in CricketerServiceImplArraylist.java and CricketerServiceImplJdbc.java class
    default List<Cricketer> getCricketersByTeam(int teamId)throws SQLException{
        return null;
    }
}
