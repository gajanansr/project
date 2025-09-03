package com.wecp.progressive.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wecp.progressive.entity.Team;

public interface TeamRepository extends JpaRepository<Team, Integer> {
    public Team findByTeamId(int teamId);
}
