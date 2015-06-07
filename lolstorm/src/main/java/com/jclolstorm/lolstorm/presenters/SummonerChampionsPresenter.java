package com.jclolstorm.lolstorm.presenters;

import com.jclolstorm.lolstorm.models.User;
import com.jclolstorm.lolstorm.views.SummonerChampionsView;

import java.util.Collections;
import java.util.List;

import lolstormSDK.models.ChampionStats;
import lolstormSDK.models.RankedStats;
import lolstormSDK.modules.RiotApiModule;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;

public class SummonerChampionsPresenter {

    private SummonerChampionsView mView;

    private List<ChampionStats> mStatList;
    private ChampionStats mHeaderStat;

    public SummonerChampionsPresenter() {
    }

    public void setView(SummonerChampionsView view) {
        this.mView = view;
    }

    public void setUser(User user) {
        if (null == mStatList || null == mHeaderStat) {
            getRankedStats(user.getUserID());
        } else {
            updateView();
        }
    }

    private void updateView() {
        mView.populateAdapter(mStatList);
        mView.setHeaderData(mHeaderStat);
        mView.hideLoading();
    }

    private void getRankedStats(long userID) {
        RiotApiModule.getSummonerRankedStats(userID).observeOn(AndroidSchedulers.mainThread())
                .retry(3).subscribe(new Subscriber<RankedStats>() {
            @Override
            public void onCompleted() {
                updateView();
            }

            @Override
            public void onError(Throwable e) {
                // TODO display error view
            }


            @Override
            public void onNext(RankedStats rankedStats) {

                mStatList = rankedStats.getChampions();
                Collections.sort(mStatList, ChampionStats::compareTo);

                mHeaderStat = mStatList.remove(0);
            }
        });
    }
}
