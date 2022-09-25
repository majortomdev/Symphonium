package com.majortomdev.SymphBE.controllers;

import com.majortomdev.SymphBE.models.Player;
import com.majortomdev.SymphBE.models.PlrScoreRecord;
import com.majortomdev.SymphBE.service.GoalsService;
import com.majortomdev.SymphBE.service.PlayerService;
import com.majortomdev.SymphBE.service.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class PlayerController {


    @Autowired
    private PlayerService playerService;
    @Autowired
    private GoalsService goalsService;
    @Autowired
    private Util util;

    private final String apikey = "3a175000-2890-11ed-a522-0949cf027ab6";

    @GetMapping("/soccer/players")
    public List<Player> getPlayersByCountry(@RequestParam int countryId,
                                            @RequestParam(required = false) Integer minAge,
                                            @RequestParam(required = false) Integer maxAge) throws IOException, ParseException {
        if(maxAge == null) maxAge = 55;//REQUIRED=FALSE was the ticket!!!!
        if(minAge == null) minAge = 15;// ..WITH these safeguards to rotect the params before they are called
        URL url = new URL("https://app.sportdataapi.com/api/v1/soccer/players?apikey="+apikey+
                "&country_id="+countryId+"&min_age="+minAge+"&max_age="+maxAge);//MAX_age. MIN_age are optional!!!!
        String playersAsString = util.urlToString(url);
        return playerService.getPlayersByCountry(playersAsString);
    }

    @GetMapping("/soccer/players/{id}")
    public Player getPlayerById(@PathVariable(value = "id") int id) throws IOException, ParseException {
        URL url = new URL("https://app.sportdataapi.com/api/v1/soccer/players/"+id+"?apikey="+apikey);
        String playerString = util.urlToString(url);
        return playerService.getPlayerById(playerString);
    }

//    @GetMapping("/soccer/playerswithclub")
//    public List<Player> getPlayersAtclub(@RequestParam int countryId,
//                                         @RequestParam int teamId) throws IOException, ParseException {
//        URL url = new URL("https://app.sportdataapi.com/api/v1/soccer/players?apikey="+apikey+"&countryId="+countryId);
//        String playerString = util.urlToString(url);
//
//        return playerService.getPlayersByClub(playerString, teamId);
//    }

    @GetMapping("/soccer/topscorers")
    public List<PlrScoreRecord> getPlayerGoalsScoredForSeason(@RequestParam int seasonId) throws IOException{
        URL url = new URL("https://app.sportdataapi.com/api/v1/soccer/topscorers?apikey="+apikey+"&season_id="+seasonId);
        String goalsScoredString = util.urlToString(url);
        return goalsService.getSeasonGoals(goalsScoredString);
    }

    @GetMapping("/soccer/topscorersbyteam")
    public List<PlrScoreRecord> getTeamGoalsScoredForSeason(@RequestParam int seasonId, @RequestParam int teamId) throws IOException{
        URL url = new URL("https://app.sportdataapi.com/api/v1/soccer/topscorers?apikey="+apikey+"&season_id="+seasonId);
        String goalsScoredString = util.urlToString(url);
        return goalsService.getSeasonTeamGoals(goalsScoredString,teamId);
    }

}
