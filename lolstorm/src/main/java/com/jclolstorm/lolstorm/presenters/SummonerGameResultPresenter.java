package com.jclolstorm.lolstorm.presenters;


import com.jclolstorm.lolstorm.views.SummonerGameResultView;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import lolstormSDK.models.Game;
import lolstormSDK.models.Player;
import lolstormSDK.modules.RiotApiModule;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;

public class SummonerGameResultPresenter {

    private SummonerGameResultView view;
    private Game game;
    private Map<String, String> summonerNameIdMap;

    private Comparator<Player> winsComparator;
    private Comparator<Player> lossComparator;

    public SummonerGameResultPresenter () {
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

        RiotApiModule.getSummonerNamesFromId(playerIdList)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Map<String, String>>() {
                    @Override
                    public void onCompleted() {
                        setPlayerNames(game.getFellowPlayers(), summonerNameIdMap);
                        setPlayerWinningTeam(game.getFellowPlayers(), game.getStats().getWin(),
                                game.getTeamId());
                        sort(game.getFellowPlayers(), game.getStats().getWin());

                        view.populateAdapter(game.getFellowPlayers());
                    }

                    @Override
                    public void onError(Throwable e) {
                        // display error view
                    }

                    @Override
                    public void onNext(Map<String, String> resultMap) {
                        summonerNameIdMap = resultMap;
                    }
                });
    }

    private void initComparator() {
        winsComparator = (lhs, rhs) -> {
            if (lhs.isWinner() && !rhs.isWinner()){
                return -1;
            } else if (!lhs.isWinner() && rhs.isWinner()) {
                return 1;
            } else {
                return lhs.getSummonerName().compareTo(rhs.getSummonerName());
            }
        };

        lossComparator = (lhs , rhs) -> {
            if (lhs.isWinner() && !rhs.isWinner()){
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

        if (playerList != null){
            for (Player i : playerList) {
                playerIdList += i.getSummonerId() + ",";
            }
        }

        return playerIdList;
    }

    private void setPlayerNames(List<Player> playerList, Map<String, String> summonerNameIdMap) {
        for (Player player : playerList) {
            player.setSummonerName(summonerNameIdMap.get(Long.toString(player.getSummonerId())));
        }
    }

    private void setPlayerWinningTeam(List<Player> playerList, boolean creatorWon, int creatorId) {
        for (Player player : playerList) {
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
