package com.jclolstorm.lolstorm.presenters;


import com.jclolstorm.lolstorm.models.User;
import com.jclolstorm.lolstorm.views.SummonerLeaguesView;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import lolstormSDK.GameConstants;
import lolstormSDK.models.League;
import lolstormSDK.models.LeagueEntry;
import lolstormSDK.modules.RiotApiModule;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;

public class SummonerLeaguesPresenter {

    private final static String QUEUE_TYPE = "RANKED_SOLO_5x5";

    private League currentLeague;
    private List<LeagueEntry> currentEntries;
    private int currentDivision;

    private SummonerLeaguesView view;

    public SummonerLeaguesPresenter() {}

    public void setView(SummonerLeaguesView view) {
        this.view = view;
    }

    private void updateView() {
        view.populateAdapter(currentEntries);
        view.initHeaderData(currentLeague, currentDivision);
        view.hideLoading();
    }

    public void setUser(User user) {
        if (null == currentEntries || null == currentLeague) {
            getLeagues(user.getUserID());
        } else {
            updateView();
        }
    }

    private void getLeagues(long userID) {
        RiotApiModule.getSummonerLeagues(userID)
                .observeOn(AndroidSchedulers.mainThread())
                .retry(3)
                .subscribe(new Subscriber<Map<String, List<League>>>() {
                    @Override
                    public void onCompleted() {
                        updateView();
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.showNoDataView();
                    }

                    @Override
                    public void onNext(Map<String, List<League>> stringListMap) {
                        findQueue(stringListMap.get(Long.toString(userID)), userID);
                    }
                });
    }

    private void findQueue(List<League> leagues, long userID) {
        for (League league : leagues) {
            if (league.getQueue().equals(QUEUE_TYPE)) {
                currentLeague = league;
                sortEntriesIntoDivision(league.getEntries(), userID);
                break;
            }
        }
    }

    private void sortEntriesIntoDivision(List<LeagueEntry> entries, long userID) {
        ArrayList<ArrayList<LeagueEntry>> sorted = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            sorted.add(i, new ArrayList<>());
        }

        for (LeagueEntry i : entries) {
            if (i.getDivision().equals(GameConstants.DIV_1)) {
                sorted.get(0).add(i);
            } else if (i.getDivision().equals(GameConstants.DIV_2)) {
                sorted.get(1).add(i);
            } else if (i.getDivision().equals(GameConstants.DIV_3)) {
                sorted.get(2).add(i);
            } else if (i.getDivision().equals(GameConstants.DIV_4)) {
                sorted.get(3).add(i);
            } else {
                sorted.get(4).add(i);
            }
        }

        currentDivision = getCurrentDivision(sorted, userID);
        Collections.sort(sorted.get(currentDivision - 1));

        currentEntries = sorted.get(currentDivision - 1);
    }

    private int getCurrentDivision(ArrayList<ArrayList<LeagueEntry>> entries, Long id) {
        for (int i = 0; i < entries.size(); i++) {
            for (int u = 0; u < entries.get(i).size(); u++)
                if (Long.parseLong(entries.get(i).get(u).getPlayerOrTeamId()) == id) {
                    return  i + 1;
                }
        }
        return 0;
    }
}
