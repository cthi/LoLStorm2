package com.jclolstorm.lolstorm.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jclolstorm.lolstorm.R;


import butterknife.ButterKnife;
import butterknife.InjectView;

public class ChampionsFragment extends Fragment {

    @InjectView(R.id.champions_rv)
    RecyclerView mRecyclerView;

    public static ChampionsFragment newInstance() {
        return new ChampionsFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.champions_fragment, container, false);

        ButterKnife.inject(this, view);

        initRecyclerView();

        return view;
    }

    private void initRecyclerView() {
        if (null != mRecyclerView) {
            mRecyclerView.setHasFixedSize(true);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        }
    }
}
