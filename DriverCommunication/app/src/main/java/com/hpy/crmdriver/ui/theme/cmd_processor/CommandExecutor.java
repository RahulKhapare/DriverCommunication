package com.hpy.crmdriver.ui.theme.cmd_processor;

import android.content.Context;
import android.hardware.usb.UsbDeviceConnection;
import android.hardware.usb.UsbEndpoint;
import android.util.Log;
import android.widget.TextView;

public class CommandExecutor {

    public CommandControllerProcessor commandControllerProcessor = new CommandControllerProcessor();

    public String init(Context context, UsbDeviceConnection usbConnection, UsbEndpoint endpointOne, UsbEndpoint endpointTwo, UsbEndpoint endpointThree, TextView txtCommunicationProcess) {
        String returnValue = "";

        //Get Firmware
        returnValue = commandControllerProcessor.getFirmwareVersion(context, usbConnection, endpointOne, endpointTwo, endpointThree, txtCommunicationProcess);
//        //Get Unit Info
//        commandControllerProcessor.getUnitInfo(context, usbConnection, endpointOne, endpointTwo, endpointThree, txtCommunicationProcess);
//        //Set Unit Info
        commandControllerProcessor.setUnitInfo(context, usbConnection, endpointOne, endpointTwo, endpointThree, txtCommunicationProcess);
//        //Get Bank Note Info
//        commandControllerProcessor.getBanknoteInfo(context, usbConnection, endpointOne, endpointTwo, endpointThree, txtCommunicationProcess);
//        //Get Cassette Info
//        commandControllerProcessor.getCassetteInfo(context, usbConnection, endpointOne, endpointTwo, endpointThree, txtCommunicationProcess);
//        //Set Denomination Code
//        commandControllerProcessor.setDenominationCode(context, usbConnection, endpointOne, endpointTwo, endpointThree, txtCommunicationProcess);
//        //Reset
        commandControllerProcessor.reset(context, usbConnection, endpointOne, endpointTwo, endpointThree, txtCommunicationProcess);

        return returnValue;
    }

    public String deposit(Context context, UsbDeviceConnection usbConnection, UsbEndpoint endpointOne, UsbEndpoint endpointTwo, UsbEndpoint endpointThree, TextView txtCommunicationProcess) {
        String returnValue = "";

        //Open Shutter
        //Prepare Transaction
        commandControllerProcessor.prepareTransaction(context, usbConnection, endpointOne, endpointTwo, endpointThree, txtCommunicationProcess);
        //Cash Count
        commandControllerProcessor.cashCount(context, usbConnection, endpointOne, endpointTwo, endpointThree, txtCommunicationProcess);
        //Store Money
        commandControllerProcessor.storeMoney(context, usbConnection, endpointOne, endpointTwo, endpointThree, txtCommunicationProcess);
        //RollBack If Error
        commandControllerProcessor.cashRollback(context, usbConnection, endpointOne, endpointTwo, endpointThree, txtCommunicationProcess);
        //Switch Success
        //Call Transfer
        commandControllerProcessor.transfer(context, usbConnection, endpointOne, endpointTwo, endpointThree, txtCommunicationProcess);

        return returnValue;
    }

    public String dispense(Context context, UsbDeviceConnection usbConnection, UsbEndpoint endpointOne, UsbEndpoint endpointTwo, UsbEndpoint endpointThree, TextView txtCommunicationProcess) {
        String returnValue = "";

        //Close Shutter
        //Start Transaction
        //Dispense Command
        // If Normal End - Open Shutter & Dispense
        //If Error Response (with warning) - Go For Next Transaction - Prepare Next Transaction
        //Call Retract

        return returnValue;
    }

    public String reset(Context context, UsbDeviceConnection usbConnection, UsbEndpoint endpointOne, UsbEndpoint endpointTwo, UsbEndpoint endpointThree, TextView txtCommunicationProcess) {
        String returnValue = "";
        returnValue = commandControllerProcessor.reset(context, usbConnection, endpointOne, endpointTwo, endpointThree, txtCommunicationProcess);
        return returnValue;
    }

    public String getCassetteStatus(Context context, UsbDeviceConnection usbConnection, UsbEndpoint endpointOne, UsbEndpoint endpointTwo, UsbEndpoint endpointThree, TextView txtCommunicationProcess) {
        String returnValue = "";
        returnValue = commandControllerProcessor.getCassetteInfo(context, usbConnection, endpointOne, endpointTwo, endpointThree, txtCommunicationProcess);
        return returnValue;
    }

    public String reverseDeposit(Context context, UsbDeviceConnection usbConnection, UsbEndpoint endpointOne, UsbEndpoint endpointTwo, UsbEndpoint endpointThree, TextView txtCommunicationProcess) {
        String returnValue = "";
        returnValue = commandControllerProcessor.reboot(context, usbConnection, endpointOne, endpointTwo, endpointThree, txtCommunicationProcess);
        return returnValue;
    }

    public String getLogsData(Context context, UsbDeviceConnection usbConnection, UsbEndpoint endpointOne, UsbEndpoint endpointTwo, UsbEndpoint endpointThree, TextView txtCommunicationProcess) {
        String returnValue = "";
        returnValue = commandControllerProcessor.getLogData(context, usbConnection, endpointOne, endpointTwo, endpointThree, txtCommunicationProcess);
        return returnValue;
    }

    public String reboot(Context context, UsbDeviceConnection usbConnection, UsbEndpoint endpointOne, UsbEndpoint endpointTwo, UsbEndpoint endpointThree, TextView txtCommunicationProcess) {
        String returnValue = "";
        returnValue = commandControllerProcessor.reboot(context, usbConnection, endpointOne, endpointTwo, endpointThree, txtCommunicationProcess);
        return returnValue;
    }

    public String download(Context context, UsbDeviceConnection usbConnection, UsbEndpoint endpointOne, UsbEndpoint endpointTwo, UsbEndpoint endpointThree, TextView txtCommunicationProcess) {
        String returnValue = "";
        returnValue = commandControllerProcessor.programDownload(context, usbConnection, endpointOne, endpointTwo, endpointThree, txtCommunicationProcess);
        return returnValue;
    }

    public String upload(Context context, UsbDeviceConnection usbConnection, UsbEndpoint endpointOne, UsbEndpoint endpointTwo, UsbEndpoint endpointThree, TextView txtCommunicationProcess) {
        String returnValue = "";

        return returnValue;
    }


    public String testCommand(Context context, UsbDeviceConnection usbConnection, UsbEndpoint endpointOne, UsbEndpoint endpointTwo, UsbEndpoint endpointThree, TextView txtCommunicationProcess) {
        String returnValue = "";

        return returnValue;
    }
}
