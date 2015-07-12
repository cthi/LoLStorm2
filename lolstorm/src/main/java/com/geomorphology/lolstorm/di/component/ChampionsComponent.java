package com.geomorphology.lolstorm.di.component;

import com.geomorphology.lolstorm.di.PerScope;
import com.geomorphology.lolstorm.di.module.ChampionsModule;
import com.geomorphology.lolstorm.ui.ChampionsFragment;

import dagger.Component;

@PerScope
@Component(
        dependencies = AppComponent.class,
        modules = ChampionsModule.class
)
public interface ChampionsComponent {
    void inject(ChampionsFragment fragment);
}
