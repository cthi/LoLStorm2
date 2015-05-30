package com.jclolstorm.lolstorm.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jclolstorm.lolstorm.R;
import com.jclolstorm.lolstorm.adapters.SummonerGamesAdapter;
import com.jclolstorm.lolstorm.presenters.SummonerGamesPresenter;
import com.jclolstorm.lolstorm.ui.widgets.headers.SummonerGamesHeader;
import com.jclolstorm.lolstorm.utils.Constants;
import com.jclolstorm.lolstorm.views.SummonerGamesView;

import org.parceler.Parcels;
import butterknife.ButterKnife;
import butterknife.InjectView;
import lolstormSDK.models.RecentGames;

public class SummonerGamesFragment extends Fragment implements SummonerGamesView,
        SummonerGamesAdapter.OnClick {

    @InjectView(R.id.summoner_games_rv)
    RecyclerView mRecyclerView;
    private SummonerGamesHeader mHeader;

    private SummonerGamesPresenter presenter;

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
        initRecyclerView();

        presenter = new SummonerGamesPresenter();
        presenter.setView(this);
        presenter.setSummoner(Parcels.unwrap(getArguments()
                .getParcelable(Constants.SUMMONER_TAG)));
        presenter.setLeauges(Parcels.unwrap(getArguments()
                .getParcelable(Constants.LEAGUES_TAG)));
        presenter.setPlayerStatsSummary(Parcels.unwrap(getArguments()
                .getParcelable(Constants.PLAYER_STATS_SUMMARY_TAG)));
        presenter.initHeaderData();

        return view;
    }

    private void initHeader() {
        mHeader = new SummonerGamesHeader(getActivity());
    }

    private void initRecyclerView() {
        if (null != mRecyclerView) {
            mRecyclerView.setHasFixedSize(true);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

            initHeader();

            RecentGames rg = Parcels.unwrap(getArguments()
                    .getParcelable(Constants.RECENT_GAMES_TAG));

            SummonerGamesAdapter adapter =
                    new SummonerGamesAdapter(getActivity(), rg.getGames(), mHeader, this);
            mRecyclerView.setAdapter(adapter);
        }
    }

    @Override
    public void initHeaderData(String name, String info, int profileImage){
        mHeader.setPlayerName(name);
        mHeader.setPlayerInfo(info);
        mHeader.setPlayerIcon(profileImage);
    }

    @Override
    public void onClick(int position) {

    }

    @Override
    public boolean hasConnection() {
        //TODO fix
        return true;
    }
}
