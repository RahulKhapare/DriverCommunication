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

    public String getFirmwareVersion(Context context, UsbDeviceConnection usbConnection, UsbEndpoint endpointOne, UsbEndpoint endpointTwo, UsbEndpoint endpointThree, TextView txtCommunicationProcess) {
        //TODO - check for entire process
        String returnValue = "";
//        if (isCheckingInterrupt(context, usbConnection, endpointThree, txtCommunicationProcess)) {
//        }
        returnValue = commandGenerator.generate(context, usbConnection, endpointOne, endpointTwo, commandType.GET_FIRMWARE, txtCommunicationProcess);
        return returnValue;
    }

    public String programDownload(Context context, UsbDeviceConnection usbConnection, UsbEndpoint endpointOne, UsbEndpoint endpointTwo, UsbEndpoint endpointThree, TextView txtCommunicationProcess) {
        //TODO - check for entire process
        String returnValue = "";
//        if (isCheckingInterrupt(context, usbConnection, endpointThree, txtCommunicationProcess)) {
//        }
        returnValue = commandGenerator.generate(context, usbConnection, endpointOne, endpointTwo, commandType.PROGRAM_DOWNLOAD, txtCommunicationProcess);

        return returnValue;
    }

    public String getUnitInfo(Context context, UsbDeviceConnection usbConnection, UsbEndpoint endpointOne, UsbEndpoint endpointTwo, UsbEndpoint endpointThree, TextView txtCommunicationProcess) {
        //TODO - check for entire process
        String returnValue = "";
//        if (isCheckingInterrupt(context, usbConnection, endpointThree, txtCommunicationProcess)) {
//        }
        returnValue = commandGenerator.generate(context, usbConnection, endpointOne, endpointTwo, commandType.GET_UNIT_INFO, txtCommunicationProcess);
        return returnValue;
    }

    public String setUnitInfo(Context context, UsbDeviceConnection usbConnection, UsbEndpoint endpointOne, UsbEndpoint endpointTwo, UsbEndpoint endpointThree, TextView txtCommunicationProcess) {
        //TODO - check for entire process
        String returnValue = "";
//        if (isCheckingInterrupt(context, usbConnection, endpointThree, txtCommunicationProcess)) {
//        }
        returnValue = commandGenerator.generate(context, usbConnection, endpointOne, endpointTwo, commandType.SET_UNIT_INFO, txtCommunicationProcess);

        return returnValue;
    }

    public String reset(Context context, UsbDeviceConnection usbConnection, UsbEndpoint endpointOne, UsbEndpoint endpointTwo, UsbEndpoint endpointThree, TextView txtCommunicationProcess) {
        //TODO - check for entire process
        String returnValue = "";
//        if (isCheckingInterrupt(context, usbConnection, endpointThree, txtCommunicationProcess)) {
//        }
        returnValue = commandGenerator.generate(context, usbConnection, endpointOne, endpointTwo, commandType.RESET, txtCommunicationProcess);

        return returnValue;
    }

    public String driveAccessory(Context context, UsbDeviceConnection usbConnection, UsbEndpoint endpointOne, UsbEndpoint endpointTwo, UsbEndpoint endpointThree, TextView txtCommunicationProcess) {
        //TODO - check for entire process
        String returnValue = "";
//        if (isCheckingInterrupt(context, usbConnection, endpointThree, txtCommunicationProcess)) {
//        }
        returnValue = commandGenerator.generate(context, usbConnection, endpointOne, endpointTwo, commandType.DRIVER_ACCESSORY, txtCommunicationProcess);

        return returnValue;
    }

    public String readStatus(Context context, UsbDeviceConnection usbConnection, UsbEndpoint endpointOne, UsbEndpoint endpointTwo, UsbEndpoint endpointThree, TextView txtCommunicationProcess) {
        //TODO - check for entire process
        String returnValue = "";
//        if (isCheckingInterrupt(context, usbConnection, endpointThree, txtCommunicationProcess)) {
//        }
        returnValue = commandGenerator.generate(context, usbConnection, endpointOne, endpointTwo, commandType.READ_STATUS, txtCommunicationProcess);

        return returnValue;
    }

    public String getLogData(Context context, UsbDeviceConnection usbConnection, UsbEndpoint endpointOne, UsbEndpoint endpointTwo, UsbEndpoint endpointThree, TextView txtCommunicationProcess) {
        //TODO - check for entire process
        String returnValue = "";
//        if (isCheckingInterrupt(context, usbConnection, endpointThree, txtCommunicationProcess)) {
//        }
        returnValue = commandGenerator.generate(context, usbConnection, endpointOne, endpointTwo, commandType.GET_LOGS_DATA, txtCommunicationProcess);

        return returnValue;
    }

    public String cancel(Context context, UsbDeviceConnection usbConnection, UsbEndpoint endpointOne, UsbEndpoint endpointTwo, UsbEndpoint endpointThree, TextView txtCommunicationProcess) {
        //TODO - check for entire process
        String returnValue = "";
//        if (isCheckingInterrupt(context, usbConnection, endpointThree, txtCommunicationProcess)) {
//        }
        returnValue = commandGenerator.generate(context, usbConnection, endpointOne, endpointTwo, commandType.CANCEL, txtCommunicationProcess);

        return returnValue;
    }


    public String cashCount(Context context, UsbDeviceConnection usbConnection, UsbEndpoint endpointOne, UsbEndpoint endpointTwo, UsbEndpoint endpointThree, TextView txtCommunicationProcess) {
        //TODO - check for entire process
        String returnValue = "";
//        if (isCheckingInterrupt(context, usbConnection, endpointThree, txtCommunicationProcess)) {
//        }
        returnValue = commandGenerator.generate(context, usbConnection, endpointOne, endpointTwo, commandType.CASH_COUNT, txtCommunicationProcess);

        return returnValue;
    }

    public String dispense(Context context, UsbDeviceConnection usbConnection, UsbEndpoint endpointOne, UsbEndpoint endpointTwo, UsbEndpoint endpointThree, TextView txtCommunicationProcess) {
        //TODO - check for entire process
        String returnValue = "";
//        if (isCheckingInterrupt(context, usbConnection, endpointThree, txtCommunicationProcess)) {
//        }
        returnValue = commandGenerator.generate(context, usbConnection, endpointOne, endpointTwo, commandType.DISPENSE, txtCommunicationProcess);

        return returnValue;
    }

    public String storeMoney(Context context, UsbDeviceConnection usbConnection, UsbEndpoint endpointOne, UsbEndpoint endpointTwo, UsbEndpoint endpointThree, TextView txtCommunicationProcess) {
        //TODO - check for entire process
        String returnValue = "";
//        if (isCheckingInterrupt(context, usbConnection, endpointThree, txtCommunicationProcess)) {
//        }
        returnValue = commandGenerator.generate(context, usbConnection, endpointOne, endpointTwo, commandType.STORE_MONEY, txtCommunicationProcess);

        return returnValue;
    }

    public String cashRollback(Context context, UsbDeviceConnection usbConnection, UsbEndpoint endpointOne, UsbEndpoint endpointTwo, UsbEndpoint endpointThree, TextView txtCommunicationProcess) {
        //TODO - check for entire process
        String returnValue = "";
//        if (isCheckingInterrupt(context, usbConnection, endpointThree, txtCommunicationProcess)) {
//        }
        returnValue = commandGenerator.generate(context, usbConnection, endpointOne, endpointTwo, commandType.CASH_ROLLBACK, txtCommunicationProcess);

        return returnValue;
    }

    public String retract(Context context, UsbDeviceConnection usbConnection, UsbEndpoint endpointOne, UsbEndpoint endpointTwo, UsbEndpoint endpointThree, TextView txtCommunicationProcess) {
        //TODO - check for entire process
        String returnValue = "";
//        if (isCheckingInterrupt(context, usbConnection, endpointThree, txtCommunicationProcess)) {
//        }
        returnValue = commandGenerator.generate(context, usbConnection, endpointOne, endpointTwo, commandType.RETRACT, txtCommunicationProcess);

        return returnValue;
    }

    public String transfer(Context context, UsbDeviceConnection usbConnection, UsbEndpoint endpointOne, UsbEndpoint endpointTwo, UsbEndpoint endpointThree, TextView txtCommunicationProcess) {
        //TODO - check for entire process
        String returnValue = "";
//        if (isCheckingInterrupt(context, usbConnection, endpointThree, txtCommunicationProcess)) {
//        }
        returnValue = commandGenerator.generate(context, usbConnection, endpointOne, endpointTwo, commandType.TRANSFER, txtCommunicationProcess);

        return returnValue;
    }

    public String driveShutter(Context context, UsbDeviceConnection usbConnection, UsbEndpoint endpointOne, UsbEndpoint endpointTwo, UsbEndpoint endpointThree, TextView txtCommunicationProcess) {
        //TODO - check for entire process
        String returnValue = "";
//        if (isCheckingInterrupt(context, usbConnection, endpointThree, txtCommunicationProcess)) {
//        }
        returnValue = commandGenerator.generate(context, usbConnection, endpointOne, endpointTwo, commandType.DRIVE_SHUTTER, txtCommunicationProcess);

        return returnValue;
    }

    public String prepareTransaction(Context context, UsbDeviceConnection usbConnection, UsbEndpoint endpointOne, UsbEndpoint endpointTwo, UsbEndpoint endpointThree, TextView txtCommunicationProcess) {
        //TODO - check for entire process
        String returnValue = "";
//        if (isCheckingInterrupt(context, usbConnection, endpointThree, txtCommunicationProcess)) {
//        }
        returnValue = commandGenerator.generate(context, usbConnection, endpointOne, endpointTwo, commandType.PREPARE_TRANS, txtCommunicationProcess);

        return returnValue;
    }

    public String getBanknoteInfo(Context context, UsbDeviceConnection usbConnection, UsbEndpoint endpointOne, UsbEndpoint endpointTwo, UsbEndpoint endpointThree, TextView txtCommunicationProcess) {
        //TODO - check for entire process
        String returnValue = "";
//        if (isCheckingInterrupt(context, usbConnection, endpointThree, txtCommunicationProcess)) {
//        }
        returnValue = commandGenerator.generate(context, usbConnection, endpointOne, endpointTwo, commandType.GET_BANK_NOTE_INFO, txtCommunicationProcess);

        return returnValue;
    }

    public String getCassetteInfo(Context context, UsbDeviceConnection usbConnection, UsbEndpoint endpointOne, UsbEndpoint endpointTwo, UsbEndpoint endpointThree, TextView txtCommunicationProcess) {
        //TODO - check for entire process
        String returnValue = "";
//        if (isCheckingInterrupt(context, usbConnection, endpointThree, txtCommunicationProcess)) {
//        }
        returnValue = commandGenerator.generate(context, usbConnection, endpointOne, endpointTwo, commandType.GET_CASSETTE_INFO, txtCommunicationProcess);

        return returnValue;
    }

    public String setDenominationCode(Context context, UsbDeviceConnection usbConnection, UsbEndpoint endpointOne, UsbEndpoint endpointTwo, UsbEndpoint endpointThree, TextView txtCommunicationProcess) {
        //TODO - check for entire process
        String returnValue = "";
//        if (isCheckingInterrupt(context, usbConnection, endpointThree, txtCommunicationProcess)) {
//        }
        returnValue = commandGenerator.generate(context, usbConnection, endpointOne, endpointTwo, commandType.SET_DENOMINATION_CODE, txtCommunicationProcess);

        return returnValue;
    }

    public String userMemoryWrite(Context context, UsbDeviceConnection usbConnection, UsbEndpoint endpointOne, UsbEndpoint endpointTwo, UsbEndpoint endpointThree, TextView txtCommunicationProcess) {
        //TODO - check for entire process
        String returnValue = "";
//        if (isCheckingInterrupt(context, usbConnection, endpointThree, txtCommunicationProcess)) {
//        }
        returnValue = commandGenerator.generate(context, usbConnection, endpointOne, endpointTwo, commandType.USER_MEMORY_WRITE, txtCommunicationProcess);

        return returnValue;
    }

    public String userMemoryRead(Context context, UsbDeviceConnection usbConnection, UsbEndpoint endpointOne, UsbEndpoint endpointTwo, UsbEndpoint endpointThree, TextView txtCommunicationProcess) {
        //TODO - check for entire process
        String returnValue = "";
//        if (isCheckingInterrupt(context, usbConnection, endpointThree, txtCommunicationProcess)) {
//        }
        returnValue = commandGenerator.generate(context, usbConnection, endpointOne, endpointTwo, commandType.USER_MEMORY_READ, txtCommunicationProcess);

        return returnValue;
    }

    public String reboot(Context context, UsbDeviceConnection usbConnection, UsbEndpoint endpointOne, UsbEndpoint endpointTwo, UsbEndpoint endpointThree, TextView txtCommunicationProcess) {
        //TODO - check for entire process
        String returnValue = "";
//        if (isCheckingInterrupt(context, usbConnection, endpointThree, txtCommunicationProcess)) {
//        }
        returnValue = commandGenerator.generate(context, usbConnection, endpointOne, endpointTwo, commandType.REBOOT, txtCommunicationProcess);

        return returnValue;
    }

//    public void testCommand(Context context, UsbDeviceConnection usbConnection, UsbEndpoint endpointOne, UsbEndpoint endpointTwo, UsbEndpoint endpointThree, TextView txtCommunicationProcess) {
//        //TODO - check for entire process
//        if (isCheckingInterrupt(context, usbConnection, endpointThree, txtCommunicationProcess)) {
//            commandGenerator.generate(context, usbConnection, endpointOne, endpointTwo, commandType.TEST_COMMAND, txtCommunicationProcess);
//        }
//    }

}

