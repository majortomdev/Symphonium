package com.majortomdev.SymphBE.service;

import com.majortomdev.SymphBE.models.Country;
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
            if(Objects.equals(key, "3")){//got a redundant obj for europe
                return; //WHO KNEW ??????????
            }
            JSONObject insideObj = dataObj.getJSONObject(key); //SOLUTION !!!!!!!
            Object countryId = insideObj.get("country_id");
            String name = (String)insideObj.get("name");
            Object cCode = insideObj.get("country_code");
            String countryCode;
            if(!JSONObject.NULL.equals(cCode)){ //NOT TO MENTION THIS
                 countryCode = (String) cCode;  //LITTLE DOOZY....NPE SAVIOUR
            }else countryCode = "";

            String continent = (String)insideObj.get("continent");
            countries.add(new Country((int)countryId,name,countryCode,continent));
        });

        return countries;
    }

    public Country getCountryById(String jsonCountryString) {
        JSONObject jsonCountry = new JSONObject(jsonCountryString);
        JSONObject countryInfo = jsonCountry.getJSONObject("data");

        Object countryId = countryInfo.get("country_id");
        String name = (String)countryInfo.get("name");
        Object cCode = countryInfo.get("country_code");
        String continent = (String) countryInfo.get("continent");

        return new Country((int)countryId,name,(String)cCode,continent);
    }
}

