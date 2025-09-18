package com.wecp.progressive.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wecp.progressive.controller.VoteController;
import com.wecp.progressive.entity.Vote;
import com.wecp.progressive.repository.VoteRepository;
import com.wecp.progressive.service.VoteService;

@Service
public class VoteServiceImpl implements VoteService {

    @Autowired
    VoteRepository voteRepository;

    public List<Vote> getAllVotes()
    {
        return voteRepository.findAll();
    }

    public int createVote(Vote vote)
    {
        Vote createdVote = voteRepository.save(vote);
        return createdVote.getVoteId();
    }

    public Map<String,Long> getVotesCountOfAllCategories()
    {
        Map<String, Long> myMap = new HashMap<>();
        
        List<String> categories = List.of("Team", "Batsman", "Bowler" , "All-rounder" , "Wicketkeeper"); 
        
        for (String category : categories) {
            Long count = voteRepository.countByCategory(category);
            myMap.put(category, count);
        }

        return myMap;
    }

}