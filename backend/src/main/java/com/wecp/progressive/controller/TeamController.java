package com.wecp.progressive.controller;

import com.wecp.progressive.entity.Team;
import com.wecp.progressive.service.TeamService;
import com.wecp.progressive.service.impl.TeamServiceImplArraylist;
import com.wecp.progressive.service.impl.TeamServiceImplJpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/team")
public class TeamController {

    @Autowired
    private TeamServiceImplJpa teamServiceImplJpa;

    @Autowired
    private TeamServiceImplArraylist teamArrayListService;


    @GetMapping
    public ResponseEntity<List<Team>> getAllTeams() throws SQLException {
        return ResponseEntity.ok(teamServiceImplJpa.getAllTeams());
    }

    @GetMapping("/{teamId}")
    public ResponseEntity<Team> getTeamById( @PathVariable int teamId) throws SQLException{
        Team team = teamServiceImplJpa.getTeamById(teamId);
        return team !=null ? ResponseEntity.ok(team): ResponseEntity.noContent().build();
    }

   
    @PostMapping
    public ResponseEntity<Integer> addTeam(@RequestBody Team team) {
       try {
        int teamId = teamServiceImplJpa.addTeam(team);
        return new ResponseEntity<>(teamId,HttpStatus.CREATED);
       }
       catch(SQLException e)
       {
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
       }
   
   
    }

    @PutMapping("/{teamId}")
    public ResponseEntity<Void> updateTeam( @PathVariable int teamId, @RequestBody Team team) throws SQLException {
        team.setTeamId(teamId);
        teamServiceImplJpa.updateTeam(team);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{teamId}")
    public ResponseEntity<Void> deleteTeam( @PathVariable int teamId) throws SQLException{
        teamServiceImplJpa.deleteTeam(teamId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/fromArrayList")
    public ResponseEntity<List<Team>> getAllTeamsFromArrayList() throws SQLException {
        return ResponseEntity.ok(teamArrayListService.getAllTeams());
    }

    @GetMapping("/fromArrayList/sorted")
    public ResponseEntity<List<Team>> getAllTeamsSortedByNameFromArrayList() throws SQLException{
        return ResponseEntity.ok(teamArrayListService.getAllTeamsSortedByName());
    }

    @PostMapping("/toArrayList")
    public ResponseEntity<Integer> addTeamToArrayList(@RequestBody Team team) throws SQLException {
        return ResponseEntity.status(201).body(teamArrayListService.addTeam(team));
    }
}
