package com.jclolstorm.lolstorm.ui;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.astuetz.PagerSlidingTabStrip;
import com.jclolstorm.lolstorm.R;
import com.jclolstorm.lolstorm.adapters.HomePagerAdapter;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class HomeActivity extends AppCompatActivity{

    @InjectView(R.id.home_activity_toolbar)
    Toolbar mToolbar;
    @InjectView(R.id.home_activity_tab_host)
    PagerSlidingTabStrip mTabHost;
    @InjectView(R.id.home_activity_viewpager)
    ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);
        ButterKnife.inject(this);

        initToolbar();
        initPager();
    }

    private void initToolbar() {
        if (null != mToolbar) {
            setSupportActionBar(mToolbar);
        }
    }

    private void initPager() {
        if (null != mTabHost) {
            mViewPager.setAdapter(new HomePagerAdapter(this, getSupportFragmentManager()));
            mTabHost.setBackgroundResource(R.color.blue);
            mTabHost.setDividerColor(getResources().getColor(R.color.white));
            mTabHost.setTextColor(getResources().getColor(R.color.white));
            mTabHost.setIndicatorColor(getResources().getColor(R.color.white));
            mTabHost.setViewPager(mViewPager);
        }
    }
}
