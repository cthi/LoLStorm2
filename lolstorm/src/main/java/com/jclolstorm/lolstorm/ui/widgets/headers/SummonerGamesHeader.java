package com.jclolstorm.lolstorm.ui.widgets.headers;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jclolstorm.lolstorm.R;
import com.jclolstorm.lolstorm.models.User;
import com.jclolstorm.lolstorm.utils.ResourceUtils;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class SummonerGamesHeader extends RelativeLayout {

    @InjectView(R.id.header_player_games_player_name)
    TextView mPlayerName;
    @InjectView(R.id.header_player_games_player_level)
    TextView mPlayerLevel;
    @InjectView(R.id.header_player_games_player_icon)
    ImageView mPlayerIcon;

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

    private void init() {
        inflate(getContext(), R.layout.header_player_games, this);
        ButterKnife.inject(this);
    }

    public void initHeaderData(User user) {
        mPlayerName.setText(user.getName());
        mPlayerLevel.setText(Long.toString(user.getLevel()));
        Glide.with(getContext()).load(ResourceUtils.numberedDrawableFromID(user.getIconID(),
                getContext())).into(mPlayerIcon);
    }
}
