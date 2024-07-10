package com.hpy.crmdriver.ui.theme.cmd_generator;

import android.content.Context;
import android.hardware.usb.UsbDeviceConnection;
import android.hardware.usb.UsbEndpoint;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.widget.TextView;
import android.widget.Toast;

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
import com.hpy.crmdriver.ui.theme.session.SessionModel;
import com.hpy.crmdriver.ui.theme.util.AppLogs;
import com.hpy.crmdriver.ui.theme.util.SessionData;
import com.hpy.crmdriver.ui.theme.util.TimeOut;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CountDownLatch;

public class CommandGeneratorUpdated {

    public CommandCalculator commandCalculator = new CommandCalculator();
    public ControlBulkCmdGenerator controlBulkCmdGenerator = new ControlBulkCmdGenerator();
    public CmdSupportClass cmdSupportClass = new CmdSupportClass();
    public CommandType commandFormatType = new CommandType();
    public CmdErrorCode cmdErrorCode = new CmdErrorCode();
    public StringBuilder stringBuilder;

    public int commandSendingRequestCount = 0;
    public int commandSendingConfirmationCount = 0;
    public int responseReceiveRequestCount = 0;
    public int responseReceiveRequestPosition = 0;
    public int responseReceiveConfirmationCount = 0;
    public int responseReceiveConfirmationPosition = 0;

    public boolean isResponseReceived = false;

    public SessionData sessionData = new SessionData();
    public SessionModel sessionModel = new SessionModel();
    public TimeOut timeOut = new TimeOut();
    private CountDownTimer countDownTimer;

    public String responseCode = "";
    public String ERROR_CODE = "f6";

    public int commandLength = 0;
    public boolean isCodeRunning = false;

    public boolean generate(Context context, UsbDeviceConnection usbConnection, UsbEndpoint endpointOne, UsbEndpoint endpointTwo, String commandType, TextView textView) {
        clearData();;
        stringBuilder = new StringBuilder();
        String message = "";
        message = textView.getText().toString();
        appendText(message, textView);

        message = "\n\n**** Start " + commandType + " Command ****";
        appendText(message, textView);
        AppLogs.generate(message);
        getInputCommandValue(true, context, usbConnection, endpointOne, endpointTwo, commandType, textView, new OnTimerFinishedListener() {
            @Override
            public void onTimerFinished(boolean isFinished) {
                Toast.makeText(context, "Timer finished: " + isResponseReceived, Toast.LENGTH_SHORT).show();
            }
        });

        message = "**** End " + commandType + " Command ****\n\n";
        appendText(message, textView);
        AppLogs.generate(message);

        return isResponseReceived;

    }

    public void clearData() {
        isCodeRunning = false;
        isResponseReceived = false;
        commandSendingRequestCount = 0;
        commandSendingConfirmationCount = 0;
        responseReceiveRequestCount = 0;
        responseReceiveRequestPosition = 0;
        responseReceiveConfirmationCount = 0;
        responseReceiveConfirmationPosition = 0;
    }

    public int getResponseLength(String input) {
        int decimalValue = 0;
        if (input.length() >= 4) {
            String hexString = input.substring(input.length() - 4);
            decimalValue = Integer.parseInt(hexString, 16);
        }
        return decimalValue;
    }

    private void stopTimer() {
        if (countDownTimer != null) {
            countDownTimer.cancel();
            countDownTimer = null;
        }
    }

    public interface OnTimerFinishedListener {
        void onTimerFinished(boolean isFinished);
    }

