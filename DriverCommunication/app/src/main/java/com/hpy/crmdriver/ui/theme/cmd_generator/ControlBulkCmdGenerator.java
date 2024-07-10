package com.hpy.crmdriver.ui.theme.cmd_generator;

import android.content.Context;
import android.hardware.usb.UsbDeviceConnection;
import android.hardware.usb.UsbEndpoint;
import android.util.Log;
import android.widget.TextView;

import com.hpy.crmdriver.ui.theme.util.AppLogs;
import com.hpy.crmdriver.ui.theme.util.KeyValue;
import com.hpy.crmdriver.ui.theme.util.SessionData;
import com.hpy.crmdriver.ui.theme.util.TimeOut;

public class ControlBulkCmdGenerator {

    CmdSupportClass cmdSupportClass = new CmdSupportClass();
    CommandSequence commandSequence = new CommandSequence();
    CommandType commandType = new CommandType();
    TimeOut timeOut = new TimeOut();
    String TAG = "CMD_INPUT_OUTPUT";

    AppLogs appLogs = new AppLogs();

    public String deviceDescriptorData(UsbDeviceConnection usbConnection, TextView textView) {
        String returnValue = "";

        byte[] buffer = new byte[64];
        int length = usbConnection.controlTransfer(0x80, 0x06, 0x0100,
                0x0000, buffer, buffer.length, timeOut.TIMEOUT_10);
        if (length > 0) {
            String bytesToHex = cmdSupportClass.byteArrayToHexDecimal(length, buffer);
            returnValue = "Device Descriptor Data : " + bytesToHex;
            appLogs.writeGenerateLogs(returnValue);
        } else {
            returnValue = "Failed to get device descriptor";
            appLogs.writeGenerateLogs(returnValue);
        }
        textView.setText(textView.getText().toString() + "\n\n" + returnValue);
        return returnValue;
    }

