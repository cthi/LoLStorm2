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

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.jclolstorm.lolstorm.R;
import com.jclolstorm.lolstorm.adapters.BaseHeaderRecyclerViewAdapter;
import com.jclolstorm.lolstorm.adapters.SummonerGameResultAdapter;
import com.jclolstorm.lolstorm.models.User;
import com.jclolstorm.lolstorm.presenters.SummonerGameResultPresenter;
import com.jclolstorm.lolstorm.ui.widgets.headers.SummonerGameResultHeader;
import com.jclolstorm.lolstorm.utils.Constants;
import com.jclolstorm.lolstorm.utils.NetworkUtils;
import com.jclolstorm.lolstorm.views.SummonerGameResultView;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import lolstormSDK.GameConstants;
import lolstormSDK.RiotEndpoint;
import lolstormSDK.models.Game;
import lolstormSDK.models.Player;

public class SummonerGameResultActivity extends AppCompatActivity implements
        SummonerGameResultView, SummonerGameResultAdapter.OnSummonerItemClick {

    @InjectView(R.id.summoner_game_result_toolbar)
    Toolbar mToolbar;
    @InjectView(R.id.summoner_game_result_rv)
    RecyclerView mRecyclerView;
    @InjectView(R.id.error_placeholder)
    TextView mErrorPlaceholder;

    private SummonerGameResultHeader mHeader;

    private BaseHeaderRecyclerViewAdapter<Player> mAdapter;
    private SummonerGameResultPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.summoner_game_result_activity);
        ButterKnife.inject(this);

        mHeader = new SummonerGameResultHeader(this);

        initToolbar();
        initRecyclerView();

        presenter = new SummonerGameResultPresenter();
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
                RiotEndpoint.getInstance().getRegion(), player.getProfileIcon(), player.getLevel(), player.getSummonerId())));

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

    @Override
    public boolean hasConnection() {
        return NetworkUtils.hasConnection(this);
    }
}
