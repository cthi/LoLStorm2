package com.jclolstorm.lolstorm.ui.widgets.headers;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jclolstorm.lolstorm.R;

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
        int played = stats.getTotalSessionsPlayed();
        int won = stats.getTotalSessionsWon();
        int lost = stats.getTotalSessionsLost();

        return String.format(String.format("Games: %d Won: %d Lost: %d", played, won, lost));
    }
}
