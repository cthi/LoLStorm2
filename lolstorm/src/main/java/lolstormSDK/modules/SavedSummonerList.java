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

package lolstormSDK.modules;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.geomorphology.lolstorm.models.User;

import java.lang.reflect.Type;
import java.util.LinkedList;

public class SavedSummonerList {

    private final String USER_TAG = "user";

    private SharedPreferences mPreferences;

    public SavedSummonerList(Context context) {
        mPreferences = PreferenceManager.getDefaultSharedPreferences(context);
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
