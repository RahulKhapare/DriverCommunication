package com.hpy.crmdriver.ui.theme.cmd_generator;

import android.content.Context;
import android.hardware.usb.UsbDeviceConnection;
import android.hardware.usb.UsbEndpoint;
import android.text.TextUtils;
import android.widget.TextView;

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
import com.hpy.crmdriver.ui.theme.cmd_msg_data.Transfer;
import com.hpy.crmdriver.ui.theme.cmd_msg_data.UserMemoryRead;
import com.hpy.crmdriver.ui.theme.cmd_msg_data.UserMemoryWrite;
import com.hpy.crmdriver.ui.theme.util.AppLogs;

public class CommandGenerator {

    public CommandCalculator commandCalculator = new CommandCalculator();
    public ControlBulkCmdGenerator controlBulkCmdGenerator = new ControlBulkCmdGenerator();
    public CmdSupportClass cmdSupportClass = new CmdSupportClass();
    public CommandType commandType = new CommandType();
    public CmdErrorCode cmdErrorCode = new CmdErrorCode();
    public StringBuilder stringBuilder;

    public int commandSendingRequestCount = 0;
    public int commandSendingConfirmationCount = 0;
    public int responseReceiveRequestCount = 0;
    public int responseReceiveConfirmationCount = 0;

    public boolean isResponseReceived = false;

    public String generate(Context context, UsbDeviceConnection usbConnection, UsbEndpoint endpointOne, UsbEndpoint endpointTwo, String commandType, TextView textView) {
        isResponseReceived = false;
        clearCommandCounter();
        stringBuilder = new StringBuilder();
        String returnValue = "";
        String message = "";
        message = textView.getText().toString();
        appendText(message, textView);

//        String commandCode = getCommandCode(commandType);
//        if (TextUtils.isEmpty(commandCode)) {
//            message = "**** Unable to find command code ****";
//            appendText(message, textView);
//            AppLogs.generate(message);
//        }

        message = "**** Start " + commandType + " Command ****";
        appendText(message, textView);
        AppLogs.generate(message);
        returnValue = getInputCommandValue(context, usbConnection, endpointOne, endpointTwo, commandType, textView);
        message = "**** End " + commandType + " Command ****";
        appendText(message, textView);
        AppLogs.generate(message);
        AppLogs.generate(returnValue);
        return returnValue;
    }

