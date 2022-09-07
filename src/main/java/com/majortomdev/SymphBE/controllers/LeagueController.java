package com.majortomdev.SymphBE.controllers;

import com.majortomdev.SymphBE.models.League;
import com.majortomdev.SymphBE.service.LeagueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URL;
import java.util.List;

@RestController
public class LeagueController {

    @Autowired
    private LeagueService leagueService;

    @Autowired
    private Util util;

    private final String apikey = "3a175000-2890-11ed-a522-0949cf027ab6";


    @GetMapping("/soccer/leagues")
    public List<League> getLeaguesByCountry(@RequestParam int countryId) throws IOException {

        URL url = new URL("https://app.sportdataapi.com/api/v1/soccer/leagues?apikey="
                + apikey + "&country_id=" + countryId);
        String leaguesString= util.urlToString(url);
        return leagueService.getLeaguesFromCountry(leaguesString);
    }

    @GetMapping("soccer/league/{id}")
    public League getLeagueById(@PathVariable(value = "id") int id) throws IOException {
        URL url = new URL("https://app.sportdataapi.com/api/v1/soccer/leagues/"+id+"?apikey="+apikey);
        String leagueString= util.urlToString(url);
        return leagueService.getLeagueById(leagueString);
    }

}
