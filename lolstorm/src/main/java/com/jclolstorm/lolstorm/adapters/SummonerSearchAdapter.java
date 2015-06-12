package com.jclolstorm.lolstorm.adapters;

import android.content.Context;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
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

    private Context mContext;
    private List<User> mUserList;
    private View mHeader;
    private OnSummonerItemClick mListener;

    public interface OnSummonerItemClick {
        void onSummonerClick(User user);

        void onSummonerFavorite(User user);

        void onSummonerRemove(User user);
    }

    public SummonerSearchAdapter(List<User> userList, View header, OnSummonerItemClick listener, Context
            context) {
        this.mUserList = userList;
        this.mHeader = header;
        this.mListener = listener;
        this.mContext = context;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.item_summoner_league_name)
        TextView mName;
        @InjectView(R.id.item_summoner_region)
        TextView mRegion;
        @InjectView(R.id.item_summoner_img)
        ImageView mIcon;
        @InjectView(R.id.item_summoner_fav)
        ImageButton mFav;

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
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_summoner,
                    parent, false);

            ViewHolder viewHolder = new ViewHolder(view);
            view.setOnClickListener(new OnSummonerClickedListener(viewHolder));
            viewHolder.mFav.setOnClickListener(new OnSummonerMenuClickedListener(viewHolder));

            return viewHolder;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) != HEADER) {
            ViewHolder viewHolder = (ViewHolder) holder;
            User user = mUserList.get(position - 1);

            viewHolder.mName.setText(user.getName());
            viewHolder.mRegion.setText(user.getRegion());
            Glide.with(mContext).load(ResourceUtils.numberedDrawableFromID(user.getIconID(),
                    mContext)).into(viewHolder.mIcon);
        }
    }

    @Override
    public int getItemCount() {
        return mUserList.size() + 1;
    }

    private class OnSummonerClickedListener implements View.OnClickListener {
        RecyclerView.ViewHolder viewHolder;

        public OnSummonerClickedListener(RecyclerView.ViewHolder viewHolder) {
            this.viewHolder = viewHolder;
        }

        @Override
        public void onClick(View v) {
            mListener.onSummonerClick(mUserList.get(viewHolder.getAdapterPosition() - 1));
        }
    }

    private class OnSummonerMenuClickedListener implements View.OnClickListener, PopupMenu
            .OnMenuItemClickListener {
        RecyclerView.ViewHolder viewHolder;

        public OnSummonerMenuClickedListener(RecyclerView.ViewHolder viewHolder) {
            this.viewHolder = viewHolder;
        }

        @Override
        public void onClick(View v) {
            PopupMenu popupMenu = new PopupMenu(mContext, v);
            popupMenu.setOnMenuItemClickListener(this);
            popupMenu.inflate(R.menu.summoner_menu);
            popupMenu.show();
        }

        @Override
        public boolean onMenuItemClick(MenuItem item) {
            switch (item.getItemId()) {
                case R.id.summoner_menu_fav:
                    mListener.onSummonerFavorite(mUserList.get(viewHolder.getAdapterPosition() - 1));
                    return true;
                case R.id.summoner_menu_remove:
                    mListener.onSummonerRemove(mUserList.get(viewHolder.getAdapterPosition() - 1));
                    notifyItemRemoved(viewHolder.getLayoutPosition());
                    return true;
                default:
                    return false;
            }
        }
    }

    @Override
    public void populate(List<User> users) {
        mUserList = users;
        notifyDataSetChanged();
    }
}
