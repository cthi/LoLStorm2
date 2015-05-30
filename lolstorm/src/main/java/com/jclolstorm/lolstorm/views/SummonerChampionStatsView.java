package com.jclolstorm.lolstorm.views;

import com.jclolstorm.lolstorm.models.Stat;

import java.util.List;

public interface SummonerChampionStatsView {

    void populateAdapter(List<Stat> stats);
}
