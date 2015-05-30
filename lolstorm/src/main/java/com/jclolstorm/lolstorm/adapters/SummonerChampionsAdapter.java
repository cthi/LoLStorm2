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
import lolstormSDK.models.ChampionStat;
import lolstormSDK.models.ChampionStats;

public class SummonerChampionsAdapter extends BaseHeaderRecyclerViewAdapter<ChampionStats> {

    private Context mContext;
    private List<ChampionStats> mChampionStatList;
    private View mHeader;
    private OnClick mCallback;
    private View.OnClickListener mOnClickListener;

    public interface OnClick {
        void onClick(ChampionStats chapionStat);
    }

    public SummonerChampionsAdapter(Context context, OnClick callback, List<ChampionStats> championStatList,
                                    View header) {
        this.mContext = context;
        this.mCallback = callback;
        this.mChampionStatList = championStatList;
        this.mHeader = header;
        this.mOnClickListener = (View v) -> mCallback.onClick(getChampionStat(v));
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int position) {
        if (getItemViewType(position) == HEADER) {
            return new HeaderViewHolder(mHeader);
        } else {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_summoner_champion, parent, false);

            view.setOnClickListener(mOnClickListener);

            return new ViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) != HEADER) {
            ViewHolder viewHolder = (ViewHolder) holder;
            ChampionStats currentStat = mChampionStatList.get(position - 1);

            Glide.with(mContext)
                    .load(ResourceUtils.championDrawableFromID(currentStat.getId(), mContext))
                    .into(viewHolder.mChampionImage);
            viewHolder.mChampionName.setText(GameConstants.CHAMPION_NAME_MAP
                    .get(Integer.toString(currentStat.getId())));
            viewHolder.mChampionKda.setText(formatKDA(currentStat.getStats()));
            viewHolder.mChampionStats.setText(formatStats(currentStat.getStats()));
        }
    }

    @Override
    public int getItemCount() {
        return mChampionStatList.size() + 1;
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
            view.setTag(this);
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

        return String.format(String.format("Games: %d Won: %d Lost: %d", played, won, lost));
    }

    private ChampionStats getChampionStat(View view) {
        return mChampionStatList.get(((ViewHolder)view.getTag()).getAdapterPosition());
    }
}
