package com.jclolstorm.lolstorm.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jclolstorm.lolstorm.R;
import com.jclolstorm.lolstorm.adapters.ChampionsAdapter;
import com.jclolstorm.lolstorm.presenters.ChampionsPresenter;
import com.jclolstorm.lolstorm.views.ChampionsView;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import lolstormSDK.models.Champion;

public class ChampionsFragment extends Fragment implements ChampionsView {

    @InjectView(R.id.champions_rv)
    RecyclerView mRecyclerView;

    private ChampionsAdapter mAdapter;
    private ChampionsPresenter mPresenter;

    public static ChampionsFragment newInstance() {
        return new ChampionsFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        View view = inflater.inflate(R.layout.champions_fragment, container, false);

        ButterKnife.inject(this, view);

        initRecyclerView();
        mPresenter = new ChampionsPresenter();
        mPresenter.setView(this);

        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(R.string.summoner_champions_title);
        return view;
    }

    private void initRecyclerView() {
        if (null != mRecyclerView) {
            mRecyclerView.setHasFixedSize(true);
            mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
            mAdapter = new ChampionsAdapter(getActivity(), new ArrayList<>());
            mRecyclerView.setAdapter(mAdapter);
        }
    }

    @Override
    public void populate(List<Champion> championList) {
        mAdapter.populate(championList);
    }
}
