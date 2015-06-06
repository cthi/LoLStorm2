package com.jclolstorm.lolstorm.views;

import com.jclolstorm.lolstorm.models.User;

import java.util.List;

import lolstormSDK.models.League;
import lolstormSDK.models.PlayerStatsSummaryList;
import lolstormSDK.models.RankedStats;
import lolstormSDK.models.RecentGames;
import lolstormSDK.models.Summoner;

/**
 * Created by Chris Thi on 5/17/2015.
 */
public interface SummonerSearchView extends BaseView {

    void displaySummonerNotFoundError();

    void displayInternetError();

    void populateAdapter(List<User> users);

    void startPlayerView(User user, Summoner summoner, PlayerStatsSummaryList pss, RecentGames rg,
                         RankedStats rs, List<League> leagues);

}
