package com.geomorphology.lolstorm.persistence.user;

import android.content.res.Resources;

import com.geomorphology.lolstorm.R;

import lolstormSDK.RiotEndpoint;

public class RegionManager {

    private CharSequence[] regions;
    private UserManager mUserManager;

    public RegionManager(UserManager manager, Resources resources) {
        this.mUserManager = manager;
        initRegions(resources);
    }

    public void initRegions(Resources resources) {
        regions = resources.getStringArray(R.array.regions);
    }

    public CharSequence[] getRegionList() {
        return regions;
    }

    public void setRegion(int regionId) {
        RiotEndpoint.getInstance().setRegion(regionId);
        mUserManager.updateSavedRegion(regionId);
    }

    public void restoreSavedRegion() {
        RiotEndpoint.getInstance().setRegion(mUserManager.getSavedRegion());
    }
}
