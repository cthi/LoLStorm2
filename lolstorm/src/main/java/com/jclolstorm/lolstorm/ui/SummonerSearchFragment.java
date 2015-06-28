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


import android.app.Activity;
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
import lolstormSDK.RiotEndpoint;

public class SummonerSearchFragment extends Fragment implements SummonerSearchView, SummonerSearchAdapter.OnSummonerItemClick {

    @InjectView(R.id.summoner_search_rv)
    RecyclerView mRecyclerView;
    private View mHeader;

    private OnFavorite mFavoriteCallback;
    private BaseHeaderRecyclerViewAdapter<User> mAdapter;
    private EditText mSearchText;
    private Button mSearchButton;

    SummonerSearchPresenter mPresenter;

    public interface OnFavorite {
        void onFavorite(User user);
    }

    public SummonerSearchFragment() {}

    public static SummonerSearchFragment newInstance() {
        return new SummonerSearchFragment();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mFavoriteCallback = (OnFavorite) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement onSummonerFavorite");
        }
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

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.onResume();
    }

    @Override
    public void displayErrorMessage(int errorMessage) {
        Snackbar.make(mRecyclerView, errorMessage, Snackbar.LENGTH_LONG)
                .show();
    }

    @Override
    public void populateAdapter(List<User> users) {
        mAdapter.populate(users);
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
    public void onSummonerClick(User user) {
        if (NetworkUtils.hasConnection(getActivity())) {
            startPlayerView(user);
            RiotEndpoint.getInstance().setRegion(user.getRegionId());
        } else {
            Snackbar.make(mRecyclerView, R.string.error_internet_connection, Snackbar.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onSummonerFavorite(User user) {
        Snackbar.make(mRecyclerView, R.string.player_updated, Snackbar.LENGTH_SHORT).show();
        mFavoriteCallback.onFavorite(user);
    }

    @Override
    public void onSummonerRemove(User user) {
        mPresenter.removeUser(user);
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
