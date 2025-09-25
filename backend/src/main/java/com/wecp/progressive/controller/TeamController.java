package com.wecp.progressive.controller;

import com.wecp.progressive.entity.Team;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class TeamController {
    public ResponseEntity<List<Team>> getAllTeams() {
        return null;
    }

    public ResponseEntity<Team> getTeamById(int teamId) {
        return null;
    }

    public ResponseEntity<Integer> addTeam(Team team) {
        return null;
    }

    public ResponseEntity<Void> updateTeam(int teamId, Team team) {
        return null;
    }

    public ResponseEntity<Void> deleteTeam(int teamId) {
        return null;
    }

    public ResponseEntity<List<Team>> getAllTeamsFromArrayList() {
        return null;
    }

    public ResponseEntity<Integer> addTeamToArrayList(Team team) {
        return null;
    }

    public ResponseEntity<List<Team>> getAllTeamsSortedByNameFromArrayList() {
        return null;
    }
}
