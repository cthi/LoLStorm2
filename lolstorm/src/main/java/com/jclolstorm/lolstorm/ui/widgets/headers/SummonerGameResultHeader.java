package com.jclolstorm.lolstorm.ui.widgets.headers;

import android.content.Context;
import android.graphics.PorterDuff;
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
import lolstormSDK.models.Game;
import lolstormSDK.models.RawStats;

public class SummonerGameResultHeader extends RelativeLayout {

    @InjectView(R.id.header_game_result_champion_image)
    ImageView mChampionImage;
    @InjectView(R.id.header_game_result_name)
    TextView mSummonerName;
    @InjectView(R.id.header_game_result_champion)
    TextView mChampionName;

    @InjectView(R.id.header_game_result_spell_01)
    ImageView mSpell01;
    @InjectView(R.id.header_game_result_spell_02)
    ImageView mSpell02;
    @InjectView(R.id.header_game_result_spell_03)
    ImageView mSpell03;
    @InjectView(R.id.header_game_result_spell_04)
    ImageView mSpell04;
    @InjectView(R.id.header_game_result_spell_05)
    ImageView mSpell05;
    @InjectView(R.id.header_game_result_spell_06)
    ImageView mSpell06;
    @InjectView(R.id.header_game_result_spell_07)
    ImageView mSpell07;
    @InjectView(R.id.header_game_result_spell_08)
    ImageView mSpell08;
    @InjectView(R.id.header_game_result_spell_09)
    ImageView mSpell09;

    @InjectView(R.id.header_game_result_indicator)
    ImageView mResultIndicator;

    public SummonerGameResultHeader(Context context) {
        super(context, null);
        init();
    }

    public SummonerGameResultHeader(Context context, AttributeSet attrs) {
        super(context, attrs, 0);
        init();
    }

    public SummonerGameResultHeader(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public void setGame(Game game) {
        RawStats stats = game.getStats();

        mChampionName.setText(GameConstants.CHAMPION_NAME_MAP.get(Integer.toString(game
                .getChampionId())));
        Glide.with(getContext())
                .load(ResourceUtils.championDrawableFromID(game.getChampionId(), getContext()))
                .into(mChampionImage);
        Glide.with(getContext())
                .load(ResourceUtils.numberedDrawableFromID(stats.getItem0(), getContext()))
                .into(mSpell01);
        Glide.with(getContext())
                .load(ResourceUtils.numberedDrawableFromID(stats.getItem1(), getContext()))
                .into(mSpell02);
        Glide.with(getContext())
                .load(ResourceUtils.numberedDrawableFromID(stats.getItem2(), getContext()))
                .into(mSpell03);
        Glide.with(getContext())
                .load(ResourceUtils.numberedDrawableFromID(stats.getItem3(), getContext()))
                .into(mSpell04);
        Glide.with(getContext())
                .load(ResourceUtils.numberedDrawableFromID(stats.getItem4(), getContext()))
                .into(mSpell05);
        Glide.with(getContext())
                .load(ResourceUtils.numberedDrawableFromID(stats.getItem5(), getContext()))
                .into(mSpell06);
        Glide.with(getContext())
                .load(ResourceUtils.numberedDrawableFromID(stats.getItem6(), getContext()))
                .into(mSpell07);
        Glide.with(getContext())
                .load(ResourceUtils.numberedDrawableFromID(game.getSpell1(), getContext()))
                .into(mSpell08);
        Glide.with(getContext())
                .load(ResourceUtils.numberedDrawableFromID(game.getSpell2(), getContext()))
                .into(mSpell09);

        mResultIndicator.getBackground()
                .setColorFilter(formatResultIndicator(stats.getWin()), PorterDuff.Mode.MULTIPLY);
    }

    private void init() {
        inflate(getContext(), R.layout.header_game_result, this);
        ButterKnife.inject(this);
    }

    private int formatResultIndicator(boolean playerWon) {
        if (playerWon) {
            return getContext().getResources().getColor(R.color.game_blue);
        } else {
            return getContext().getResources().getColor(R.color.game_red);
        }
    }
}
