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
