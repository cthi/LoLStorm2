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

package com.geomorphology.lolstorm.utils;

import android.content.Context;
import android.content.res.Resources;

import com.geomorphology.lolstorm.R;

import lolstormSDK.GameConstants;

public class ResourceUtils {
    private static String DRAWABLE = "drawable";

    public static int championDrawableFromID(int id, Context context) {
        Resources resources = context.getResources();
        return resources.getIdentifier(GameConstants.CHAMPION_KEY_MAP.get(Integer.toString(id))
                .toLowerCase(), DRAWABLE, context.getPackageName());
    }

    public static int summonerSpellDrawableFromID(int id, Context context) {
        Resources resources = context.getResources();
        return resources.getIdentifier(GameConstants.SPELL_ID_MAP.get(id), DRAWABLE, context
                .getPackageName());
    }

    public static int spellDrawableFromName(String name, Context context) {
        Resources resources = context.getResources();
        return resources.getIdentifier(name.toLowerCase(), DRAWABLE, context
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
