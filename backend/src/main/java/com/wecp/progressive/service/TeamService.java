package com.wecp.progressive.service;

import com.wecp.progressive.entity.Team;

import java.util.List;

public interface TeamService {

    List<Team> getAllTeams();

    int addTeam(Team team);

    List<Team> getAllTeamsSortedByName();

    default void emptyArrayList() {
    }

    //Do not implement these methods in TeamServiceImplArrayList.java class
    default Team getTeamById(int teamId) {
        return null;
    }

    default void updateTeam(Team team) {}

    default void deleteTeam(int teamId) {}

}
