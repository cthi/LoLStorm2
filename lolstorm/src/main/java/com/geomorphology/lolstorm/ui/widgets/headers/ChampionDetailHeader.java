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

package com.geomorphology.lolstorm.ui.widgets.headers;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.geomorphology.lolstorm.R;
import com.geomorphology.lolstorm.utils.ResourceUtils;

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
