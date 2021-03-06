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
import lolstormSDK.models.ChampionSpell;

public class ChampionSpellAdapter extends BaseHeaderRecyclerViewAdapter<ChampionSpell> {
    private Context mContext;
    private List<ChampionSpell> mChampionSpells;
    private View mHeader;

    public ChampionSpellAdapter(Context context, List<ChampionSpell> championSpells, View header) {
        this.mContext = context;
        this.mChampionSpells = championSpells;
        this.mHeader = header;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.item_champion_spell_bg)
        RelativeLayout mBg;
        @InjectView(R.id.item_champion_spell_image)
        ImageView mSpellImage;
        @InjectView(R.id.item_champion_spell_name)
        TextView mSpellName;
        @InjectView(R.id.item_champion_spell_cost)
        TextView mSpellCost;
        @InjectView(R.id.item_champion_spell_cd)
        TextView mSpellCd;
        @InjectView(R.id.item_champion_spell_desc)
        TextView mSpellDesc;

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
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout
                    .item_champion_spell, parent, false);

            return new ViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) != HEADER) {
            ViewHolder viewHolder = (ViewHolder) holder;

            ChampionSpell currentSpell = mChampionSpells.get(position - 1);

            Glide.with(mContext).load(ResourceUtils.spellDrawableFromName(currentSpell.getKey(),
                    mContext)).into(viewHolder.mSpellImage);
            viewHolder.mSpellName.setText(currentSpell.getName());
            viewHolder.mSpellDesc.setText(currentSpell.getSanitizedDescription());
            setSpellCost(viewHolder.mSpellCost, currentSpell);
            setCoolDown(viewHolder.mSpellCd, currentSpell);
            setBackgroundColor(viewHolder.mBg, viewHolder.getLayoutPosition());
        }
    }

    @Override
    public int getItemCount() {
        return mChampionSpells.size() + 1;
    }

    @Override
    public void populate(List<ChampionSpell> championSpells) {
        this.mChampionSpells = championSpells;
        notifyDataSetChanged();
    }

    private void setSpellCost(TextView spellCost, ChampionSpell spell) {
        if (!spell.getCostType().equals("NoCost")) {
            if (spell.getCost().get(0) == spell.getCost().get(1) && spell.getCost().get(0) ==
                    spell.getCost().get(2)) {
                spellCost.setText("Cost: " + Integer.toString(spell.getCost().get(0)) + " " +
                        spell.getCostType());
            } else {
                String cost = "";
                for (Integer i : spell.getCost()) {
                    cost = cost + Integer.toString(i) + "/";
                }
                cost = "Cost: " + cost.substring(0, cost.length() - 1) + " " + spell.getCostType();
                spellCost.setText(cost);
            }
        } else {
            spellCost.setVisibility(View.GONE);
        }
    }

    private void setCoolDown(TextView coolDown, ChampionSpell spell) {
        if (Double.compare(spell.getCooldown().get(0), spell.getCooldown().get(1)) == 0 && Double.compare(spell.getCooldown().get(2), spell.getCooldown().get(0)) == 0) {
            coolDown.setText("Cooldown: " + String.format("%.0f", spell.getCooldown().get(0)) + " Seconds");
        } else {
            String cd = "";
            for (Double i : spell.getCooldown()) {
                cd = cd + String.format("%.0f/", i);
            }
            cd = "Cooldown: " + cd.substring(0, cd.length() - 1) + " Seconds";
            coolDown.setText(cd);
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
