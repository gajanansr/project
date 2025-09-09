package com.wecp.progressive.dao;

import com.wecp.progressive.entity.Cricketer;

import java.util.List;

public interface CricketerDAO {
    int addCricketer(Cricketer cricketer);
    Cricketer getCricketerById(int cricketerId);
    void updateCricketer (Cricketer cricketer);
    void deleteCricketer (int cricketerId);
    List<Cricketer> getAllCricketers();
}