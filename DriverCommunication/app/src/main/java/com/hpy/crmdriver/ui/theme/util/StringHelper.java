package com.hpy.crmdriver.ui.theme.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringHelper {

    public String removeDigitSequence(String input, int digitLength) {
        // Regular expression to match the first occurrence of any digitLength-digit sequence
        String regex = "\\b\\d{" + digitLength + "}\\b"; // \b matches word boundaries, \d{n} matches n digits

        // Use a StringBuilder for efficient string manipulation
        StringBuilder sb = new StringBuilder(input);

        // Find the first occurrence of the pattern
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(sb);

        if (matcher.find()) {
            // Remove the first occurrence of the matched pattern
            sb.replace(matcher.start(), matcher.end(), "");
        }

        // Convert StringBuilder back to String
        return sb.toString();
    }

    public String getSubstringData(String response, int startValue, int endValue) {
        String returnValue = "";
        try {
            returnValue = response.substring(startValue, endValue);
        } catch (Exception e) {
        }

        AppLogs.generate(returnValue);
        return returnValue;
    }

    public int getMultiplyValue(int x) {
        return x * 2;
    }

}
