package com.wecp.progressive.controller;

import com.wecp.progressive.entity.Match;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/matches")
public class MatchController {

    @GetMapping
    public ResponseEntity<List<Match>> getAllMatches() {
        return ResponseEntity.ok(new ArrayList<>());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Match> getMatchById(@PathVariable int matchId) {
        return ResponseEntity.ok(null);
    }

    @PostMapping
    public ResponseEntity<Integer> addMatch(@RequestBody Match match) {
        return ResponseEntity.ok(0);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateMatch(@PathVariable int matchId, @RequestBody Match match) {
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMatch(@PathVariable int matchId) {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<Match>> getAllMatchesByStatus(@PathVariable String status) {
        return ResponseEntity.ok(new ArrayList<>());
    }
}
