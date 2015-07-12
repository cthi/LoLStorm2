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

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.geomorphology.lolstorm.LoLStormApplication;
import com.geomorphology.lolstorm.R;
import com.geomorphology.lolstorm.adapters.ChampionSpellAdapter;
import com.geomorphology.lolstorm.di.component.DaggerChampionSpellComponent;
import com.geomorphology.lolstorm.di.module.ChampionSpellModule;
import com.geomorphology.lolstorm.presenters.ChampionSpellPresenter;
import com.geomorphology.lolstorm.ui.widgets.headers.ChampionDetailHeader;
import com.geomorphology.lolstorm.utils.Constants;
import com.geomorphology.lolstorm.views.ChampionDetailView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import lolstormSDK.models.Champion;
import lolstormSDK.models.ChampionSpell;

public class ChampionSpellActivity extends AppCompatActivity implements ChampionDetailView {
    @InjectView(R.id.toolbar)
    Toolbar mToolbar;
    @InjectView(R.id.champion_spell_rv)
    RecyclerView mRecyclerView;

    @Inject
    ChampionSpellPresenter mPresenter;

    private ChampionSpellAdapter mAdapter;
    private ChampionDetailHeader mHeader;

    private void buildGraph() {
        DaggerChampionSpellComponent.builder()
                .appComponent(LoLStormApplication.get(this).component())
                .championSpellModule(new ChampionSpellModule())
                .build()
                .inject(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.champion_spell_activity);
        buildGraph();
        ButterKnife.inject(this);

        initToolbar();
        initHeader();
        initRecyclerView();

        mPresenter.setView(this);
        mPresenter.setChampionId(getIntent().getExtras().getInt(Constants.CHAMPION_ID_TAG));
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
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void initHeader() {
        mHeader = new ChampionDetailHeader(this);
    }

    private void initRecyclerView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new ChampionSpellAdapter(this, new ArrayList<>(), mHeader);
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

    @Override
    public void setTitle(String title) {
        if (null != getSupportActionBar()) {
            getSupportActionBar().setTitle(title);
        }
    }
}
