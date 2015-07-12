package com.geomorphology.lolstorm.di.module;

import android.database.sqlite.SQLiteDatabase;

import com.geomorphology.lolstorm.presenters.ChampionSpellPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class ChampionSpellModule {
    @Provides
    public ChampionSpellPresenter providePresenter(SQLiteDatabase db) {
        return new ChampionSpellPresenter(db);
    }
}
