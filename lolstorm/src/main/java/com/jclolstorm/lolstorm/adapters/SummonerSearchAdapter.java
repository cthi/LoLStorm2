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
import com.jclolstorm.lolstorm.models.User;
import com.jclolstorm.lolstorm.utils.ResourceUtils;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class SummonerSearchAdapter extends BaseHeaderRecyclerViewAdapter<User> {

    private List<User> mUserList;
    private OnClick mListener;
    private View mHeader;
    private Context mContext;

    public interface OnClick {
        void onClick(int position);

        void onFavorited(int position);
    }

    public SummonerSearchAdapter(List<User> userList, View header, OnClick listener, Context context) {
        this.mUserList = userList;
        this.mHeader = header;
        this.mListener = listener;
        this.mContext = context;
    }

    @Override
    public int getItemCount() {
        return mUserList.size() + 1;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) != HEADER) {
            ViewHolder viewHolder = (ViewHolder) holder;
            User user = mUserList.get(position -1);

            viewHolder.mName.setText(user.getName());
            viewHolder.mRegion.setText(user.getRegion());
            Glide.with(mContext).load(ResourceUtils.numberedDrawableFromID(user.getIconID(), mContext))
            .into(viewHolder.mIcon);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int position) {
        if (getItemViewType(position) == HEADER) {
            return new HeaderViewHolder(mHeader);
        } else {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_summoner, parent, false);

            return new ViewHolder(view);
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @InjectView(R.id.item_summoner_league_name)
        TextView mName;
        @InjectView(R.id.item_summoner_region)
        TextView mRegion;
        @InjectView(R.id.item_summoner_img)
        ImageView mIcon;
        @InjectView(R.id.item_summoner_fav)
        ImageView mFav;

        public ViewHolder(View view) {
            super(view);

            ButterKnife.inject(this, view);

            view.setOnClickListener(this);

            mFav.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onFavorited(getAdapterPosition());
                }
            });
        }

        @Override
        public void onClick(View view) {
            mListener.onClick(getAdapterPosition());
        }
    }

    @Override
    public void populate(List<User> users) {
        mUserList = users;
        notifyDataSetChanged();
    }
}