package com.hpy.crmdriver.ui.theme.util;

public class ValueConvertor {

    public String decimalToHexadecimal(int decimal) {
        String hexadecimal = Integer.toHexString(decimal);
        return hexadecimal;
    }

    public String decimalToHexString(int decimalNumber) {
        String hexString = Integer.toHexString(decimalNumber);

        // Pad the hexadecimal string with leading zeros if necessary to make it four characters long
        while (hexString.length() < 4) {
            hexString = "0" + hexString;
        }

        return hexString;
    }

    public String decimalToHexString8(int decimalNumber) {
        String hexString = Integer.toHexString(decimalNumber);

        // Pad the hexadecimal string with leading zeros if necessary to make it four characters long
        while (hexString.length() < 8) {
            hexString = "0" + hexString;
        }

        return hexString;
    }

    public String decimalToHexString8(long decimalNumber) {
        String hexString = Long.toHexString(decimalNumber);

        // Pad the hexadecimal string with leading zeros if necessary to make it four characters long
        while (hexString.length() < 8) {
            hexString = "0" + hexString;
        }

        return hexString;
    }

    public String decimalToHexString(long decimalNumber) {
        String hexString = Long.toHexString(decimalNumber);

        // Pad the hexadecimal string with leading zeros if necessary to make it four characters long
        while (hexString.length() < 4) {
            hexString = "0" + hexString;
        }

        return hexString;
    }

}
