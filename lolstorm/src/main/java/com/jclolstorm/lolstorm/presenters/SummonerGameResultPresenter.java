package com.jclolstorm.lolstorm.presenters;


import com.jclolstorm.lolstorm.views.SummonerGameResultView;

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

    public void setView(SummonerGameResultView view) {
        this.view = view;
    }

    public void setGame(Game game) {
        this.game = game;
        loadPlayersFromId(game.getFellowPlayers());
    }

    private void loadPlayersFromId(List<Player> playerList) {
        String playerIdList = "";

        if (playerList != null){
            for (Player i : playerList) {
                playerIdList += i.getSummonerId() + ",";
            }
        }

        RiotApiModule.getSummonerNamesFromId(playerIdList)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Map<String, String>>() {
                    @Override
                    public void onCompleted() {
                        setPlayerNames(game.getFellowPlayers(), summonerNameIdMap);
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

    private void setPlayerNames(List<Player> playerList, Map<String, String> summonerNameIdMap) {
        for (Player player : playerList) {
            player.setSummonerName(summonerNameIdMap.get(Long.toString(player.getSummonerId())));
        }
    }
}
