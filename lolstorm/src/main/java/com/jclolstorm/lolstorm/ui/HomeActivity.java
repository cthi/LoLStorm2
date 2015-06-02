package com.jclolstorm.lolstorm.ui;

import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.jclolstorm.lolstorm.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class HomeActivity extends AppCompatActivity {

    @InjectView(R.id.home_activity_toolbar)
    Toolbar mToolbar;
    @InjectView(R.id.home_drawer_layout)
    DrawerLayout mDrawerLayout;
    @InjectView(R.id.home_drawer)
    NavigationView mNavView;
    private ActionBarDrawerToggle mDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);
        ButterKnife.inject(this);

        initToolbar();
        initNavDrawer();
        linkDrawer();
    }

    @Override
    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    private void initToolbar() {
        if (null != mToolbar) {
            setSupportActionBar(mToolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    private void initNavDrawer() {
        if (null != mNavView) {
            mNavView.setNavigationItemSelectedListener((menuItem -> {
                menuItem.setChecked(true);

                // TODO fix this
                String item = menuItem.getTitle().toString();
                mDrawerLayout.closeDrawers();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if(item.equals("Search")) {
                            getSupportFragmentManager()
                                    .beginTransaction()
                                    .replace(R.id.home_fragment, SummonerSearchFragment.newInstance())
                                    .commit();
                        } else if (item.equals("About")) {
                            getSupportFragmentManager()
                                    .beginTransaction()
                                    .replace(R.id.home_fragment, AboutFragment.newInstance())
                                    .commit();
                        }

                    }
                }, 300);
                return true;
            }));

        }
    }

    private void linkDrawer() {
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.drawer_open, R.string.drawer_close);
        mDrawerToggle.setDrawerIndicatorEnabled(true);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
    }
}
