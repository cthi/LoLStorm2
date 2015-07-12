/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2015 Christopher C. Thi
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.geomorphology.lolstorm.presenters;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.google.gson.Gson;
import com.geomorphology.lolstorm.persistence.champion.ChampionEntry;
import com.geomorphology.lolstorm.views.ChampionDetailView;

import lolstormSDK.models.Champion;

public class ChampionSpellPresenter {

    private SQLiteDatabase mChampionDb;
    private ChampionDetailView mView;
    private Champion mChampion;

    public ChampionSpellPresenter(SQLiteDatabase championDb) {
        this.mChampionDb = championDb;
    }

    public void setView(ChampionDetailView view) {
        this.mView = view;
    }

    public void setChampionId(int championId) {

        String order = ChampionEntry.COLUMN_NAME_CHAMPION_ID + "= " + championId;

        String query = "SELECT * FROM " + ChampionEntry.TABLE_NAME + " WHERE " + order;
        Cursor cursor = mChampionDb.rawQuery(query, null);
        cursor.moveToFirst();

        String championAsString = cursor.getString(cursor.getColumnIndex(ChampionEntry
                .COLUMN_NAME_CHAMPION_ENTRY));
        cursor.close();
        mChampion = new Gson().fromJson(championAsString, Champion.class);

        mView.setTitle(mChampion.getName());
        mView.initHeaderData(mChampion);
        mView.populateAdapter(mChampion.getSpells());
    }
}
