/*
 * Copyright 2015 Christopher C. Thi
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.jclolstorm.lolstorm.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.jclolstorm.lolstorm.R;
import com.jclolstorm.lolstorm.ui.SummonerSearchFragment;

public class HomePagerAdapter extends FragmentPagerAdapter {
    private final String[] mTitles;

    public HomePagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mTitles = context.getResources().getStringArray(R.array.home_pager_items);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return SummonerSearchFragment.newInstance();
            case 1:
                return SummonerSearchFragment.newInstance();
            case 2:
                return SummonerSearchFragment.newInstance();
            default:
                return SummonerSearchFragment.newInstance();
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
