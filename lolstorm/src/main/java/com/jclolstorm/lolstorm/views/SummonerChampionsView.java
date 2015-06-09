package com.jclolstorm.lolstorm.views;


import java.util.List;

import lolstormSDK.models.ChampionStats;

public interface SummonerChampionsView {

    void populateAdapter(List<ChampionStats> championStats);

    void setHeaderData(ChampionStats championStat);

    void hideLoading();

    void showNoDataView();

}
