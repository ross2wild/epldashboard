package eplcode.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class Match {

    @Id
    @Column(name = "match_ID")
    private String matchID;

    private String season;
    private int matchWeek;
    private LocalDate date;
    private LocalTime time;
    private String homeTeam;
    private String awayTeam;
    private int fullTimeHomeTeamGoals;
    private int fullTimeAwayTeamGoals;
    private String fullTimeResult;
    private int halfTimeHomeTeamGoals;
    private int halfTimeAwayTeamGoals;
    private String halfTimeResult;
    private String referee;
    private int homeTeamShots;
    private int awayTeamShots;
    private int homeTeamShotsOnTarget;
    private int awayTeamShotsOnTarget;
    private int homeTeamCorners;
    private int awayTeamCorners;
    private int homeTeamFouls;
    private int awayTeamFouls;
    private int homeTeamYellowCards;
    private int awayTeamYellowCards;
    private int homeTeamRedCards;
    private int awayTeamRedCards;
    private int homeTeamPoints;
    private int awayTeamPoints;

    public Match() {
    }

    public Match(String homeTeam, String awayTeam) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
    }


    public String getMatchID() {
        return matchID;
    }

    public void setMatchID(String matchID) {
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

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
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

    public int getHalfTimeHomeTeamGoals() {
        return halfTimeHomeTeamGoals;
    }

    public void setHalfTimeHomeTeamGoals(int halfTimeHomeTeamGoals) {
        this.halfTimeHomeTeamGoals = halfTimeHomeTeamGoals;
    }

    public int getHalfTimeAwayTeamGoals() {
        return halfTimeAwayTeamGoals;
    }

    public void setHalfTimeAwayTeamGoals(int halfTimeAwayTeamGoals) {
        this.halfTimeAwayTeamGoals = halfTimeAwayTeamGoals;
    }

    public String getHalfTimeResult() {
        return halfTimeResult;
    }

    public void setHalfTimeResult(String halfTimeResult) {
        this.halfTimeResult = halfTimeResult;
    }

    public String getReferee() {
        return referee;
    }

    public void setReferee(String referee) {
        this.referee = referee;
    }

    public int getHomeTeamShots() {
        return homeTeamShots;
    }

    public void setHomeTeamShots(int homeTeamShots) {
        this.homeTeamShots = homeTeamShots;
    }

    public int getAwayTeamShots() {
        return awayTeamShots;
    }

    public void setAwayTeamShots(int awayTeamShots) {
        this.awayTeamShots = awayTeamShots;
    }

    public int getHomeTeamShotsOnTarget() {
        return homeTeamShotsOnTarget;
    }

    public void setHomeTeamShotsOnTarget(int homeTeamShotsOnTarget) {
        this.homeTeamShotsOnTarget = homeTeamShotsOnTarget;
    }

    public int getAwayTeamShotsOnTarget() {
        return awayTeamShotsOnTarget;
    }

    public void setAwayTeamShotsOnTarget(int awayTeamShotsOnTarget) {
        this.awayTeamShotsOnTarget = awayTeamShotsOnTarget;
    }

    public int getHomeTeamCorners() {
        return homeTeamCorners;
    }

    public void setHomeTeamCorners(int homeTeamCorners) {
        this.homeTeamCorners = homeTeamCorners;
    }

    public int getAwayTeamCorners() {
        return awayTeamCorners;
    }

    public void setAwayTeamCorners(int awayTeamCorners) {
        this.awayTeamCorners = awayTeamCorners;
    }

    public int getHomeTeamFouls() {
        return homeTeamFouls;
    }

    public void setHomeTeamFouls(int homeTeamFouls) {
        this.homeTeamFouls = homeTeamFouls;
    }

    public int getAwayTeamFouls() {
        return awayTeamFouls;
    }

    public void setAwayTeamFouls(int awayTeamFouls) {
        this.awayTeamFouls = awayTeamFouls;
    }

    public int getHomeTeamYellowCards() {
        return homeTeamYellowCards;
    }

    public void setHomeTeamYellowCards(int homeTeamYellowCards) {
        this.homeTeamYellowCards = homeTeamYellowCards;
    }

    public int getAwayTeamYellowCards() {
        return awayTeamYellowCards;
    }

    public void setAwayTeamYellowCards(int awayTeamYellowCards) {
        this.awayTeamYellowCards = awayTeamYellowCards;
    }

    public int getHomeTeamRedCards() {
        return homeTeamRedCards;
    }

    public void setHomeTeamRedCards(int homeTeamRedCards) {
        this.homeTeamRedCards = homeTeamRedCards;
    }

    public int getAwayTeamRedCards() {
        return awayTeamRedCards;
    }

    public void setAwayTeamRedCards(int awayTeamRedCards) {
        this.awayTeamRedCards = awayTeamRedCards;
    }

    public int getHomeTeamPoints() {
        return homeTeamPoints;
    }

    public void setHomeTeamPoints(int homeTeamPoints) {
        this.homeTeamPoints = homeTeamPoints;
    }

    public int getAwayTeamPoints() {
        return awayTeamPoints;
    }

    public void setAwayTeamPoints(int awayTeamPoints) {
        this.awayTeamPoints = awayTeamPoints;
    }
}
