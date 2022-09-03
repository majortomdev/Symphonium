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
    private String apikey = "3a175000-2890-11ed-a522-0949cf027ab6";

    @GetMapping("/hello")
    public String hello () {
        return "hello motherTruckers!!";
    }

    @GetMapping("/bye")
    public String byee () {
        return "see ya later bro!!";
    }


    @GetMapping("/soccer/countries")
    public List<Country> getCountries() throws IOException {

        URL url = new URL("https://app.sportdataapi.com/api/v1/soccer/countries?apikey="+apikey+"&continent=Europe");
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
            StringBuffer response = new StringBuffer();
            while ((dataLine = in.readLine()) != null) {
                response.append(dataLine);
            }
            in.close();
            //List<Country> countries = countryService.getCountryData(response.toString());


//            JSONObject jsonObj = new JSONObject(response.toString());
//            JSONObject dataObj = jsonObj.getJSONObject("data");
//            //jsonObj.g
//            return dataObj.length();






            countries = countryService.getCountryData(response.toString());
            return countries;

            //System.out.println("countries size is:  "+countries.size());
            //System.out.println(response.substring(0,200));








        }


    }



}
