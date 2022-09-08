package com.majortomdev.SymphBE.controllers;

import com.majortomdev.SymphBE.models.Country;
import com.majortomdev.SymphBE.models.Team;
import com.majortomdev.SymphBE.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@RestController
public class CountryController {

    @Autowired
    private CountryService countryService;
    @Autowired
    private Util util;

    private final String apikey = "3a175000-2890-11ed-a522-0949cf027ab6";

    @GetMapping("/hello")
    public String hello () {
        return "hello motherTruckers!!";
    }

    @GetMapping("/bye")
    public String byee () {
        return "see ya later bro!!";
    }

    private List<Country> countries= new ArrayList<>();

    @GetMapping("/soccer/countries")
    public List<Country> getCountries(@RequestParam String continent) throws IOException {
        //test for validity of continent param???
        URL url = new URL("https://app.sportdataapi.com/api/v1/soccer/countries?apikey="+apikey+"&continent="+continent);
        String countriesString = util.urlToString(url);
        return countryService.getCountryData(countriesString);
    }

    @GetMapping("/soccer/country/{id}")
    public Country getCountryById(@PathVariable(value = "id") int id) throws IOException {
        URL url = new URL("https://app.sportdataapi.com/api/v1/soccer/countries/"+id+"?apikey="+apikey);
        String countryString = util.urlToString(url);
        return countryService.getCountryById(countryString);
    }

    @GetMapping("/soccer/teams")
    public List<Team> getTeams(@RequestParam int countryId) throws IOException {
        URL url = new URL("https://app.sportdataapi.com/api/v1/soccer/teams?apikey="+apikey+"&country_id="+countryId);
        String teamsString = util.urlToString(url);
        return countryService.getTeamsInCountry(teamsString);
    }

    @GetMapping("/soccer/team/{id}")
    public Team getTeamById(@PathVariable(value = "id") int id) throws IOException {
        URL url = new URL("https://app.sportdataapi.com/api/v1/soccer/teams/"+id+"?apikey="+apikey);
        String teamString = util.urlToString(url);
        return countryService.getTeamById(teamString);
    }

}
