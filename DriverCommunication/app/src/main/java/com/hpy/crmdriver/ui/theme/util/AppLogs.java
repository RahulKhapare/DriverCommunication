package com.hpy.crmdriver.ui.theme.util;

import android.util.Log;

public class AppLogs {

    public static void generate(String log) {
        Log.e("AppLogs", log);
    }

    public static void generateTAG(String tag, String log) {
        Log.d(tag, log);
    }
}
