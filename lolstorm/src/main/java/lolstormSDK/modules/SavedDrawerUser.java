package lolstormSDK.modules;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.jclolstorm.lolstorm.models.User;

public class SavedDrawerUser {

    private final String DRAWER_USER_TAG = "drawer_user_tag";

    private SharedPreferences mPreferences;

    public SavedDrawerUser(Context context) {
        mPreferences = PreferenceManager.getDefaultSharedPreferences(context);
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
            return null;
        } else {
            return new Gson().fromJson(historyAsJson, User.class);
        }
    }
}
