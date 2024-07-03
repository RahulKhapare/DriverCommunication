package com.hpy.crmdriver.ui.theme.cmd_generator;

import android.content.Context;
import android.util.Log;

import com.hpy.crmdriver.ui.theme.cmd_msg_data.Cancel;
import com.hpy.crmdriver.ui.theme.cmd_msg_data.CashCount;
import com.hpy.crmdriver.ui.theme.cmd_msg_data.CashRollback;
import com.hpy.crmdriver.ui.theme.cmd_msg_data.Dispense;
import com.hpy.crmdriver.ui.theme.cmd_msg_data.DriveShutter;
import com.hpy.crmdriver.ui.theme.cmd_msg_data.DriverAccessory;
import com.hpy.crmdriver.ui.theme.cmd_msg_data.FirmwareCommand;
import com.hpy.crmdriver.ui.theme.cmd_msg_data.GetBankNoteInfo;
import com.hpy.crmdriver.ui.theme.cmd_msg_data.GetCassetteInfo;
import com.hpy.crmdriver.ui.theme.cmd_msg_data.GetLogsData;
import com.hpy.crmdriver.ui.theme.cmd_msg_data.GetUnitInfo;
import com.hpy.crmdriver.ui.theme.cmd_msg_data.PrepareTransaction;
import com.hpy.crmdriver.ui.theme.cmd_msg_data.ProgramDownload;
import com.hpy.crmdriver.ui.theme.cmd_msg_data.ReadStatus;
import com.hpy.crmdriver.ui.theme.cmd_msg_data.Reboot;
import com.hpy.crmdriver.ui.theme.cmd_msg_data.Reset;
import com.hpy.crmdriver.ui.theme.cmd_msg_data.Retract;
import com.hpy.crmdriver.ui.theme.cmd_msg_data.SetDenominationCode;
import com.hpy.crmdriver.ui.theme.cmd_msg_data.SetUnitInfo;
import com.hpy.crmdriver.ui.theme.cmd_msg_data.StoreMoney;
import com.hpy.crmdriver.ui.theme.cmd_msg_data.Testcommand;
import com.hpy.crmdriver.ui.theme.cmd_msg_data.Transfer;
import com.hpy.crmdriver.ui.theme.cmd_msg_data.UserMemoryRead;
import com.hpy.crmdriver.ui.theme.cmd_msg_data.UserMemoryWrite;
import com.hpy.crmdriver.ui.theme.util.AppConfig;
import com.hpy.crmdriver.ui.theme.util.AppLogs;

public class CommandCalculator {

    public CommandSequence commandSequence = new CommandSequence();
    public XORCalculator xorCalculator = new XORCalculator();
    public CommandType commandType = new CommandType();
    public AppConfig appConfig = new AppConfig();
    public String SA_UA = "00 00";
    public String SEQ = "00";
    public String FLG = "01";
    public String RSV = "00 00";

    //0012 0000 0801 0000 0008 0001 0004 3F00 371E

    public String getCommand(Context context, String commandType,boolean isNewCommandSeq) {
        String returnValue = "";

        SEQ = commandSequence.getNextSeqCommand(context,isNewCommandSeq);

        AppLogs.generateTAG("CMD_SWQ", commandType + " SEQ : " + SEQ);

        String beforeLengthData = SA_UA + SEQ + FLG + RSV + getPacketMessageData(context, commandType);

        //get total message length
        String totalLength = getTotalMessageLength(beforeLengthData.replaceAll(" ", ""));
        Log.e("TAG", "beforeLengthData: " + beforeLengthData);
        Log.e("TAG", "totalLength: " + totalLength);

        //convert total length in HEXADECIMAL
        String hexLengthValue = decimalToHex(Integer.parseInt(totalLength));
        Log.e("TAG", "totalHexLengthValue: " + totalLength);

        //return value with MH + MD
        returnValue = hexLengthValue + beforeLengthData;

        //XOR for overall message
        String getXOR = xorCalculator.getXOR(returnValue);

        //total request data with MH + MD + BCC
        returnValue = returnValue + getXOR;

//        returnValue = getSequenceOfCommand(returnValue);
        AppLogs.generate("Generated Command: " + returnValue.replaceAll(" ", ""));

        return returnValue.replaceAll(" ", "");

    }

