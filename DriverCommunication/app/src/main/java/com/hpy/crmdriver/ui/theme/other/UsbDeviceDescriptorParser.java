package com.hpy.crmdriver.ui.theme.other;

import android.hardware.usb.UsbConstants;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbDeviceConnection;
import android.hardware.usb.UsbManager;
import android.util.Log;

public class UsbDeviceDescriptorParser {

    private StringBuilder logBuilder = new StringBuilder();
    private static final int TIMEOUT = 10000; // Timeout in milliseconds

    public void enumerateDevice(UsbDevice device, UsbDeviceConnection connection, UsbManager usbManager) {

        if (connection == null) {
            log("Failed to open device");
            return;
        }

        // Step 1: Get Device Descriptor (First 8 bytes)
        byte[] deviceDescriptor = new byte[18];
        int length = connection.controlTransfer(
                0x80,
                0x06, // USB_REQUEST_GET_DESCRIPTOR value (Standard USB request to get descriptor)
                0x0100, 0,
                deviceDescriptor, deviceDescriptor.length,
                TIMEOUT
        );
        log("step1.  Get Device Descriptor (First 8 bytes)");
        logTransfer("80 06 00 01 00 00 08 00", deviceDescriptor, length);

        // Step 2: Set Address
        int newAddress = 1;
//        length = connection.controlTransfer(
//                00,
//                0x05, // USB_REQUEST_SET_ADDRESS value (Standard USB request to set device address)
//                newAddress, 0,
//                null, 0,
//                TIMEOUT
//        );
        log("step2.   Set Address");
        log("00 05 01 00 00 00 - commented code");
        log("ACK");

        // Close and reopen the connection with the new address
//        connection.close();
//        connection = usbManager.openDevice(device);

        // Step 3: Get Full Device Descriptor
        length = connection.controlTransfer(
                0x80,
                0x06, // USB_REQUEST_GET_DESCRIPTOR value (Standard USB request to get descriptor)
                0x0100, 0,
                deviceDescriptor, deviceDescriptor.length,
                TIMEOUT
        );
        log("step3. Get Full Device Descriptor");
        logTransfer("80 06 00 01 00 00 12 00", deviceDescriptor, length);
        parseDeviceDescriptor(deviceDescriptor);

        // Step 4: Get Configuration Descriptor (initial part)
        byte[] configDescriptor = new byte[9];
        length = connection.controlTransfer(
                0x80,
                0x06, // USB_REQUEST_GET_DESCRIPTOR value (Standard USB request to get descriptor)
                0x0200, 0,
                configDescriptor, configDescriptor.length,
                TIMEOUT
        );
        log("step4. Get Configuration Descriptor (initial part)");
        logTransfer("80 06 00 02 00 00 09 00", configDescriptor, length);

        // Step 5: Get Full Configuration Descriptor
        int totalLength = (configDescriptor[2] & 0xFF) | ((configDescriptor[3] & 0xFF) << 8);
        configDescriptor = new byte[totalLength];
        length = connection.controlTransfer(
                0x80,
                0x06, // USB_REQUEST_GET_DESCRIPTOR value (Standard USB request to get descriptor)
                0x0200, 0,
                configDescriptor, configDescriptor.length,
                TIMEOUT
        );
        log("step5. Get Full Configuration Descriptor");
        logTransfer("80 06 00 02 00 00 " + String.format("%02X", totalLength & 0xFF) + " " + String.format("%02X", (totalLength >> 8) & 0xFF), configDescriptor, length);
        parseConfigDescriptor(configDescriptor);

        //UsbConstants.USB_DIR_OUT | UsbConstants.USB_TYPE_STANDARD,
//         Step 6: Set Configuration
//        length = connection.controlTransfer(
//                00,
//                9, // USB_REQUEST_SET_CONFIGURATION value
//                1, 0,
//                null, 0,
//                TIMEOUT
//        );
        log("step6. Set Configuration");
        log("00 09 01 00 00 00 - commented code");
        log("ACK");

        byte[] configDescriptor1 = new byte[7];
        length = connection.controlTransfer(
                0x80,
                0x06, // USB_REQUEST_GET_DESCRIPTOR value (Standard USB request to get descriptor)
                0x5100, 0,
                configDescriptor1, configDescriptor1.length,
                TIMEOUT
        );
        log("step4. Get endpoint 1 Descriptor (initial part)");
        logTransfer("80 06 00 05 00 00 07 00", configDescriptor, length);
        connection.close();
    }

