package com.wecp.progressive.service.impl;

import java.sql.SQLException;
import java.util.List;
// import java.util.regex.MatchResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wecp.progressive.entity.Match;
import com.wecp.progressive.repository.MatchRepository;
import com.wecp.progressive.service.MatchService;


@Service
public class MatchServiceImplJpa implements MatchService {

    @Autowired
    private MatchRepository matchRepository;

    

    public MatchServiceImplJpa() {
    }

    public MatchServiceImplJpa(MatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }

    @Override
    public List<Match> getAllMatches() throws SQLException {
        // return matchRepository.findAll();
        return List.of();
    }

    @Override
    public Match getMatchById(int matchId) throws SQLException {
        // return matchRepository.findById(matchId).orElse(null);
        return null;
    }

    @Override
    public Integer addMatch(Match match) throws SQLException {
        // matchRepository.save(match);
        // return match.getMatchId();
        return -1;
    }

    @Override
    public void updateMatch(Match match) throws SQLException {
        // if(matchRepository.existsById(match.getMatchId()))
        // matchRepository.save(match);
    }

    @Override
    public void deleteMatch(int matchId) throws SQLException {
        // matchRepository.deleteById(matchId);

    }

    public List<Match> getAllMatchesByStatus(String status) throws SQLException
    {
        // return matchRepository.findByStatus(status);
        return null;
    }

    
}
