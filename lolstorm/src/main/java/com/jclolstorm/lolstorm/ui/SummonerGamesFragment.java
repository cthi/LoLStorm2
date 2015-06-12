package com.jclolstorm.lolstorm.ui;

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

import com.jclolstorm.lolstorm.R;
import com.jclolstorm.lolstorm.adapters.BaseHeaderRecyclerViewAdapter;
import com.jclolstorm.lolstorm.adapters.SummonerGamesAdapter;
import com.jclolstorm.lolstorm.models.User;
import com.jclolstorm.lolstorm.presenters.SummonerGamesPresenter;
import com.jclolstorm.lolstorm.ui.widgets.headers.SummonerGamesHeader;
import com.jclolstorm.lolstorm.utils.Constants;
import com.jclolstorm.lolstorm.views.SummonerGamesView;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import lolstormSDK.models.Game;

public class SummonerGamesFragment extends Fragment implements SummonerGamesView, SummonerGamesAdapter.OnGameItemClick {

    @InjectView(R.id.no_info_error)
    TextView mNoData;
    @InjectView(R.id.summoner_games_progress)
    ProgressBar mProgressBar;
    @InjectView(R.id.summoner_games_rv)
    RecyclerView mRecyclerView;
    private SummonerGamesHeader mHeader;

    private BaseHeaderRecyclerViewAdapter<Game> mAdapter;
    private SummonerGamesPresenter presenter;

    private User user;

    public static SummonerGamesFragment newInstance(Bundle bundle) {
        SummonerGamesFragment fragment = new SummonerGamesFragment();
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.summoner_games_fragment, container, false);

        ButterKnife.inject(this, view);

        initHeader();
        initRecyclerView();

        user = Parcels.unwrap(getArguments().getParcelable(Constants.USER_TAG));

        presenter = new SummonerGamesPresenter();
        presenter.setView(this);
        presenter.setUser(user);

        mHeader.initHeaderData(user);

        return view;
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
        mHeader = new SummonerGamesHeader(getActivity());
    }

    private void initRecyclerView() {
        if (null != mRecyclerView) {
            mRecyclerView.setHasFixedSize(true);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

            mAdapter = new SummonerGamesAdapter(getActivity(), new ArrayList<>(), mHeader, this);
            mRecyclerView.setAdapter(mAdapter);
        }
    }

    @Override
    public void onGameClick(Game game) {
        Intent intent = new Intent(getActivity(), SummonerGameResultActivity.class);

        Bundle bundle = new Bundle();
        bundle.putParcelable(Constants.GAME_TAG, Parcels.wrap(game));
        bundle.putParcelable(Constants.USER_TAG, Parcels.wrap(user));

        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public void populate(List<Game> gameList) {
        mAdapter.populate(gameList);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean hasConnection() {
        //TODO fix
        return true;
    }
}