    public void readInterruptData(Context context, UsbDeviceConnection usbConnection, UsbEndpoint endpointThree, int miliseconds) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                while (true) {

                    byte[] bufferResponse = new byte[endpointThree.getMaxPacketSize()];
                    int bytesReadResp = usbConnection.bulkTransfer(endpointThree, bufferResponse, bufferResponse.length, timeOut.TIMEOUT_10);

                    if (bytesReadResp > 0) {
                        String bytesToHex = cmdSupportClass.byteArrayToHexDecimal(bytesReadResp, bufferResponse);
                        SessionData.addValue(context, KeyValue.INTERRUPT_DATA, bytesToHex);
                        appLogs.writeGenerateLogs("Interrupt Data : " + bytesToHex);
                    } else {
                        appLogs.writeGenerateLogs("Interrupt Data : " + "0");
                    }

                    try {
                        Thread.sleep(miliseconds);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        });
        thread.start();
    }

    public String commandSendingRequest(Context context, UsbDeviceConnection usbConnection, int position, TextView textView, StringBuilder stringBuilder, int commandLength) {
        String returnValue = "";

        int reqType = 0xC2;
//        int req = 0x00;
        int req = commandSequence.getNextSeqCmdSendingReq(context, position);
//        int value = 0x1200;
//        int value = 0x0012;
        int value = commandLength;
        int index = 0x0001;
        byte[] buffer = new byte[8];

        String inputMessage = "Command Sending Request (Input): " + "reqType:" + cmdSupportClass.getHexFromInt(reqType) + " req:" + cmdSupportClass.getHexFromInt(req) + " value:" + cmdSupportClass.getHexFromInt4(value) + " index:" + cmdSupportClass.getHexFromInt(index) + " bLength:" + buffer.length;

        appendText(inputMessage, textView, stringBuilder);
        appLogs.writeGenerateLogs(inputMessage);

        int length = usbConnection.controlTransfer(reqType, req, value, index, buffer, buffer.length, timeOut.TIMEOUT_10);

        if (length > 0) {

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < length; i++) {
                sb.append(String.format("%02x ", buffer[i]));
            }
            returnValue = sb.toString();
            appLogs.writeGenerateLogs("Command Sending Request (Response): " + sb.toString());

        } else {
            appLogs.writeGenerateLogs("Command Sending Request (Failed)");
        }

        return returnValue;
    }


    public boolean bulkOutRequest(UsbDeviceConnection usbConnection, byte[] commandArray, UsbEndpoint endpointOne, String command, String commandType, TextView textView, StringBuilder stringBuilder) {
        boolean returnValue = true;
        byte[] bufferRequestFirmware = commandArray;
        int transferredBytesReqFirmware = usbConnection.bulkTransfer(endpointOne, bufferRequestFirmware, bufferRequestFirmware.length, timeOut.TIMEOUT_10);
//        CmdSupportClass.printCommandHexSequence(commandArray);
        String inputMessage = "";
        if (transferredBytesReqFirmware > 0) {
            inputMessage = "Sending BulkOut Command ( " + commandType + " ): " + "Successfully : " + command;
            appLogs.writeGenerateLogs(TAG, inputMessage);
        } else {
            inputMessage = "Sending BulkOut Command ( " + commandType + " ): " + "Failed";
            appLogs.writeGenerateLogs(TAG, inputMessage);
        }
        appendText(inputMessage, textView, stringBuilder);
        return returnValue;
    }

    public String commandSendingConfirmation(Context context, UsbDeviceConnection usbConnection, int indexPosition, TextView textView, StringBuilder stringBuilder, int commandLength) {
        String returnValue = "";

        int reqType = 0xC2;
//        int req = 0x10;
        int req = commandSequence.getNextSeqCmdSendingConf(context, indexPosition);
//        int value = 0x1200;
//        int value = 0x0012;
        int value = commandLength;
        int index = 0x0001;
        byte[] buffer = new byte[8];

        String inputMessage = indexPosition + " " + "Command Sending Confirmation (Input):  " + "reqType:" + cmdSupportClass.getHexFromInt(reqType) + " req:" + cmdSupportClass.getHexFromInt(req) + " value:" + cmdSupportClass.getHexFromInt4(value) + " index:" + cmdSupportClass.getHexFromInt(index) + " bLength:" + buffer.length;
        appLogs.writeGenerateLogs(inputMessage);
        appendText(inputMessage, textView, stringBuilder);

        int length = usbConnection.controlTransfer(reqType, req, value, index, buffer, buffer.length, timeOut.TIMEOUT_10);

        if (length > 0) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < length; i++) {
                sb.append(String.format("%02x ", buffer[i]));
            }
            appLogs.writeGenerateLogs("Command Sending Confirmation (Response): " + sb.toString());
            returnValue = sb.toString();
        } else {
            appLogs.writeGenerateLogs("Command Sending Confirmation (Failed)");
        }
        return returnValue;
    }

    public String responseReceiveRequest(Context context, UsbDeviceConnection usbConnection, int totalCount, TextView textView, StringBuilder stringBuilder) {
        String returnValue = "";

        int reqType = 0xC2;
//        int req = 0x80;
        int req = commandSequence.getNextSeqResponseRecReq(context, totalCount);
        int value = 0x0000;
        int index = 0x0002;
        byte[] buffer = new byte[8];


        String inputMessage = "Response Receiving Request (Input): " + "reqType:" + cmdSupportClass.getHexFromInt(reqType) + " req:" + cmdSupportClass.getHexFromInt(req) + " value:" + cmdSupportClass.getHexFromInt4(value) + " index:" + cmdSupportClass.getHexFromInt(index) + " bLength:" + buffer.length;
        appLogs.writeGenerateLogs(inputMessage);
        appendText(inputMessage, textView, stringBuilder);

        int length = usbConnection.controlTransfer(reqType, req, value, index, buffer, buffer.length, timeOut.TIMEOUT_10);
        if (length > 0) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < length; i++) {
                sb.append(String.format("%02x ", buffer[i]));
            }
            appLogs.writeGenerateLogs("Response Receiving Request (Response): " + sb.toString());
            returnValue = sb.toString();
        } else {
            appLogs.writeGenerateLogs("Response Receiving Request (Failed)");
        }
        return returnValue;
    }


    public String receivedBulInRequest(Context context, UsbDeviceConnection usbConnection, UsbEndpoint usbEndpointTwo, String cmdType, int datLength) {

        String returnValue = "";

        byte[] bufferResponse = new byte[datLength];

//        if (cmdType.equalsIgnoreCase(commandType.RESET)) {
//            bufferResponse = new byte[63504];
//        } else {
//            bufferResponse = new byte[usbEndpointTwo.getMaxPacketSize()];
//        }

        int bytesReadResp = usbConnection.bulkTransfer(usbEndpointTwo, bufferResponse, bufferResponse.length, timeOut.TIMEOUT_20);

        //byte[] bufferResponse1 = new byte[usbEndpointTwo.getMaxPacketSize()];

        appLogs.writeGenerateLogs("receivedBulInRequest_Length : " + bufferResponse.length);
        if (bytesReadResp > 0) {
            String bytesToHex = cmdSupportClass.byteArrayToHexDecimal(bytesReadResp, bufferResponse);
            String outputMessage = "Received BulkIn Data  ( " + cmdType + " ): " + "Successfully : " + bytesToHex.replaceAll(" ", "");
            appLogs.writeGenerateLogs(TAG, outputMessage);
            returnValue = bytesToHex;
        } else {
            String outputMessage = "Received BulkIn Data  ( " + cmdType + " ): " + "Failed";
            appLogs.writeGenerateLogs(TAG, outputMessage);
        }

        return returnValue;

    }

    public String responseReceivedConformation(Context context, UsbDeviceConnection usbConnection, int totalCount, TextView textView, StringBuilder stringBuilder) {
        String returnValue = "";

        int reqType = 0xC2;
//        int req = 0x90;
        int req = commandSequence.getNextSeqResponseRecConf(context, totalCount);
        int value = 0x0000;
        int index = 0x0002;
        byte[] buffer = new byte[8];

        String inputMessage = "Response Receiving Confirmation (Input): " + "reqType:" + cmdSupportClass.getHexFromInt(reqType) + " req:" + cmdSupportClass.getHexFromInt(req) + " value:" + cmdSupportClass.getHexFromInt4(value) + " index:" + cmdSupportClass.getHexFromInt(index) + " bLength:" + buffer.length;
        appLogs.writeGenerateLogs(inputMessage);

        appendText(inputMessage, textView, stringBuilder);
        int length = usbConnection.controlTransfer(reqType, req, value, index, buffer, buffer.length, timeOut.TIMEOUT_10);
        if (length > 0) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < length; i++) {
                sb.append(String.format("%02x ", buffer[i]));
            }
            appLogs.writeGenerateLogs("Response Receiving Confirmation (Response): " + sb.toString());
            returnValue = sb.toString();
        } else {
            appLogs.writeGenerateLogs("Response Receiving Confirmation (Failed)");
        }
        return returnValue;
    }

    public void appendText(String message, TextView textView, StringBuilder stringBuilder) {
        stringBuilder.append(message + "\n");
        textView.setText(stringBuilder.toString());
    }
}
