package com.hpy.crmdriver.ui.theme.util;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionData {
    public static String DB = "MySharedPref";

    public static void addValue(Context context, String key, String value) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(DB, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public static void addValue(Context context, String key, int value) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(DB, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(key, value);
        editor.commit();
    }

    public static void addValue(Context context, String key, boolean value) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(DB, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    public static String getStringValue(Context context, String key) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(DB, MODE_PRIVATE);
        String value = sharedPreferences.getString(key, "");
        return value;
    }

    public static int getIntValue(Context context, String key) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(DB, MODE_PRIVATE);
        int value = sharedPreferences.getInt(key, 0);
        return value;
    }

    public static boolean getBooleanValue(Context context, String key) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(DB, MODE_PRIVATE);
        boolean value = sharedPreferences.getBoolean(key, false);
        return value;
    }

    public void clearAllSession(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(DB, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }
}
