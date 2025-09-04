package com.wecp.progressive.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wecp.progressive.entity.Match;

@Repository
public interface MatchRepository extends JpaRepository<Match, Integer> {
    // List<Match> findByStatus(String status);
}
