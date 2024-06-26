package com.hpy.crmdriver.ui.theme.cmd_formatter;

public class MessageDataLengthGenerator {

    public String getMessageHeaderLength(String dataString) {
        String returnValue = "";
        int chunkSize = 2; // Define the size of each chunk
        String inputString = dataString + "0000";// last zero for additional length

        // Calculate the effective length considering four digits as one character
        int effectiveLength = inputString.length() / chunkSize;

        // Format the output with leading zeros if necessary
        returnValue = String.format("%04d", effectiveLength);

        return returnValue;
    }
}
