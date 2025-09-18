package com.wecp.progressive.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.wecp.progressive.entity.TicketBooking;

@Repository
public interface TicketBookingRepository extends JpaRepository<TicketBooking, Integer>{

    List<TicketBooking> findByEmail(String email);

    @Modifying
    @Transactional
    @Query("DELETE FROM TicketBooking t WHERE t.match.matchId IN (SELECT m.matchId FROM matches m WHERE m.firstTeam.teamId = :teamId OR m.secondTeam.teamId = :teamId)")
    void deleteByTeamId(@Param("teamId") int teamId);
    
    @Modifying
    @Transactional
    @Query("DELETE FROM TicketBooking t WHERE t.match.matchId IN :matchId")
    void deleteByMatchId(@Param("matchId") int matchId);
}