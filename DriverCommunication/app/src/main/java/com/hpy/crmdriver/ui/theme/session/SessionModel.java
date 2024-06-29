package com.hpy.crmdriver.ui.theme.session;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hpy.crmdriver.ui.theme.packet_model.ModelPacket0001;

import java.lang.reflect.Type;

public class SessionModel {
    public String MY_PREF = "MY_PREF";

    public  <T> void saveModelToSession(Context context, String key, T object) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(MY_PREF, Context.MODE_PRIVATE);
        // Convert the object to JSON string using Gson library
        Gson gson = new Gson();
        String json = gson.toJson(object);

        // Save the JSON string in SharedPreferences
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, json);
        editor.apply();
    }

    // Generic function to retrieve an object of any type from SharedPreferences
    public  <T> T getModelFromSession(Context context, String key, Class<T> classOfT) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(MY_PREF, Context.MODE_PRIVATE);
        // Retrieve the JSON string from SharedPreferences
        String json = sharedPreferences.getString(key, "");
        Gson gson = new Gson();
        // Convert the JSON string back to object of specified type
        T object = gson.fromJson(json, classOfT);
        return object;
    }

    public void clearData(Context context, String key) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(MY_PREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(key);
        editor.apply();
    }

    public void clearAllSession(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(MY_PREF, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }

}
