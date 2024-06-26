package com.hpy.crmdriver.ui.theme.interrupt_formatter;

import android.content.Context;

import com.hpy.crmdriver.ui.theme.util.KeyValue;
import com.hpy.crmdriver.ui.theme.util.SessionData;

public class InterruptFormatter {

    public String getByteToBinaryBlockData(Context context, int position) {
        String byteData = getActualByteData(context);
        byte byteAtPosition = getByteAtPosition(byteData, position);
        String formattedByte = String.format("%02X", byteAtPosition);
        String binaryValue = decimalToBinary(formattedByte);
        return binaryValue;
    }

    public String getActualByteData(Context context) {

        int numBytesToRemoveFromStart = 4;
        int numBytesToRemoveFromEnd = 4;
        String hexString = SessionData.getStringValue(context, KeyValue.INTERRUPT_DATA);

        // Remove bytes from the start
        String removedFromStart = hexString.substring(numBytesToRemoveFromStart * 3);
        // Remove bytes from the end
        String removedFromEnd = removedFromStart.substring(0, removedFromStart.length() - (numBytesToRemoveFromEnd * 3));

        return removedFromEnd;
    }

    public byte getByteAtPosition(String hexString, int position) {
        // Remove spaces from the hex string and convert it to upper case
        hexString = hexString.replaceAll(" ", "").toUpperCase();

        // Calculate the index of the byte in the string
        int byteIndex = position * 2;

        // Extract the substring representing the byte
        String byteString = hexString.substring(byteIndex, byteIndex + 2);

        // Parse the byte from the substring
        byte b = (byte) Integer.parseInt(byteString, 16);

        return b;
    }

    public String decimalToBinary(String hexByte) {

        // Convert the hexadecimal byte to decimal
        int decimalValue = Integer.parseInt(hexByte, 16);

        // Convert the decimal byte to binary
        String binaryValue = Integer.toBinaryString(decimalValue);

        // Ensure the binary representation has leading zeros if necessary
        while (binaryValue.length() < 8) {
            binaryValue = "0" + binaryValue;
        }

        return binaryValue;
    }
}
