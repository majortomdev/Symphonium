package com.majortomdev.SymphBE.service;

import com.majortomdev.SymphBE.models.Season;

import com.majortomdev.SymphBE.models.SeasonStats;
import com.majortomdev.SymphBE.models.Standing;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.*;

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

    public List<Standing> getStandingsForSeason(String jsonSeasonResults){
        List<Standing> standings = new ArrayList<>();
        JSONObject jsonObj = new JSONObject(jsonSeasonResults);
        JSONObject dataObj = jsonObj.getJSONObject("data");
        JSONArray standingsArray = dataObj.getJSONArray("standings");

        for (int i=0; i<standingsArray.length(); i++){
            JSONObject standingJson = standingsArray.getJSONObject(i);
            standings.add(createStandingInstance(standingJson));
        }
        return standings;
    }

    private Standing createStandingInstance(JSONObject jsonStanding){
        Object teamId = jsonStanding.get("team_id");
        Object position = jsonStanding.get("position");
        Object points = jsonStanding.get("points");
        String status = (String)jsonStanding.get("status");

        Object res = jsonStanding.get("result");
        String result;
        if(!JSONObject.NULL.equals(res)){
            result = (String) res;
        }else result = "";

        Map<String,SeasonStats> statsForSeason = new LinkedHashMap<>();
        JSONObject overall = jsonStanding.getJSONObject("overall");
        JSONObject home = jsonStanding.getJSONObject("home");
        JSONObject away = jsonStanding.getJSONObject("away");

        statsForSeason.put("overall",createSeasonsStatsInstance(overall));
        statsForSeason.put("home",createSeasonsStatsInstance(home));
        statsForSeason.put("away",createSeasonsStatsInstance(away));
        return new Standing((int)teamId,(int)position,(int)points,status,result,statsForSeason);
    }

    private SeasonStats createSeasonsStatsInstance(JSONObject jsonSeasonStats){
        Object played = jsonSeasonStats.get("games_played");
        Object wins = jsonSeasonStats.get("won");
        Object draws = jsonSeasonStats.get("draw");
        Object losses = jsonSeasonStats.get("lost");
        Object goalDiff = jsonSeasonStats.get("goals_diff");
        Object goalsFor = jsonSeasonStats.get("goals_scored");
        Object goalsAgainst = jsonSeasonStats.get("goals_against");

        return new SeasonStats((int)played,(int)wins,(int)draws,
                (int)losses,(int)goalDiff,(int)goalsFor,(int)goalsAgainst);
    }


}