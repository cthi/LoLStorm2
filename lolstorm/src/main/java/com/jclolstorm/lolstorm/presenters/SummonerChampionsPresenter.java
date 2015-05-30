package com.jclolstorm.lolstorm.presenters;

import com.jclolstorm.lolstorm.views.SummonerChampionsView;

import java.util.Collections;
import java.util.List;

import lolstormSDK.models.ChampionStats;
import lolstormSDK.models.RankedStats;

public class SummonerChampionsPresenter {

    private SummonerChampionsView view;
    private List<ChampionStats> stats;
    private ChampionStats headerStat;

    public SummonerChampionsPresenter() {
    }

    public void setView(SummonerChampionsView view) {
        this.view = view;
    }

    public void setRankedStats(RankedStats rankedStats) {
        this.stats = rankedStats.getChampions();

        Collections.sort(stats, ChampionStats::compareTo);
        headerStat = stats.remove(0);

        view.populateAdapter(stats);
        view.setHeaderData(headerStat);
    }
}
