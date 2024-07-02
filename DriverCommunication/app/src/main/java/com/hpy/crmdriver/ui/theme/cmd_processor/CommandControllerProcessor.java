package com.hpy.crmdriver.ui.theme.cmd_processor;

import android.content.Context;
import android.hardware.usb.UsbDeviceConnection;
import android.hardware.usb.UsbEndpoint;
import android.text.TextUtils;
import android.util.Log;
import android.widget.TextView;

import com.hpy.crmdriver.ui.theme.cmd_generator.CommandGenerator;
import com.hpy.crmdriver.ui.theme.cmd_generator.CommandType;
import com.hpy.crmdriver.ui.theme.cmd_generator.ControlBulkCmdGenerator;
import com.hpy.crmdriver.ui.theme.interrupt_formatter.InterruptFormatter;
import com.hpy.crmdriver.ui.theme.util.AppConfig;
import com.hpy.crmdriver.ui.theme.util.SessionData;

public class CommandControllerProcessor {

    public CommandGenerator commandGenerator = new CommandGenerator();
    public CommandType commandType = new CommandType();
    public AppConfig appConfig = new AppConfig();
    public InterruptFormatter interruptFormatter = new InterruptFormatter();
    public ControlBulkCmdGenerator controlBulkCmdGenerator = new ControlBulkCmdGenerator();

