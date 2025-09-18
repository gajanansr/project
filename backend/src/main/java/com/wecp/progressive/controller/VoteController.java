package com.wecp.progressive.controller;
import com.wecp.progressive.entity.Vote;
import com.wecp.progressive.service.impl.VoteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/vote")
public class VoteController {
    @Autowired
    VoteServiceImpl voteService;
    @GetMapping
    public ResponseEntity<List<Vote>> getAllVotes() {
        List<Vote> voteList = voteService.getAllVotes();
        return new ResponseEntity<>(voteList, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<Integer> createVote(@RequestBody Vote vote) {
        int id = voteService.createVote(vote);
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }
    @GetMapping("/count")
    public ResponseEntity<Map<String, Long>> getVotesCountOfAllCategories() {
        Map<String, Long> countMap = voteService.getVotesCountOfAllCategories();
        return new ResponseEntity<>(countMap, HttpStatus.OK);
    }

}
