package com.wecp.progressive.service.impl;
 
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
 
import com.wecp.progressive.entity.Cricketer;
import com.wecp.progressive.service.CricketerService;
 
public class CricketerServiceImplArraylist implements CricketerService {
private static List<Cricketer> cricketersList =new ArrayList<>();
    @Override
    public List<Cricketer> getAllCricketers() {
       return cricketersList; }
 
    @Override
    public Integer addCricketer(Cricketer cricketer) {
    cricketersList.add(cricketer);
    return cricketersList.size();
    }
 
    @Override
    public List<Cricketer> getAllCricketersSortedByExperience() {
        List<Cricketer> sortedCricketer = cricketersList;
        sortedCricketer.sort(Comparator.comparing(Cricketer::getExperience));
        return  sortedCricketer;
    }
 
    @Override
    public void emptyArrayList(){
        cricketersList = new ArrayList<>();
    }
 
   
 
}