package com.wecp.progressive.service;

import com.wecp.progressive.entity.Match;

import java.util.List;

public interface MatchService {

    List<Match> getAllMatches();

    Match getMatchById(int matchId);

    Integer addMatch(Match match);

    void updateMatch(Match match);

    void deleteMatch(int matchId);

    //Do not implement these methods in MatchServiceImplJdbc.java class
    default List<Match> getAllMatchesByStatus(String status) {
        return null;
    }
}
