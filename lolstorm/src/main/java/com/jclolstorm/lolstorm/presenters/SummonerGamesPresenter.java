package com.jclolstorm.lolstorm.presenters;

import com.jclolstorm.lolstorm.views.SummonerGamesView;

import java.util.List;

import lolstormSDK.models.League;
import lolstormSDK.models.LeagueEntry;
import lolstormSDK.models.PlayerStatsSummary;
import lolstormSDK.models.PlayerStatsSummaryList;
import lolstormSDK.models.Summoner;

public class SummonerGamesPresenter {

    private SummonerGamesView view;

    private Summoner summoner;
    private List<League> leagues;
    private PlayerStatsSummaryList playerStatsSummary;

    public SummonerGamesPresenter () {}

    public void setView(SummonerGamesView view) {
        this.view = view;
    }

    public void setSummoner(Summoner summoner) {
        this.summoner = summoner;
    }

    public void setLeauges(List<League> leagues) {
        this.leagues = leagues;
    }

    public void setPlayerStatsSummary(PlayerStatsSummaryList playerStatsSummary) {
        this.playerStatsSummary = playerStatsSummary;
    }

    public void initHeaderData() {
        int wins = 0;

        for (PlayerStatsSummary i : playerStatsSummary.getPlayerStatSummaries()) {
            if (i.getPlayerStatSummaryType().equals("Unranked")) {
                wins = i.getWins();
                break;
            }
        }

        String rating = null;

        for (League i : leagues) {
            if (i.getQueue().equals("RANKED_SOLO_5x5")) {
                for (LeagueEntry u : i.getEntries()) {
                    if (Long.parseLong(u.getPlayerOrTeamId()) == summoner.getId()) {

                        rating = i.getTier() + " " + u.getDivision() + " " + u.getLeaguePoints() + "LP";

                        break;
                    }
                }
                break;
            }
        }

        String name = summoner.getName();
        String info = String.format("Level %d %s %d Wins", summoner.getSummonerLevel(), rating, wins);
        int profileImage = summoner.getProfileIconId();

        view.initHeaderData(name, info, profileImage);
    }
}
