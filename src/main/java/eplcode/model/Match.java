package eplcode.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;


@Entity
public class Match {

    @Id
    @Column(name = "match_ID")
    private long matchID;

    private String season;
    private int matchWeek;
    private LocalDate date;
    private String homeTeam;
    private String awayTeam;
    private int fullTimeHomeTeamGoals;
    private int fullTimeAwayTeamGoals;
    private String fullTimeResult;

    public Match() {
    }

    public Match(String homeTeam, String awayTeam) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
    }


    public long getMatchID() {
        return matchID;
    }

    public void setMatchID(long matchID) {
        this.matchID = matchID;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public int getMatchWeek() {
        return matchWeek;
    }

    public void setMatchWeek(int matchWeek) {
        this.matchWeek = matchWeek;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(String homeTeam) {
        this.homeTeam = homeTeam;
    }

    public String getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(String awayTeam) {
        this.awayTeam = awayTeam;
    }

    public int getFullTimeHomeTeamGoals() {
        return fullTimeHomeTeamGoals;
    }

    public void setFullTimeHomeTeamGoals(int fullTimeHomeTeamGoals) {
        this.fullTimeHomeTeamGoals = fullTimeHomeTeamGoals;
    }

    public int getFullTimeAwayTeamGoals() {
        return fullTimeAwayTeamGoals;
    }

    public void setFullTimeAwayTeamGoals(int fullTimeAwayTeamGoals) {
        this.fullTimeAwayTeamGoals = fullTimeAwayTeamGoals;
    }

    public String getFullTimeResult() {
        return fullTimeResult;
    }

    public void setFullTimeResult(String fullTimeResult) {
        this.fullTimeResult = fullTimeResult;
    }



}
