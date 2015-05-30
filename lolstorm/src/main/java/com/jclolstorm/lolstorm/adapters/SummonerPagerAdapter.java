package com.jclolstorm.lolstorm.adapters;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.jclolstorm.lolstorm.R;
import com.jclolstorm.lolstorm.ui.SummonerChampionsFragment;
import com.jclolstorm.lolstorm.ui.SummonerGamesFragment;
import com.jclolstorm.lolstorm.ui.SummonerLeaguesFragment;

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
