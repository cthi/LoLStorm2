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

package com.geomorphology.lolstorm.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.geomorphology.lolstorm.LoLStormApplication;
import com.geomorphology.lolstorm.R;
import com.geomorphology.lolstorm.adapters.BaseHeaderRecyclerViewAdapter;
import com.geomorphology.lolstorm.adapters.SummonerGameResultAdapter;
import com.geomorphology.lolstorm.di.component.DaggerSummonerGameResultComponent;
import com.geomorphology.lolstorm.di.module.SummonerGameResultModule;
import com.geomorphology.lolstorm.models.User;
import com.geomorphology.lolstorm.presenters.SummonerGameResultPresenter;
import com.geomorphology.lolstorm.ui.widgets.headers.SummonerGameResultHeader;
import com.geomorphology.lolstorm.utils.Constants;
import com.geomorphology.lolstorm.views.SummonerGameResultView;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import lolstormSDK.GameConstants;
import lolstormSDK.RiotEndpoint;
import lolstormSDK.models.Game;
import lolstormSDK.models.Player;

public class SummonerGameResultActivity extends AppCompatActivity implements
        SummonerGameResultView, SummonerGameResultAdapter.OnSummonerItemClick {

    @Inject
    SummonerGameResultPresenter presenter;

    @InjectView(R.id.summoner_game_result_toolbar)
    Toolbar mToolbar;
    @InjectView(R.id.summoner_game_result_rv)
    RecyclerView mRecyclerView;
    @InjectView(R.id.error_placeholder)
    TextView mErrorPlaceholder;
    private SummonerGameResultHeader mHeader;

    private BaseHeaderRecyclerViewAdapter<Player> mAdapter;

    public void buildGraph() {
        DaggerSummonerGameResultComponent.builder()
                .appComponent(LoLStormApplication.get(this).component())
                .summonerGameResultModule(new SummonerGameResultModule())
                .build()
                .inject(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.summoner_game_result_activity);
        buildGraph();
        ButterKnife.inject(this);

        mHeader = new SummonerGameResultHeader(this);

        initToolbar();
        initRecyclerView();

        presenter.setView(this);

        Game game = Parcels.unwrap(getIntent().getExtras().getParcelable(Constants.GAME_TAG));
        setTitle(GameConstants.GAME_TYPES.get(game.getSubType()));
        presenter.setGame(game);
        mHeader.setGame(game);

        User user = Parcels.unwrap(getIntent().getExtras().getParcelable(Constants.USER_TAG));
        mHeader.setUser(user);
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

            mAdapter = new SummonerGameResultAdapter(this, this, new ArrayList<>(), mHeader);
            mRecyclerView.setAdapter(mAdapter);
        }
    }

    @Override
    public void onSummonerClick(Player player) {
        Intent intent = new Intent(this, SummonerPagerActivity.class);

        Bundle bundle = new Bundle();
        bundle.putParcelable(Constants.USER_TAG, Parcels.wrap(new User(player.getSummonerName(),
                RiotEndpoint.getInstance().getRegionId(), player.getProfileIcon(), player.getLevel(), player.getSummonerId())));

        intent.putExtras(bundle);

        startActivity(intent);
    }

    @Override
    public void setTitle(String string) {
        if (null != getSupportActionBar()) {
            getSupportActionBar().setTitle(string);
        }
    }

    @Override
    public void populateAdapter(List<Player> stats) {
        mAdapter.populate(stats);
    }

    @Override
    public void showErrorView(int errorMessage) {
        mErrorPlaceholder.setVisibility(View.VISIBLE);
        mErrorPlaceholder.setText(getString(errorMessage));
        mRecyclerView.setVisibility(View.GONE);
    }
}
