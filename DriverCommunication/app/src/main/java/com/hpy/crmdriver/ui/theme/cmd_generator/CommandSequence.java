package com.hpy.crmdriver.ui.theme.cmd_generator;

import android.content.Context;
import android.text.TextUtils;

import com.hpy.crmdriver.ui.theme.util.KeyValue;
import com.hpy.crmdriver.ui.theme.util.SessionData;

public class CommandSequence {

    public String getNextSeqCommand(Context context) {
        // Convert the previous value to an integer
        String previousValue = SessionData.getStringValue(context, KeyValue.CMD_SEQ);
        if (TextUtils.isEmpty(previousValue)) {
            previousValue = "00";
        }
        int intValue = Integer.parseInt(previousValue, 16);
        // Increment the integer value
        intValue++;
        // Reset to 0 if it reaches 16
        if (intValue == 16) {
            intValue = 0;
        }
        // Convert the integer back to hexadecimal format
        String nextHexValue = String.format("%02X", intValue);
        SessionData.addValue(context, KeyValue.CMD_SEQ, nextHexValue);
        // Return the next hexadecimal value
        return nextHexValue;
    }

    public int getNextSeqCmdSendingReq(Context context) {
        // Retrieve the previous value stored in session data
        String previousValue = SessionData.getStringValue(context, KeyValue.CMD_SEND_REQ);

        boolean isUpdate = false;
        // If no previous value exists, initialize it to "00"
        if (TextUtils.isEmpty(previousValue)) {
            previousValue = "00";
        } else {
            isUpdate = true;
        }

        // Parse the hexadecimal string to an integer
        int intValue = Integer.parseInt(previousValue, 16);

        // Increment the integer value
        if (isUpdate) {
            intValue++;
        }

        // Reset to 0 if it reaches 0F
        if (intValue > 0x0F) {
            intValue = 0x00;
        }

        // Convert the integer back to hexadecimal format
        String nextHexValue = String.format("%02X", intValue);

        // Store the new hexadecimal value in session data
        SessionData.addValue(context, KeyValue.CMD_SEND_REQ, nextHexValue);

        // Return the next integer value
        return intValue;
    }

    public int getNextSeqCmdSendingConf(Context context, int indexPosition) {
        // Retrieve the previous value stored in session data
        String previousValue = SessionData.getStringValue(context, KeyValue.CMD_SEND_CONF);

        boolean isUpdate = false;
        // If no previous value exists, initialize it to "10"
        if (TextUtils.isEmpty(previousValue)) {
            previousValue = "10";
        } else {
            isUpdate = true;
        }

        // Parse the hexadecimal string to an integer
        int intValue = Integer.parseInt(previousValue, 16);

        // Increment the integer value
        if (isUpdate && indexPosition == 0) {
            intValue++;
        }

        // Reset to 0x10 if it reaches 0x1F
        if (intValue > 0x1F) {
            intValue = 0x10;
        }

        // Convert the integer back to hexadecimal format
        String nextHexValue = String.format("%02X", intValue);

        // Store the new hexadecimal value in session data
        SessionData.addValue(context, KeyValue.CMD_SEND_CONF, nextHexValue);

        // Return the next integer value
        return intValue;
    }


    public int getNextSeqResponseRecReq(Context context, int totalCount) {
        // Retrieve the previous value stored in session data
        String previousValue = SessionData.getStringValue(context, KeyValue.RES_RECEIVING_REQ);

        boolean isUpdate = false;
        // If no previous value exists, initialize it to "80"
        if (TextUtils.isEmpty(previousValue)) {
            previousValue = "80";
        } else {
            isUpdate = true;
        }

        // Parse the hexadecimal string to an integer
        int intValue = Integer.parseInt(previousValue, 16);

        // Increment the integer value
        if (isUpdate) {
            if (totalCount <= 3) {
                //do nothing
            } else {
                intValue++;
            }
        }


        // Reset to 0x80 if it reaches 0x8F
        if (intValue > 0x8F) {
            intValue = 0x80;
        }

        // Convert the integer back to hexadecimal format
        String nextHexValue = String.format("%02X", intValue);

        // Store the new hexadecimal value in session data
        SessionData.addValue(context, KeyValue.RES_RECEIVING_REQ, nextHexValue);

        // Return the next integer value
        return intValue;
    }

    public int getNextSeqResponseRecConf(Context context, int totalCount) {
        // Retrieve the previous value stored in session data
        String previousValue = SessionData.getStringValue(context, KeyValue.RES_RECEIVING_CONF);

        boolean isUpdate = false;
        // If no previous value exists, initialize it to "90"
        if (TextUtils.isEmpty(previousValue)) {
            previousValue = "90";
        } else {
            isUpdate = true;
        }

        // Parse the hexadecimal string to an integer
        int intValue = Integer.parseInt(previousValue, 16);

        // Increment the integer value
        if (isUpdate) {
            if (totalCount < 3) {
                //do nothing
            } else {
                intValue++;
            }
        }

        // Reset to 0x90 if it reaches 0x9F
        if (intValue > 0x9F) {
            intValue = 0x90;
        }

        // Convert the integer back to hexadecimal format
        String nextHexValue = String.format("%02X", intValue);

        // Store the new hexadecimal value in session data
        SessionData.addValue(context, KeyValue.RES_RECEIVING_CONF, nextHexValue);

        // Return the next integer value
        return intValue;
    }

}
