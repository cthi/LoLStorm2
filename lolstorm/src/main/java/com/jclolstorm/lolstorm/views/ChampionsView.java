package com.jclolstorm.lolstorm.views;

import java.util.List;

import lolstormSDK.models.Champion;

public interface ChampionsView {

    void populate(List<Champion> championList);
}
