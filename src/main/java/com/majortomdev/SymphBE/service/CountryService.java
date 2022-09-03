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

        dataObj.keySet().forEach( key -> {
            if(key == "3"){
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
}

