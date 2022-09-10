package com.majortomdev.SymphBE.service;

import com.majortomdev.SymphBE.models.Season;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class SeasonsService {

    @Autowired
    private Util util;

    public List<Season> getSeasonsForLeague(String jsonSeasonsString) throws ParseException {
        List<Season> seasons = new ArrayList<>();
        JSONObject jsonObj = new JSONObject(jsonSeasonsString);
        JSONArray seasonsArray = jsonObj.getJSONArray("data");
        for (int i = 0; i < seasonsArray.length(); i++) {
            JSONObject jsonSeason = seasonsArray.getJSONObject(i);
            seasons.add(createSeasonInstance(jsonSeason));
        }
        return seasons;
    }

    public Season getSeasonById(String jsonSeason) throws ParseException {
        JSONObject jsonOb = new JSONObject(jsonSeason);
        JSONObject seasonInfo = jsonOb.getJSONObject("data");
        return createSeasonInstance(seasonInfo);
    }

    private Season createSeasonInstance(JSONObject jsonSeason) throws ParseException {
        Object seasonId = jsonSeason.get("season_id");
        String name = (String) jsonSeason.get("name");
        Object isCurr = jsonSeason.get("is_current");
        boolean isCurrent = false;
        if ((int) isCurr == 1) {
            isCurrent = true;
        }
        Object countryId = jsonSeason.get("country_id");
        Object leagueId = jsonSeason.get("league_id");

        Date startDate = util.createShortDateObjFromString((String) jsonSeason.get("start_date"));
        Date endDate = util.createShortDateObjFromString((String) jsonSeason.get("end_date"));

        return new Season((int)seasonId, name, isCurrent,(int)countryId,
                (int)leagueId,startDate,endDate);

    }

}