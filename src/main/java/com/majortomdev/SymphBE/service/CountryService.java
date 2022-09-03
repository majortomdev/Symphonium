package com.majortomdev.SymphBE.service;

import com.majortomdev.SymphBE.models.Country;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CountryService {
    public List<Country> getCountryData(String jsonString){
        List<Country> countries = new ArrayList<>();
        JSONObject jsonObj = new JSONObject(jsonString);
        JSONObject dataObj = jsonObj.getJSONObject("data");

        System.out.println(dataObj.toString());


        dataObj.keySet().forEach( key -> {

            JSONObject insideObj = dataObj.getInt("");

            String countryId = (String)dataObj.get("country_id");
            String name = (String)dataObj.get("name");
            String countryCode = (String)dataObj.get("country_code");
            String continent = (String)dataObj.get("continent");
            countries.add(new Country(Integer.parseInt(countryId),name,countryCode,continent));
        });

        return countries;
    }
}

