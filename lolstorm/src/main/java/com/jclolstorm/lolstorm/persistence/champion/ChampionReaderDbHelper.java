package com.jclolstorm.lolstorm.persistence.champion;

import android.content.Context;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;


public class ChampionReaderDbHelper extends SQLiteAssetHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "ChampionReader.db";

    private static ChampionReaderDbHelper INSTANCE;

    private ChampionReaderDbHelper(Context context) {
        super (context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static ChampionReaderDbHelper getInstance(Context context){
        if (null == INSTANCE) {
            INSTANCE = new ChampionReaderDbHelper(context);
        }

        return INSTANCE;
    }
}
