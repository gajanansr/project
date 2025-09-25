package com.wecp.progressive.service.impl;

import com.wecp.progressive.dao.TeamDAO;
import com.wecp.progressive.entity.Team;
import com.wecp.progressive.service.TeamService;

import java.sql.SQLException;
import java.util.Comparator;
import java.util.List;

public class TeamServiceImplJdbc implements TeamService {

    private TeamDAO teamDAO;

    public TeamServiceImplJdbc(TeamDAO teamDAO) {
        this.teamDAO = teamDAO;
    }

    @Override
    public List<Team> getAllTeams() throws SQLException {
        return teamDAO.getAllTeams();
    }

    @Override
    public int addTeam(Team team) throws SQLException {
        return teamDAO.addTeam(team);
    }

    @Override
    public List<Team> getAllTeamsSortedByName() throws SQLException {
        List<Team> sortedTeams = teamDAO.getAllTeams();
        if (!sortedTeams.isEmpty()) {
            sortedTeams.sort(Comparator.comparing(Team::getTeamName));
        }
        return sortedTeams;
    }

    @Override
    public Team getTeamById(int teamId) throws SQLException {
        return teamDAO.getTeamById(teamId);
    }

    @Override
    public void updateTeam(Team team) throws SQLException {
        teamDAO.updateTeam(team);
    }

    @Override
    public void deleteTeam(int teamId) throws SQLException {
        teamDAO.deleteTeam(teamId);
    }
}



