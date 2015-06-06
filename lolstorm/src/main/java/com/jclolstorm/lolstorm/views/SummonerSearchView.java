package com.jclolstorm.lolstorm.views;

import com.jclolstorm.lolstorm.models.User;

import java.util.List;

public interface SummonerSearchView extends BaseView {

    void displaySummonerNotFoundError();

    void displayInternetError();

    void populateAdapter(List<User> users);

    void startPlayerView(User user);

}
