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

package com.geomorphology.lolstorm.ui.widgets.headers;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.geomorphology.lolstorm.R;

import java.text.DecimalFormat;

import butterknife.ButterKnife;
import butterknife.InjectView;
import lolstormSDK.models.AggregatedStats;
import lolstormSDK.models.ChampionStats;

public class SummonerChampionsHeader extends RelativeLayout {

    @InjectView(R.id.header_summoner_champions_kda)
    TextView mSummonerTotalKda;
    @InjectView(R.id.header_summoner_champions_games)
    TextView mSummonerTotalGames;

    public SummonerChampionsHeader(Context context) {
        super(context, null);
        init();
    }

    public SummonerChampionsHeader(Context context, AttributeSet attrs) {
        super(context, attrs, 0);
        init();
    }

    public SummonerChampionsHeader(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public void setStats(ChampionStats championStats) {
        mSummonerTotalKda.setText(formatKDA(championStats.getStats()));
        mSummonerTotalGames.setText(formatStats(championStats.getStats()));
    }

    private void init() {
        inflate(getContext(), R.layout.header_summoner_champions, this);
        ButterKnife.inject(this);
    }

    private String formatKDA(AggregatedStats stats) {
        int played = stats.getTotalSessionsPlayed();
        String kills = String.format("%.1f", (double) stats.getTotalChampionKills() / played);
        String deaths = String.format("%.1f", (double) stats.getTotalDeathsPerSession() / played);
        String assists = String.format("%.1f", (double) stats.getTotalAssists() / played);

        return String.format("%s / %s / %s", kills, deaths, assists);
    }

    private String formatStats(AggregatedStats stats) {
        DecimalFormat decimalFormat = new DecimalFormat("#.#");
        int played = stats.getTotalSessionsPlayed();
        int won = stats.getTotalSessionsWon();
        int lost = stats.getTotalSessionsLost();
        float wonPct = (float) won / played * 100;
        float lostPct = (float) lost / played * 100;

        return String.format("W: %d - %s%% L: %d - %s%%", won, decimalFormat.format(wonPct),
                lost, decimalFormat.format(lostPct));
    }
}