    public String getPacketMessageData(Context context, String cmdType) {
        String returnValue = "";

        if (cmdType.equals(commandType.GET_FIRMWARE)) {
            returnValue = new FirmwareCommand().generateCommand();
        } else if (cmdType.equals(commandType.PROGRAM_DOWNLOAD)) {
            returnValue = new ProgramDownload().generateCommand();
        } else if (cmdType.equals(commandType.GET_UNIT_INFO)) {
            returnValue = new GetUnitInfo().generateCommand(context);
        } else if (cmdType.equals(commandType.SET_UNIT_INFO)) {
            returnValue = new SetUnitInfo().generateCommand();
        } else if (cmdType.equals(commandType.RESET)) {
            returnValue = new Reset().generateCommand(context);
        } else if (cmdType.equals(commandType.DRIVER_ACCESSORY)) {
            returnValue = new DriverAccessory().generateCommand();
        } else if (cmdType.equals(commandType.READ_STATUS)) {
            returnValue = new ReadStatus().generateCommand();
        } else if (cmdType.equals(commandType.GET_LOGS_DATA)) {
            returnValue = new GetLogsData().generateCommand(context);
        } else if (cmdType.equals(commandType.CANCEL)) {
            returnValue = new Cancel().generateCommand();
        } else if (cmdType.equals(commandType.CASH_COUNT)) {
            returnValue = new CashCount().generateCommand(context);
        } else if (cmdType.equals(commandType.DISPENSE)) {
            returnValue = new Dispense().generateCommand(context);
        } else if (cmdType.equals(commandType.STORE_MONEY)) {
            returnValue = new StoreMoney().generateCommand(context);
        } else if (cmdType.equals(commandType.CASH_ROLLBACK)) {
            returnValue = new CashRollback().generateCommand(context);
        } else if (cmdType.equals(commandType.RETRACT)) {
            returnValue = new Retract().generateCommand(context);
        } else if (cmdType.equals(commandType.TRANSFER)) {
            returnValue = new Transfer().generateCommand();
        } else if (cmdType.equals(commandType.DRIVE_SHUTTER)) {
            returnValue = new DriveShutter().generateCommand(context);
        } else if (cmdType.equals(commandType.PREPARE_TRANS)) {
            returnValue = new PrepareTransaction().generateCommand(context);
        } else if (cmdType.equals(commandType.GET_BANK_NOTE_INFO)) {
            returnValue = new GetBankNoteInfo().generateCommand();
        } else if (cmdType.equals(commandType.GET_CASSETTE_INFO)) {
            returnValue = new GetCassetteInfo().generateCommand();
        } else if (cmdType.equals(commandType.SET_DENOMINATION_CODE)) {
            returnValue = new SetDenominationCode().generateCommand();
        } else if (cmdType.equals(commandType.USER_MEMORY_WRITE)) {
            returnValue = new UserMemoryWrite().generateCommand();
        } else if (cmdType.equals(commandType.USER_MEMORY_READ)) {
            returnValue = new UserMemoryRead().generateCommand();
        } else if (cmdType.equals(commandType.REBOOT)) {
            returnValue = new Reboot().generateCommand();
        }
//        else if (cmdType.equals(commandType.TEST_COMMAND)) {
//            returnValue = new Testcommand().generateCommand();
//        }

        Log.e("TAG", "PacketMessageData: " + returnValue);

        AppLogs.generate(returnValue);
        return returnValue;
    }

    public String getCommandLength(String packetId, String commandCode) {
        String returnValue = "";
        int chunkSize = 2; // Define the size of each chunk
        String inputString = packetId + commandCode;

        // Calculate the effective length considering four digits as one character
        int effectiveLength = inputString.length() / chunkSize;

        // Format the output with leading zeros if necessary
        returnValue = String.format("%04d", effectiveLength);

        AppLogs.generate(returnValue);
        return returnValue;
    }

    public String getTotalMessageLength(String message) {
        String returnValue = "";
        int chunkSize = 2; // Define the size of each chunk
        String inputString = message + "00000000";// last zero for additional length MLEN + BCC

        // Calculate the effective length considering four digits as one character
        int effectiveLength = inputString.length() / chunkSize;

        // Format the output with leading zeros if necessary
        returnValue = String.format("%04d", effectiveLength);

        AppLogs.generate(returnValue);
        return returnValue;
    }

    public String decimalToHex(int decimalNumber) {
        String hexString = Integer.toHexString(decimalNumber);

        // Pad the hexadecimal string with leading zeros if necessary to make it four characters long
        while (hexString.length() < 4) {
            hexString = "0" + hexString;
        }

        return hexString;
    }

    public byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i + 1), 16));
        }
        return data;
    }

    public String printByteArray(byte[] byteArray) {
        StringBuilder sb = new StringBuilder();
        for (byte b : byteArray) {
//            Log.e("TAG", "printByteArrayQQ: " + String.format("0x%02X, ", b & 0xFF));
            sb.append(String.format("0x%02X, ", b & 0xFF));
        }
        String result = sb.toString();
        // Removing the last comma and space
        result = result.substring(0, result.length() - 2);
        // Adding explicit casting for bytes with values > 127
        result = result.replaceAll("0x(8[0-9A-F]|9[0-9A-F]|[A-F][0-9A-F]),", "(byte) $0");
        return result;
    }

    public String getSequenceOfCommand(String command) {
        String returnValue = "";
        returnValue = command.replaceAll(" ", "");
        returnValue = addSpaceEveryTwo(returnValue);
        AppLogs.generate(returnValue);
        return returnValue;
    }

    public String addSpaceEveryTwo(String input) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < input.length(); i += 2) {
            if (i > 0) {
                builder.append(" "); // Add a space
            }
            builder.append(input.substring(i, Math.min(i + 2, input.length())));
        }
        return builder.toString();
    }

}
