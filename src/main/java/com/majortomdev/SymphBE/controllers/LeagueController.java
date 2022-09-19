package com.majortomdev.SymphBE.controllers;

import com.majortomdev.SymphBE.models.League;
import com.majortomdev.SymphBE.models.Season;
import com.majortomdev.SymphBE.models.Standing;
import com.majortomdev.SymphBE.models.TeamResult;
import com.majortomdev.SymphBE.service.LeagueService;
import com.majortomdev.SymphBE.service.SeasonsService;
import com.majortomdev.SymphBE.service.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class LeagueController {

    @Autowired
    private LeagueService leagueService;

    @Autowired
    private SeasonsService seasonsService;

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

    @GetMapping("/soccer/seasons")
    public List<Season> getSeasons(@RequestParam int leagueId) throws IOException, ParseException {
        URL url = new URL("https://app.sportdataapi.com/api/v1/soccer/seasons?apikey="+apikey+"&league_id="+leagueId);
        String seasonsString = util.urlToString(url);
        return seasonsService.getSeasonsForLeague(seasonsString);
    }

    @GetMapping("/soccer/season/{id}")
    public Season getSeasonById(@PathVariable(value = "id") int id) throws IOException, ParseException {
        URL url = new URL("https://app.sportdataapi.com/api/v1/soccer/seasons/"+id+"?apikey="+apikey);
        String seasonString = util.urlToString(url);
        return seasonsService.getSeasonById(seasonString);
    }

//    @GetMapping("/soccer/standings") //i could mkae this a diff url to below, wud hit the same EP and then do whatever different processing i may need with the list of Standing objects that im building from it
//    public List<Standing> getLeagueStandingsForSeason(@RequestParam int seasonId) throws IOException{
//        URL url = new URL("https://app.sportdataapi.com/api/v1/soccer/standings?apikey="+apikey+"&season_id="+seasonId);
//        String seasonsStandings = util.urlToString(url);
//        return seasonsService.getStandingsForSeason(seasonsStandings);
//    }

    @GetMapping("/soccer/standings/{seasonId}")
    public List<TeamResult> getLeagueStandingsDisplayForSeason(@PathVariable (value = "seasonId")int seasonId) throws IOException{
        URL url = new URL("https://app.sportdataapi.com/api/v1/soccer/standings?apikey="+apikey+"&season_id="+seasonId);
        String teamStandings = util.urlToString(url);
        List<TeamResult> seasonsStandings = seasonsService.getSeasonOverlookDisplay(teamStandings);
        return seasonsStandings;
    }

}
