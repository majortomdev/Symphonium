package com.majortomdev.SymphBE.controllers;

import com.majortomdev.SymphBE.models.Season;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class SeasonsService {

    public List<Season> getSeasonsForLeague(String jsonSeasonsString) throws ParseException {
        List<Season> seasons = new ArrayList<>();
        JSONObject jsonObj = new JSONObject(jsonSeasonsString);
        JSONArray seasonsArray = jsonObj.getJSONArray("data");

        for (int i = 0; i < seasonsArray.length(); i++) {
            JSONObject jsonSeason = seasonsArray.getJSONObject(i);
            Object seasonId = jsonSeason.get("season_id");
            String name = (String) jsonSeason.get("name");
            Object isCurr = jsonSeason.get("is_current");
            boolean isCurrent = false;
            if ((int) isCurr == 1) {
                isCurrent = true;
            }
            Object countryId = jsonSeason.get("country_id");
            Object leagueId = jsonSeason.get("league_id");
            Date startDate = createDateObj((String) jsonSeason.get("start_date"));
            Date endDate = createDateObj((String) jsonSeason.get("end_date"));

            seasons.add(new Season((int)seasonId, name, isCurrent,(int)countryId,
                    (int)leagueId,startDate,endDate));

        }
        return seasons;
    }

    private Date createDateObj(String strDate) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return simpleDateFormat.parse(strDate);
    }

}