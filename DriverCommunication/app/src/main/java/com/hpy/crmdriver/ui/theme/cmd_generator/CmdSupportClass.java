package com.hpy.crmdriver.ui.theme.cmd_generator;

import com.hpy.crmdriver.ui.theme.util.AppLogs;

import java.util.Arrays;

public class CmdSupportClass {

    public CommandCalculator commandCalculator = new CommandCalculator();
    public CmdErrorCode cmdErrorCode = new CmdErrorCode();

    public String byteArrayToHexDecimal(int length, byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(String.format("%02x ", bytes[i]));
        }
        return sb.toString();
    }

    public String get3rdHexValue(String hexString) {
        // Split the string into individual hexadecimal values
        String[] hexValues = hexString.split(" ");

        // Check if the array contains enough elements to access the third position
        if (hexValues.length > 2) {
            // Get the value at the third position (index 2)
            String hex = hexValues[2];
            return hex;
        } else {
            return "";
        }
    }

    public byte[] generateControlCommand(int wValue, int wIndex) {
        byte bmRequestType = (byte) 0xC2; // bmRequestType: 1 (C2)16 - Control Read Transfer, Vendor Request, Recipient: End Point
        byte[] wValueBytes = intToLittleEndianBytes(wValue, 2); // Convert wValue to 2-byte little-endian format
        byte[] wIndexBytes = intToLittleEndianBytes(wIndex, 2); // Convert wIndex to 2-byte little-endian format
        byte[] wLengthBytes = intToLittleEndianBytes(0x0008, 2); // wLength: 2 (0008)16 - Data length (byte)

        // Create the command byte array
        byte[] command = new byte[8];
        command[0] = bmRequestType;
        command[1] = 0; // bRequest is handled internally in this method
        System.arraycopy(wValueBytes, 0, command, 2, 2); // Copy wValue bytes to command array
        System.arraycopy(wIndexBytes, 0, command, 4, 2); // Copy wIndex bytes to command array
        System.arraycopy(wLengthBytes, 0, command, 6, 2); // Copy wLength bytes to command array

        return command;
    }

    public byte[] intToLittleEndianBytes(int value, int length) {
        byte[] bytes = new byte[length];
        for (int i = 0; i < length; i++) {
            bytes[i] = (byte) ((value >> (8 * i)) & 0xFF);
        }
        return bytes;
    }

    public byte[] convertToLittleEndian(String hexValue) {
        int intValue = Integer.parseInt(hexValue, 16); // Convert hexadecimal string to integer

        // Ensure the integer value is even
        if (intValue % 2 != 0) {
            intValue++; // Increment if odd
        }

        // Convert the integer value to bytes in little endian format
        byte[] bytes = new byte[]{
                (byte) (intValue & 0xFF),
                (byte) ((intValue >> 8) & 0xFF)
        };

        return bytes;
    }

    public void printCommandHexSequence(byte[] commandArray) {
        AppLogs.generate("Command Byte: " + Arrays.toString(commandArray));
        AppLogs.generate("Command ByteArray: " + commandCalculator.printByteArray(commandArray));
    }

    public String getErrorReason(String errorCode) {
        String returnValue = "";
        if (errorCode.equalsIgnoreCase(cmdErrorCode.CODE_01)) {
            returnValue = "Command Sending Request is not received yet.";
        } else if (errorCode.equalsIgnoreCase(cmdErrorCode.CODE_02)) {
            returnValue = "Response Receiving Request is not received yet.";
        } else if (errorCode.equalsIgnoreCase(cmdErrorCode.CODE_11)) {
            returnValue = "Command length is shorter/longer than the specified value.";
        } else if (errorCode.equalsIgnoreCase(cmdErrorCode.CODE_7E)) {
            returnValue = "Sending response data is not completed yet.";
        } else if (errorCode.equalsIgnoreCase(cmdErrorCode.CODE_7F)) {
            returnValue = "Receiving command data is not completed yet";
        } else if (errorCode.equalsIgnoreCase(cmdErrorCode.CODE_F0)) {
            returnValue = "No Error Reason.";
        } else if (errorCode.equalsIgnoreCase(cmdErrorCode.CODE_F1)) {
            returnValue = "No Error Reason.";
        } else if (errorCode.equalsIgnoreCase(cmdErrorCode.CODE_F2)) {
            returnValue = "wValue is out of the range or an odd number.";
        } else if (errorCode.equalsIgnoreCase(cmdErrorCode.CODE_F3)) {
            returnValue = "Specified End Point does not exist or can not be designated";
        } else if (errorCode.equalsIgnoreCase(cmdErrorCode.CODE_F5)) {
            returnValue = "Specified End Point can not be used. (STALL) ";
        } else if (errorCode.equalsIgnoreCase(cmdErrorCode.CODE_F6)) {
            returnValue = "End Point does not have data. (No response)";
        } else if (errorCode.equalsIgnoreCase(cmdErrorCode.CODE_F7)) {
            returnValue = "Beyond the number (receiving buffer busy) of receivable commands by Device in a row (without any response)";
        } else if (errorCode.equalsIgnoreCase(cmdErrorCode.CODE_F9)) {
            returnValue = "";
        } else if (errorCode.equalsIgnoreCase(cmdErrorCode.CODE_FC)) {
            returnValue = "wlLngth other than 8";
        } else {
            returnValue = "No Error Reason.";
        }
        AppLogs.generate(returnValue);
        return returnValue;
    }

    public String getHexFromInt(int input) {
        String value = String.format("%02X", input);
        return value;
    }

    public String getHexFromInt4(int input) {
        String value = String.format("%04X", input);
        return value;
    }


    public String addSpaceAfterTwoDigits(String input) {
        StringBuilder output = new StringBuilder();

        for (int i = 0; i < input.length(); i++) {
            output.append(input.charAt(i));
            // Add a space after every two digits except the last one
            if ((i + 1) % 2 == 0 && i != input.length() - 1) {
                output.append(" ");
            }
        }

        return output.toString();
    }

}
