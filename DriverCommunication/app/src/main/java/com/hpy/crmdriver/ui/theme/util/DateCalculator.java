package com.hpy.crmdriver.ui.theme.util;

import java.util.Calendar;
import java.util.Date;

public class DateCalculator {

    public int getYear() {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.YEAR) % 100; // Get last 2 digits of the year
    }

    // Function to get month (1-based) from a Date object
    public int getMonth() {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MONTH) + 1; // January is 0, so add 1
    }

    // Function to get day of the month from a Date object
    public int getDay() {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    // Function to get hour (24-hour format) from a Date object
    public int getHour() {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.HOUR_OF_DAY);
    }

    // Function to get minute from a Date object
    public int getMinute() {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MINUTE);
    }

    // Function to get second from a Date object
    public int getSecond() {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.SECOND);
    }

    public String formatTwoDigitsWithHex(int number) {
        String returnValue = "";
        if (number >= 0 && number < 10) {
            returnValue = "0" + number; // Add leading zero for single-digit numbers
        } else {
            returnValue = String.valueOf(number); // Return the number as-is for two-digit numbers
        }
        returnValue = toHex(returnValue);
        return returnValue;
    }

    // Function to convert a 2-digit string to hexadecimal format with leading zero if necessary
    public static String toHex(String twoDigitString) {
        int decimal = Integer.parseInt(twoDigitString);
        String hex = Integer.toHexString(decimal).toUpperCase(); // Convert to uppercase hexadecimal string
        return hex.length() == 1 ? "0" + hex : hex; // Add leading zero if hex is a single character
    }
}
