package com.hpy.crmdriver.ui.theme.util;

import android.os.Environment;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;

public class FileAccess {
    public void writeLogsToFile(String message) {
        try {

            // Create a File object representing the directory
            File directory = new File(Environment.getExternalStoragePublicDirectory(
                    Environment.DIRECTORY_DOCUMENTS), "CRM");

//            Make sure the directory exists; create it if it doesn't
            if (!directory.exists()) {
                if (!directory.mkdirs()) {
                    return;
                }
            }

            // Create a file inside the directory for storing logs
            File logFile = new File(directory, getCurrentDate() + "_logs.txt");

            BufferedWriter writer = new BufferedWriter(new FileWriter(logFile, true));
            writer.write(message + "\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getCurrentDate() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1; // Month is zero-based
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        // Display or use the date as needed
        String currentDate = dayOfMonth + "-" + month + "-" + year;
        return currentDate;
    }

    public void testWrite() {
        for (int i = 0; i < 10; i++) {
            writeLogsToFile(i + " Test Write");
        }
    }
}
