package com.jclolstorm.lolstorm.presenters;

import android.content.Context;

import com.jclolstorm.lolstorm.R;

import lolstormSDK.RiotEndpoint;

public class RegionPresenter {

    private CharSequence[] regions;

    public RegionPresenter() {
    }

    public void initRegions(Context context) {
        regions = context.getResources().getStringArray(R.array.regions);
    }

    public CharSequence[] getRegionList() {
        return regions;
    }

    public void setRegion(int position) {
        RiotEndpoint.getInstance().setRegion(regions[position].toString().toLowerCase());
    }
}
