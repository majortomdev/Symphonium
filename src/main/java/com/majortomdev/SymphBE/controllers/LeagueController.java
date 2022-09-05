package com.majortomdev.SymphBE.controllers;

import com.majortomdev.SymphBE.models.League;
import com.majortomdev.SymphBE.service.LeagueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

@RestController
public class LeagueController {

    @Autowired
    private LeagueService leagueService;

    private final String apikey = "3a175000-2890-11ed-a522-0949cf027ab6";


    @GetMapping("/soccer/leagues")
    public List<League> getLeaguesByCountry(@RequestParam int countryId) throws IOException {

        URL url = new URL("https://app.sportdataapi.com/api/v1/soccer/leagues?apikey="
                + apikey + "&country_id=" + countryId);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.connect();
        List<League> leagues;
        int responseCode = conn.getResponseCode();
        if (responseCode != 200) {
            throw new RuntimeException("HttpResponseCode: " + responseCode);
        } else {
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String dataLine;
            StringBuilder response = new StringBuilder();
            while ((dataLine = in.readLine()) != null) {
                response.append(dataLine);
            }
            in.close();
            leagues = leagueService.getLeaguesFromCountry(response.toString());
        }
        return leagues;
    }
}
