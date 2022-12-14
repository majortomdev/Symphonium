package com.majortomdev.SymphBE.service;

import com.majortomdev.SymphBE.models.Country;
import com.majortomdev.SymphBE.models.Player;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PlayerService {

    @Autowired
    private CountryService countryService;

    @Autowired
    private Util util;

    public List<Player> getPlayersByCountry(String jsonPlayersString) throws ParseException {
        List<Player> players = new ArrayList<>();
        JSONObject jsonObj = new JSONObject(jsonPlayersString);
        JSONArray playersArray = jsonObj.getJSONArray("data");
        for (int i = 0; i < playersArray.length(); i++) {
            JSONObject jsonPlayer = playersArray.getJSONObject(i);
            players.add(createPlayerInst(jsonPlayer));
        }
        return players;
    }

    public Player getPlayerById(String jsonPlayerString) throws ParseException {
        JSONObject jsonObject = new JSONObject(jsonPlayerString);
        JSONObject jsonPlayer = jsonObject.getJSONObject("data");
        return createPlayerInst(jsonPlayer);
    }


//    public List<Player> getPlayersByClub(String jsonPlayers, int teamId){
//        List<Player> playersAtClub = new ArrayList<>();
//        JSONObject jsonObj = new JSONObject(jsonPlayers);



//check if player id







//        JSONArray goalsArray = jsonObj.getJSONArray("data");
//        for(int i=0; i<goalsArray.length(); i++){
//            JSONObject jsonPlayerGoals = goalsArray.getJSONObject(i);
//            JSONObject innerTeamObj = jsonPlayerGoals.getJSONObject("team");
//            if(teamId == (int)innerTeamObj.get("team_id")) {
//                players.add(createPlayerSeasonGoalsInst(jsonPlayerGoals));
//            }
//        }




 //   }

    private Player createPlayerInst(JSONObject jsonPlayer) throws ParseException {
        Object playerId = jsonPlayer.get("player_id");
        String firstName = (String)jsonPlayer.get("firstname");
        String lastName = (String)jsonPlayer.get("lastname");

        Date birthday = util.createShortDateObjFromString((String)jsonPlayer.get("birthday"));
        Object age = jsonPlayer.get("age");

        Object heightObj = jsonPlayer.get("height");
        int height;
        if(!JSONObject.NULL.equals(heightObj)){
            height = (int)heightObj;
        }else height = 0;

        Object weightObj = jsonPlayer.get("weight");
        int weight;
        if(!JSONObject.NULL.equals(weightObj)){
            weight = (int)weightObj;
        }else weight = 0;

        Object img = jsonPlayer.get("img");
        String image;
        if(!JSONObject.NULL.equals(img)){
            image = (String) img;
        }else image = "";

        JSONObject countryOfPlayer = jsonPlayer.getJSONObject("country");
        Country country = countryService.createCountryInst(countryOfPlayer);

        return new Player((int)playerId,firstName,lastName,birthday,(int)age,height,
                weight, image,country);
    }
}