    public boolean getInputCommandValue(boolean isNewCommandSeq, Context context, UsbDeviceConnection usbConnection, UsbEndpoint endpointOne, UsbEndpoint endpointTwo, String commandType, TextView textView, OnTimerFinishedListener listener) {

        isCodeRunning = false;
        isResponseReceived = false;

        String generatedCommand = commandCalculator.getCommand(context, commandType, isNewCommandSeq);

        appendText("Input Command : " + generatedCommand, textView);
        byte[] commandArray = commandCalculator.hexStringToByteArray(generatedCommand);

        commandLength = 0;
        if (!TextUtils.isEmpty(generatedCommand)) {
            String length = generatedCommand.substring(0, Math.min(generatedCommand.length(), 4));
            commandLength = Integer.parseInt(length, 16);
            AppLogs.generate("Command Length : " + commandLength);
        }

        isCodeRunning = false;
        isResponseReceived = false;

        //TODO - Timer For Execution
        int timeOutValue = getTimeOut(commandType);
        countDownTimer = new CountDownTimer(timeOutValue, timeOut.TIMEOUT_1) {
            @Override
            public void onTick(long millisUntilFinished) {

                if (!isCodeRunning) {
                    isCodeRunning = true;

                    int commandSendingRequestLength = 5;
                    commandSendingRequestLoop:
                    for (int i = 0; i < commandSendingRequestLength; i++) {

                        //TODO - Command Sending Request (Step 1)
                        String commandSendingRequest = controlBulkCmdGenerator.commandSendingRequest(context, usbConnection, commandSendingRequestCount, textView, stringBuilder, commandLength);
                        appendText("Command Sending Request (Output) : " + commandSendingRequest, textView);
                        responseCode = cmdSupportClass.get3rdHexValue(commandSendingRequest);
                        commandSendingRequestCount = commandSendingRequestCount + 1;

                        if (!TextUtils.isEmpty(responseCode) && responseCode.equalsIgnoreCase(cmdErrorCode.CODE_00)) {

                            //TODO - Bulk-Out Request (Step 2)
                            boolean isBulkOutRequest = controlBulkCmdGenerator.bulkOutRequest(usbConnection, commandArray, endpointOne, generatedCommand, commandType, textView, stringBuilder);

                            if (isBulkOutRequest) {

                                int commandSendingConfirmationLength = 5;
                                commandSendingConfirmationLoop:
                                for (int j = 0; j < commandSendingConfirmationLength; j++) {

                                    //TODO - Command Sending Confirmation (Step 3)
                                    String commandSendingConfirmation = controlBulkCmdGenerator.commandSendingConfirmation(context, usbConnection, commandSendingConfirmationCount, textView, stringBuilder, commandLength);
                                    appendText(i + " - " + "Command Sending Confirmation (Output) : " + commandSendingConfirmation, textView);
                                    responseCode = cmdSupportClass.get3rdHexValue(commandSendingConfirmation);
                                    commandSendingConfirmationCount = commandSendingConfirmationCount + 1;

                                    if (!TextUtils.isEmpty(responseCode) && responseCode.equalsIgnoreCase(cmdErrorCode.CODE_00)) {

                                        int responseReceiveRequestLength = 5;
                                        responseReceiveRequestLoop:
                                        for (int k = 0; k < responseReceiveRequestLength; k++) {

                                            //TODO - Response Receiving Request (Step 4)
                                            String responseReceiveRequest = controlBulkCmdGenerator.responseReceiveRequest(context, usbConnection, responseReceiveRequestPosition, textView, stringBuilder);
                                            appendText("Response Receiving Request (Output) : " + responseReceiveRequest, textView);
                                            responseCode = cmdSupportClass.get3rdHexValue(responseReceiveRequest);
                                            responseReceiveRequestPosition = responseReceiveRequestPosition + 1;

                                            int datLength = getResponseLength(responseReceiveRequest.replace(" ", ""));

                                            if (!TextUtils.isEmpty(responseCode) && responseCode.equalsIgnoreCase(cmdErrorCode.CODE_00)) {
                                                responseReceiveRequestCount = responseReceiveRequestCount + 1;

                                                //TODO - Bulk-In Request (Step 5)
                                                String receivedBulInRequest = controlBulkCmdGenerator.receivedBulInRequest(context, usbConnection, endpointTwo, commandType, datLength);
                                                appendText("Bulk-In Request Response : " + receivedBulInRequest, textView);

                                                if (!TextUtils.isEmpty(receivedBulInRequest)) {

                                                    //TODO - Set Bulk In Response
                                                    checkResponseStatus(context, commandType, receivedBulInRequest);

                                                    int responseReceivedConformationLength = 5;
                                                    responseReceivedConformationLoop:
                                                    for (int l = 0; l < responseReceivedConformationLength; l++) {

                                                        //TODO - Response Receiving Confirmation (Step 6)
                                                        String responseReceivedConformation = controlBulkCmdGenerator.responseReceivedConformation(context, usbConnection, responseReceiveConfirmationCount, textView, stringBuilder);
                                                        appendText("Response Receiving Confirmation (Output) : " + responseReceivedConformation, textView);
                                                        responseCode = cmdSupportClass.get3rdHexValue(responseReceivedConformation);
                                                        responseReceiveConfirmationCount = responseReceiveConfirmationCount + 1;

                                                        if (!TextUtils.isEmpty(responseCode) && responseCode.equalsIgnoreCase(cmdErrorCode.CODE_00)) {
                                                            Toast.makeText(context, "True", Toast.LENGTH_SHORT).show();
                                                            isResponseReceived = true;
                                                            if (listener != null) {
                                                                listener.onTimerFinished(isResponseReceived);
                                                            }
                                                            stopTimer();
                                                            break commandSendingRequestLoop;
                                                        } else if (responseCode.equalsIgnoreCase(ERROR_CODE)) {
                                                            returnError(responseCode);
                                                            errorSleep(commandType);
                                                        } else if (isErrorFound017E(responseCode)) {
                                                            returnError(responseCode);
                                                            errorSleep(commandType);
                                                        } else if (isErrorFound80FF(responseCode)) {
                                                            returnError(responseCode);
                                                            errorSleep(commandType);
                                                        }

                                                        //TODO - STOP TIMER
                                                        if (j == responseReceivedConformationLength - 1) {
                                                            stopTimer();
                                                        }
                                                    }

                                                } else {
                                                    stopTimer();
                                                    break commandSendingRequestLoop;
                                                }

                                            } else if (responseCode.equalsIgnoreCase(ERROR_CODE)) {
                                                returnError(responseCode);
                                                errorSleep(commandType);
                                            } else if (isErrorFound017E(responseCode)) {
                                                returnError(responseCode);
                                                errorSleep(commandType);
                                            } else if (isErrorFound80FF(responseCode)) {
                                                returnError(responseCode);
                                                errorSleep(commandType);
                                            }

                                            //TODO - STOP TIMER
                                            if (j == responseReceiveRequestLength - 1) {
                                                stopTimer();
                                            }
                                        }

                                    } else if (responseCode.equalsIgnoreCase(ERROR_CODE)) {
                                        returnError(responseCode);
                                        errorSleep(commandType);
                                    } else if (isErrorFound017E(responseCode)) {
                                        returnError(responseCode);
                                        errorSleep(commandType);
                                    } else if (isErrorFound80FF(responseCode)) {
                                        returnError(responseCode);
                                        errorSleep(commandType);
                                    }
                                    //TODO - STOP TIMER
                                    if (j == commandSendingConfirmationLength - 1) {
                                        stopTimer();
                                    }
                                }

                            } else {
                                stopTimer();
                                break commandSendingRequestLoop;
                            }

                        } else if (responseCode.equalsIgnoreCase(ERROR_CODE)) {
                            returnError(responseCode);
                            errorSleep(commandType);
                        } else if (isErrorFound017E(responseCode)) {
                            returnError(responseCode);
                            errorSleep(commandType);
                        } else if (isErrorFound80FF(responseCode)) {
                            returnError(responseCode);
                            errorSleep(commandType);
                        }

                        //TODO - STOP TIMER
                        if (i == commandSendingRequestLength - 1) {
                            stopTimer();
                        }
                    }

                }
            }

            @Override
            public void onFinish() {
                stopTimer();
            }
        }.start();
        return isResponseReceived;
    }


