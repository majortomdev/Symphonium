package com.majortomdev.SymphBE.models;

public class Team {

    int teamId;
    String name;
    String shortCode;
    String commonName;
    Country country;

    public Team(int teamId, String name, String shortCode, String commonName, Country country) {
        this.teamId = teamId;
        this.name = name;
        this.shortCode = shortCode;
        this.commonName = commonName;
        this.country = country;
    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortCode() {
        return shortCode;
    }

    public void setShortCode(String shortCode) {
        this.shortCode = shortCode;
    }

    public String getCommonName() {
        return commonName;
    }

    public void setCommonName(String commonName) {
        this.commonName = commonName;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}
