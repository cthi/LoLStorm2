package com.jclolstorm.lolstorm.presenters;

import com.jclolstorm.lolstorm.models.User;
import com.jclolstorm.lolstorm.views.SummonerGamesView;

import java.util.List;

import lolstormSDK.models.Game;
import lolstormSDK.models.RecentGames;
import lolstormSDK.modules.RiotApiModule;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;

public class SummonerGamesPresenter {

    private SummonerGamesView view;
    private List<Game> gameList;

    public SummonerGamesPresenter() {
    }

    public void setView(SummonerGamesView view) {
        this.view = view;
    }

    private void updateView() {
        view.populate(gameList);
        view.hideLoading();
    }

    public void setUser(User user) {
        if (null == gameList) {
            getRecentGames(user.getUserID());
        } else {
            updateView();
        }
    }

    private void getRecentGames(long userID) {
        RiotApiModule.getSummonerRecentGames(userID).observeOn(AndroidSchedulers.mainThread())
                .retry(3).subscribe(new Subscriber<RecentGames>() {
            @Override
            public void onCompleted() {
                updateView();
            }

            @Override
            public void onError(Throwable e) {
                // TODO show error view
            }

            @Override
            public void onNext(RecentGames recentGames) {
                gameList = recentGames.getGames();
            }
        });
    }
}
