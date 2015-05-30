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

public class SummonerGamesHeader extends RelativeLayout {

    @InjectView(R.id.header_player_games_player_name)
    TextView mPlayerName;
    @InjectView(R.id.header_player_games_player_info)
    TextView mPlayerInfo;
    @InjectView(R.id.header_player_games_player_icon)
    ImageView mPlayerIcon;
    @InjectView(R.id.header_player_games_player_rank)
    ImageView mPlayerRank;

    public SummonerGamesHeader(Context context) {
        super(context, null);
        init();
    }

    public SummonerGamesHeader(Context context, AttributeSet attrs) {
        super(context, attrs, 0);
        init();
    }

    public SummonerGamesHeader(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public void setPlayerName(String name) {
        mPlayerName.setText(name);
    }

    public void setPlayerInfo(String info) {
        mPlayerInfo.setText(info);
    }

    public void setPlayerIcon(int resource) {
        Glide.with(getContext()).load(ResourceUtils.numberedDrawableFromID(resource, getContext()))
                .into(mPlayerIcon);
    }

    private void init() {
        inflate(getContext(), R.layout.header_player_games, this);
        ButterKnife.inject(this);
    }
}
