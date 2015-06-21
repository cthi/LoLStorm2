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

package com.jclolstorm.lolstorm.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.jclolstorm.lolstorm.R;
import com.jclolstorm.lolstorm.adapters.SummonerChampionStatsAdapter;
import com.jclolstorm.lolstorm.models.Stat;
import com.jclolstorm.lolstorm.presenters.SummonerChampionStatsPresenter;
import com.jclolstorm.lolstorm.utils.Constants;
import com.jclolstorm.lolstorm.views.SummonerChampionStatsView;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import lolstormSDK.models.ChampionStats;

public class SummonerChampionStatsActivity extends AppCompatActivity
        implements SummonerChampionStatsView {

    @InjectView(R.id.summoner_champion_stats_toolbar)
    Toolbar mToolbar;
    @InjectView(R.id.summoner_champion_stats_rv)
    RecyclerView mRecyclerView;

    private SummonerChampionStatsPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.summoner_champion_stats_activity);
        ButterKnife.inject(this);

        initToolbar();
        initRecyclerView();

        presenter = new SummonerChampionStatsPresenter();
        presenter.setView(this);

        ChampionStats championStats = Parcels.unwrap(getIntent().getExtras()
                .getParcelable(Constants.RANKED_CHAMPION_STATS_TAG));
        presenter.setStats(championStats);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initToolbar() {
        if (null != mToolbar) {
            setSupportActionBar(mToolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    private void initRecyclerView() {
        if (null != mRecyclerView) {
            mRecyclerView.setHasFixedSize(true);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

            SummonerChampionStatsAdapter adapter =
                    new SummonerChampionStatsAdapter(new ArrayList<>());
            mRecyclerView.setAdapter(adapter);
        }
    }

    @Override
    public void setTitle(String string) {
        getSupportActionBar().setTitle(string);
    }

    @Override
    public void populateAdapter(List<Stat> stats) {
        ((SummonerChampionStatsAdapter)mRecyclerView.getAdapter()).populate(stats);
    }
}
