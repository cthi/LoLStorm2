package com.jclolstorm.lolstorm.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

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
                    new SummonerChampionStatsAdapter(this, new ArrayList<>());
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
