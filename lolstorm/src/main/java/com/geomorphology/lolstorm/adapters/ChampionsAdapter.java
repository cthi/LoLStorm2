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
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.geomorphology.lolstorm.R;
import com.geomorphology.lolstorm.utils.ResourceUtils;

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
        @InjectView(R.id.item_champion_bg)
        RelativeLayout mBg;
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
        setBackgroundColor(viewHolder.mBg, viewHolder.getLayoutPosition());
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

    private void setBackgroundColor(View view, int position) {
        if (position % 2 == 0) {
            view.setBackgroundColor(mContext.getResources().getColor(R.color.list_grey_1));
        } else {
            view.setBackgroundColor(mContext.getResources().getColor(R.color.list_grey_2));
        }
    }
}