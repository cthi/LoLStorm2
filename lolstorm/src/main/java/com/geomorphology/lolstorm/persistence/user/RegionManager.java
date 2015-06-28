package com.geomorphology.lolstorm.persistence.user;

import android.content.Context;

import com.geomorphology.lolstorm.R;

import lolstormSDK.RiotEndpoint;

public class RegionManager {

    private CharSequence[] regions;
    private UserSettings mUserSettings;

    public RegionManager() {
    }

    public void initRegions(Context context) {
        regions = context.getResources().getStringArray(R.array.regions);
        mUserSettings = new UserSettings(context);
    }

    public CharSequence[] getRegionList() {
        return regions;
    }

    public void setRegion(int regionId) {
        RiotEndpoint.getInstance().setRegion(regionId);
        mUserSettings.updateSavedRegion(regionId);
    }

    public void restoreSavedRegion() {
        RiotEndpoint.getInstance().setRegion(mUserSettings.getSavedRegion());
    }
}
