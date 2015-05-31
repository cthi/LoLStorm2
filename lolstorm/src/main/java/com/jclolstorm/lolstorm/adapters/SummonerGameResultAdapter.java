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
import lolstormSDK.models.Player;

public class SummonerGameResultAdapter extends BaseHeaderRecyclerViewAdapter<Player> {

    private Context mContext;
    private List<Player> mPlayerList;
    private View mHeader;

    public SummonerGameResultAdapter(Context context, List<Player> playerList, View header) {
        this.mContext = context;
        this.mPlayerList = playerList;
        this.mHeader = header;
    }

    @Override
    public void populate(List<Player> playerList) {
        this.mPlayerList = playerList;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int position) {
        if (getItemViewType(position) == HEADER) {
            return new HeaderViewHolder(mHeader);
        } else {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_game_summoner, parent, false);

            return new ViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) != HEADER) {
            ViewHolder viewHolder = (ViewHolder) holder;

            Player currentPlayer = mPlayerList.get(position - 1);
            viewHolder.summonerName.setText(currentPlayer.getSummonerName());
            viewHolder.summonerChampionName.setText(GameConstants.CHAMPION_NAME_MAP.get(Integer.toString(currentPlayer.getChampionId())));
            Glide.with(mContext)
                    .load(ResourceUtils.championDrawableFromID(currentPlayer.getChampionId(), mContext))
                    .into(viewHolder.summonerChampionImage);
        }
    }

    @Override
    public int getItemCount() {
        return mPlayerList.size() + 1;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.item_game_summoner_champion_image)
        ImageView summonerChampionImage;
        @InjectView(R.id.item_game_summoner_result)
        ImageView summonerGameResult;
        @InjectView(R.id.item_game_summoner_name)
        TextView summonerName;
        @InjectView(R.id.item_game_summoner_champion_name)
        TextView summonerChampionName;

        public ViewHolder(View view) {
            super(view);

            ButterKnife.inject(this, view);
        }
    }

    private int formatResultIndicator(boolean playerWon, int teamId) {
       // if (playerWon && teamId == game)
        return 12;
    }
}
