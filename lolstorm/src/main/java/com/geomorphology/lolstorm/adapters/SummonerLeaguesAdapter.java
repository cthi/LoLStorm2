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

package com.geomorphology.lolstorm.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.geomorphology.lolstorm.R;
import com.geomorphology.lolstorm.ui.widgets.MiniSeries;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import lolstormSDK.models.LeagueEntry;

public class SummonerLeaguesAdapter extends BaseHeaderRecyclerViewAdapter<LeagueEntry> {

    private Context mContext;
    private List<LeagueEntry> mEntryList;
    private View mHeader;
    private OnLeagueItemClick mListener;

    public interface OnLeagueItemClick {
        void onSummonerClick(LeagueEntry leagueEntry);
    }

    public SummonerLeaguesAdapter(Context context, OnLeagueItemClick listener, List<LeagueEntry> entryList, View header) {
        this.mContext = context;
        this.mListener = listener;
        this.mEntryList = entryList;
        this.mHeader = header;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.item_summoner_league_rank)
        TextView mSummonerRank;
        @InjectView(R.id.item_summoner_league_name)
        TextView mSummonerName;
        @InjectView(R.id.item_summoner_league_wins)
        TextView mSummonerWins;
        @InjectView(R.id.item_summoner_league_lp)
        TextView mSummonerLp;
        @InjectView(R.id.item_summoner_league_miniseries)
        MiniSeries mSummonerSeries;

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
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_summoner_league, parent, false);

            ViewHolder viewHolder = new ViewHolder(view);
            view.setOnClickListener(new OnChampionClickedListener(viewHolder));

            return viewHolder;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) != HEADER) {
            ViewHolder viewHolder = (ViewHolder) holder;

            LeagueEntry currentEntry = mEntryList.get(position - 1);
            viewHolder.mSummonerRank.setText(Integer.toString(position));
            viewHolder.mSummonerName.setText(currentEntry.getPlayerOrTeamName());
            viewHolder.mSummonerWins.setText(winsFormatter(currentEntry.getWins()));
            viewHolder.mSummonerLp.setText(Integer.toString(currentEntry.getLeaguePoints()));
            viewHolder.mSummonerSeries.setEntry(currentEntry, viewHolder.mSummonerLp);
        }
    }

    @Override
    public int getItemCount() {
        return mEntryList.size() + 1;
    }

    private class OnChampionClickedListener implements View.OnClickListener {
        RecyclerView.ViewHolder viewHolder;

        public OnChampionClickedListener(RecyclerView.ViewHolder viewHolder) {
            this.viewHolder = viewHolder;
        }

        @Override
        public void onClick(View v) {
            mListener.onSummonerClick(mEntryList.get(viewHolder.getAdapterPosition() - 1));
        }
    }

    @Override
    public void populate(List<LeagueEntry> entryList) {
        this.mEntryList = entryList;
        notifyDataSetChanged();
    }

    private static String winsFormatter(int wins) {
        return String.format("%d Wins", wins);
    }
}
