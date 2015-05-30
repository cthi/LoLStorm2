package com.jclolstorm.lolstorm.ui.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.jclolstorm.lolstorm.R;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import lolstormSDK.models.LeagueEntry;

public class MiniSeries extends LinearLayout {
    @InjectView(R.id.item_summoner_miniseries_game_01)
    ImageView game01;
    @InjectView(R.id.item_summoner_miniseries_game_02)
    ImageView game02;
    @InjectView(R.id.item_summoner_miniseries_game_03)
    ImageView game03;
    @InjectView(R.id.item_summoner_miniseries_game_04)
    ImageView game04;
    @InjectView(R.id.item_summoner_miniseries_game_05)
    ImageView game05;

    private static final char WIN = 'W';
    private static final char LOSE = 'L';

    public MiniSeries(Context context) {
        super(context, null);
    }

    public MiniSeries(Context context, AttributeSet attrs) {
        super(context, attrs, 0);
    }

    public MiniSeries(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void setEntry (LeagueEntry entry, View view) {
        if (entry.getLeaguePoints() == 100 && null != entry.getMiniSeries()) {
            setVisibility(View.VISIBLE);
            view.setVisibility(View.GONE);

            String series = entry.getMiniSeries().getProgress();
            int size = series.length();

            if (size == 3) {
                game04.setVisibility(View.GONE);
                game05.setVisibility(View.GONE);
            }

            setGameImages(size, series);
        } else {
            setVisibility(View.GONE);
            view.setVisibility(View.VISIBLE);
        }
    }

    private void setGameImages(int size, String series) {
        ArrayList<ImageView> gameImages = new ArrayList<>();
        gameImages.add(game01);
        gameImages.add(game02);
        gameImages.add(game03);
        gameImages.add(game04);
        gameImages.add(game05);

        for (int i = 0; i < size; i++) {
            if (series.charAt(i) == WIN) {
                gameImages.get(i).setImageResource(R.drawable.ico_game_won);
            } else if (series.charAt(i) == LOSE) {
                gameImages.get(i).setImageResource(R.drawable.ico_game_lost);
            } else {
                gameImages.get(i).setImageResource(R.drawable.ico_neutral);
            }
        }
    }

    @Override
    public void onFinishInflate() {
        ButterKnife.inject(this);
        game01.setVisibility(View.VISIBLE);
        game02.setVisibility(View.VISIBLE);
        game03.setVisibility(View.VISIBLE);
        game04.setVisibility(View.VISIBLE);
        game05.setVisibility(View.VISIBLE);
        setVisibility(View.GONE);
    }
}
