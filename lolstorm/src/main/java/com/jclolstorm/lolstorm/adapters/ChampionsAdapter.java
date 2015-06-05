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

    public ChampionsAdapter(Context context, List<Champion> championList) {
        this.mContext = context;
        this.mChampionList = championList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int position) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_champion,
                parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        Champion currentChampion = mChampionList.get(position);

        Glide.with(mContext)
                .load(ResourceUtils.championDrawableFromID((int) currentChampion
                .getId(), mContext)).into(viewHolder.mChampionImage);
        viewHolder.mChampionName.setText(currentChampion.getName());
        viewHolder.mChampionTitle.setText(currentChampion.getTitle());
    }

    @Override
    public int getItemCount() {
        return mChampionList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.item_stat_image)
        ImageView mChampionImage;
        @InjectView(R.id.item_stat_label)
        TextView mChampionName;
        @InjectView(R.id.item_stat_value)
        TextView mChampionTitle;

        public ViewHolder(View view) {
            super(view);

            ButterKnife.inject(this, view);
        }
    }

    public void populate(List<Champion> championList) {
        this.mChampionList = championList;
        notifyDataSetChanged();
    }
}