package com.hpy.crmdriver.ui.theme.cmd_formatter;

import android.util.Log;

public class MessageDataLengthGenerator {

    public String getMessageHeaderLength(String dataString) {
        String returnValue = "";
        int chunkSize = 2; // Define the size of each chunk
        String inputString = dataString + "0000";// last zero for additional length

        Log.e("TAG", "getMessageHeaderLength_Data: " + inputString);

        // Calculate the effective length considering four digits as one character
        int effectiveLength = inputString.length() / chunkSize;

        Log.e("TAG", "getMessageHeaderLength_Length: " + effectiveLength);

        // Format the output with leading zeros if necessary
        returnValue = String.format("%04d", effectiveLength);

        return decimalToHex(Integer.parseInt(returnValue));
    }

    public static String toHex(String twoDigitString) {
        int decimal = Integer.parseInt(twoDigitString);
        return Integer.toHexString(decimal).toUpperCase();
    }

    public String decimalToHex(int decimalNumber) {
        String hexString = Integer.toHexString(decimalNumber);

        // Pad the hexadecimal string with leading zeros if necessary to make it four characters long
        while (hexString.length() < 4) {
            hexString = "0" + hexString;
        }

        return hexString;
    }
}
