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

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jclolstorm.lolstorm.R;
import com.jclolstorm.lolstorm.models.Stat;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class SummonerChampionStatsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Stat> mStatList;

    public SummonerChampionStatsAdapter(List<Stat> statList) {
        this.mStatList = statList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int position) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_champion_stat, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        Stat currentStat = mStatList.get(position);

        viewHolder.mLabel.setText(currentStat.getLabel());
        viewHolder.mValue.setText(Integer.toString(currentStat.getValue()));
    }

    @Override
    public int getItemCount() {
        return mStatList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
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
