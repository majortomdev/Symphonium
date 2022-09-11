package com.majortomdev.SymphBE.controllers;

import com.majortomdev.SymphBE.models.Player;
import com.majortomdev.SymphBE.service.PlayerService;
import com.majortomdev.SymphBE.service.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.util.List;

@RestController
public class PlayerController {


    @Autowired
    private PlayerService playerService;
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


}