    private void parseDeviceDescriptor(byte[] data) {
        int bLength = data[0] & 0xFF;
        int bDescriptorType = data[1] & 0xFF;
        int bcdUSB = (data[2] & 0xFF) | ((data[3] & 0xFF) << 8);
        int bDeviceClass = data[4] & 0xFF;
        int bDeviceSubClass = data[5] & 0xFF;
        int bDeviceProtocol = data[6] & 0xFF;
        int bMaxPacketSize0 = data[7] & 0xFF;
        int idVendor = (data[8] & 0xFF) | ((data[9] & 0xFF) << 8);
        int idProduct = (data[10] & 0xFF) | ((data[11] & 0xFF) << 8);
        int bcdDevice = (data[12] & 0xFF) | ((data[13] & 0xFF) << 8);
        int iManufacturer = data[14] & 0xFF;
        int iProduct = data[15] & 0xFF;
        int iSerialNumber = data[16] & 0xFF;
        int bNumConfigurations = data[17] & 0xFF;

        log(String.format("Device Descriptor:\n  bLength: %d\n  bDescriptorType: %d\n  bcdUSB: 0x%04X\n  bDeviceClass: %d\n  bDeviceSubClass: %d\n  bDeviceProtocol: %d\n  bMaxPacketSize0: %d\n  idVendor: 0x%04X\n  idProduct: 0x%04X\n  bcdDevice: 0x%04X\n  iManufacturer: %d\n  iProduct: %d\n  iSerialNumber: %d\n  bNumConfigurations: %d",
                bLength, bDescriptorType, bcdUSB, bDeviceClass, bDeviceSubClass, bDeviceProtocol, bMaxPacketSize0, idVendor, idProduct, bcdDevice, iManufacturer, iProduct, iSerialNumber, bNumConfigurations));
    }

    private void parseConfigDescriptor(byte[] data) {
        int offset = 0;
        while (offset < data.length) {
            int bLength = data[offset] & 0xFF;
            int bDescriptorType = data[offset + 1] & 0xFF;
            if (bDescriptorType == UsbConstants.USB_CLASS_PER_INTERFACE) {
                int wTotalLength = (data[offset + 2] & 0xFF) | ((data[offset + 3] & 0xFF) << 8);
                int bNumInterfaces = data[offset + 4] & 0xFF;
                int bConfigurationValue = data[offset + 5] & 0xFF;
                int iConfiguration = data[offset + 6] & 0xFF;
                int bmAttributes = data[offset + 7] & 0xFF;
                int bMaxPower = data[offset + 8] & 0xFF;

                log(String.format("Configuration Descriptor:\n  wTotalLength: %d\n  bNumInterfaces: %d\n  bConfigurationValue: %d\n  iConfiguration: %d\n  bmAttributes: %d\n  bMaxPower: %d",
                        wTotalLength, bNumInterfaces, bConfigurationValue, iConfiguration, bmAttributes, bMaxPower));
                offset += bLength;
//            } else if (bDescriptorType == UsbConstants.USB_DT_INTERFACE) {
            } else if (bDescriptorType == 4) {
                int bInterfaceNumber = data[offset + 2] & 0xFF;
                int bAlternateSetting = data[offset + 3] & 0xFF;
                int bNumEndpoints = data[offset + 4] & 0xFF;
                int bInterfaceClass = data[offset + 5] & 0xFF;
                int bInterfaceSubClass = data[offset + 6] & 0xFF;
                int bInterfaceProtocol = data[offset + 7] & 0xFF;
                int iInterface = data[offset + 8] & 0xFF;

                log(String.format("Interface Descriptor:\n  bInterfaceNumber: %d\n  bAlternateSetting: %d\n  bNumEndpoints: %d\n  bInterfaceClass: %d\n  bInterfaceSubClass: %d\n  bInterfaceProtocol: %d\n  iInterface: %d",
                        bInterfaceNumber, bAlternateSetting, bNumEndpoints, bInterfaceClass, bInterfaceSubClass, bInterfaceProtocol, iInterface));
                offset += bLength;
//            } else if (bDescriptorType == UsbConstants.USB_DT_ENDPOINT) {
            } else if (bDescriptorType == 5) {
                int bEndpointAddress = data[offset + 2] & 0xFF;
                int bmAttributes = data[offset + 3] & 0xFF;
                int wMaxPacketSize = (data[offset + 4] & 0xFF) | ((data[offset + 5] & 0xFF) << 8);
                int bInterval = data[offset + 6] & 0xFF;

                log(String.format("Endpoint Descriptor:\n  bEndpointAddress: 0x%02X\n  bmAttributes: %d\n  wMaxPacketSize: %d\n  bInterval: %d",
                        bEndpointAddress, bmAttributes, wMaxPacketSize, bInterval));
                offset += bLength;
            } else {
                log(String.format("Unknown Descriptor: bDescriptorType = %d", bDescriptorType));
                offset += bLength;
            }
        }
    }

    private void logTransfer(String hostMessage, byte[] deviceResponse, int length) {
        log("Host: " + hostMessage);
        if (length > 0) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < length; i++) {
                sb.append(String.format("%02X ", deviceResponse[i]));
            }
            log("Device: " + sb.toString().trim());
        } else {
            log("Device: No Response");
        }
    }

    private void log(String message) {
        logBuilder.append(message).append("\n");
        Log.e("USB_Device_Descriptor", message);
    }

    public String getLog() {
        return logBuilder.toString();
    }
}
