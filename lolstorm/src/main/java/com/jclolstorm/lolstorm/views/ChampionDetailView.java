package com.jclolstorm.lolstorm.views;

import java.util.List;

import lolstormSDK.models.Champion;
import lolstormSDK.models.ChampionSpell;

public interface ChampionDetailView {

    void initHeaderData(Champion champion);

    void populateAdapter(List<ChampionSpell> championSpellList);

    void setTitle(String title);
}
