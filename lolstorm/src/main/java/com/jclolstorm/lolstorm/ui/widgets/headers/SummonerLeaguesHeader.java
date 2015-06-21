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

package com.jclolstorm.lolstorm.ui.widgets.headers;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jclolstorm.lolstorm.R;
import com.jclolstorm.lolstorm.utils.ResourceUtils;

import butterknife.ButterKnife;
import butterknife.InjectView;
import lolstormSDK.GameConstants;
import lolstormSDK.models.League;

public class SummonerLeaguesHeader extends RelativeLayout {

    @InjectView(R.id.header_summoner_leagues_tier)
    TextView mTier;
    @InjectView(R.id.header_summoner_leagues_tier_name)
    TextView mTierName;
    @InjectView(R.id.header_summoner_leagues_image)
    ImageView mImage;

    public SummonerLeaguesHeader(Context context) {
        super(context, null);
        init();
    }

    public SummonerLeaguesHeader(Context context, AttributeSet attrs) {
        super(context, attrs, 0);
        init();
    }

    public SummonerLeaguesHeader(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public void setLeague(League league, int division) {
        String tier = league.getTier();
        String div =  GameConstants.divisionFromInt(division);
        mTier.setText(tier + " " + div);
        mTierName.setText(league.getName());
        Glide.with(getContext())
                .load(ResourceUtils.tierDrawableFromTierAndDivision(tier, div, getContext()))
                .into(mImage);
    }

    private void init() {
        inflate(getContext(), R.layout.header_summoner_leagues, this);
        ButterKnife.inject(this);
    }
}
