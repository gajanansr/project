package com.wecp.progressive.service.impl;

import com.wecp.progressive.entity.Cricketer;
import com.wecp.progressive.exception.TeamCricketerLimitExceededException;
import com.wecp.progressive.repository.CricketerRepository;
import com.wecp.progressive.repository.VoteRepository;
import com.wecp.progressive.service.CricketerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Comparator;
import java.util.List;

@Service
public class CricketerServiceImplJpa implements CricketerService {

    private CricketerRepository cricketerRepository;

    @Autowired
    VoteRepository voteRepository;

    @Autowired
    public CricketerServiceImplJpa(CricketerRepository cricketerRepository) {
        this.cricketerRepository = cricketerRepository;
    }

    @Override
    public List<Cricketer> getAllCricketers() throws SQLException {
        return cricketerRepository.findAll();
    }

    @Override
    public Integer addCricketer(Cricketer cricketer) throws TeamCricketerLimitExceededException {
        Long totalCricketersInTeam = cricketerRepository.countByTeam_TeamId(cricketer.getTeam().getTeamId());
        if (totalCricketersInTeam == 11) {
            throw new TeamCricketerLimitExceededException("Team = " + cricketer.getTeam().getTeamId() + " already has reached its maximum limit of 11 cricketers");
        }
        return cricketerRepository.save(cricketer).getCricketerId();
    }

    @Override
    public List<Cricketer> getAllCricketersSortedByExperience() throws SQLException {
        List<Cricketer> sortedCricketer = cricketerRepository.findAll();
        sortedCricketer.sort(Comparator.comparing(Cricketer::getExperience));
        return sortedCricketer;
    }

    @Override
    public void updateCricketer(Cricketer cricketer) throws SQLException {
        cricketerRepository.save(cricketer);
    }

    @Override
    public void deleteCricketer(int cricketerId) throws SQLException {
        voteRepository.deleteByCricketerId(cricketerId);
        cricketerRepository.deleteById(cricketerId);
    }

    @Override
    public Cricketer getCricketerById(int cricketerId) throws SQLException {
        return cricketerRepository.findByCricketerId(cricketerId);
    }

    @Override
    public List<Cricketer> getCricketersByTeam(int teamId) throws SQLException {
        return cricketerRepository.findByTeam_TeamId(teamId);
    }
}
