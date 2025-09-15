package com.wecp.progressive.repository;

import com.wecp.progressive.entity.Team;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends JpaRepository<Team, Integer> {

    Optional<Team> findByTeamId(int teamId);
    Optional<Team> findByTeamName(String teamName);
}
