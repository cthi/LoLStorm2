package com.jclolstorm.lolstorm.utils;

import android.content.Context;
import android.content.res.Resources;

import com.jclolstorm.lolstorm.R;

import lolstormSDK.GameConstants;

public class ResourceUtils {
    private static String DRAWABLE = "drawable";

    public static int championDrawableFromID(int id, Context context) {
        Resources resources = context.getResources();
        return resources.getIdentifier(GameConstants.CHAMPION_KEY_MAP.get(Integer.toString(id))
                .toLowerCase(), DRAWABLE, context.getPackageName());
    }

    public static int spellDrawableFromID(int id, Context context) {
        Resources resources = context.getResources();
        return resources.getIdentifier(GameConstants.SPELL_ID_MAP.get(id), DRAWABLE, context
                .getPackageName());
    }

    public static int numberedDrawableFromID(int id, Context context) {
        Resources resources = context.getResources();
        return resources.getIdentifier("_" + Integer.toString(id), DRAWABLE, context
                .getPackageName());
    }

    public static int numberedItemDrawableFromID(int id, Context context) {
        if (id == 0) {
            return R.drawable.no_item;
        } else {
            return numberedDrawableFromID(id, context);
        }
    }
    public static int tierDrawableFromTierAndDivision(String tier, String division, Context
            context) {
        Resources resources = context.getResources();
        String tdImg = tier.toLowerCase() + "_" + division.toLowerCase();
        return resources.getIdentifier(tdImg, DRAWABLE, context.getPackageName());
    }
}
