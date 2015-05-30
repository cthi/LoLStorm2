package com.jclolstorm.lolstorm.adapters;

import android.content.Context;
import android.graphics.PorterDuff;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jclolstorm.lolstorm.R;
import com.jclolstorm.lolstorm.models.Stat;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class SummonerChampionStatsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private List<Stat> mStatList;

    public SummonerChampionStatsAdapter(Context context, List<Stat> statList) {
        this.mContext = context;
        this.mStatList = statList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int position) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_stat, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        Stat currentStat = mStatList.get(position);

        viewHolder.mImage.getBackground()
                .setColorFilter(mContext.getResources()
                        .getColor(currentStat.getIndicatorColor()), PorterDuff.Mode.MULTIPLY);
        viewHolder.mLabel.setText(currentStat.getLabel());
        viewHolder.mValue.setText(Integer.toString(currentStat.getValue()));
    }

    @Override
    public int getItemCount() {
        return mStatList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.item_stat_image)
        ImageView mImage;
        @InjectView(R.id.item_stat_label)
        TextView mLabel;
        @InjectView(R.id.item_stat_value)
        TextView mValue;

        public ViewHolder(View view) {
            super(view);

            ButterKnife.inject(this, view);
        }
    }

    public void populate(List<Stat> statList) {
        this.mStatList = statList;
        notifyDataSetChanged();
    }
}
