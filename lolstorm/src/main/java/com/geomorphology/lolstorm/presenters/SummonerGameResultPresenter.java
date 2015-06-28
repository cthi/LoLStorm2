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

package com.geomorphology.lolstorm.presenters;

import com.geomorphology.lolstorm.R;
import com.geomorphology.lolstorm.views.SummonerGameResultView;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import lolstormSDK.RiotErrors;
import lolstormSDK.models.Game;
import lolstormSDK.models.Player;
import lolstormSDK.models.Summoner;
import lolstormSDK.modules.RiotApiModule;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;

public class SummonerGameResultPresenter {

    private SummonerGameResultView view;
    private Game game;
    private Map<String, Summoner> summonerMap;

    private Comparator<Player> winsComparator;
    private Comparator<Player> lossComparator;

    public SummonerGameResultPresenter() {
        initComparator();
    }

    public void setView(SummonerGameResultView view) {
        this.view = view;
    }

    public void setGame(Game game) {
        this.game = game;
        loadPlayersFromId(game.getFellowPlayers());
    }

    private void loadPlayersFromId(List<Player> playerList) {
        String playerIdList = constructQueryList(playerList);

        RiotApiModule.getSummonersById(playerIdList).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Map<String, Summoner>>() {
            @Override
            public void onCompleted() {
                setPlayerInfo(game.getFellowPlayers(), summonerMap, game.getStats().getWin(),
                        game.getTeamId());
                sort(game.getFellowPlayers(), game.getStats().getWin());

                view.populateAdapter(game.getFellowPlayers());
            }

            @Override
            public void onError(Throwable e) {
                if (e instanceof RiotErrors.RiotConnectionException) {
                    view.showErrorView(R.string.error_internet_connection);
                } else if (e instanceof RiotErrors.RiotDataNotFoundException) {
                    view.showErrorView(R.string.error_data_not_found);
                } else if (e instanceof RiotErrors.RiotServerFailureException) {
                    view.showErrorView(R.string.error_server_failure);
                } else if (e instanceof RiotErrors.RiotApiLimitException) {
                    view.showErrorView(R.string.error_server_failure);
                } else if (e instanceof RiotErrors.RiotGenericFailureException) {
                    view.showErrorView(R.string.error_app_update);
                } else {
                    view.showErrorView(R.string.error_app_unknown);
                }
            }

            @Override
            public void onNext(Map<String, Summoner> stringSummonerMap) {
                summonerMap = stringSummonerMap;
            }
        });
    }

    private void initComparator() {
        winsComparator = (lhs, rhs) -> {
            if (lhs.isWinner() && !rhs.isWinner()) {
                return -1;
            } else if (!lhs.isWinner() && rhs.isWinner()) {
                return 1;
            } else {
                return lhs.getSummonerName().compareTo(rhs.getSummonerName());
            }
        };

        lossComparator = (lhs, rhs) -> {
            if (lhs.isWinner() && !rhs.isWinner()) {
                return 1;
            } else if (!lhs.isWinner() && rhs.isWinner()) {
                return -1;
            } else {
                return lhs.getSummonerName().compareTo(rhs.getSummonerName());
            }
        };
    }

    private String constructQueryList(List<Player> playerList) {
        String playerIdList = "";

        if (playerList != null) {
            for (Player i : playerList) {
                playerIdList += i.getSummonerId() + ",";
            }
        }

        return playerIdList;
    }

    private void setPlayerInfo(List<Player> playerList, Map<String, Summoner> summonerInfoMap,
                               boolean creatorWon, int creatorId) {
        for (Player player : playerList) {
            Summoner playerAsSummoner = summonerInfoMap.get(Long.toString(player.getSummonerId()));
            player.setSummonerName(playerAsSummoner.getName());
            player.setLevel(playerAsSummoner.getSummonerLevel());
            player.setProfileIcon(playerAsSummoner.getProfileIconId());
            player.setIsWinner(creatorWon && player.getTeamId() == creatorId || !creatorWon &&
                    player.getTeamId() != creatorId);
        }
    }

    private void sort(List<Player> playerList, boolean creatorWon) {
        if (creatorWon) {
            Collections.sort(playerList, winsComparator);
        } else {
            Collections.sort(playerList, lossComparator);
        }
    }
}
