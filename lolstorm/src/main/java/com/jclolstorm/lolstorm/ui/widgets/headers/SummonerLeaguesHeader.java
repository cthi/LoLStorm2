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
