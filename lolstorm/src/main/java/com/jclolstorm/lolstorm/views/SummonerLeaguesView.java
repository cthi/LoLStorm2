package com.jclolstorm.lolstorm.views;

import com.jclolstorm.lolstorm.models.User;

import java.util.List;

import lolstormSDK.models.League;
import lolstormSDK.models.LeagueEntry;

public interface SummonerLeaguesView {
    void populateAdapter(List<LeagueEntry> entries);

    void initHeaderData(League league, int division);

    void hideLoading();

    void showNoDataView();

    void startPlayerView(User user);

    void displaySummonerNotFoundError();
}
