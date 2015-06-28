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

import com.geomorphology.lolstorm.models.Stat;
import com.geomorphology.lolstorm.views.SummonerChampionStatsView;

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

        statList.add(new Stat("Games Played", stats.getTotalSessionsPlayed()));
        statList.add(new Stat("Games Won", stats.getTotalSessionsWon()));
        statList.add(new Stat("Games Lost", stats.getTotalSessionsLost()));
        statList.add(new Stat("Gold Earned", stats.getTotalGoldEarned()));
        statList.add(new Stat("Kills", stats.getTotalChampionKills()));
        statList.add(new Stat("Deaths", stats.getTotalDeathsPerSession()));
        statList.add(new Stat("Assists", stats.getTotalAssists()));
        statList.add(new Stat("Max Kills", stats.getMostChampionKillsPerSession()));
        statList.add(new Stat("Damage Dealt", stats.getTotalDamageDealt()));
        statList.add(new Stat("Physical Damage", stats.getTotalPhysicalDamageDealt()));
        statList.add(new Stat("Magic Damage Dealt", stats.getTotalMagicDamageDealt()));
        statList.add(new Stat("Damage Taken", stats.getTotalDamageTaken()));
        statList.add(new Stat("Double Kills", stats.getTotalDoubleKills()));
        statList.add(new Stat("Triple Kills", stats.getTotalTripleKills()));
        statList.add(new Stat("Quadra Kills", stats.getTotalQuadraKills()));
        statList.add(new Stat("Penta Kills", stats.getTotalPentaKills()));
        statList.add(new Stat("Legendary Kills", stats.getTotalUnrealKills()));
        statList.add(new Stat("Minions Killed", stats.getTotalMinionKills()));
        statList.add(new Stat("Jungle Minions Killed", stats.getTotalNeutralMinionsKilled()));
        statList.add(new Stat("Turrets Destroyed", stats.getTotalTurretsKilled()));

        view.populateAdapter(statList);
    }
}
