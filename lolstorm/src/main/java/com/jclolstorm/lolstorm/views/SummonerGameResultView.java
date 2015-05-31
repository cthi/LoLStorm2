package com.jclolstorm.lolstorm.views;

import java.util.List;
import java.util.Map;

import lolstormSDK.models.Player;

public interface SummonerGameResultView extends BaseView {

    void setTitle(String title);

    void populateAdapter(List<Player> stats);

}