    public void errorSleep(String commandType) {
        if (commandType.equals(commandFormatType.RESET)) {
            threadSleep(timeOut.TIMEOUT_10);
        } else {
            threadSleep(timeOut.TIMEOUT_1);
        }
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

        if (cmdType.equals(commandFormatType.GET_FIRMWARE)) {
            FirmwareCommand firmwareCommand = new FirmwareCommand();
            firmwareCommand.parseCommandResponse(context, response);
        } else if (cmdType.equals(commandFormatType.PROGRAM_DOWNLOAD)) {
            ProgramDownload programDownload = new ProgramDownload();
            programDownload.parseCommandResponse(context, response);
        } else if (cmdType.equals(commandFormatType.GET_UNIT_INFO)) {
            GetUnitInfo getUnitInfo = new GetUnitInfo();
            getUnitInfo.parseCommandResponse(context, response);
        } else if (cmdType.equals(commandFormatType.SET_UNIT_INFO)) {
            SetUnitInfo setUnitInfo = new SetUnitInfo();
            setUnitInfo.parseCommandResponse(context, response);
        } else if (cmdType.equals(commandFormatType.RESET)) {
            Reset reset = new Reset();
            reset.parseCommandResponse(context, response);
        } else if (cmdType.equals(commandFormatType.DRIVER_ACCESSORY)) {
            DriverAccessory driverAccessory = new DriverAccessory();
            driverAccessory.parseCommandResponse(context, response);
        } else if (cmdType.equals(commandFormatType.READ_STATUS)) {
            ReadStatus readStatus = new ReadStatus();
            readStatus.parseCommandResponse(context, response);
        } else if (cmdType.equals(commandFormatType.GET_LOGS_DATA)) {
            GetLogsData getLogsData = new GetLogsData();
            getLogsData.parseCommandResponse(context, response);
        } else if (cmdType.equals(commandFormatType.CANCEL)) {
            Cancel cancel = new Cancel();
            cancel.parseCommandResponse(context, response);
        } else if (cmdType.equals(commandFormatType.CASH_COUNT)) {
            CashCount cashCount = new CashCount();
            cashCount.parseCommandResponse(context, response);
        } else if (cmdType.equals(commandFormatType.DISPENSE)) {
            Dispense dispense = new Dispense();
            dispense.parseCommandResponse(context, response);
        } else if (cmdType.equals(commandFormatType.STORE_MONEY)) {
            StoreMoney storeMoney = new StoreMoney();
            storeMoney.parseCommandResponse(context, response);
        } else if (cmdType.equals(commandFormatType.CASH_ROLLBACK)) {
            CashRollback cashRollback = new CashRollback();
            cashRollback.parseCommandResponse(context, response);
        } else if (cmdType.equals(commandFormatType.RETRACT)) {
            Retract retract = new Retract();
            retract.parseCommandResponse(context, response);
        } else if (cmdType.equals(commandFormatType.TRANSFER)) {
            Transfer transfer = new Transfer();
            transfer.parseCommandResponse(context, response);
        } else if (cmdType.equals(commandFormatType.DRIVE_SHUTTER)) {
            DriveShutter driveShutter = new DriveShutter();
            driveShutter.parseCommandResponse(context, response);
        } else if (cmdType.equals(commandFormatType.PREPARE_TRANS)) {
            PrepareTransaction prepareTransaction = new PrepareTransaction();
            prepareTransaction.parseCommandResponse(context, response);
        } else if (cmdType.equals(commandFormatType.GET_BANK_NOTE_INFO)) {
            GetBankNoteInfo getBankNoteInfo = new GetBankNoteInfo();
            getBankNoteInfo.parseCommandResponse(context, response);
        } else if (cmdType.equals(commandFormatType.GET_CASSETTE_INFO)) {
            GetCassetteInfo getCassetteInfo = new GetCassetteInfo();
            getCassetteInfo.parseCommandResponse(context, response);
        } else if (cmdType.equals(commandFormatType.SET_DENOMINATION_CODE)) {
            SetDenominationCode setDenominationCode = new SetDenominationCode();
            setDenominationCode.parseCommandResponse(context, response);
        } else if (cmdType.equals(commandFormatType.USER_MEMORY_WRITE)) {
            UserMemoryWrite userMemoryWrite = new UserMemoryWrite();
            userMemoryWrite.parseCommandResponse(context, response);
        } else if (cmdType.equals(commandFormatType.USER_MEMORY_READ)) {
            UserMemoryRead userMemoryRead = new UserMemoryRead();
            userMemoryRead.parseCommandResponse(context, response);
        } else if (cmdType.equals(commandFormatType.REBOOT)) {
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

        if (cmdType.equals(commandFormatType.GET_FIRMWARE)) {
            //TODO - need to check how we can use different function for different command
//            returnValue = InterruptStatusChecker.getStatusOfByteEight(context);

        } else if (cmdType.equals(commandFormatType.PROGRAM_DOWNLOAD)) {

        } else if (cmdType.equals(commandFormatType.GET_UNIT_INFO)) {

        } else if (cmdType.equals(commandFormatType.SET_UNIT_INFO)) {

        } else if (cmdType.equals(commandFormatType.RESET)) {

        } else if (cmdType.equals(commandFormatType.DRIVER_ACCESSORY)) {

        } else if (cmdType.equals(commandFormatType.READ_STATUS)) {

        } else if (cmdType.equals(commandFormatType.GET_LOGS_DATA)) {

        } else if (cmdType.equals(commandFormatType.CANCEL)) {

        } else if (cmdType.equals(commandFormatType.CASH_COUNT)) {

        } else if (cmdType.equals(commandFormatType.DISPENSE)) {

        } else if (cmdType.equals(commandFormatType.STORE_MONEY)) {

        } else if (cmdType.equals(commandFormatType.CASH_ROLLBACK)) {

        } else if (cmdType.equals(commandFormatType.RETRACT)) {

        } else if (cmdType.equals(commandFormatType.TRANSFER)) {

        } else if (cmdType.equals(commandFormatType.DRIVE_SHUTTER)) {

        } else if (cmdType.equals(commandFormatType.PREPARE_TRANS)) {

        } else if (cmdType.equals(commandFormatType.GET_BANK_NOTE_INFO)) {

        } else if (cmdType.equals(commandFormatType.GET_CASSETTE_INFO)) {

        } else if (cmdType.equals(commandFormatType.SET_DENOMINATION_CODE)) {

        } else if (cmdType.equals(commandFormatType.USER_MEMORY_WRITE)) {

        } else if (cmdType.equals(commandFormatType.USER_MEMORY_READ)) {

        } else if (cmdType.equals(commandFormatType.REBOOT)) {

        }
//        else if (cmdType.equals(commandType.TEST_COMMAND)) {
//
//        }

        AppLogs.generate(returnValue);
        return returnValue;
    }

    public int getTimeOut(String cmdType) {
        int returnValue = 0;

        if (cmdType.equals(commandFormatType.GET_FIRMWARE)) {
            returnValue = timeOut.TIMEOUT_20;
        } else if (cmdType.equals(commandFormatType.PROGRAM_DOWNLOAD)) {
            returnValue = timeOut.TIMEOUT_210;
        } else if (cmdType.equals(commandFormatType.GET_UNIT_INFO)) {
            returnValue = timeOut.TIMEOUT_20;
        } else if (cmdType.equals(commandFormatType.SET_UNIT_INFO)) {
            returnValue = timeOut.TIMEOUT_20;
        } else if (cmdType.equals(commandFormatType.RESET)) {
            returnValue = timeOut.TIMEOUT_180;
        } else if (cmdType.equals(commandFormatType.DRIVER_ACCESSORY)) {
            returnValue = timeOut.TIMEOUT_20;
        } else if (cmdType.equals(commandFormatType.READ_STATUS)) {
            returnValue = timeOut.TIMEOUT_20;
        } else if (cmdType.equals(commandFormatType.GET_LOGS_DATA)) {
            returnValue = timeOut.TIMEOUT_120;
        } else if (cmdType.equals(commandFormatType.CANCEL)) {
            returnValue = timeOut.TIMEOUT_180;
        } else if (cmdType.equals(commandFormatType.CASH_COUNT)) {
            returnValue = timeOut.TIMEOUT_180;
        } else if (cmdType.equals(commandFormatType.DISPENSE)) {
            returnValue = timeOut.TIMEOUT_300;
        } else if (cmdType.equals(commandFormatType.STORE_MONEY)) {
            returnValue = timeOut.TIMEOUT_180;
        } else if (cmdType.equals(commandFormatType.CASH_ROLLBACK)) {
            returnValue = timeOut.TIMEOUT_240;
        } else if (cmdType.equals(commandFormatType.RETRACT)) {
            returnValue = timeOut.TIMEOUT_180;
        } else if (cmdType.equals(commandFormatType.TRANSFER)) {
            returnValue = timeOut.TIMEOUT_180;
        } else if (cmdType.equals(commandFormatType.DRIVE_SHUTTER)) {
            returnValue = timeOut.TIMEOUT_120;
        } else if (cmdType.equals(commandFormatType.PREPARE_TRANS)) {
            returnValue = timeOut.TIMEOUT_180;
        } else if (cmdType.equals(commandFormatType.GET_BANK_NOTE_INFO)) {
            returnValue = timeOut.TIMEOUT_120;
        } else if (cmdType.equals(commandFormatType.GET_CASSETTE_INFO)) {
            returnValue = timeOut.TIMEOUT_120;
        } else if (cmdType.equals(commandFormatType.SET_DENOMINATION_CODE)) {
            returnValue = timeOut.TIMEOUT_20;
        } else if (cmdType.equals(commandFormatType.USER_MEMORY_WRITE)) {
            returnValue = timeOut.TIMEOUT_120;
        } else if (cmdType.equals(commandFormatType.USER_MEMORY_READ)) {
            returnValue = timeOut.TIMEOUT_120;
        } else if (cmdType.equals(commandFormatType.REBOOT)) {
            returnValue = timeOut.TIMEOUT_210;
        }
        return returnValue;
    }


    public void threadSleep(int timing) {
        try {
            Thread.sleep(timing);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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

    public void clearAllSession(Context context) {
        sessionData.clearAllSession(context);
        sessionModel.clearAllSession(context);
    }

    public String returnError(String responseCode) {
        String returnValue = "Error Reason: " + cmdSupportClass.getErrorReason(responseCode);
        AppLogs.generate(returnValue);
        getError();
        AppLogs.generate(returnValue);
        return returnValue;
    }
}
