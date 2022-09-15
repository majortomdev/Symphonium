package com.majortomdev.SymphBE.models;

public class PlayerSsnGoals {

    private int rank;
    private int playerId;
    private int teamId;
    private int leagueId;
    private int seasonId;
    private int matchesPlayed;

    private int minutesPlayed;
    private int sustitutedIn;
    private int goalsHome;
    private int goalsAway;

    public PlayerSsnGoals(int ranking, int playerId, int teamId, int leagueId, int seasonId,
                          int matchesPlayed, int minutesPlayed, int sustitutedIn, int goalsHome, int goalsAway) {
        this.rank = ranking;
        this.playerId = playerId;
        this.teamId = teamId;
        this.leagueId = leagueId;
        this.seasonId = seasonId;
        this.matchesPlayed = matchesPlayed;
        this.minutesPlayed = minutesPlayed;
        this.sustitutedIn = sustitutedIn;
        this.goalsHome = goalsHome;
        this.goalsAway = goalsAway;
    }

    public int getGoalsOverall() {
        return goalsHome + goalsAway;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public int getLeagueId() {
        return leagueId;
    }

    public void setLeagueId(int leagueId) {
        this.leagueId = leagueId;
    }

    public int getSeasonId() {
        return seasonId;
    }

    public void setSeasonId(int seasonId) {
        this.seasonId = seasonId;
    }

    public int getMatchesPlayed() {
        return matchesPlayed;
    }

    public void setMatchesPlayed(int matchesPlayed) {
        this.matchesPlayed = matchesPlayed;
    }

    public int getMinutesPlayed() {
        return minutesPlayed;
    }

    public void setMinutesPlayed(int minutesPlayed) {
        this.minutesPlayed = minutesPlayed;
    }

    public int getSustitutedIn() {
        return sustitutedIn;
    }

    public void setSustitutedIn(int sustitutedIn) {
        this.sustitutedIn = sustitutedIn;
    }

    public int getGoalsHome() {
        return goalsHome;
    }

    public void setGoalsHome(int goalsHome) {
        this.goalsHome = goalsHome;
    }

    public int getGoalsAway() {
        return goalsAway;
    }

    public void setGoalsAway(int goalsAway) {
        this.goalsAway = goalsAway;
    }
}
