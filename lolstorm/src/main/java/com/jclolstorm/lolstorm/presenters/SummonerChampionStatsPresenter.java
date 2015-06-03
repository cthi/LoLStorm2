package com.jclolstorm.lolstorm.presenters;

import com.jclolstorm.lolstorm.R;
import com.jclolstorm.lolstorm.models.Stat;
import com.jclolstorm.lolstorm.views.SummonerChampionStatsView;

import java.util.ArrayList;

import lolstormSDK.GameConstants;
import lolstormSDK.models.AggregatedStats;
import lolstormSDK.models.ChampionStats;

public class SummonerChampionStatsPresenter {

    private SummonerChampionStatsView view;

    public SummonerChampionStatsPresenter() {
    }

    public void setStats(ChampionStats stats) {
        view.setTitle(GameConstants.CHAMPION_NAME_MAP.get(Integer.toString(stats.getId())));
        processStats(stats.getStats());
    }

    public void setView(SummonerChampionStatsView view) {
        this.view = view;
    }

    private void processStats(AggregatedStats stats) {
        ArrayList<Stat> statList = new ArrayList<>();

        statList.add(new Stat("Games Played", stats.getTotalSessionsPlayed(), R.color.green_primary));
        statList.add(new Stat("Games Won", stats.getTotalSessionsWon(), R.color.green_primary));
        statList.add(new Stat("Games Lost", stats.getTotalSessionsLost(), R.color.green_primary));
        statList.add(new Stat("Gold Earned", stats.getTotalGoldEarned(), R.color.green_primary));
        statList.add(new Stat("Kills", stats.getTotalChampionKills(), R.color.green_primary));
        statList.add(new Stat("Deaths", stats.getTotalDeathsPerSession(), R.color.green_primary));
        statList.add(new Stat("Assists", stats.getTotalAssists(), R.color.green_primary));
        statList.add(new Stat("Max Kills", stats.getMostChampionKillsPerSession(), R.color.green_primary));
        statList.add(new Stat("Damage Dealt", stats.getTotalDamageDealt(), R.color.green_primary));
        statList.add(new Stat("Physical Damage", stats.getTotalPhysicalDamageDealt(), R.color.green_primary));
        statList.add(new Stat("Magic Damage Dealt", stats.getTotalMagicDamageDealt(), R.color.green_primary));
        statList.add(new Stat("Damage Taken", stats.getTotalDamageTaken(), R.color.green_primary));
        statList.add(new Stat("Double Kills", stats.getTotalDoubleKills(), R.color.green_primary));
        statList.add(new Stat("Triple Kills", stats.getTotalTripleKills(), R.color.green_primary));
        statList.add(new Stat("Quadra Kills", stats.getTotalQuadraKills(), R.color.green_primary));
        statList.add(new Stat("Penta Kills", stats.getTotalPentaKills(), R.color.green_primary));
        statList.add(new Stat("Legendary Kills", stats.getTotalUnrealKills(), R.color.green_primary));
        statList.add(new Stat("Minions Killed", stats.getTotalMinionKills(), R.color.green_primary));
        statList.add(new Stat("Jungle Minions Killed", stats.getTotalNeutralMinionsKilled(), R.color.green_primary));
        statList.add(new Stat("Turrets Destroyed", stats.getTotalTurretsKilled(), R.color.green_primary));

        view.populateAdapter(statList);
    }
}
