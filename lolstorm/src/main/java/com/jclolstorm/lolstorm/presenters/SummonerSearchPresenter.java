package com.jclolstorm.lolstorm.presenters;

import android.content.Context;

import com.jclolstorm.lolstorm.models.User;
import com.jclolstorm.lolstorm.ui.SummonerSearchFragment;
import com.jclolstorm.lolstorm.views.SummonerSearchView;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import lolstormSDK.models.League;
import lolstormSDK.models.PlayerStatsSummaryList;
import lolstormSDK.models.RankedStats;
import lolstormSDK.models.RecentGames;
import lolstormSDK.models.Summoner;
import lolstormSDK.modules.RiotApiModule;
import lolstormSDK.modules.SavedSummonerList;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;

public class SummonerSearchPresenter {

    private SummonerSearchView view;
    private SavedSummonerList savedSummonerList;
    private LinkedList<User> users;

    public SummonerSearchPresenter(SummonerSearchView view) {
        this.view = view;

        savedSummonerList = new SavedSummonerList(((SummonerSearchFragment) view).getActivity());
        initSummonerList();
    }

    private void initSummonerList() {
        users = savedSummonerList.getSavedUsers();

        view.populateAdapter(users);
    }

    public void onSearch(String summonerName) {
        if (!view.hasConnection()) {
            view.displayInternetError();
            return;
        }

        summonerName = summonerName.replaceAll("\\s","").toLowerCase();
        final String filteredName = summonerName;

        RiotApiModule
                .getSummonerIDFromName(filteredName)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Map<String, Summoner>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        view.displaySummonerNotFoundError();
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(Map<String, Summoner> stringSummonerMap) {
                        Summoner summoner = stringSummonerMap.get(filteredName);
                        saveUser(summoner);
                        loadData(summoner);
                    }
                });
    }

    private void loadData(Summoner s) {
        Summoner summoner = s;
        Observable statsObs = RiotApiModule.getSummonerStatsSummary(s.getId());
        Observable recentGamesObs = RiotApiModule.getSummonerRecentGames(s.getId());
        Observable rankedStatsObs = RiotApiModule.getSummonerRankedStats(s.getId());
        Observable leaguesObs = RiotApiModule.getSummonerLeagues(s.getId());

        Observable.mergeDelayError(statsObs, recentGamesObs, rankedStatsObs, leaguesObs)
                .observeOn(AndroidSchedulers.mainThread())
                .retry(3)
                .subscribe(new Subscriber() {

                    PlayerStatsSummaryList stats = null;
                    RecentGames games = null;
                    RankedStats rstats = null;
                    List<League> leagues = null;

                    @Override
                    public void onCompleted() {
                        view.startPlayerView(summoner, stats,games, rstats, leagues);
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(Object o) {
                        if (o instanceof PlayerStatsSummaryList) {
                            stats = (PlayerStatsSummaryList) o;
                        } else if (o instanceof RecentGames) {
                            games = (RecentGames) o;
                        } else if (o instanceof RankedStats) {
                            rstats = (RankedStats) o;
                        } else if (o instanceof Map) {
                            leagues = ((Map<String, List<League>>) o).get(Long.toString(s.getId()));
                        }
                    }
                });
    }

    private void saveUser(Summoner summoner) {
        for (User user : users) {
            if (user.getName().equals(summoner.getName()) &&
                    user.getRegion().equals("na")) {
                return;
            }
        }

        users.add(0, new User(summoner.getName(), "na", summoner.getProfileIconId()));
        savedSummonerList.updateSavedUsers(users);
    }
}
