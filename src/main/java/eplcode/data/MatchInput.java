package eplcode.data;

public class MatchInput {
    private long MatchID;
    private String Season_End_Year;
    private String Date;
    private int Wk;
    private String Home;
    private int HomeGoals;
    private int awayGoals;
    private String Away;
    private String FTR;

    public long getMatchID() {
        return MatchID;
    }

    public void setMatchID(long matchID) {
        MatchID = matchID;
    }

    public String getSeason_End_Year() {
        return Season_End_Year;
    }

    public void setSeason_End_Year(String season_End_Year) {
        Season_End_Year = season_End_Year;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public int getWk() {
        return Wk;
    }

    public void setWk(int wk) {
        Wk = wk;
    }

    public String getHome() {
        return Home;
    }

    public void setHome(String home) {
        Home = home;
    }

    public int getHomeGoals() {
        return HomeGoals;
    }

    public void setHomeGoals(int homeGoals) {
        HomeGoals = homeGoals;
    }

    public int getAwayGoals() {
        return awayGoals;
    }

    public void setAwayGoals(int awayGoals) {
        this.awayGoals = awayGoals;
    }

    public String getAway() {
        return Away;
    }

    public void setAway(String away) {
        Away = away;
    }

    public String getFTR() {
        return FTR;
    }

    public void setFTR(String FTR) {
        this.FTR = FTR;
    }
}