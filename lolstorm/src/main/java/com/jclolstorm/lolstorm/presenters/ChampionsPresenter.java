package com.jclolstorm.lolstorm.presenters;

import com.jclolstorm.lolstorm.views.ChampionsView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import lolstormSDK.GameConstants;
import lolstormSDK.models.Champion;

public class ChampionsPresenter {

    private ChampionsView mView;

    public ChampionsPresenter() {
    }

    public void setView(ChampionsView view) {
        this.mView = view;
        getListOfChampions();
    }

    private void getListOfChampions() {
        List<Champion> championList = new ArrayList<>();

        for (Map.Entry<String, String> entry : GameConstants.CHAMPION_NAME_MAP.entrySet()) {
            championList.add(new Champion(entry.getValue(), Long.parseLong(entry.getKey())));
        }

        Collections.sort(championList, (lhs, rhs) -> lhs.getName().compareTo(rhs.getName()));
        mView.populate(championList);
    }
}
