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
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.geomorphology.lolstorm.R;
import com.geomorphology.lolstorm.ui.SummonerChampionsFragment;
import com.geomorphology.lolstorm.ui.SummonerGamesFragment;
import com.geomorphology.lolstorm.ui.SummonerLeaguesFragment;

public class SummonerPagerAdapter extends FragmentPagerAdapter {
    private final String[] mTitles;
    private Bundle mBundle;

    public SummonerPagerAdapter(Context context, FragmentManager fm, Bundle bundle) {
        super(fm);
        this.mTitles = context.getResources().getStringArray(R.array.player_pager_items);
        this.mBundle = bundle;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return SummonerGamesFragment.newInstance(mBundle);
            case 1:
                return SummonerChampionsFragment.newInstance(mBundle);
            case 2:
                return SummonerLeaguesFragment.newInstance(mBundle);
            default:
                return SummonerGamesFragment.newInstance(mBundle);
        }
    }

    @Override
    public int getCount() {
        return mTitles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }
}
