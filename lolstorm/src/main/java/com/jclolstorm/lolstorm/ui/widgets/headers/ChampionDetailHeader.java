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
import lolstormSDK.models.Champion;

public class ChampionDetailHeader extends RelativeLayout {
    @InjectView(R.id.header_champion_detail_image)
    ImageView mChampionImage;
    @InjectView(R.id.header_champion_detail_name)
    TextView mChampionName;
    @InjectView(R.id.header_champion_detail_title)
    TextView mChampionTitle;

    public ChampionDetailHeader(Context context) {
        super(context, null);
        init();
    }

    public ChampionDetailHeader(Context context, AttributeSet attrs) {
        super(context, attrs, 0);
        init();
    }

    public ChampionDetailHeader(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.header_champion_spell, this);
        ButterKnife.inject(this);
    }

    public void setChampion(Champion champion) {
        Glide.with(getContext()).load(ResourceUtils.championDrawableFromID((int) champion.getId()
                , getContext())).into(mChampionImage);
        mChampionName.setText(champion.getName());
        mChampionTitle.setText(champion.getTitle());
    }
}
