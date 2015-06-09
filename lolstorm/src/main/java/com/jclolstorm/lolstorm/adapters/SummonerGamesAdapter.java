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
import com.jclolstorm.lolstorm.utils.DateUtils;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import lolstormSDK.GameConstants;
import lolstormSDK.models.Game;
import lolstormSDK.models.RawStats;

public class SummonerGamesAdapter extends BaseHeaderRecyclerViewAdapter<Game> {

    private List<Game> mGameList;
    private OnClick mCallback;
    private View mHeader;
    private Context mContext;
    private View.OnClickListener mOnClickListener;

    public interface OnClick {
        void onClick(Game game);
    }

    public SummonerGamesAdapter(Context context, List<Game> gameList, View header, OnClick listener) {
        this.mGameList = gameList;
        this.mHeader = header;
        this.mCallback = listener;
        this.mContext = context;
        this.mOnClickListener = (View v) -> mCallback.onClick(mGameList.get(((ViewHolder)v.getTag()).getAdapterPosition() -1));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) != HEADER) {
            ViewHolder viewHolder = (ViewHolder) holder;
            Game game = mGameList.get(position - 1);
            RawStats stats = game.getStats();

            viewHolder.mGameTitle.setText(GameConstants.GAME_TYPES.get(game.getSubType()));
            viewHolder.mGameDate.setText(DateUtils.getTimeElapsed(game.getCreateDate()));
            viewHolder.mGameScore.setText(formatScore(game.getStats().getLevel(), stats.getChampionsKilled(),
                    stats.getNumDeaths(), stats.getAssists()));
            Glide.with(mContext).load(ResourceUtils
                    .championDrawableFromID(game.getChampionId(), mContext))
                    .into(viewHolder.mChampionIcon);

            viewHolder.mGameResult.
                    getBackground().setColorFilter(formatColor(game), PorterDuff.Mode.MULTIPLY);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int position) {
        if (getItemViewType(position) == HEADER) {
            return new HeaderViewHolder(mHeader);
        } else {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_player_game, parent, false);

            view.setOnClickListener(mOnClickListener);

            return new ViewHolder(view);
        }
    }

    @Override
    public int getItemCount() {
        return mGameList.size() + 1;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder  {
        @InjectView(R.id.item_player_game_icon)
        ImageView mChampionIcon;
        @InjectView(R.id.item_player_game_title)
        TextView mGameTitle;
        @InjectView(R.id.item_player_game_date)
        TextView mGameDate;
        @InjectView(R.id.item_player_game_score)
        TextView mGameScore;
        @InjectView(R.id.item_player_game_result)
        ImageView mGameResult;

        public ViewHolder(View view) {
            super(view);

            ButterKnife.inject(this, view);

            view.setTag(this);
        }
    }

    @Override
    public void populate(List<Game> gameList) {
        this.mGameList = gameList;
        notifyDataSetChanged();
    }

    private String formatScore(int level, int kills, int deaths, int assists) {
        return String.format("Lv. %d %d/%d/%d", level, kills, deaths, assists);
    }

    private int formatColor(Game game) {
        if (game.getStats().getWin()) {
            return mContext.getResources().getColor(R.color.game_blue);
        } else {
            return mContext.getResources().getColor(R.color.game_red);
        }
    }
}
