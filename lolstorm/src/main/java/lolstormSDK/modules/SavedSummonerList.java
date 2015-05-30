package lolstormSDK.modules;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jclolstorm.lolstorm.models.User;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

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
