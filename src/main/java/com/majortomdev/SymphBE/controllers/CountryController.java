package com.majortomdev.SymphBE.controllers;

import com.majortomdev.SymphBE.models.Country;
import com.majortomdev.SymphBE.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@RestController
public class CountryController {

    @Autowired
    private CountryService countryService;
    private final String apikey = "3a175000-2890-11ed-a522-0949cf027ab6";

    @GetMapping("/hello")
    public String hello () {
        return "hello motherTruckers!!";
    }

    @GetMapping("/bye")
    public String byee () {
        return "see ya later bro!!";
    }


    @GetMapping("/soccer/countries")
    public List<Country> getCountries(@RequestParam String continent) throws IOException {
        //test for validity of continent param???
        URL url = new URL("https://app.sportdataapi.com/api/v1/soccer/countries?apikey="+apikey+"&continent="+continent);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.connect();
        List<Country> countries= new ArrayList<Country>();
        int responseCode = conn.getResponseCode();
        if(responseCode !=200){
            throw new RuntimeException("HttpResponseCode: "+responseCode);
        }else {
            System.out.println("Successfully hit the endpoint!!!!!");
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String dataLine;
            StringBuilder response = new StringBuilder();
            while ((dataLine = in.readLine()) != null) {
                response.append(dataLine);
            }
            in.close();

            countries = countryService.getCountryData(response.toString());
            return countries;



        }


    }



}
