package com.wecp.progressive.service;

import com.wecp.progressive.entity.Cricketer;

import java.util.List;

public interface CricketerService {

    List<Cricketer> getAllCricketers();

    Integer addCricketer(Cricketer cricketer);

    List<Cricketer> getAllCricketersSortedByExperience();

    default void emptyArrayList() {
    }

    //Do not implement these methods in CricketerServiceImplArraylist.java class
    default void updateCricketer(Cricketer cricketer) {}

    default void deleteCricketer(int cricketerId) {}

    default Cricketer getCricketerById(int cricketerId) {
        return null;
    }

    //Do not implement these methods in CricketerServiceImplArraylist.java and CricketerServiceImplJdbc.java class
    default List<Cricketer> getCricketersByTeam(int teamId) {
        return null;
    }

}
