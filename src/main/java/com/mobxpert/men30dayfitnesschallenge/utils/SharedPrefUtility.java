package com.mobxpert.men30dayfitnesschallenge.utils;

import android.content.Context;
import android.content.SharedPreferences;


import java.util.List;


public class SharedPrefUtility {

    private SharedPreferences sharedPreferences;
    private static SharedPrefUtility utility;

    private SharedPrefUtility(Context context) {
        if (sharedPreferences == null) {
            sharedPreferences = (context).getSharedPreferences(
                    "MyPrefs", Context.MODE_PRIVATE);
        }
    }

    public static synchronized SharedPrefUtility getInstance(Context c) {
        if (utility == null) {
            utility = new SharedPrefUtility(c);
        }
        return utility;
    }

    public SharedPreferences getPreferences() {
        return sharedPreferences;
    }

    public void savePrefrences(String key, String value) {
        if (sharedPreferences != null) {
            sharedPreferences.edit().putString(key, value).commit();
        }
    }

    public void savePrefrences(String key, int value) {
        if (sharedPreferences != null) {
            sharedPreferences.edit().putInt(key, value).commit();
        }
    }

    public void savePrefrences(String key, boolean value) {
        if (sharedPreferences != null) {
            sharedPreferences.edit().putBoolean(key, value).commit();
        }
    }

    public void savePrefrences(String key, long value) {
        if (sharedPreferences != null) {
            sharedPreferences.edit().putLong(key, value).commit();
        }
    }
    public String getStringValue(String key) {
        return sharedPreferences.getString(key, null);
    }

    public boolean getBooleanValue(String key) {
        return sharedPreferences.getBoolean(key, false);
    }

    public int getIntValue(String key) {
        return sharedPreferences.getInt(key, 0);
    }

    public long getLongValue(String key) {
        return sharedPreferences.getLong(key, -1);
    }

    public void clearPreferences() {
        if (sharedPreferences != null) {
            sharedPreferences.edit().clear().commit();
        }
    }

}
