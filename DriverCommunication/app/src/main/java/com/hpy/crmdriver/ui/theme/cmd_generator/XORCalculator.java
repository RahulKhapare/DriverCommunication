package com.hpy.crmdriver.ui.theme.cmd_generator;

import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;

public class XORCalculator {

    public static String TAG = "XORCalculation";

    public String getXOR(String inputValue) {
        String returnValue = "";
        returnValue = calculateXOR(inputValue.replaceAll(" ", ""));
        returnValue = padWithZeros(returnValue, 4);
        Log.e(TAG, "XORValue: " + returnValue);
        return returnValue;
    }

    public String padWithZeros(String input, int desiredLength) {
        // Pad the string with leading zeros if its length is less than desiredLength
        if (input.length() < desiredLength) {
            input = String.format("%0" + (desiredLength - input.length()) + "d%s", 0, input);
        }
        return input;
    }

    public String calculateXOR(String input) {
        String[] sequence = getSequenceValue(input);
        Log.e(TAG, "CalculateXOR Array: " + Arrays.toString(getSequenceValue(input)));
        // Perform XOR operation on the sequence
        String xorResult = xorSequence(sequence);
        return xorResult;
    }

    public String[] getSequenceValue(String input) {

        int groupSize = 4;

        // Create an ArrayList to hold the groups
        ArrayList<String> groups = new ArrayList<>();

        // Split the string into groups of 4 characters and add them to the list
        for (int i = 0; i < input.length(); i += groupSize) {
            String group = input.substring(i, i + groupSize);

            // Check if the group contains "0000"
            if (!group.equals("0000")) {
                groups.add(group);
            }
        }

        // Convert ArrayList to an array
        String[] resultArray = groups.toArray(new String[0]);

        return resultArray;
    }

    public String xorSequence(String[] numbers) {
        int result = 0;
        for (String num : numbers) {
            result ^= Integer.parseInt(num, 16); // Convert hexadecimal string to integer
        }
        return Integer.toHexString(result).toUpperCase(); // Convert result back to hexadecimal string
    }
}
