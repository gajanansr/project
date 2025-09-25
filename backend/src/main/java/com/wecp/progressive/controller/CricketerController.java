package com.wecp.progressive.controller;

import com.wecp.progressive.entity.Cricketer;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class CricketerController {

    public ResponseEntity<List<Cricketer>> getAllCricketers() {
        return null;
    }

    public ResponseEntity<Cricketer> getCricketerById(int cricketerId) {
        return null;
    }

    public ResponseEntity<Integer> addCricketer(Cricketer cricketer) {
        return null;
    }

    public ResponseEntity<Void> updateCricketer(int cricketerId, Cricketer cricketer) {
        return null;
    }

    public ResponseEntity<Void> deleteCricketer(int cricketerId) {
        return null;
    }

    public ResponseEntity<List<Cricketer>> getCricketersByTeam(int teamId) {
        return null;
    }
}