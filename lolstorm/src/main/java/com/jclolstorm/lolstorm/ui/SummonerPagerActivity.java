package com.jclolstorm.lolstorm.ui;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.jclolstorm.lolstorm.R;
import com.jclolstorm.lolstorm.adapters.SummonerPagerAdapter;
import com.jclolstorm.lolstorm.models.User;
import com.jclolstorm.lolstorm.utils.Constants;

import org.parceler.Parcels;

import butterknife.ButterKnife;
import butterknife.InjectView;
import lolstormSDK.RiotEndpoint;
import lolstormSDK.models.Summoner;

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
            getSupportActionBar().setTitle(RiotEndpoint.getInstance().getRegion().toUpperCase() + " " + user.getName());
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
