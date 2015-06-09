package com.jclolstorm.lolstorm.ui;

import android.os.Bundle;
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
import lolstormSDK.models.Summoner;


public class SummonerLeaguesFragment extends Fragment implements SummonerLeaguesView {

    @InjectView(R.id.no_info_error)
    TextView mNoData;
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
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
    public void showNoDataView() {
        mNoData.setVisibility(View.VISIBLE);
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

            mAdapter = new SummonerLeaguesAdapter(getActivity(), new ArrayList<>(), mHeader);
            mRecyclerView.setAdapter(mAdapter);
        }
    }

    public void initHeaderData(League league, int division) {
        mHeader.setLeague(league, division);
    }

    @Override
    public void populateAdapter(List<LeagueEntry> entries) {
        mAdapter.populate(entries);
    }
}
