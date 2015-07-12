package com.geomorphology.lolstorm.di.module;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.geomorphology.lolstorm.persistence.champion.ChampionReaderDbHelper;

import dagger.Module;
import dagger.Provides;

@Module
public class ChampionDbModule {
    @Provides
    public SQLiteDatabase provideDb(Context context) {
        return ChampionReaderDbHelper.getInstance(context).getReadableDatabase();
    }
}
