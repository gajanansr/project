package com.wecp.progressive.controller;

import com.wecp.progressive.entity.Cricketer;
import com.wecp.progressive.entity.Match;
import com.wecp.progressive.exception.NoMatchesFoundException;
import com.wecp.progressive.service.impl.MatchServiceImplJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.web.server.ServerHttpSecurity.HttpsRedirectSpec;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/match")
public class MatchController {

    @Autowired
    MatchServiceImplJpa matchServiceImplJpa;

    @GetMapping
    public ResponseEntity<List<Match>> getAllMatches() {
        try {
            List<Match> matchList = matchServiceImplJpa.getAllMatches();
            return new ResponseEntity<>(matchList, HttpStatus.OK);
        } catch (SQLException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{matchId}")
    public ResponseEntity<Match> getMatchById(@PathVariable int matchId) {
        try {
            Match match = matchServiceImplJpa.getMatchById(matchId);
            return new ResponseEntity<>(match, HttpStatus.OK);
        } catch (SQLException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<Integer> addMatch(@RequestBody Match match) {
        try {
            int matchId = matchServiceImplJpa.addMatch(match);
            return new ResponseEntity<>(matchId, HttpStatus.CREATED);
        } catch (SQLException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{matchId}")
    public ResponseEntity<Void> updateMatch(@PathVariable int matchId, @RequestBody Match match) {
        try {
            match.setMatchId(matchId);
            matchServiceImplJpa.updateMatch(match);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (SQLException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{matchID}")
    public ResponseEntity<Void> deleteMatch(@PathVariable int matchID) {
        try {
            matchServiceImplJpa.deleteMatch(matchID);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (SQLException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<Match>> getAllMatchesByStatus(@PathVariable String status) {
        try {
            List<Match> matchList = matchServiceImplJpa.getAllMatchesByStatus(status);
            return new ResponseEntity<>(matchList, HttpStatus.OK);
        }catch(NoMatchesFoundException n){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } 
        catch (SQLException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}