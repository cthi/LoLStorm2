package com.jclolstorm.lolstorm.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.jclolstorm.lolstorm.R;
import com.jclolstorm.lolstorm.adapters.ChampionSpellAdater;
import com.jclolstorm.lolstorm.presenters.ChampionSpellPresenter;
import com.jclolstorm.lolstorm.ui.widgets.headers.ChampionDetailHeader;
import com.jclolstorm.lolstorm.utils.Constants;
import com.jclolstorm.lolstorm.views.ChampionDetailView;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import lolstormSDK.models.Champion;
import lolstormSDK.models.ChampionSpell;

public class ChampionSpellActivity extends AppCompatActivity implements ChampionDetailView {
    @InjectView(R.id.toolbar)
    Toolbar mToolbar;
    @InjectView(R.id.champion_spell_rv)
    RecyclerView mRecyclerView;
    private ChampionSpellAdater mAdapter;
    private ChampionDetailHeader mHeader;
    private ChampionSpellPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.champion_spell_activity);
        ButterKnife.inject(this);

        initToolbar();
        initHeader();
        initRecyclerView();

        mPresenter = new ChampionSpellPresenter();
        mPresenter.setView(this);

        mPresenter.setChampionId(getIntent().getExtras().getInt(Constants.CHAMPION_ID_TAG), this);
    }

    private void initToolbar() {
        setSupportActionBar(mToolbar);
    }

    private void initHeader() {
        mHeader = new ChampionDetailHeader(this);
    }

    private void initRecyclerView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new ChampionSpellAdater(this, new ArrayList<>(), mHeader);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void initHeaderData(Champion champion) {
        mHeader.setChampion(champion);
    }

    @Override
    public void populateAdapter(List<ChampionSpell> championSpellList) {
        mAdapter.populate(championSpellList);
    }
}
