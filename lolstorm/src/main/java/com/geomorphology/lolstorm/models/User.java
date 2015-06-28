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

package com.geomorphology.lolstorm.models;

import org.parceler.Parcel;

import lolstormSDK.GameConstants;

@Parcel
public class User {

    String name;
    int regionId;
    int iconID;
    long level;
    long userID;

    public User() {
    }

    public User(String name, int regionId, int iconID) {
        this.name = name;
        this.regionId = regionId;
        this.iconID = iconID;
    }

    public User(String name, int regionId, int iconID, long level, long userID) {
        this.name = name;
        this.regionId = regionId;
        this.iconID = iconID;
        this.userID = userID;
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public String getRegion() {
        return GameConstants.regionFromInt(regionId);
    }

    public int getRegionId () {
        return regionId;
    }

    public int getIconID() {
        return iconID;
    }

    public long getUserID() {
        return userID;
    }

    public long getLevel() {
        return level;
    }
}
