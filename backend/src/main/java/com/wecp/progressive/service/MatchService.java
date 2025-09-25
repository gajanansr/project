package com.wecp.progressive.service;
import com.wecp.progressive.entity.Match;
import com.wecp.progressive.exception.NoMatchesFoundException;

import java.sql.SQLException;
import java.util.List;
public interface MatchService {
    List<Match> getAllMatches() throws SQLException;
    Match getMatchById(int matchId) throws SQLException;
    Integer addMatch(Match match) throws SQLException;
    void updateMatch(Match match) throws SQLException;
    void deleteMatch(int matchId) throws SQLException;
    //Do not implement these methods in MatchServiceImplJdbc.java class
    default List<Match> getAllMatchesByStatus(String status) throws SQLException{
        return null;
    }
}
