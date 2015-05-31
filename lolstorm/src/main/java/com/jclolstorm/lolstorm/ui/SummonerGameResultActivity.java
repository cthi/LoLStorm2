package com.jclolstorm.lolstorm.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.jclolstorm.lolstorm.R;
import com.jclolstorm.lolstorm.adapters.BaseHeaderRecyclerViewAdapter;
import com.jclolstorm.lolstorm.adapters.SummonerGameResultAdapter;
import com.jclolstorm.lolstorm.presenters.SummonerGameResultPresenter;
import com.jclolstorm.lolstorm.utils.Constants;
import com.jclolstorm.lolstorm.utils.NetworkUtils;
import com.jclolstorm.lolstorm.views.SummonerGameResultView;


import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import lolstormSDK.models.Game;
import lolstormSDK.models.Player;

public class SummonerGameResultActivity extends AppCompatActivity
        implements SummonerGameResultView {

    @InjectView(R.id.summoner_game_result_toolbar)
    Toolbar mToolbar;
    @InjectView(R.id.summoner_game_result_rv)
    RecyclerView mRecyclerView;
    private View mHeader;

    private SummonerGameResultPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.summoner_game_result_activity);
        ButterKnife.inject(this);

        initToolbar();
        initRecyclerView();

        presenter = new SummonerGameResultPresenter();
        presenter.setView(this);

        Game game = Parcels.unwrap(getIntent().getExtras().getParcelable(Constants.GAME_TAG));
        presenter.setGame(game);
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

            BaseHeaderRecyclerViewAdapter<Player> adapter =
                    new SummonerGameResultAdapter(this, new ArrayList<>(), new View(this));
            mRecyclerView.setAdapter(adapter);
        }
    }

    @Override
    public void setTitle(String string) {
        getSupportActionBar().setTitle(string);
    }

    @Override
    public void populateAdapter(List<Player> stats) {
        ((BaseHeaderRecyclerViewAdapter)mRecyclerView.getAdapter()).populate(stats);
    }

    @Override
    public boolean hasConnection() {
        return NetworkUtils.hasConnection(this);
    }
}
