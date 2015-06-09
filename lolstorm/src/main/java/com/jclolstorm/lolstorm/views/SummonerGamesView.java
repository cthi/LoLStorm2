package com.jclolstorm.lolstorm.views;


import java.util.List;

import lolstormSDK.models.Game;

public interface SummonerGamesView extends BaseView {

    void populate(List<Game> gameList);

    void hideLoading();

    void showNoDataView();
}
