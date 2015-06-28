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

import com.geomorphology.lolstorm.R;
import com.geomorphology.lolstorm.models.User;
import com.geomorphology.lolstorm.persistence.user.RegionManager;
import com.geomorphology.lolstorm.ui.widgets.headers.NavViewHeader;

import butterknife.ButterKnife;
import butterknife.InjectView;
import lolstormSDK.RiotEndpoint;
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

    private RegionManager mRegionManager;

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
    public void onResume() {
        super.onResume();
        mRegionManager.restoreSavedRegion();
        mHeader.updateRegionText();
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

        if (null == mNavView) {
            return;
        }

        mNavView.addHeaderView(mHeader);
        mNavView.setNavigationItemSelectedListener((menuItem -> {
            mDrawerLayout.closeDrawers();

            Fragment switchTo = null;

            switch (menuItem.getItemId()) {
                case R.id.drawer_search:
                    switchTo = SummonerSearchFragment.newInstance();
                    break;
                case R.id.drawer_champions:
                    switchTo = ChampionsFragment.newInstance();
                    break;
                case R.id.drawer_regions:
                    showRegionDialog();
                    break;
                case R.id.drawer_about:
                    switchTo = AboutFragment.newInstance();
                    break;
            }

            if (menuItem.getItemId() != R.id.drawer_regions) {
                menuItem.setChecked(true);
            }

            if (null != switchTo) {
                Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id
                        .home_fragment);
                final Fragment tempFragment = switchTo;

                if (switchTo.getClass() != currentFragment.getClass()) {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                            ft.setCustomAnimations(R.anim.abc_fade_in, R.anim.abc_fade_out);
                            ft.replace(R.id.home_fragment, tempFragment).commit();
                        }
                    }, 300);
                }
            }

            return true;
        }));
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
        mRegionManager = new RegionManager();
        mRegionManager.initRegions(this);
    }

    private void showRegionDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity.this);
        builder.setSingleChoiceItems(mRegionManager.getRegionList(), RiotEndpoint.getInstance().getRegionId(), null);
        builder.setTitle(getString(R.string.region_title));
        builder.setPositiveButton(getString(R.string.dialog_ok), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                int position = ((AlertDialog) dialog).getListView()
                        .getCheckedItemPosition();
                mRegionManager.setRegion(position);
                mHeader.updateRegionText();
            }
        });
        builder.setNegativeButton(getString(R.string.dialog_cancel), null);
        builder.show();
    }
}
