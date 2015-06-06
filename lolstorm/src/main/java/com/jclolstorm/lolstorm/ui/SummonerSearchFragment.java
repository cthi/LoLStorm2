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
import android.widget.Button;
import android.widget.EditText;

import com.jclolstorm.lolstorm.R;
import com.jclolstorm.lolstorm.adapters.BaseHeaderRecyclerViewAdapter;
import com.jclolstorm.lolstorm.adapters.SummonerSearchAdapter;
import com.jclolstorm.lolstorm.presenters.SummonerSearchPresenter;
import com.jclolstorm.lolstorm.models.User;
import com.jclolstorm.lolstorm.utils.Constants;
import com.jclolstorm.lolstorm.utils.NetworkUtils;
import com.jclolstorm.lolstorm.views.SummonerSearchView;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import lolstormSDK.models.League;
import lolstormSDK.models.PlayerStatsSummaryList;
import lolstormSDK.models.RankedStats;
import lolstormSDK.models.RecentGames;
import lolstormSDK.models.Summoner;


public class SummonerSearchFragment extends Fragment implements SummonerSearchView, SummonerSearchAdapter.OnClick{

    @InjectView(R.id.summoner_search_rv)
    RecyclerView mRecyclerView;
    private View mHeader;

    private BaseHeaderRecyclerViewAdapter<User> mAdapter;
    private EditText mSearchText;
    private Button mSearchButton;

    SummonerSearchPresenter mPresenter;

    @Override
    public void displaySummonerNotFoundError() {
        Snackbar.make(mRecyclerView, R.string.error_summoner_not_found, Snackbar.LENGTH_SHORT)
                .show();
    }

    @Override
    public void displayInternetError() {
        Snackbar.make(mRecyclerView, R.string.error_internet_connection, Snackbar.LENGTH_SHORT)
                .show();
    }

    @Override
    public void populateAdapter(List<User> users) {
        mAdapter.populate(users);
    }

    public SummonerSearchFragment() {}

    public static SummonerSearchFragment newInstance() {
        return new SummonerSearchFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.summoner_search_fragment, container, false);

        ButterKnife.inject(this, view);

        initRecyclerView();
        initHeaderClick();

        mPresenter = new SummonerSearchPresenter(this);

        getActivity().setTitle(getString(R.string.summoner_search_title));
        return view;
    }

    private void initRecyclerView() {
        if (null != mRecyclerView) {
            mRecyclerView.setHasFixedSize(true);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

            mHeader = LayoutInflater.from(getActivity())
                    .inflate(R.layout.header_summoner_search, mRecyclerView, false);

            mAdapter = new SummonerSearchAdapter(new ArrayList<>(), mHeader, this, getActivity());
            mRecyclerView.setAdapter(mAdapter);
        }
    }

    private void initHeaderClick() {
        mSearchText = (EditText) mHeader.findViewById(R.id.header_summoner_edit_text);
        mSearchButton = (Button) mHeader.findViewById(R.id.header_summoner_search_button);
        if (null != mSearchButton) {
            mSearchButton.setOnClickListener((View v) ->
                            mPresenter.onSearch(mSearchText.getText().toString())
            );
        }
    }

    @Override
    public void onClick(int position) {

    }

    @Override
    public boolean hasConnection() {
        return NetworkUtils.hasConnection(getActivity());
    }

    @Override
    public void startPlayerView(User user) {

        Intent intent = new Intent(getActivity(), SummonerPagerActivity.class);

        Bundle bundle = new Bundle();
        bundle.putParcelable(Constants.USER_TAG, Parcels.wrap(user));

        intent.putExtras(bundle);

        startActivity(intent);
    }
}
