package com.wecp.progressive.repository;

import com.wecp.progressive.entity.Team;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends JpaRepository<Team, Integer> {

    Team findByTeamId(int teamId);
    Team findByTeamName(String teamName);
}