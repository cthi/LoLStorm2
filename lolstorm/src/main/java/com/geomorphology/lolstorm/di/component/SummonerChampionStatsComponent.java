package com.geomorphology.lolstorm.di.component;

import com.geomorphology.lolstorm.di.PerScope;
import com.geomorphology.lolstorm.di.module.SummonerChampionStatsModule;
import com.geomorphology.lolstorm.ui.SummonerChampionStatsActivity;

import dagger.Component;

@PerScope
@Component(
        dependencies = AppComponent.class,
        modules = SummonerChampionStatsModule.class
)
public interface SummonerChampionStatsComponent {
    void inject(SummonerChampionStatsActivity activity);
}
