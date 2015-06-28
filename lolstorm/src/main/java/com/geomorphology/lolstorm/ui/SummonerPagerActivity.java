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

package com.geomorphology.lolstorm.ui;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.geomorphology.lolstorm.R;
import com.geomorphology.lolstorm.adapters.SummonerPagerAdapter;
import com.geomorphology.lolstorm.models.User;
import com.geomorphology.lolstorm.utils.Constants;

import org.parceler.Parcels;

import butterknife.ButterKnife;
import butterknife.InjectView;
import lolstormSDK.RiotEndpoint;

public class SummonerPagerActivity extends AppCompatActivity {

    @InjectView(R.id.player_pager_activity_toolbar)
    Toolbar mToolbar;
    @InjectView(R.id.player_pager_activity_tab_host)
    TabLayout mTabLayout;
    @InjectView(R.id.player_pager_activity_pager)
    ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.summoner_pager_activity);
        ButterKnife.inject(this);

        initToolbar();
        initPager();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initToolbar() {
        if (null != mToolbar) {
            setSupportActionBar(mToolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

            User user = Parcels.unwrap(getIntent()
                    .getExtras().getParcelable(Constants.USER_TAG));
            getSupportActionBar().setTitle(RiotEndpoint.getInstance().getRegionAsString().toUpperCase() + " " + user.getName());
        }
    }

    private void initPager() {
        if (null != mTabLayout) {
            mViewPager.setOffscreenPageLimit(2);
            mViewPager.setAdapter(new SummonerPagerAdapter(this, getSupportFragmentManager(),
                    getIntent().getExtras()));

            mTabLayout.setupWithViewPager(mViewPager);
        }
    }
}
