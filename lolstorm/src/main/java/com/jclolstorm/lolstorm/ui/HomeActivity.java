package com.jclolstorm.lolstorm.ui;

import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.jclolstorm.lolstorm.R;
import com.jclolstorm.lolstorm.models.User;
import com.jclolstorm.lolstorm.ui.widgets.headers.NavViewHeader;

import butterknife.ButterKnife;
import butterknife.InjectView;
import lolstormSDK.modules.SavedDrawerUser;

public class HomeActivity extends AppCompatActivity implements SummonerSearchFragment.OnFavorite {

    @InjectView(R.id.home_activity_toolbar)
    Toolbar mToolbar;
    @InjectView(R.id.home_drawer_layout)
    DrawerLayout mDrawerLayout;
    @InjectView(R.id.home_drawer)
    NavigationView mNavView;
    private NavViewHeader mHeader;
    private ActionBarDrawerToggle mDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);
        ButterKnife.inject(this);

        initToolbar();
        initHeader();
        initNavDrawer();
        linkDrawer();
        initView();
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

    @Override
    public void onFavorite(User user) {
        new SavedDrawerUser(this).updateSavedDrawerUser(user);
        mHeader.setUser(user);
    }

    private void initToolbar() {
        if (null != mToolbar) {
            setSupportActionBar(mToolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    private void initHeader() {
        mHeader = new NavViewHeader(this);

        SavedDrawerUser savedDrawerUser = new SavedDrawerUser(this);
        User savedUser = savedDrawerUser.getSavedDrawerUser();

        mHeader.setUser(savedUser);
    }

    private void initNavDrawer() {
        if (null != mNavView) {
            mNavView.addHeaderView(mHeader);
            mNavView.setNavigationItemSelectedListener((menuItem -> {
                menuItem.setChecked(true);

                // TODO fix this
                String item = menuItem.getTitle().toString();
                mDrawerLayout.closeDrawers();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Fragment fragment;

                        if (item.equals("Search")) {
                            fragment = SummonerSearchFragment.newInstance();
                        } else if (item.equals("About")) {
                            fragment = AboutFragment.newInstance();
                        } else if (item.equals("Champions")) {
                            fragment = ChampionsFragment.newInstance();
                        } else {
                            fragment = SummonerSearchFragment.newInstance();
                        }

                        Fragment currentFragment = getSupportFragmentManager().findFragmentById(R
                                .id.home_fragment);
                        if (fragment.getClass() != currentFragment.getClass()) {
                            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                            ft.setCustomAnimations(R.anim.abc_fade_in, R.anim.abc_fade_out);
                            ft.replace(R.id.home_fragment, fragment).commit();

                        }
                    }
                }, 300);
                return true;
            }));

        }
    }

    private void linkDrawer() {
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string
                .drawer_open, R.string.drawer_close);
        mDrawerToggle.setDrawerIndicatorEnabled(true);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
    }

    private void initView() {
        getSupportFragmentManager().beginTransaction().replace(R.id.home_fragment,
                SummonerSearchFragment.newInstance()).commit();
        mNavView.getMenu().getItem(0).setChecked(true);
    }
}
