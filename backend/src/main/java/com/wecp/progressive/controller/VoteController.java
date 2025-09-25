package com.wecp.progressive.controller;

import com.wecp.progressive.entity.Vote;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public class VoteController {

    public ResponseEntity<List<Vote>> getAllVotes() {
        return null;
    }

    public ResponseEntity<Integer> createVote(Vote vote) {
        return null;
    }

    // Each key (k) represents a category (categories - “Team”, “Batsman”, “Bowler”, “All-rounder” and “Wicketkeeper”)
    // and each value (v) represents the total number of votes for that category.
    public ResponseEntity<Map<String, Long>> getVotesCountOfAllCategories() {
        return null;
    }
}
