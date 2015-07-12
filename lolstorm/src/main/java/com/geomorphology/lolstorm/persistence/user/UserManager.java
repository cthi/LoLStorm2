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

package com.geomorphology.lolstorm.persistence.user;

import android.content.SharedPreferences;

import com.geomorphology.lolstorm.models.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.LinkedList;

import lolstormSDK.GameConstants;

public class UserManager {

    private final String REGION_TAG = "region";
    private final String USER_TAG = "user";
    private final String DRAWER_USER_TAG = "drawer_user_tag";

    private SharedPreferences mPreferences;

    private User defaultUser;

    public UserManager(SharedPreferences preferences) {
        mPreferences = preferences;
    }

    public void updateSavedRegion(int region) {
        mPreferences.edit().putInt(REGION_TAG, region).apply();
    }

    public int getSavedRegion() {
        return mPreferences.getInt(REGION_TAG, GameConstants.REGION_NA);
    }

    public void updateSavedDrawerUser(User user) {
        String userAsJson = new Gson().toJson(user);

        SharedPreferences.Editor editor = mPreferences.edit();
        editor.putString(DRAWER_USER_TAG, userAsJson);
        editor.apply();
    }

    public User getSavedDrawerUser() {
        String historyAsJson = mPreferences.getString(DRAWER_USER_TAG, null);

        if (null == historyAsJson) {
            return getDefaultUser();
        } else {
            return new Gson().fromJson(historyAsJson, User.class);
        }
    }

    private User getDefaultUser() {
        if (null == defaultUser) {
            defaultUser = new User("LoLStorm", GameConstants.REGION_NA, 502);
        }

        return defaultUser;
    }

    public void updateSavedUsers(LinkedList<User> users) {
        while (users.size() > 15) {
            users.removeLast();
        }

        String userAsJson = new Gson().toJson(users);

        SharedPreferences.Editor editor = mPreferences.edit();
        editor.putString(USER_TAG, userAsJson);
        editor.apply();
    }

    public LinkedList<User> getSavedUsers() {
        String historyAsJson = mPreferences.getString(USER_TAG, null);

        if (null == historyAsJson) {
            return new LinkedList<>();
        } else {
            Type type = new TypeToken<LinkedList<User>>() {}.getType();
            return new Gson().fromJson(historyAsJson, type);
        }
    }
}
