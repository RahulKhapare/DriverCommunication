package com.hpy.crmdriver.ui.theme.session;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hpy.crmdriver.ui.theme.packet_model.ModelPacket0001;

import java.lang.reflect.Type;
import java.util.List;

public class Session {

    public String MY_PREF = "MY_PREF";

    public void saveModelData(Context context, String key, ModelPacket0001 model) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(MY_PREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(model);
        editor.putString(key, json);
        editor.apply();

    }

    public ModelPacket0001 getModelData(Context context, String key) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(MY_PREF, Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString(key, null);
        Type type = new TypeToken<ModelPacket0001>() {
        }.getType();
        return gson.fromJson(json, type);
    }


    public void addValue(Context context, String key, String value) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(MY_PREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public String getValue(Context context, String key) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(MY_PREF, Context.MODE_PRIVATE);
        String value = sharedPreferences.getString(key, "");
        return value;
    }

    public void clearData(Context context, String key) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(MY_PREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(key);
        editor.apply();
    }

}
