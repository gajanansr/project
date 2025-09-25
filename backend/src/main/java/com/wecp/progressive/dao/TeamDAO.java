package com.wecp.progressive.dao;

import com.wecp.progressive.entity.Team;

import java.util.List;

public interface TeamDAO {
    int addTeam(Team team);
    Team getTeamById(int teamId);
    void updateTeam(Team team);
    void deleteTeam(int teamId) ;
    List<Team> getAllTeams();
}
