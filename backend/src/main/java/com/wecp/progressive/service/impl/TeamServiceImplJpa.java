package com.wecp.progressive.service.impl;

import com.wecp.progressive.entity.Team;
import com.wecp.progressive.exception.TeamAlreadyExistsException;
import com.wecp.progressive.exception.TeamDoesNotExistException;
import com.wecp.progressive.repository.CricketerRepository;
import com.wecp.progressive.repository.MatchRepository;
import com.wecp.progressive.repository.TeamRepository;
import com.wecp.progressive.repository.TicketBookingRepository;
import com.wecp.progressive.repository.VoteRepository;
import com.wecp.progressive.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class TeamServiceImplJpa  implements TeamService {

    private TeamRepository teamRepository;

    @Autowired
    CricketerRepository cricketerRepository;

    @Autowired
    MatchRepository matchRepository;

    @Autowired
    VoteRepository voteRepository;

    @Autowired
    private TicketBookingRepository ticketBookingRepository;

    @Autowired
    public TeamServiceImplJpa(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @Override
    public List<Team> getAllTeams() throws SQLException {
        return teamRepository.findAll();
    }

    @Override
    public int addTeam(Team team) throws TeamAlreadyExistsException {
        Optional<Team> existingTeam = teamRepository.findByTeamName(team.getTeamName());
        if (existingTeam.isPresent()) {
            throw new TeamAlreadyExistsException("Team with name " + team.getTeamName() + " already exists.");
        }
        return teamRepository.save(team).getTeamId();
    }

    @Override
    public List<Team> getAllTeamsSortedByName() throws SQLException {
        List<Team> sortedTeam = teamRepository.findAll();
        sortedTeam.sort(Comparator.comparing(Team::getTeamName));
        return sortedTeam;
    }

    @Override
    public Team getTeamById(int teamId) throws TeamDoesNotExistException {
        Optional<Team> existingTeam = teamRepository.findByTeamId(teamId);
        if(!existingTeam.isPresent())
        {
            throw new TeamDoesNotExistException("team does not exist");
        }
        return teamRepository.findByTeamId(teamId).get();
    }

    @Override
    public void updateTeam(Team team) throws TeamAlreadyExistsException {
        Optional<Team> existingTeam = teamRepository.findByTeamName(team.getTeamName());
        if(existingTeam.isPresent())
        {
            throw new TeamAlreadyExistsException("team already exists");
        }
        else
        {
            teamRepository.save(team);
        }
    }

    @Override
    public void deleteTeam(int teamId) throws SQLException {
        voteRepository.deleteByTeamId(teamId);
        ticketBookingRepository.deleteByTeamId(teamId);
        matchRepository.deleteByTeamId(teamId);
        cricketerRepository.deleteByTeamId(teamId);
        teamRepository.deleteById(teamId);
    }
}
