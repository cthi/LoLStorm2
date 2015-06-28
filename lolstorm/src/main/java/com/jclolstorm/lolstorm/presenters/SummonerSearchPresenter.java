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

package com.jclolstorm.lolstorm.presenters;

import com.jclolstorm.lolstorm.R;
import com.jclolstorm.lolstorm.models.User;
import com.jclolstorm.lolstorm.ui.SummonerSearchFragment;
import com.jclolstorm.lolstorm.views.SummonerSearchView;

import java.util.LinkedList;
import java.util.Map;

import lolstormSDK.RiotEndpoint;
import lolstormSDK.RiotErrors;
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
            view.displayErrorMessage(R.string.error_internet_connection);
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
                        if (e instanceof RiotErrors.RiotConnectionException) {
                            view.displayErrorMessage(R.string.error_internet_connection);
                        } else if (e instanceof RiotErrors.RiotDataNotFoundException) {
                            view.displayErrorMessage(R.string.error_summoner_not_found);
                        } else if (e instanceof RiotErrors.RiotServerFailureException) {
                            view.displayErrorMessage(R.string.error_server_failure);
                        } else if (e instanceof RiotErrors.RiotApiLimitException) {
                            view.displayErrorMessage(R.string.error_server_failure);
                        } else if (e instanceof RiotErrors.RiotGenericFailureException) {
                            view.displayErrorMessage(R.string.error_app_update);
                        } else {
                            view.displayErrorMessage(R.string.error_app_unknown);
                        }
                    }

                    @Override
                    public void onNext(Map<String, Summoner> stringSummonerMap) {
                        Summoner summoner = stringSummonerMap.get(filteredName);
                        newUser = new User(summoner.getName(),
                                RiotEndpoint.getInstance().getRegionId(),
                                summoner.getProfileIconId(),
                                summoner.getSummonerLevel(), summoner.getId());

                        saveUser(newUser);
                    }
                });
    }

    public void removeUser(User toRemove) {
        for (User user : users) {
            if (user.getName().equals(toRemove.getName())
                    && user.getRegionId() == toRemove.getRegionId()) {
                users.remove(user);
                savedSummonerList.updateSavedUsers(users);
                return;
            }
        }
    }

    private void saveUser(User newUser) {
        for (User user : users) {
            if (user.getName().equals(newUser.getName())
                    && user.getRegionId() == newUser.getRegionId()) {
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
