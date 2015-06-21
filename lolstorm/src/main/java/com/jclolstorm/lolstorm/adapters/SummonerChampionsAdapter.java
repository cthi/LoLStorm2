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

package com.jclolstorm.lolstorm.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jclolstorm.lolstorm.R;
import com.jclolstorm.lolstorm.utils.ResourceUtils;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import lolstormSDK.GameConstants;
import lolstormSDK.models.AggregatedStats;
import lolstormSDK.models.ChampionStats;

public class SummonerChampionsAdapter extends BaseHeaderRecyclerViewAdapter<ChampionStats> {

    private Context mContext;
    private List<ChampionStats> mChampionStatList;
    private View mHeader;
    private OnChampionItemClick mListener;

    public interface OnChampionItemClick {
        void onChampionClick(ChampionStats championStats);
    }

    public SummonerChampionsAdapter(Context context, OnChampionItemClick callback, List<ChampionStats>
            championStatList, View header) {
        this.mContext = context;
        this.mListener = callback;
        this.mChampionStatList = championStatList;
        this.mHeader = header;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.item_summoner_champion_image)
        ImageView mChampionImage;
        @InjectView(R.id.item_summoner_champion_name)
        TextView mChampionName;
        @InjectView(R.id.item_summoner_champion_kda)
        TextView mChampionKda;
        @InjectView(R.id.item_summoner_champion_stats)
        TextView mChampionStats;

        public ViewHolder(View view) {
            super(view);

            ButterKnife.inject(this, view);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int position) {
        if (getItemViewType(position) == HEADER) {
            return new HeaderViewHolder(mHeader);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout
                    .item_summoner_champion, parent, false);

            ViewHolder viewHolder = new ViewHolder(view);
            view.setOnClickListener(new OnChampionClickedListener(viewHolder));

            return viewHolder;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) != HEADER) {
            ViewHolder viewHolder = (ViewHolder) holder;
            ChampionStats currentStat = mChampionStatList.get(position - 1);

            Glide.with(mContext).load(ResourceUtils.championDrawableFromID(currentStat.getId(),
                    mContext)).into(viewHolder.mChampionImage);
            viewHolder.mChampionName.setText(GameConstants.CHAMPION_NAME_MAP.get(Integer.toString
                    (currentStat.getId())));
            viewHolder.mChampionKda.setText(formatKDA(currentStat.getStats()));
            viewHolder.mChampionStats.setText(formatStats(currentStat.getStats()));
        }
    }

    @Override
    public int getItemCount() {
        return mChampionStatList.size() + 1;
    }

    private class OnChampionClickedListener implements View.OnClickListener {
        RecyclerView.ViewHolder viewHolder;

        public OnChampionClickedListener(RecyclerView.ViewHolder viewHolder) {
            this.viewHolder = viewHolder;
        }

        @Override
        public void onClick(View v) {
            mListener.onChampionClick(mChampionStatList.get(viewHolder.getAdapterPosition() - 1));
        }
    }

    @Override
    public void populate(List<ChampionStats> championStatsList) {
        this.mChampionStatList = championStatsList;
        notifyDataSetChanged();
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

        return String.format("Games: %d Won: %d Lost: %d", played, won, lost);
    }
}
