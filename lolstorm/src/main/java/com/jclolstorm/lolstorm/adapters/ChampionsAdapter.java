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
import lolstormSDK.models.Champion;


public class ChampionsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private List<Champion> mChampionList;
    private OnChampionItemClick mListener;

    public interface OnChampionItemClick {
        void onChampionClick(long championId);
    }

    public ChampionsAdapter(Context context, OnChampionItemClick listener, List<Champion> championList) {
        this.mContext = context;
        this.mListener = listener;
        this.mChampionList = championList;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.item_champion_image)
        ImageView mChampionImage;
        @InjectView(R.id.item_champion_name)
        TextView mChampionName;

        public ViewHolder(View view) {
            super(view);

            ButterKnife.inject(this, view);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int position) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_champion,
                parent, false);

        ViewHolder viewHolder = new ViewHolder(view);
        view.setOnClickListener(new OnChampionClickedListener(viewHolder));

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        Champion currentChampion = mChampionList.get(position);

        Glide.with(mContext)
                .load(ResourceUtils.championDrawableFromID((int) currentChampion
                .getId(), mContext)).into(viewHolder.mChampionImage);
        viewHolder.mChampionName.setText(currentChampion.getName());
    }

    @Override
    public int getItemCount() {
        return mChampionList.size();
    }

    public void populate(List<Champion> championList) {
        this.mChampionList = championList;
        notifyDataSetChanged();
    }

    private class OnChampionClickedListener implements View.OnClickListener {
        RecyclerView.ViewHolder viewHolder;

        public OnChampionClickedListener(RecyclerView.ViewHolder viewHolder) {
            this.viewHolder = viewHolder;
        }

        @Override
        public void onClick(View v) {
            mListener.onChampionClick(mChampionList.get(viewHolder.getAdapterPosition()).getId());
        }
    }
}