    public boolean isCheckingInterrupt(Context context, UsbDeviceConnection usbConnection, UsbEndpoint endpointThree, TextView txtCommunicationProcess) {
        boolean returnValue;
        if (!appConfig.IS_CHECKING_INTERRUPT) {
            String deviceDescriptorDetails = controlBulkCmdGenerator.deviceDescriptorData(usbConnection, txtCommunicationProcess);
            if (!TextUtils.isEmpty(deviceDescriptorDetails)) {
                controlBulkCmdGenerator.readInterruptData(context, usbConnection, endpointThree, appConfig.MILI_100);
                returnValue = true;
                appConfig.IS_CHECKING_INTERRUPT = returnValue;
            } else {
                returnValue = false;
                appConfig.IS_CHECKING_INTERRUPT = returnValue;
            }
        } else {
            returnValue = appConfig.IS_CHECKING_INTERRUPT;
        }

        //TODO - check byte block in binary
        checkBlockInBinary(context);

        try {
            Thread.sleep(appConfig.MILI_100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return returnValue;
    }

    public void checkBlockInBinary(Context context) {
        for (int i = 0; i < 24; i++) {
            String byteData = interruptFormatter.getByteToBinaryBlockData(context, i);
            Log.e("TAG", "InterruptCheckerBock_Byte_" + getPosition(i) + " : " + byteData);
        }
    }

    public int getPosition(int value) {
        return value + 1;
    }

    public boolean getFirmwareVersion(Context context, UsbDeviceConnection usbConnection, UsbEndpoint endpointOne, UsbEndpoint endpointTwo, UsbEndpoint endpointThree, TextView txtCommunicationProcess) {
        //TODO - check for entire process
        boolean isSuccess = false;
//        if (isCheckingInterrupt(context, usbConnection, endpointThree, txtCommunicationProcess)) {
//        }
        isSuccess = commandGenerator.generate(context, usbConnection, endpointOne, endpointTwo, commandType.GET_FIRMWARE, txtCommunicationProcess);
        return isSuccess;
    }

    public boolean programDownload(Context context, UsbDeviceConnection usbConnection, UsbEndpoint endpointOne, UsbEndpoint endpointTwo, UsbEndpoint endpointThree, TextView txtCommunicationProcess) {
        //TODO - check for entire process
        boolean isSuccess = false;
//        if (isCheckingInterrupt(context, usbConnection, endpointThree, txtCommunicationProcess)) {
//        }
        isSuccess = commandGenerator.generate(context, usbConnection, endpointOne, endpointTwo, commandType.PROGRAM_DOWNLOAD, txtCommunicationProcess);
        return isSuccess;
    }

    public boolean getUnitInfo(Context context, UsbDeviceConnection usbConnection, UsbEndpoint endpointOne, UsbEndpoint endpointTwo, UsbEndpoint endpointThree, String cmdType, TextView txtCommunicationProcess) {
        //TODO - check for entire process
        boolean isSuccess;
//        if (isCheckingInterrupt(context, usbConnection, endpointThree, txtCommunicationProcess)) {
//        }
        SessionData.addValue(context, appConfig.GET_UNIT_INFO, cmdType);
        isSuccess = commandGenerator.generate(context, usbConnection, endpointOne, endpointTwo, commandType.GET_UNIT_INFO, txtCommunicationProcess);
        return isSuccess;
    }

    public boolean setUnitInfo(Context context, UsbDeviceConnection usbConnection, UsbEndpoint endpointOne, UsbEndpoint endpointTwo, UsbEndpoint endpointThree, TextView txtCommunicationProcess) {
        //TODO - check for entire process
        boolean isSuccess = false;
//        if (isCheckingInterrupt(context, usbConnection, endpointThree, txtCommunicationProcess)) {
//        }
        isSuccess = commandGenerator.generate(context, usbConnection, endpointOne, endpointTwo, commandType.SET_UNIT_INFO, txtCommunicationProcess);
        return isSuccess;
    }

    public boolean reset(Context context, UsbDeviceConnection usbConnection, UsbEndpoint endpointOne, UsbEndpoint endpointTwo, UsbEndpoint endpointThree, String cmdType, TextView txtCommunicationProcess) {
        //TODO - check for entire process
        boolean isSuccess;
//        if (isCheckingInterrupt(context, usbConnection, endpointThree, txtCommunicationProcess)) {
//        }
        SessionData.addValue(context, appConfig.RESET_TYPE, cmdType);
        isSuccess = commandGenerator.generate(context, usbConnection, endpointOne, endpointTwo, commandType.RESET, txtCommunicationProcess);
        return isSuccess;
    }

    public boolean driveAccessory(Context context, UsbDeviceConnection usbConnection, UsbEndpoint endpointOne, UsbEndpoint endpointTwo, UsbEndpoint endpointThree, TextView txtCommunicationProcess) {
        //TODO - check for entire process
        boolean isSuccess;
//        if (isCheckingInterrupt(context, usbConnection, endpointThree, txtCommunicationProcess)) {
//        }
        isSuccess = commandGenerator.generate(context, usbConnection, endpointOne, endpointTwo, commandType.DRIVER_ACCESSORY, txtCommunicationProcess);
        return isSuccess;
    }

    public boolean readStatus(Context context, UsbDeviceConnection usbConnection, UsbEndpoint endpointOne, UsbEndpoint endpointTwo, UsbEndpoint endpointThree, TextView txtCommunicationProcess) {
        //TODO - check for entire process
        boolean isSuccess;
//        if (isCheckingInterrupt(context, usbConnection, endpointThree, txtCommunicationProcess)) {
//        }
        isSuccess = commandGenerator.generate(context, usbConnection, endpointOne, endpointTwo, commandType.READ_STATUS, txtCommunicationProcess);
        return isSuccess;
    }

    public boolean getLogData(Context context, UsbDeviceConnection usbConnection, UsbEndpoint endpointOne, UsbEndpoint endpointTwo, UsbEndpoint endpointThree, String cmdType, TextView txtCommunicationProcess) {
        //TODO - check for entire process
        boolean isSuccess;
//        if (isCheckingInterrupt(context, usbConnection, endpointThree, txtCommunicationProcess)) {
//        }
        SessionData.addValue(context, appConfig.GET_LOGS_DATA_VALUE, cmdType);
        isSuccess = commandGenerator.generate(context, usbConnection, endpointOne, endpointTwo, commandType.GET_LOGS_DATA, txtCommunicationProcess);
        return isSuccess;
    }

    public boolean cancel(Context context, UsbDeviceConnection usbConnection, UsbEndpoint endpointOne, UsbEndpoint endpointTwo, UsbEndpoint endpointThree, TextView txtCommunicationProcess) {
        //TODO - check for entire process
        boolean isSuccess;
//        if (isCheckingInterrupt(context, usbConnection, endpointThree, txtCommunicationProcess)) {
//        }
        isSuccess = commandGenerator.generate(context, usbConnection, endpointOne, endpointTwo, commandType.CANCEL, txtCommunicationProcess);
        return isSuccess;
    }


    public boolean cashCount(Context context, UsbDeviceConnection usbConnection, UsbEndpoint endpointOne, UsbEndpoint endpointTwo, UsbEndpoint endpointThree, TextView txtCommunicationProcess) {
        //TODO - check for entire process
        boolean returnValue;
//        if (isCheckingInterrupt(context, usbConnection, endpointThree, txtCommunicationProcess)) {
//        }
        returnValue = commandGenerator.generate(context, usbConnection, endpointOne, endpointTwo, commandType.CASH_COUNT, txtCommunicationProcess);
        return returnValue;
    }

    public boolean dispense(Context context, UsbDeviceConnection usbConnection, UsbEndpoint endpointOne, UsbEndpoint endpointTwo, UsbEndpoint endpointThree, String cmdType, TextView txtCommunicationProcess) {
        //TODO - check for entire process
        boolean isSuccess;
//        if (isCheckingInterrupt(context, usbConnection, endpointThree, txtCommunicationProcess)) {
//        }
        SessionData.addValue(context, appConfig.DISPENSE_VALUE, cmdType);
        isSuccess = commandGenerator.generate(context, usbConnection, endpointOne, endpointTwo, commandType.DISPENSE, txtCommunicationProcess);
        return isSuccess;
    }

    public boolean storeMoney(Context context, UsbDeviceConnection usbConnection, UsbEndpoint endpointOne, UsbEndpoint endpointTwo, UsbEndpoint endpointThree, String cmdType, TextView txtCommunicationProcess) {
        //TODO - check for entire process
        boolean isSuccess;
//        if (isCheckingInterrupt(context, usbConnection, endpointThree, txtCommunicationProcess)) {
//        }
        SessionData.addValue(context, appConfig.STORE_MONEY_VALUE, cmdType);
        isSuccess = commandGenerator.generate(context, usbConnection, endpointOne, endpointTwo, commandType.STORE_MONEY, txtCommunicationProcess);
        return isSuccess;
    }

    public boolean cashRollback(Context context, UsbDeviceConnection usbConnection, UsbEndpoint endpointOne, UsbEndpoint endpointTwo, UsbEndpoint endpointThree, TextView txtCommunicationProcess) {
        //TODO - check for entire process
        boolean isSuccess;
//        if (isCheckingInterrupt(context, usbConnection, endpointThree, txtCommunicationProcess)) {
//        }
        isSuccess = commandGenerator.generate(context, usbConnection, endpointOne, endpointTwo, commandType.CASH_ROLLBACK, txtCommunicationProcess);
        return isSuccess;
    }

    public boolean retract(Context context, UsbDeviceConnection usbConnection, UsbEndpoint endpointOne, UsbEndpoint endpointTwo, UsbEndpoint endpointThree, TextView txtCommunicationProcess) {
        //TODO - check for entire process
        boolean returnValue;
//        if (isCheckingInterrupt(context, usbConnection, endpointThree, txtCommunicationProcess)) {
//        }
        returnValue = commandGenerator.generate(context, usbConnection, endpointOne, endpointTwo, commandType.RETRACT, txtCommunicationProcess);
        return returnValue;
    }

    public boolean transfer(Context context, UsbDeviceConnection usbConnection, UsbEndpoint endpointOne, UsbEndpoint endpointTwo, UsbEndpoint endpointThree, TextView txtCommunicationProcess) {
        //TODO - check for entire process
        boolean returnValue;
//        if (isCheckingInterrupt(context, usbConnection, endpointThree, txtCommunicationProcess)) {
//        }
        returnValue = commandGenerator.generate(context, usbConnection, endpointOne, endpointTwo, commandType.TRANSFER, txtCommunicationProcess);
        return returnValue;
    }

    public boolean driveShutter(Context context, UsbDeviceConnection usbConnection, UsbEndpoint endpointOne, UsbEndpoint endpointTwo, UsbEndpoint endpointThree, String cmdType, TextView txtCommunicationProcess) {
        //TODO - check for entire process
        boolean isSuccess = false;
//        if (isCheckingInterrupt(context, usbConnection, endpointThree, txtCommunicationProcess)) {
//        }
        SessionData.addValue(context, appConfig.DRIVE_SHUTTER_VALUE, cmdType);
        isSuccess = commandGenerator.generate(context, usbConnection, endpointOne, endpointTwo, commandType.DRIVE_SHUTTER, txtCommunicationProcess);
        return isSuccess;
    }

    public boolean prepareTransaction(Context context, UsbDeviceConnection usbConnection, UsbEndpoint endpointOne, UsbEndpoint endpointTwo, UsbEndpoint endpointThree, String cmdType, TextView txtCommunicationProcess) {
        //TODO - check for entire process
        boolean isSuccess;
//        if (isCheckingInterrupt(context, usbConnection, endpointThree, txtCommunicationProcess)) {
//        }
        SessionData.addValue(context, appConfig.PREPARE_TRANSACTION_VALUE, cmdType);
        isSuccess = commandGenerator.generate(context, usbConnection, endpointOne, endpointTwo, commandType.PREPARE_TRANS, txtCommunicationProcess);
        return isSuccess;
    }

    public boolean getBanknoteInfo(Context context, UsbDeviceConnection usbConnection, UsbEndpoint endpointOne, UsbEndpoint endpointTwo, UsbEndpoint endpointThree, TextView txtCommunicationProcess) {
        //TODO - check for entire process
        boolean isSuccess;
//        if (isCheckingInterrupt(context, usbConnection, endpointThree, txtCommunicationProcess)) {
//        }
        isSuccess = commandGenerator.generate(context, usbConnection, endpointOne, endpointTwo, commandType.GET_BANK_NOTE_INFO, txtCommunicationProcess);
        return isSuccess;
    }

    public boolean getCassetteInfo(Context context, UsbDeviceConnection usbConnection, UsbEndpoint endpointOne, UsbEndpoint endpointTwo, UsbEndpoint endpointThree, TextView txtCommunicationProcess) {
        //TODO - check for entire process
        boolean isSuccess;
//        if (isCheckingInterrupt(context, usbConnection, endpointThree, txtCommunicationProcess)) {
//        }
        isSuccess = commandGenerator.generate(context, usbConnection, endpointOne, endpointTwo, commandType.GET_CASSETTE_INFO, txtCommunicationProcess);
        return isSuccess;
    }

    public boolean setDenominationCode(Context context, UsbDeviceConnection usbConnection, UsbEndpoint endpointOne, UsbEndpoint endpointTwo, UsbEndpoint endpointThree, TextView txtCommunicationProcess) {
        //TODO - check for entire process
        boolean isSuccess;
//        if (isCheckingInterrupt(context, usbConnection, endpointThree, txtCommunicationProcess)) {
//        }
        isSuccess = commandGenerator.generate(context, usbConnection, endpointOne, endpointTwo, commandType.SET_DENOMINATION_CODE, txtCommunicationProcess);
        return isSuccess;
    }

    public boolean userMemoryWrite(Context context, UsbDeviceConnection usbConnection, UsbEndpoint endpointOne, UsbEndpoint endpointTwo, UsbEndpoint endpointThree, TextView txtCommunicationProcess) {
        //TODO - check for entire process
        boolean isSuccess;
//        if (isCheckingInterrupt(context, usbConnection, endpointThree, txtCommunicationProcess)) {
//        }
        isSuccess = commandGenerator.generate(context, usbConnection, endpointOne, endpointTwo, commandType.USER_MEMORY_WRITE, txtCommunicationProcess);
        return isSuccess;
    }

    public boolean userMemoryRead(Context context, UsbDeviceConnection usbConnection, UsbEndpoint endpointOne, UsbEndpoint endpointTwo, UsbEndpoint endpointThree, TextView txtCommunicationProcess) {
        //TODO - check for entire process
        boolean isSuccess;
//        if (isCheckingInterrupt(context, usbConnection, endpointThree, txtCommunicationProcess)) {
//        }
        isSuccess = commandGenerator.generate(context, usbConnection, endpointOne, endpointTwo, commandType.USER_MEMORY_READ, txtCommunicationProcess);
        return isSuccess;
    }

    public boolean reboot(Context context, UsbDeviceConnection usbConnection, UsbEndpoint endpointOne, UsbEndpoint endpointTwo, UsbEndpoint endpointThree, TextView txtCommunicationProcess) {
        //TODO - check for entire process
        boolean isSuccess;
//        if (isCheckingInterrupt(context, usbConnection, endpointThree, txtCommunicationProcess)) {
//        }
        isSuccess = commandGenerator.generate(context, usbConnection, endpointOne, endpointTwo, commandType.REBOOT, txtCommunicationProcess);
        return isSuccess;
    }

//    public void testCommand(Context context, UsbDeviceConnection usbConnection, UsbEndpoint endpointOne, UsbEndpoint endpointTwo, UsbEndpoint endpointThree, TextView txtCommunicationProcess) {
//        //TODO - check for entire process
//        if (isCheckingInterrupt(context, usbConnection, endpointThree, txtCommunicationProcess)) {
//            commandGenerator.generate(context, usbConnection, endpointOne, endpointTwo, commandType.TEST_COMMAND, txtCommunicationProcess);
//        }
//    }

}

