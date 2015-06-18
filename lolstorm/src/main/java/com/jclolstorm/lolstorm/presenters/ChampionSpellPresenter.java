package com.jclolstorm.lolstorm.presenters;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.google.gson.Gson;
import com.jclolstorm.lolstorm.persistence.champion.ChampionEntry;
import com.jclolstorm.lolstorm.persistence.champion.ChampionReaderDbHelper;
import com.jclolstorm.lolstorm.views.ChampionDetailView;

import lolstormSDK.models.Champion;

public class ChampionSpellPresenter {

    private ChampionDetailView mView;
    private Champion mChampion;

    public ChampionSpellPresenter() {
    }

    public void setView(ChampionDetailView view) {
        this.mView = view;
    }

    public void setChampionId(int championId, Context context) {

        SQLiteDatabase db = ChampionReaderDbHelper.getInstance(context).getReadableDatabase();

        String order = ChampionEntry.COLUMN_NAME_CHAMPION_ID + "= " + championId;

        String query = "SELECT * FROM " + ChampionEntry.TABLE_NAME + " WHERE " + order;
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();

        String championAsString = cursor.getString(cursor.getColumnIndex(ChampionEntry
                .COLUMN_NAME_CHAMPION_ENTRY));
        cursor.close();
        mChampion = new Gson().fromJson(championAsString, Champion.class);

        mView.initHeaderData(mChampion);
        mView.populateAdapter(mChampion.getSpells());
    }
}
