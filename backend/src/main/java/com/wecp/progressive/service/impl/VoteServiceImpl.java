package com.wecp.progressive.service.impl;

import com.wecp.progressive.entity.Vote;
import com.wecp.progressive.repository.VoteRepository;
import com.wecp.progressive.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class VoteServiceImpl implements VoteService {

    @Autowired
    VoteRepository voteRepository;

    @Override
    public List<Vote> getAllVotes() {
        return voteRepository.findAll();
    }

    @Override
    public int createVote(Vote vote) {
        return voteRepository.save(vote).getVoteId();
    }

    @Override
    public Map<String, Long> getVotesCountOfAllCategories() {
        Map<String, Long> countsMap = new HashMap<>();
        List<String> categories = List.of("Team", "Batsman", "Bowler", "All-rounder", "Wicketkeeper");
        for (String category : categories) {
            Long count = voteRepository.countByCategory(category);
            countsMap.put(category, count);
        }
        return countsMap;
    }

}
