package com.jclolstorm.lolstorm.presenters;

import android.util.Log;

import com.jclolstorm.lolstorm.views.SummonerLeaguesView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import lolstormSDK.GameConstants;
import lolstormSDK.models.League;
import lolstormSDK.models.LeagueEntry;

public class SummonerLeaguesPresenter {

    private final static String QUEUE_TYPE = "RANKED_SOLO_5x5";


    private SummonerLeaguesView view;

    private League currentLeague;
    private List<LeagueEntry> currentEntries;
    private long summonerID;
    private int currentDivision;

    public SummonerLeaguesPresenter() {}

    public void setView(SummonerLeaguesView view) {
        this.view = view;
    }

    public void setSummonerID(long summonerID) {
        this.summonerID = summonerID;
    }

    public void setLeagues(List<League> leagues) {
        findQueue(leagues);

        view.populateAdapter(currentEntries);
        view.initHeaderData(currentLeague, currentDivision);
        // set views here.
    }

    private void findQueue(List<League> leagues) {
        for (League league : leagues) {
            if (league.getQueue().equals(QUEUE_TYPE)) {
                currentLeague = league;
                sortEntriesIntoDivision(league.getEntries());
                break;
            }
        }
    }

    private void sortEntriesIntoDivision(List<LeagueEntry> entries) {
        ArrayList<ArrayList<LeagueEntry>> sorted = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            sorted.add(i, new ArrayList<>());
        }

        for (LeagueEntry i : entries) {
            if (i.getDivision().equals(GameConstants.DIV_1)) {
                sorted.get(0).add(i);
            } else if (i.getDivision().equals(GameConstants.DIV_2)) {
                sorted.get(1).add(i);
            } else if (i.getDivision().equals(GameConstants.DIV_3)) {
                sorted.get(2).add(i);
            } else if (i.getDivision().equals(GameConstants.DIV_4)) {
                sorted.get(3).add(i);
            } else {
                sorted.get(4).add(i);
            }
        }

        currentDivision = getCurrentDivision(sorted, summonerID);
        Collections.sort(sorted.get(currentDivision -1));

        currentEntries = sorted.get(currentDivision -1);
    }

    private int getCurrentDivision(ArrayList<ArrayList<LeagueEntry>> entries, Long id) {
        for (int i = 0; i < entries.size(); i++) {
            for (int u = 0; u < entries.get(i).size(); u++)
                if (Long.parseLong(entries.get(i).get(u).getPlayerOrTeamId()) == id) {
                    return  i + 1;
                }
        }
        return 0;
    }
}
