package com.hpy.crmdriver.ui.theme.util;

import android.util.Log;

public class AppLogs {

    public String TAG = "AppLogs";

    public static void generate(String log) {
        Log.e("AppLogs", log);
    }

    public static void generateTAG(String tag, String log) {
        Log.d(tag, log);
    }

    public void writeGenerateLogs(String tag, String log) {
        Log.d(tag, log);
        FileAccess fileAccess = new FileAccess();
        fileAccess.writeLogsToFile(log);
    }

    public void writeGenerateLogs(String log) {
        Log.d(TAG, log);
        FileAccess fileAccess = new FileAccess();
        fileAccess.writeLogsToFile(log);
    }
}
