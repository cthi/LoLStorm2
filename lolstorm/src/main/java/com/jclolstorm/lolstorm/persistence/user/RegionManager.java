package com.jclolstorm.lolstorm.persistence.user;

import android.content.Context;

import com.jclolstorm.lolstorm.R;
import com.jclolstorm.lolstorm.persistence.user.UserSettings;

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
        System.out.println(mUserSettings.getSavedRegion());
        RiotEndpoint.getInstance().setRegion(mUserSettings.getSavedRegion());
    }
}
