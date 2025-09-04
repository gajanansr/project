package com.wecp.progressive.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "matches")
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int matchId;
    private int firstTeamId;
    private int secondTeamId;
    private Date matchDate;
    private String venue;
    private String result;
    private String status;
    private int winnerTeamId;
    public Match() {
    }
    public Match(int matchId, int firstTeamId, int secondTeamId, Date matchDate, String venue, String result,
            String status, int winnerTeamId) {
        this.matchId = matchId;
        this.firstTeamId = firstTeamId;
        this.secondTeamId = secondTeamId;
        this.matchDate = matchDate;
        this.venue = venue;
        this.result = result;
        this.status = status;
        this.winnerTeamId = winnerTeamId;
    }
    public int getMatchId() {
        return matchId;
    }
    public void setMatchId(int matchId) {
        this.matchId = matchId;
    }
    public int getFirstTeamId() {
        return firstTeamId;
    }
    public void setFirstTeamId(int firstTeamId) {
        this.firstTeamId = firstTeamId;
    }
    public int getSecondTeamId() {
        return secondTeamId;
    }
    public void setSecondTeamId(int secondTeamId) {
        this.secondTeamId = secondTeamId;
    }
    public Date getMatchDate() {
        return matchDate;
    }
    public void setMatchDate(Date matchDate) {
        this.matchDate = matchDate;
    }
    public String getVenue() {
        return venue;
    }
    public void setVenue(String venue) {
        this.venue = venue;
    }
    public String getResult() {
        return result;
    }
    public void setResult(String result) {
        this.result = result;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public int getWinnerTeamId() {
        return winnerTeamId;
    }
    public void setWinnerTeamId(int winnerTeamId) {
        this.winnerTeamId = winnerTeamId;
    }
    @Override
    public String toString() {
        return "Match [matchId=" + matchId + ", firstTeamId=" + firstTeamId + ", secondTeamId=" + secondTeamId
                + ", matchDate=" + matchDate + ", venue=" + venue + ", result=" + result + ", status=" + status
                + ", winnerTeamId=" + winnerTeamId + "]";
    }


    
    
}
