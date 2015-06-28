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
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.jclolstorm.lolstorm.R;
import com.jclolstorm.lolstorm.adapters.BaseHeaderRecyclerViewAdapter;
import com.jclolstorm.lolstorm.adapters.SummonerLeaguesAdapter;
import com.jclolstorm.lolstorm.models.User;
import com.jclolstorm.lolstorm.presenters.SummonerLeaguesPresenter;
import com.jclolstorm.lolstorm.ui.widgets.headers.SummonerLeaguesHeader;
import com.jclolstorm.lolstorm.utils.Constants;
import com.jclolstorm.lolstorm.views.SummonerLeaguesView;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import lolstormSDK.models.League;
import lolstormSDK.models.LeagueEntry;


public class SummonerLeaguesFragment extends Fragment implements SummonerLeaguesView,
        SummonerLeaguesAdapter.OnLeagueItemClick {

    @InjectView(R.id.error_placeholder)
    TextView mErrorPlaceholder;
    @InjectView(R.id.summoner_leagues_progress)
    ProgressBar mProgressBar;
    @InjectView(R.id.summoner_leagues_rv)
    RecyclerView mRecyclerView;
    private SummonerLeaguesHeader mHeader;

    private BaseHeaderRecyclerViewAdapter<LeagueEntry> mAdapter;
    private SummonerLeaguesPresenter presenter;

    public static SummonerLeaguesFragment newInstance(Bundle bundle) {
        SummonerLeaguesFragment fragment = new SummonerLeaguesFragment();
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        View view = inflater.inflate(R.layout.summoner_leagues_fragment, container, false);

        ButterKnife.inject(this, view);

        showLoading();

        initHeader();
        initRecyclerView();

        presenter = new SummonerLeaguesPresenter();
        presenter.setView(this);
        presenter.setUser(Parcels.unwrap(getArguments().getParcelable(Constants.USER_TAG)));

        return view;
    }

    private void showLoading() {
        mRecyclerView.setVisibility(View.GONE);
    }

    @Override
    public void hideLoading() {
        mProgressBar.setVisibility(View.GONE);
        mRecyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showErrorView(int errorMessage) {
        mErrorPlaceholder.setVisibility(View.VISIBLE);
        mErrorPlaceholder.setText(getString(errorMessage));
        mRecyclerView.setVisibility(View.GONE);
        mProgressBar.setVisibility(View.GONE);
    }

    private void initHeader() {
        mHeader = new SummonerLeaguesHeader(getActivity());
    }

    private void initRecyclerView() {
        if (null != mRecyclerView) {
            mRecyclerView.setHasFixedSize(true);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

            mAdapter = new SummonerLeaguesAdapter(getActivity(), this, new ArrayList<>(), mHeader);
            mRecyclerView.setAdapter(mAdapter);
        }
    }

    public void initHeaderData(League league, int division) {
        mHeader.setLeague(league, division);
    }

    @Override
    public void startPlayerView(User user) {
        Intent intent = new Intent(getActivity(), SummonerPagerActivity.class);

        Bundle bundle = new Bundle();
        bundle.putParcelable(Constants.USER_TAG, Parcels.wrap(user));

        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public void displaySummonerNotFoundError() {
        Snackbar.make(mRecyclerView, R.string.error_summoner_not_found, Snackbar.LENGTH_SHORT)
                .show();
    }

    @Override
    public void onSummonerClick(LeagueEntry leagueEntry) {
        presenter.onSearch(leagueEntry.getPlayerOrTeamName());
    }

    @Override
    public void populateAdapter(List<LeagueEntry> entries) {
        mAdapter.populate(entries);
    }
}
