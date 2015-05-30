package com.jclolstorm.lolstorm.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jclolstorm.lolstorm.R;
import com.jclolstorm.lolstorm.adapters.BaseHeaderRecyclerViewAdapter;
import com.jclolstorm.lolstorm.adapters.SummonerChampionsAdapter;
import com.jclolstorm.lolstorm.presenters.SummonerChampionsPresenter;
import com.jclolstorm.lolstorm.ui.widgets.headers.SummonerChampionsHeader;
import com.jclolstorm.lolstorm.utils.Constants;
import com.jclolstorm.lolstorm.views.SummonerChampionsView;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import lolstormSDK.models.ChampionStats;

public class SummonerChampionsFragment extends Fragment implements
        SummonerChampionsView, SummonerChampionsAdapter.OnClick {

    @InjectView(R.id.summoner_champions_rv)
    RecyclerView mRecyclerView;

    private SummonerChampionsHeader header;
    private SummonerChampionsPresenter presenter;

    public static SummonerChampionsFragment newInstance(Bundle bundle) {
        SummonerChampionsFragment fragment = new SummonerChampionsFragment();
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.summoner_champions_fragment, container, false);

        ButterKnife.inject(this, view);

        header = new SummonerChampionsHeader(getActivity());
        initRecyclerView();

        presenter = new SummonerChampionsPresenter();
        presenter.setView(this);
        presenter.setRankedStats(Parcels.unwrap(getArguments()
                .getParcelable(Constants.RANKED_STATS_TAG)));

        return view;
    }

    @Override
    public void populateAdapter(List<ChampionStats> championStats) {
        ((BaseHeaderRecyclerViewAdapter) mRecyclerView.getAdapter()).populate(championStats);
    }

    @Override
    public void setHeaderData(ChampionStats championStat) {
        header.setStats(championStat);
    }

    private void initRecyclerView() {
        if (null != mRecyclerView) {
            mRecyclerView.setHasFixedSize(true);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

            SummonerChampionsAdapter adapter =
                    new SummonerChampionsAdapter(getActivity(), this, new ArrayList<>(), header);
            mRecyclerView.setAdapter(adapter);
        }
    }

    @Override
    public void onClick(ChampionStats championStats) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(Constants.RANKED_CHAMPION_STATS_TAG,Parcels.wrap(championStats));

        Intent intent = new Intent(getActivity(), SummonerChampionStatsActivity.class);
        intent.putExtras(bundle);

        startActivity(intent);
    }
}
