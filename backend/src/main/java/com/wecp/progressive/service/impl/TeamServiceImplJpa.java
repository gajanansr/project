package com.wecp.progressive.service.impl;
 
import java.sql.SQLException;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wecp.progressive.dao.TeamDAO;
import com.wecp.progressive.entity.Team;
import com.wecp.progressive.exception.TeamAlreadyExistsException;
import com.wecp.progressive.exception.TeamDoesNotExistException;
import com.wecp.progressive.repository.CricketerRepository;
import com.wecp.progressive.repository.MatchRepository;
import com.wecp.progressive.repository.TeamRepository;
import com.wecp.progressive.repository.TicketBookingRepository;
import com.wecp.progressive.repository.VoteRepository;
import com.wecp.progressive.service.TeamService;
 
@Service
public class TeamServiceImplJpa  implements TeamService {

    private TeamRepository teamRepository;

    @Autowired
    private CricketerRepository cricketerRepository;

    @Autowired
    private MatchRepository matchRepository;

    @Autowired
    private VoteRepository voteRepository;

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
    public int addTeam(Team team) throws SQLException {

        if(teamRepository.findByTeamName(team.getTeamName()) != null){
            throw new TeamAlreadyExistsException("Team with same name exists");
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
    public Team getTeamById(int teamId) throws SQLException {
        if(teamRepository.findByTeamId(teamId) == null){
            throw new TeamDoesNotExistException("Team does not exist");
        }
        return teamRepository.findByTeamId(teamId);
    }

    @Override
    public void updateTeam(Team team) throws SQLException {
        if(teamRepository.findByTeamName(team.getTeamName()) != null){
            throw new TeamAlreadyExistsException("Team with same name exists");
        }
        teamRepository.save(team);
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