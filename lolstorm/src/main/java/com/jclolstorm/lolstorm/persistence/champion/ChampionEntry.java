package com.jclolstorm.lolstorm.persistence.champion;

import android.provider.BaseColumns;


public abstract class ChampionEntry implements BaseColumns {

    public static final String TABLE_NAME = "champion_entries";
    public static final String COLUMN_NAME_CHAMPION_ID = "ids";
    public static final String COLUMN_NAME_CHAMPION_ENTRY = "entries";
    public static final String TEXT_TYPE = " TEXT";
    public static final String INTEGER_TYPE = " INTEGER";
    public static final String COMMA_SEP = ",";
    public static final String SQL_CREATE_ENTRIES = "CREATE TABLE " + ChampionEntry.TABLE_NAME +
            " (" +
            ChampionEntry._ID + " INTEGER PRIMARY KEY," +
            ChampionEntry.COLUMN_NAME_CHAMPION_ID + INTEGER_TYPE + COMMA_SEP +
            ChampionEntry.COLUMN_NAME_CHAMPION_ENTRY + TEXT_TYPE +
            " )";

}