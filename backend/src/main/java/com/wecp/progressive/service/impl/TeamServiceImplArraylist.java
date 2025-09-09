package com.wecp.progressive.service.impl;

import java.util.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

import com.wecp.progressive.entity.Team;
import com.wecp.progressive.service.TeamService;

@Service
public class TeamServiceImplArraylist implements TeamService {
    private static List<Team> teams = new ArrayList<>();
    @Override
    public List<Team> getAllTeams() {
        return teams;
    }

    @Override
    public int addTeam(Team team) {
    teams.add(team);
    return teams.size();
    }

    @Override
    public List<Team> getAllTeamsSortedByName() {
       List<Team> sortedList = teams;
    //    sortedList.sort(Comparator.comparing(Team::getTeamName));
        Collections.sort(sortedList);
       return sortedList;
    }

    @Override
    public void emptyArrayList(){
        teams=new ArrayList<>();
    }
}
