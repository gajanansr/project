package com.wecp.progressive.controller;

import com.wecp.progressive.entity.Match;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class MatchController {

    public ResponseEntity<List<Match>> getAllMatches() {
        return null;
    }

    public ResponseEntity<Match> getMatchById(int matchId) {
        return null;
    }

    public ResponseEntity<Integer> addMatch(Match match) {
        return null;
    }

    public ResponseEntity<Void> updateMatch(int matchId, Match match) {
        return null;
    }

    public ResponseEntity<Void> deleteMatch(int matchId) {
        return null;
    }

    public ResponseEntity<List<Match>> getAllMatchesByStatus(String status) {
        return null;
    }
}
