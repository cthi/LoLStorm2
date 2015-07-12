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
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.geomorphology.lolstorm.LoLStormApplication;
import com.geomorphology.lolstorm.R;
import com.geomorphology.lolstorm.adapters.BaseHeaderRecyclerViewAdapter;
import com.geomorphology.lolstorm.adapters.SummonerChampionsAdapter;
import com.geomorphology.lolstorm.di.component.DaggerSummonerChampionsComponent;
import com.geomorphology.lolstorm.di.module.SummonerChampionsModule;
import com.geomorphology.lolstorm.presenters.SummonerChampionsPresenter;
import com.geomorphology.lolstorm.ui.widgets.headers.SummonerChampionsHeader;
import com.geomorphology.lolstorm.utils.Constants;
import com.geomorphology.lolstorm.views.SummonerChampionsView;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import lolstormSDK.models.ChampionStats;

public class SummonerChampionsFragment extends Fragment implements
        SummonerChampionsView, SummonerChampionsAdapter.OnChampionItemClick {

    @InjectView(R.id.error_placeholder)
    TextView mErrorPlaceholder;
    @InjectView(R.id.summoner_champions_progress)
    ProgressBar mProgressBar;
    @InjectView(R.id.summoner_champions_rv)
    RecyclerView mRecyclerView;
    private SummonerChampionsHeader header;

    private BaseHeaderRecyclerViewAdapter<ChampionStats> mAdapter;

    @Inject
    SummonerChampionsPresenter presenter;

    public static SummonerChampionsFragment newInstance(Bundle bundle) {
        SummonerChampionsFragment fragment = new SummonerChampionsFragment();
        fragment.setArguments(bundle);

        return fragment;
    }

    public void buildGraph() {
        DaggerSummonerChampionsComponent.builder()
                .appComponent(LoLStormApplication.get(getActivity()).component())
                .summonerChampionsModule(new SummonerChampionsModule())
                .build()
                .inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.summoner_champions_fragment, container, false);
        buildGraph();
        ButterKnife.inject(this, view);

        header = new SummonerChampionsHeader(getActivity());
        initRecyclerView();

        presenter.setView(this);
        presenter.setUser(Parcels.unwrap(getArguments().getParcelable(Constants.USER_TAG)));

        return view;
    }

    @Override
    public void populateAdapter(List<ChampionStats> championStats) {
        mAdapter.populate(championStats);
    }

    @Override
    public void setHeaderData(ChampionStats championStat) {
        header.setStats(championStat);
    }

    @Override
    public void showErrorView(int errorMessage) {
        mErrorPlaceholder.setVisibility(View.VISIBLE);
        mErrorPlaceholder.setText(getString(errorMessage));
        mRecyclerView.setVisibility(View.GONE);
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void hideLoading() {
        mProgressBar.setVisibility(View.GONE);
        mRecyclerView.setVisibility(View.VISIBLE);
    }

    private void initRecyclerView() {
        if (null != mRecyclerView) {
            mRecyclerView.setHasFixedSize(true);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

            mAdapter = new SummonerChampionsAdapter(getActivity(), this, new ArrayList<>(), header);
            mRecyclerView.setAdapter(mAdapter);
        }
    }

    @Override
    public void onChampionClick(ChampionStats championStats) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(Constants.RANKED_CHAMPION_STATS_TAG,Parcels.wrap(championStats));

        Intent intent = new Intent(getActivity(), SummonerChampionStatsActivity.class);
        intent.putExtras(bundle);

        startActivity(intent);
    }
}
