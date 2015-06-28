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

import com.geomorphology.lolstorm.R;
import com.geomorphology.lolstorm.adapters.ChampionsAdapter;
import com.geomorphology.lolstorm.presenters.ChampionsPresenter;
import com.geomorphology.lolstorm.utils.Constants;
import com.geomorphology.lolstorm.views.ChampionsView;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import lolstormSDK.models.Champion;

public class ChampionsFragment extends Fragment implements ChampionsView, ChampionsAdapter
        .OnChampionItemClick {

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

        getActivity().setTitle(R.string.summoner_champions_title);
        return view;
    }

    private void initRecyclerView() {
        if (null != mRecyclerView) {
            mRecyclerView.setHasFixedSize(true);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            mAdapter = new ChampionsAdapter(getActivity(), this, new ArrayList<>());
            mRecyclerView.setAdapter(mAdapter);
        }
    }

    @Override
    public void populate(List<Champion> championList) {
        mAdapter.populate(championList);
    }

    @Override
    public void onChampionClick(long championId) {
        Intent intent = new Intent(getActivity(), ChampionSpellActivity.class);

        Bundle bundle = new Bundle();
        bundle.putInt(Constants.CHAMPION_ID_TAG, (int) championId);

        intent.putExtras(bundle);
        startActivity(intent);
    }
}
