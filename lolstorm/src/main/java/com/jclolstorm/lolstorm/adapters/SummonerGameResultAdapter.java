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
import android.graphics.PorterDuff;
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
    private OnSummonerItemClick mListener;

    public interface OnSummonerItemClick {
        void onSummonerClick(Player player);
    }

    public SummonerGameResultAdapter(Context context, OnSummonerItemClick listener, List<Player> playerList, View header) {
        this.mContext = context;
        this.mListener = listener;
        this.mPlayerList = playerList;
        this.mHeader = header;
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

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int position) {
        if (getItemViewType(position) == HEADER) {
            return new HeaderViewHolder(mHeader);
        } else {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_game_summoner, parent, false);

            ViewHolder viewHolder = new ViewHolder(view);
            view.setOnClickListener(new OnSummonerClickedListener(viewHolder));

            return viewHolder;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) != HEADER) {
            ViewHolder viewHolder = (ViewHolder) holder;

            Player currentPlayer = mPlayerList.get(position - 1);
            viewHolder.summonerName.setText(currentPlayer.getSummonerName());
            viewHolder.summonerChampionName.setText(GameConstants.CHAMPION_NAME_MAP.get(Integer
                    .toString(currentPlayer.getChampionId())));
            viewHolder.summonerGameResult.getBackground()
                    .setColorFilter(formatResultIndicator(currentPlayer.isWinner()), PorterDuff.Mode.MULTIPLY);
            Glide.with(mContext).load(ResourceUtils.championDrawableFromID(currentPlayer
                    .getChampionId(), mContext)).into(viewHolder.summonerChampionImage);
        }
    }

    @Override
    public int getItemCount() {
        return mPlayerList.size() + 1;
    }

    @Override
    public void populate(List<Player> playerList) {
        this.mPlayerList = playerList;
        notifyDataSetChanged();
    }

    private class OnSummonerClickedListener implements View.OnClickListener {
        RecyclerView.ViewHolder viewHolder;

        public OnSummonerClickedListener(RecyclerView.ViewHolder viewHolder) {
            this.viewHolder = viewHolder;
        }

        @Override
        public void onClick(View v) {
            mListener.onSummonerClick(mPlayerList.get(viewHolder.getAdapterPosition() - 1));
        }
    }

    private int formatResultIndicator(boolean playerWon) {
       if (playerWon) {
           return mContext.getResources().getColor(R.color.game_blue);
       } else {
           return mContext.getResources().getColor(R.color.game_red);
       }
    }
}