    public String getInputCommandValue(Context context, UsbDeviceConnection usbConnection, UsbEndpoint endpointOne, UsbEndpoint endpointTwo, String commandType, TextView textView) {

        String responseCode = "";
        String returnValue = "";
        String interruptStatus = "";
        String command = commandCalculator.getCommand(context, commandType);
        appendText("Input Command : " + command, textView);
        byte[] commandArray = commandCalculator.hexStringToByteArray(command);

        int commandLength = 0;
        if (!TextUtils.isEmpty(command)) {
            String length = command.substring(0, Math.min(command.length(), 4));
            commandLength = Integer.parseInt(length, 16);
            AppLogs.generate("Command Length : " + commandLength);
        }

        //TODO - Check interrupt status (before fire command)
        interruptStatus = checkInterruptStatus(context, commandType);

        //TODO - Command Sending Request (Step 1)
        String commandSendingRequest = controlBulkCmdGenerator.commandSendingRequest(context, usbConnection, textView, stringBuilder, commandLength);
        appendText("Command Sending Request (Output) : " + commandSendingRequest, textView);
        responseCode = cmdSupportClass.get3rdHexValue(commandSendingRequest);
        commandSendingRequestCount = commandSendingRequestCount + 1;

        if (!TextUtils.isEmpty(responseCode) && responseCode.equals(cmdErrorCode.CODE_00)) {

            //TODO - Check interrupt status (before fire command)
            interruptStatus = checkInterruptStatus(context, commandType);

            //TODO - Bulk-Out Request (Step 2)
            boolean isBulkOutRequest = controlBulkCmdGenerator.bulkOutRequest(usbConnection, commandArray, endpointOne, textView, stringBuilder);

            if (isBulkOutRequest) {

                int length = 7;
                for (int i = 0; i < length; i++) {

                    //TODO - Stop command sending request once received final confirmation
                    if (isResponseReceived) {
                        break;
                    }

                    //TODO - Check interrupt status (before fire command)
                    interruptStatus = checkInterruptStatus(context, commandType);

                    //TODO - Command Sending Confirmation (Step 3)
                    String commandSendingConfirmation = controlBulkCmdGenerator.commandSendingConfirmation(context, usbConnection, i, textView, stringBuilder, commandLength);
                    appendText(i + " - " + "Command Sending Confirmation (Output) : " + commandSendingConfirmation, textView);
                    responseCode = cmdSupportClass.get3rdHexValue(commandSendingConfirmation);
                    commandSendingConfirmationCount = commandSendingConfirmationCount + 1;

                    if (!TextUtils.isEmpty(responseCode) && responseCode.equals(cmdErrorCode.CODE_00)) {

                        int size = 4;
                        for (int j = 0; j < size; j++) {

                            if (responseReceiveRequestCount <= 3) {
                                //TODO - Check interrupt status (before fire command)
                                interruptStatus = checkInterruptStatus(context, commandType);

                                //TODO - Response Receiving Request (Step 4)
                                String responseReceiveRequest = controlBulkCmdGenerator.responseReceiveRequest(context, usbConnection, textView, stringBuilder);
                                appendText("Response Receiving Request (Output) : " + responseReceiveRequest, textView);
                                responseCode = cmdSupportClass.get3rdHexValue(responseReceiveRequest);

                                if (!TextUtils.isEmpty(responseCode) && responseCode.equals(cmdErrorCode.CODE_00)) {
                                    responseReceiveRequestCount = responseReceiveRequestCount + 1;

                                    //TODO - Check interrupt status (before fire command)
                                    interruptStatus = checkInterruptStatus(context, commandType);

                                    //TODO - Bulk-In Request (Step 5)
                                    String receivedBulInRequest = controlBulkCmdGenerator.receivedBulInRequest(context, usbConnection, endpointTwo);
                                    appendText("Bulk-In Request Response : " + receivedBulInRequest, textView);
                                    if (!TextUtils.isEmpty(receivedBulInRequest)) {

                                        //TODO - Check BCC (check for functionality and code)

                                        //TODO - Set Bulk In Response
                                        checkResponseStatus(context, commandType, receivedBulInRequest);

                                        //TODO - Check interrupt status (before fire command)
                                        interruptStatus = checkInterruptStatus(context, commandType);

                                        //TODO - Response Receiving Confirmation (Step 6)
                                        String responseReceivedConformation = controlBulkCmdGenerator.responseReceivedConformation(context, usbConnection, textView, stringBuilder);
                                        appendText("Response Receiving Confirmation (Output) : " + responseReceivedConformation, textView);
                                        responseCode = cmdSupportClass.get3rdHexValue(responseReceivedConformation);
                                        responseReceiveConfirmationCount = responseReceiveConfirmationCount + 1;

                                        if (!TextUtils.isEmpty(responseCode) && responseCode.equals(cmdErrorCode.CODE_00)) {
                                            returnValue = getResponse(receivedBulInRequest);
                                            isResponseReceived = true;
                                            break;
                                        } else {
                                            //TODO - Response Receiving Confirmation (Error Handling)
                                            if (isErrorFound017E(responseCode)) {
                                                if (responseReceiveConfirmationCount >= 3) {
                                                    returnValue = returnError(responseCode);
                                                    break;
                                                }
                                            } else if (isErrorFound80FF(responseCode)) {
                                                returnValue = returnError(responseCode);
                                                break;
                                            } else {
                                                returnValue = returnError(responseCode);
                                                break;
                                            }
                                        }

                                    }
                                } else if (isErrorFound017E(responseCode) || isErrorFound80FF(responseCode) || responseCode.equalsIgnoreCase(cmdErrorCode.CODE_7F)) {
                                    //TODO - Response Receiving Request (Error Handling)
                                    returnValue = returnError(responseCode);
                                    break;
                                }
                            } else {
                                returnValue = returnError(responseCode);
                                break;
                            }
                        }
                    } else {
                        //TODO - Command Sending Confirmation (Error Handling)
                        if (responseCode.equalsIgnoreCase(cmdErrorCode.CODE_7F)) {
                            AppLogs.generate("CommandSendingConfirmation Failed : " + i);
                            if (i == length - 1) {
                                returnValue = returnError(responseCode);
                                break;
                            }
                        } else if (isErrorFound017E(responseCode)) {
                            if (commandSendingConfirmationCount < 3) {
                                //clear command sending request count
                                commandSendingRequestCount = 0;
                                //check command sending request
                                getInputCommandValue(context, usbConnection, endpointOne, endpointTwo, commandType, textView);
                            } else {
                                returnValue = returnError(responseCode);
                                break;
                            }
                        } else if (isErrorFound80FF(responseCode)) {
                            returnValue = returnError(responseCode);
                            break;
                        } else {
                            AppLogs.generate("CommandSendingConfirmation Failed : " + i);
                            returnValue = returnError(responseCode);
                            break;
                        }
                    }

                    if (i >= 4 && i <= 7) {
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }

            } else {
                returnValue = getError();
            }
        } else if (responseCode.equalsIgnoreCase(cmdErrorCode.CODE_7F)) {
            returnValue = returnError(responseCode);
        } else if (isErrorFound017E(responseCode)) {
            //TODO - check repeat count conditions
            if (commandSendingRequestCount < 3) {
                //check command sending request
                getInputCommandValue(context, usbConnection, endpointOne, endpointTwo, commandType, textView);
            } else {
                returnValue = returnError(responseCode);
            }
        } else if (isErrorFound80FF(responseCode)) {
            returnValue = returnError(responseCode);
        } else {
            returnValue = returnError(responseCode);
        }

        appendText("Error Reason: " + cmdSupportClass.getErrorReason(responseCode), textView);

        AppLogs.generate(returnValue);
        return returnValue;
    }

    public String returnError(String responseCode) {
        String returnValue = "Error Reason: " + cmdSupportClass.getErrorReason(responseCode);
        AppLogs.generate(returnValue);
        getError();
        AppLogs.generate(returnValue);
        return returnValue;
    }

    public boolean isErrorFound017E(String errorCode) {
        boolean isFound = false;
        for (String hex : cmdErrorCode.sequence017E) {
            if (errorCode.equalsIgnoreCase(hex)) {
                isFound = true;
                break;
            }
        }
        return isFound;
    }

    public boolean isErrorFound80FF(String errorCode) {
        boolean isFound = false;
        for (String hex : cmdErrorCode.sequence80FF) {
            if (errorCode.equalsIgnoreCase(hex)) {
                isFound = true;
                break;
            }
        }
        return isFound;
    }

    public String checkResponseStatus(Context context, String cmdType, String response) {
        String returnValue = "";

        if (cmdType.equals(commandType.GET_FIRMWARE)) {
            FirmwareCommand firmwareCommand = new FirmwareCommand();
            firmwareCommand.parseCommandResponse(context, response);
        } else if (cmdType.equals(commandType.PROGRAM_DOWNLOAD)) {
            ProgramDownload programDownload = new ProgramDownload();
            programDownload.parseCommandResponse(context, response);
        } else if (cmdType.equals(commandType.GET_UNIT_INFO)) {
            GetUnitInfo getUnitInfo = new GetUnitInfo();
            getUnitInfo.parseCommandResponse(context, response);
        } else if (cmdType.equals(commandType.SET_UNIT_INFO)) {
            SetUnitInfo setUnitInfo = new SetUnitInfo();
            setUnitInfo.parseCommandResponse(context, response);
        } else if (cmdType.equals(commandType.RESET)) {
            Reset reset = new Reset();
            reset.parseCommandResponse(context, response);
        } else if (cmdType.equals(commandType.DRIVER_ACCESSORY)) {
            DriverAccessory driverAccessory = new DriverAccessory();
            driverAccessory.parseCommandResponse(context, response);
        } else if (cmdType.equals(commandType.READ_STATUS)) {
            ReadStatus readStatus = new ReadStatus();
            readStatus.parseCommandResponse(context, response);
        } else if (cmdType.equals(commandType.GET_LOGS_DATA)) {
            GetLogsData getLogsData = new GetLogsData();
            getLogsData.parseCommandResponse(context, response);
        } else if (cmdType.equals(commandType.CANCEL)) {
            Cancel cancel = new Cancel();
            cancel.parseCommandResponse(context, response);
        } else if (cmdType.equals(commandType.CASH_COUNT)) {
            CashCount cashCount = new CashCount();
            cashCount.parseCommandResponse(context, response);
        } else if (cmdType.equals(commandType.DISPENSE)) {
            Dispense dispense = new Dispense();
            dispense.parseCommandResponse(context, response);
        } else if (cmdType.equals(commandType.STORE_MONEY)) {
            StoreMoney storeMoney = new StoreMoney();
            storeMoney.parseCommandResponse(context, response);
        } else if (cmdType.equals(commandType.CASH_ROLLBACK)) {
            CashRollback cashRollback = new CashRollback();
            cashRollback.parseCommandResponse(context, response);
        } else if (cmdType.equals(commandType.RETRACT)) {
            Retract retract = new Retract();
            retract.parseCommandResponse(context, response);
        } else if (cmdType.equals(commandType.TRANSFER)) {
            Transfer transfer = new Transfer();
            transfer.parseCommandResponse(context, response);
        } else if (cmdType.equals(commandType.DRIVE_SHUTTER)) {
            DriveShutter driveShutter = new DriveShutter();
            driveShutter.parseCommandResponse(context, response);
        } else if (cmdType.equals(commandType.PREPARE_TRANS)) {
            PrepareTransaction prepareTransaction = new PrepareTransaction();
            prepareTransaction.parseCommandResponse(context, response);
        } else if (cmdType.equals(commandType.GET_BANK_NOTE_INFO)) {
            GetBankNoteInfo getBankNoteInfo = new GetBankNoteInfo();
            getBankNoteInfo.parseCommandResponse(context, response);
        } else if (cmdType.equals(commandType.GET_CASSETTE_INFO)) {
            GetCassetteInfo getCassetteInfo = new GetCassetteInfo();
            getCassetteInfo.parseCommandResponse(context, response);
        } else if (cmdType.equals(commandType.SET_DENOMINATION_CODE)) {
            SetDenominationCode setDenominationCode = new SetDenominationCode();
            setDenominationCode.parseCommandResponse(context, response);
        } else if (cmdType.equals(commandType.USER_MEMORY_WRITE)) {
            UserMemoryWrite userMemoryWrite = new UserMemoryWrite();
            userMemoryWrite.parseCommandResponse(context, response);
        } else if (cmdType.equals(commandType.USER_MEMORY_READ)) {
            UserMemoryRead userMemoryRead = new UserMemoryRead();
            userMemoryRead.parseCommandResponse(context, response);
        } else if (cmdType.equals(commandType.REBOOT)) {
            Reboot reboot = new Reboot();
            reboot.parseCommandResponse(context, response);
        }

//        else if (cmdType.equals(commandType.TEST_COMMAND)) {
//
//        }

        AppLogs.generate(returnValue);
        return returnValue;

    }


    public String checkInterruptStatus(Context context, String cmdType) {
        String returnValue = "";

        if (cmdType.equals(commandType.GET_FIRMWARE)) {
            //TODO - need to check how we can use different function for different command
//            returnValue = InterruptStatusChecker.getStatusOfByteEight(context);

        } else if (cmdType.equals(commandType.PROGRAM_DOWNLOAD)) {

        } else if (cmdType.equals(commandType.GET_UNIT_INFO)) {

        } else if (cmdType.equals(commandType.SET_UNIT_INFO)) {

        } else if (cmdType.equals(commandType.RESET)) {

        } else if (cmdType.equals(commandType.DRIVER_ACCESSORY)) {

        } else if (cmdType.equals(commandType.READ_STATUS)) {

        } else if (cmdType.equals(commandType.GET_LOGS_DATA)) {

        } else if (cmdType.equals(commandType.CANCEL)) {

        } else if (cmdType.equals(commandType.CASH_COUNT)) {

        } else if (cmdType.equals(commandType.DISPENSE)) {

        } else if (cmdType.equals(commandType.STORE_MONEY)) {

        } else if (cmdType.equals(commandType.CASH_ROLLBACK)) {

        } else if (cmdType.equals(commandType.RETRACT)) {

        } else if (cmdType.equals(commandType.TRANSFER)) {

        } else if (cmdType.equals(commandType.DRIVE_SHUTTER)) {

        } else if (cmdType.equals(commandType.PREPARE_TRANS)) {

        } else if (cmdType.equals(commandType.GET_BANK_NOTE_INFO)) {

        } else if (cmdType.equals(commandType.GET_CASSETTE_INFO)) {

        } else if (cmdType.equals(commandType.SET_DENOMINATION_CODE)) {

        } else if (cmdType.equals(commandType.USER_MEMORY_WRITE)) {

        } else if (cmdType.equals(commandType.USER_MEMORY_READ)) {

        } else if (cmdType.equals(commandType.REBOOT)) {

        }
//        else if (cmdType.equals(commandType.TEST_COMMAND)) {
//
//        }

        AppLogs.generate(returnValue);
        return returnValue;
    }

    public String getError() {
        AppLogs.generate("Communication process end");
        return "Unable to generate command";
    }

    public String getResponse(String response) {
        AppLogs.generate("Communication process end");
        return "Command Response : " + response;
    }

    public void appendText(String message, TextView textView) {
        stringBuilder.append(message + "\n\n");
        textView.setText(stringBuilder.toString());
    }

    public void clearCommandCounter() {
        commandSendingRequestCount = 0;
    }
}
