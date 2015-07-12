package com.geomorphology.lolstorm.di.module;

import com.geomorphology.lolstorm.presenters.SummonerChampionStatsPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class SummonerChampionStatsModule {

    @Provides
    public SummonerChampionStatsPresenter providePresenter() {
        return new SummonerChampionStatsPresenter();
    }
}
