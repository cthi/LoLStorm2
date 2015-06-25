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

package com.jclolstorm.lolstorm.ui;

import android.content.DialogInterface;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.jclolstorm.lolstorm.R;
import com.jclolstorm.lolstorm.models.User;
import com.jclolstorm.lolstorm.presenters.RegionPresenter;
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

    private RegionPresenter mRegionPresenter;

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
        initRegions();
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
                        Fragment fragment = null;

                        if (item.equals("Search")) {
                            fragment = SummonerSearchFragment.newInstance();
                        } else if (item.equals("About")) {
                            fragment = AboutFragment.newInstance();
                        } else if (item.equals("Champions")) {
                            fragment = ChampionsFragment.newInstance();
                        } else if (item.equals("Region")) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity.this);
                            builder.setSingleChoiceItems(mRegionPresenter.getRegionList(), 0, null);
                            builder.setTitle("Region");
                            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                    int position = ((AlertDialog) dialog).getListView().getCheckedItemPosition();
                                    mRegionPresenter.setRegion(position);
                                }
                            });
                            builder.show();
                        } else {
                            fragment = SummonerSearchFragment.newInstance();
                        }

                        if (null != fragment) {
                            Fragment currentFragment = getSupportFragmentManager().findFragmentById(R
                                    .id.home_fragment);
                            if (fragment.getClass() != currentFragment.getClass()) {
                                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                                ft.setCustomAnimations(R.anim.abc_fade_in, R.anim.abc_fade_out);
                                ft.replace(R.id.home_fragment, fragment).commit();
                            }
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

    private void initRegions() {
        mRegionPresenter = new RegionPresenter();
        mRegionPresenter.initRegions(this);
    }
}
