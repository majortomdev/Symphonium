package com.majortomdev.SymphBE.service;

import com.majortomdev.SymphBE.models.Country;
import com.majortomdev.SymphBE.models.Team;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class CountryService {
    public List<Country> getCountryData(String jsonString){
        List<Country> countries = new ArrayList<>();
        JSONObject jsonObj = new JSONObject(jsonString);
        JSONObject dataObj = jsonObj.getJSONObject("data");

        dataObj.keySet().forEach( key -> {
            if(Objects.equals(key, "3")){
                return;
            }
            JSONObject insideObj = dataObj.getJSONObject(key);
            countries.add(createCountryInst(insideObj));

        });

        return countries;
    }

    public Country getCountryById(String jsonCountryString) {
        JSONObject jsonCountry = new JSONObject(jsonCountryString);
        JSONObject countryJsonObj = jsonCountry.getJSONObject("data");
        return createCountryInst(countryJsonObj);
    }

    public List<Team> getTeamsInCountry(String jsonTeamsString){
        List<Team> teams = new ArrayList<>();
        JSONObject jsonObj = new JSONObject(jsonTeamsString);
        JSONArray teamArray = jsonObj.getJSONArray("data");
        for(int i=0; i<teamArray.length(); i++){
            JSONObject jsonTeam = teamArray.getJSONObject(i);
            teams.add(createTeamInst(jsonTeam));
        }

        return teams;
    }

    public Team getTeamById(String jsonTeamString) {
        JSONObject jsonObject = new JSONObject(jsonTeamString);
        JSONObject teamJson = jsonObject.getJSONObject("data");
        return createTeamInst(teamJson);
    }

    private Team createTeamInst(JSONObject teamInJson){
        Object teamId = teamInJson.get("team_id");
        String name = (String)teamInJson.get("name");
        String shortCode = (String)teamInJson.get("short_code");
        String logo = (String)teamInJson.get("logo");
        Object common = teamInJson.get("common_name");
        String commonName;
        if(!JSONObject.NULL.equals(common)){
            commonName = (String) common;
        }else commonName = "";

        JSONObject countryInner = teamInJson.getJSONObject("country");
        Country country = createCountryInst(countryInner);
        return new Team((int)teamId,name,shortCode,commonName,logo,country);
    }

    public Country createCountryInst(JSONObject jsonCountry){
        Object countryId = jsonCountry.get("country_id");
        String name = (String)jsonCountry.get("name");
        Object cCode = jsonCountry.get("country_code");
        String countryCode;
        if(!JSONObject.NULL.equals(cCode)){
            countryCode = (String) cCode;
        }else countryCode = "";

        String continent = (String) jsonCountry.get("continent");
        return new Country((int)countryId,name,countryCode,continent);
    }
}

