package com.jclolstorm.lolstorm.presenters;

import com.jclolstorm.lolstorm.models.User;
import com.jclolstorm.lolstorm.ui.SummonerSearchFragment;
import com.jclolstorm.lolstorm.views.SummonerSearchView;

import java.util.LinkedList;
import java.util.Map;

import lolstormSDK.models.Summoner;
import lolstormSDK.modules.RiotApiModule;
import lolstormSDK.modules.SavedSummonerList;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;

public class SummonerSearchPresenter {

    private SummonerSearchView view;
    private SavedSummonerList savedSummonerList;
    private LinkedList<User> users;

    public SummonerSearchPresenter(SummonerSearchView view) {
        this.view = view;

        savedSummonerList = new SavedSummonerList(((SummonerSearchFragment) view).getActivity());
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
                    User newUser;

                    @Override
                    public void onCompleted() {
                        view.startPlayerView(newUser);
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.displaySummonerNotFoundError();
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(Map<String, Summoner> stringSummonerMap) {
                        Summoner summoner = stringSummonerMap.get(filteredName);
                        newUser = new User(summoner.getName(), "na", summoner.getProfileIconId(),
                                summoner.getSummonerLevel(), summoner.getId());

                        saveUser(newUser);
                    }
                });
    }

    public void removeUser(User toRemove) {
        for (User user : users) {
            if (user.getName().equals(toRemove.getName()) &&
                    user.getRegion().equals("na")) {
                users.remove(user);
                savedSummonerList.updateSavedUsers(users);
                return;
            }
        }
    }

    private void saveUser(User newUser) {
        for (User user : users) {
            if (user.getName().equals(newUser.getName()) &&
                    user.getRegion().equals("na")) {
                return;
            }
        }

        users.add(0, newUser);
        savedSummonerList.updateSavedUsers(users);
    }

    public void onResume() {
        initSummonerList();
    }
}
