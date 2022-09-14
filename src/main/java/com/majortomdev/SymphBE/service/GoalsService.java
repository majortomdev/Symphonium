package com.majortomdev.SymphBE.service;

import com.majortomdev.SymphBE.models.PlayerSeasonGoals;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GoalsService {

    public List<PlayerSeasonGoals> getSeasonGoals(String jsonSeasonGoals){
        List<PlayerSeasonGoals> players = new ArrayList<>();
        JSONObject jsonObj = new JSONObject(jsonSeasonGoals);
        JSONArray goalsArray = jsonObj.getJSONArray("data");

        for(int i=0; i<goalsArray.length(); i++){
            JSONObject jsonPlayerGoals = goalsArray.getJSONObject(i);
            players.add(createPlayerSeasonGoalsInst(jsonPlayerGoals));
        }
        return players;
    }

    private PlayerSeasonGoals createPlayerSeasonGoalsInst(JSONObject goalsScoredObj){
            Object ranking = goalsScoredObj.get("pos");
            JSONObject innerPlayer = goalsScoredObj.getJSONObject("player");
            Object playerId = innerPlayer.get("player_id");
            JSONObject innerTeam = goalsScoredObj.getJSONObject("team");
            Object teamId = innerTeam.get("team_id");
            Object leagueId = goalsScoredObj.get("league_id");
            Object seasonId = goalsScoredObj.get("season_id");
            Object matchesPlayed = goalsScoredObj.get("matches_played");
            Object minutesPlayed = goalsScoredObj.get("minutes_played");
            Object substituted = goalsScoredObj.get("substituted_in");
            int substitutedIn;
            if(!JSONObject.NULL.equals(substituted)){
                substitutedIn = (int) substituted;
            }else substitutedIn = 0;
            JSONObject innerGoals = goalsScoredObj.getJSONObject("goals");
            Object goalsHome = innerGoals.get("home");
            Object goalsAway = innerGoals.get("away");

    return new PlayerSeasonGoals((int) ranking, (int) playerId, (int) teamId, (int) leagueId, (int) seasonId,
            (int) matchesPlayed, (int) minutesPlayed, substitutedIn, (int) goalsHome, (int) goalsAway);
    }
}
