package com.hpy.crmdriver.ui.theme.cmd_processor;

import android.content.Context;
import android.hardware.usb.UsbDeviceConnection;
import android.hardware.usb.UsbEndpoint;
import android.os.Handler;
import android.util.Log;
import android.widget.TextView;

import com.hpy.crmdriver.ui.theme.cmd_generator.CommandType;
import com.hpy.crmdriver.ui.theme.data.Packet;
import com.hpy.crmdriver.ui.theme.packet_model.ModelPacket0081;
import com.hpy.crmdriver.ui.theme.packet_model.ModelPacket008E;
import com.hpy.crmdriver.ui.theme.session.SessionModel;
import com.hpy.crmdriver.ui.theme.util.AppConfig;
import com.hpy.crmdriver.ui.theme.util.StringHelper;

public class CommandExecutor {

    SessionModel sessionModel = new SessionModel();
    Packet packet = new Packet();
    AppConfig appConfig = new AppConfig();
    StringHelper stringHelper = new StringHelper();
    CommandType commandType = new CommandType();
    public CommandControllerProcessor commandControllerProcessor = new CommandControllerProcessor();

    public boolean initialize(Context context, UsbDeviceConnection usbConnection, UsbEndpoint endpointOne, UsbEndpoint endpointTwo, UsbEndpoint endpointThree, TextView txtCommunicationProcess) {
        boolean isSuccess = false;
        //TODO - Get Firmware
        boolean isFirmware = commandControllerProcessor.getFirmwareVersion(context, usbConnection, endpointOne, endpointTwo, endpointThree, txtCommunicationProcess);
        if (isFirmware) {
            //TODO - Check Success Code
            if (getSuccessCode0081(context, commandType.GET_FIRMWARE).equals(appConfig.SUCCESS_CODE)) {
                //TODO - Set Unit Info
                boolean isSetUnitInfo = commandControllerProcessor.setUnitInfo(context, usbConnection, endpointOne, endpointTwo, endpointThree, txtCommunicationProcess);
                if (isSetUnitInfo) {
                    //TODO - Check Success Code
                    if (getSuccessCode0081(context, commandType.SET_UNIT_INFO).equals(appConfig.SUCCESS_CODE)) {
                        //TODO - Reset
                        boolean isReset = commandControllerProcessor.reset(context, usbConnection, endpointOne, endpointTwo, endpointThree, appConfig.RESET_TYPE_FIRMWARE, txtCommunicationProcess);
                        if (isReset) {
                            //TODO - Check Success Code
                            if (getSuccessCode0081(context, commandType.RESET).equals(appConfig.SUCCESS_CODE)) {
                                isSuccess = true;
                            }
                        }
                    }
                }
            }

        }
        return isSuccess;
    }

    boolean isSuccessDeposit = false;

    public boolean deposit(Context context, UsbDeviceConnection usbConnection, UsbEndpoint endpointOne, UsbEndpoint endpointTwo, UsbEndpoint endpointThree, TextView txtCommunicationProcess) {
        isSuccessDeposit = false;
        //TODO - Prepare Transaction
        boolean isPrepareTransaction = commandControllerProcessor.prepareTransaction(context, usbConnection, endpointOne, endpointTwo, endpointThree, appConfig.PREPARE_START_TRANSACTION, txtCommunicationProcess);
        if (isPrepareTransaction) {
            //TODO - Check Success Code
            if (getSuccessCode0081(context, commandType.PREPARE_TRANS).equals(appConfig.SUCCESS_CODE)) {
                //TODO - Open Shutter
                boolean isDriveShutter_Open = commandControllerProcessor.driveShutter(context, usbConnection, endpointOne, endpointTwo, endpointThree, appConfig.OPEN_SHUTTER, txtCommunicationProcess);
                if (isDriveShutter_Open) {
                    //TODO - Check Success Code
                    if (getSuccessCode0081(context, commandType.DRIVE_SHUTTER).equals(appConfig.SUCCESS_CODE)) {
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            public void run() {
                                //TODO - Cash Count
                                boolean isCashCount = commandControllerProcessor.cashCount(context, usbConnection, endpointOne, endpointTwo, endpointThree, txtCommunicationProcess);
                                if (isCashCount) {
                                    //TODO - Check Success Code
                                    if (getSuccessCode0081(context, commandType.CASH_COUNT).equals(appConfig.SUCCESS_CODE)) {
                                        boolean isStoreMoney = commandControllerProcessor.storeMoney(context, usbConnection, endpointOne, endpointTwo, endpointThree, appConfig.STORE_MONEY_NORMAL, txtCommunicationProcess);
                                        if (isStoreMoney) {
                                            if (getSuccessCode0081(context, commandType.STORE_MONEY).equals(appConfig.SUCCESS_CODE)) {
                                                isSuccessDeposit = true;
                                            }
                                        }
                                    }
                                }
                            }
                        }, 5000);
                    }
                }
            }
        }
        return isSuccessDeposit;
    }


    public boolean isFirmware(Context context, UsbDeviceConnection usbConnection, UsbEndpoint endpointOne, UsbEndpoint endpointTwo, UsbEndpoint endpointThree, TextView txtCommunicationProcess) {
        boolean isSuccess = commandControllerProcessor.getFirmwareVersion(context, usbConnection, endpointOne, endpointTwo, endpointThree, txtCommunicationProcess);
        if (isSuccess) {
            //TODO - Check Success Code
            if (getSuccessCode0081(context, commandType.GET_FIRMWARE).equals(appConfig.SUCCESS_CODE)) {
                isSuccess = true;
            } else {
                isSuccess = false;
            }
        }
        return isSuccess;
    }

    public boolean isSetDenominationCode(Context context, UsbDeviceConnection usbConnection, UsbEndpoint endpointOne, UsbEndpoint endpointTwo, UsbEndpoint endpointThree, TextView txtCommunicationProcess) {
        boolean isSuccess = commandControllerProcessor.setDenominationCode(context, usbConnection, endpointOne, endpointTwo, endpointThree, txtCommunicationProcess);
        if (isSuccess) {
            //TODO - Check Success Code
            if (getSuccessCode0081(context, commandType.SET_DENOMINATION_CODE).equals(appConfig.SUCCESS_CODE)) {
                isSuccess = true;
            } else {
                isSuccess = false;
            }
        }
        return isSuccess;
    }

    public boolean isSetUnitInfo(Context context, UsbDeviceConnection usbConnection, UsbEndpoint endpointOne, UsbEndpoint endpointTwo, UsbEndpoint endpointThree, TextView txtCommunicationProcess) {
        boolean isSuccess = commandControllerProcessor.setUnitInfo(context, usbConnection, endpointOne, endpointTwo, endpointThree, txtCommunicationProcess);
        if (isSuccess) {
            //TODO - Check Success Code
            if (getSuccessCode0081(context, commandType.SET_UNIT_INFO).equals(appConfig.SUCCESS_CODE)) {
                isSuccess = true;
            } else {
                isSuccess = false;
            }
        }
        return isSuccess;
    }

    public boolean isGetUnitInfo(Context context, UsbDeviceConnection usbConnection, UsbEndpoint endpointOne, UsbEndpoint endpointTwo, UsbEndpoint endpointThree, TextView txtCommunicationProcess) {
        boolean isSuccess = commandControllerProcessor.getUnitInfo(context, usbConnection, endpointOne, endpointTwo, endpointThree, appConfig.GET_UNIT_INFO_NORMAL, txtCommunicationProcess);
        if (isSuccess) {
            //TODO - Check Success Code
            if (getSuccessCode0081(context, commandType.GET_UNIT_INFO).equals(appConfig.SUCCESS_CODE)) {
                isSuccess = true;
            } else {
                isSuccess = false;
            }
        }
        return isSuccess;
    }

    public boolean isGetStatus(Context context, UsbDeviceConnection usbConnection, UsbEndpoint endpointOne, UsbEndpoint endpointTwo, UsbEndpoint endpointThree, TextView txtCommunicationProcess) {
        boolean isSuccess = commandControllerProcessor.readStatus(context, usbConnection, endpointOne, endpointTwo, endpointThree, txtCommunicationProcess);
        if (isSuccess) {
            //TODO - Check Success Code
            if (getSuccessCode0081(context, commandType.GET_UNIT_INFO).equals(appConfig.SUCCESS_CODE)) {
                isSuccess = true;
            } else {
                isSuccess = false;
            }
        }
        return isSuccess;
    }

    public boolean isStoreMoney(Context context, UsbDeviceConnection usbConnection, UsbEndpoint endpointOne, UsbEndpoint endpointTwo, UsbEndpoint endpointThree, TextView txtCommunicationProcess) {
        boolean isSuccess = commandControllerProcessor.storeMoney(context, usbConnection, endpointOne, endpointTwo, endpointThree, appConfig.STORE_MONEY_NORMAL, txtCommunicationProcess);
        if (isSuccess) {
            //TODO - Check Success Code
            if (getSuccessCode0081(context, commandType.STORE_MONEY).equals(appConfig.SUCCESS_CODE)) {
                isSuccess = true;
            } else {
                isSuccess = false;
            }
        }
        return isSuccess;
    }

    public boolean isResetNormal(Context context, UsbDeviceConnection usbConnection, UsbEndpoint endpointOne, UsbEndpoint endpointTwo, UsbEndpoint endpointThree, TextView txtCommunicationProcess) {
        boolean isSuccess = commandControllerProcessor.reset(context, usbConnection, endpointOne, endpointTwo, endpointThree, appConfig.RESET_TYPE_FIRMWARE, txtCommunicationProcess);
        if (isSuccess) {
            //TODO - Check Success Code
            if (getSuccessCode0081(context, commandType.RESET).equals(appConfig.SUCCESS_CODE)) {
                isSuccess = true;
            } else {
                isSuccess = false;
            }
        }
        return isSuccess;
    }

    public boolean isResetQuick(Context context, UsbDeviceConnection usbConnection, UsbEndpoint endpointOne, UsbEndpoint endpointTwo, UsbEndpoint endpointThree, TextView txtCommunicationProcess) {
        boolean isSuccess = commandControllerProcessor.reset(context, usbConnection, endpointOne, endpointTwo, endpointThree, appConfig.RESET_TYPE_QUICK, txtCommunicationProcess);
        if (isSuccess) {
            //TODO - Check Success Code
            if (getSuccessCode0081(context, commandType.RESET).equals(appConfig.SUCCESS_CODE)) {
                isSuccess = true;
            } else {
                isSuccess = false;
            }
        }
        return isSuccess;
    }


    public boolean isPrepareTransaction(Context context, UsbDeviceConnection usbConnection, UsbEndpoint endpointOne, UsbEndpoint endpointTwo, UsbEndpoint endpointThree, TextView txtCommunicationProcess) {
        boolean isSuccess = commandControllerProcessor.prepareTransaction(context, usbConnection, endpointOne, endpointTwo, endpointThree, appConfig.PREPARE_START_TRANSACTION, txtCommunicationProcess);
        if (isSuccess) {
            //TODO - Check Success Code
            if (getSuccessCode0081(context, commandType.PREPARE_TRANS).equals(appConfig.SUCCESS_CODE)) {
                isSuccess = true;
            } else {
                isSuccess = false;
            }
        }
        return isSuccess;
    }

    public boolean isPrepareNextTransaction(Context context, UsbDeviceConnection usbConnection, UsbEndpoint endpointOne, UsbEndpoint endpointTwo, UsbEndpoint endpointThree, TextView txtCommunicationProcess) {
        boolean isSuccess = commandControllerProcessor.prepareTransaction(context, usbConnection, endpointOne, endpointTwo, endpointThree, appConfig.PREPARE_NEXT_TRANSACTION, txtCommunicationProcess);
        if (isSuccess) {
            //TODO - Check Success Code
            if (getSuccessCode0081(context, commandType.PREPARE_TRANS).equals(appConfig.SUCCESS_CODE)) {
                isSuccess = true;
            } else {
                isSuccess = false;
            }
        }
        return isSuccess;
    }

    public boolean isDispense(Context context, UsbDeviceConnection usbConnection, UsbEndpoint endpointOne, UsbEndpoint endpointTwo, UsbEndpoint endpointThree, TextView txtCommunicationProcess) {
        boolean isSuccess = commandControllerProcessor.dispense(context, usbConnection, endpointOne, endpointTwo, endpointThree, appConfig.DISPENSE_PER_ROOM, txtCommunicationProcess);
        if (isSuccess) {
            //TODO - Check Success Code
            if (getSuccessCode0081(context, commandType.DISPENSE).equals(appConfig.SUCCESS_CODE)) {
                isSuccess = true;
            } else {
                isSuccess = false;
            }
        }
        return isSuccess;
    }

    public boolean isRetract(Context context, UsbDeviceConnection usbConnection, UsbEndpoint endpointOne, UsbEndpoint endpointTwo, UsbEndpoint endpointThree, TextView txtCommunicationProcess) {
        boolean isSuccess = commandControllerProcessor.retract(context, usbConnection, endpointOne, endpointTwo, endpointThree, txtCommunicationProcess);
        if (isSuccess) {
            //TODO - Check Success Code
            if (getSuccessCode0081(context, commandType.RETRACT).equals(appConfig.SUCCESS_CODE)) {
                isSuccess = true;
            } else {
                isSuccess = false;
            }
        }
        return isSuccess;
    }


    public boolean isCashCount(Context context, UsbDeviceConnection usbConnection, UsbEndpoint endpointOne, UsbEndpoint endpointTwo, UsbEndpoint endpointThree, TextView txtCommunicationProcess) {
        boolean isSuccess = commandControllerProcessor.cashCount(context, usbConnection, endpointOne, endpointTwo, endpointThree, txtCommunicationProcess);
        if (isSuccess) {
            //TODO - Check Success Code
            if (getSuccessCode0081(context, commandType.CASH_COUNT).equals(appConfig.SUCCESS_CODE)) {
                isSuccess = true;
            } else {
                isSuccess = false;
            }
        }
        return isSuccess;
    }

    public boolean isCashRollback(Context context, UsbDeviceConnection usbConnection, UsbEndpoint endpointOne, UsbEndpoint endpointTwo, UsbEndpoint endpointThree, TextView txtCommunicationProcess) {
        boolean isSuccess = commandControllerProcessor.cashRollback(context, usbConnection, endpointOne, endpointTwo, endpointThree, txtCommunicationProcess);
        if (isSuccess) {
            //TODO - Check Success Code
            if (getSuccessCode0081(context, commandType.CASH_ROLLBACK).equals(appConfig.SUCCESS_CODE)) {
                isSuccess = true;
            } else {
                isSuccess = false;
            }
        }
        return isSuccess;
    }

    public boolean isOpenShutter(Context context, UsbDeviceConnection usbConnection, UsbEndpoint endpointOne, UsbEndpoint endpointTwo, UsbEndpoint endpointThree, TextView txtCommunicationProcess) {
        boolean isSuccess = commandControllerProcessor.driveShutter(context, usbConnection, endpointOne, endpointTwo, endpointThree, appConfig.OPEN_SHUTTER, txtCommunicationProcess);
        if (isSuccess) {
            //TODO - Check Success Code
            if (getSuccessCode0081(context, commandType.DRIVE_SHUTTER).equals(appConfig.SUCCESS_CODE)) {
                isSuccess = true;
            } else {
                isSuccess = false;
            }
        }
        return isSuccess;
    }

    public boolean isCloseShutter(Context context, UsbDeviceConnection usbConnection, UsbEndpoint endpointOne, UsbEndpoint endpointTwo, UsbEndpoint endpointThree, TextView txtCommunicationProcess) {
        boolean isSuccess = commandControllerProcessor.driveShutter(context, usbConnection, endpointOne, endpointTwo, endpointThree, appConfig.CLOSE_SHUTTER, txtCommunicationProcess);
        if (isSuccess) {
            //TODO - Check Success Code
            if (getSuccessCode0081(context, commandType.DRIVE_SHUTTER).equals(appConfig.SUCCESS_CODE)) {
                isSuccess = true;
            } else {
                isSuccess = false;
            }
        }
        return isSuccess;
    }

    public boolean isReboot(Context context, UsbDeviceConnection usbConnection, UsbEndpoint endpointOne, UsbEndpoint endpointTwo, UsbEndpoint endpointThree, TextView txtCommunicationProcess) {
        boolean isSuccess = commandControllerProcessor.reboot(context, usbConnection, endpointOne, endpointTwo, endpointThree, txtCommunicationProcess);
        if (isSuccess) {
            //TODO - Check Success Code
            if (getSuccessCode0081(context, commandType.REBOOT).equals(appConfig.SUCCESS_CODE)) {
                isSuccess = true;
            } else {
                isSuccess = false;
            }
        }
        return isSuccess;
    }

    public boolean isDriverAccessory(Context context, UsbDeviceConnection usbConnection, UsbEndpoint endpointOne, UsbEndpoint endpointTwo, UsbEndpoint endpointThree, TextView txtCommunicationProcess) {
        boolean isSuccess = commandControllerProcessor.driveAccessory(context, usbConnection, endpointOne, endpointTwo, endpointThree, txtCommunicationProcess);
        if (isSuccess) {
            //TODO - Check Success Code
            if (getSuccessCode0081(context, commandType.DRIVER_ACCESSORY).equals(appConfig.SUCCESS_CODE)) {
                isSuccess = true;
            } else {
                isSuccess = false;
            }
        }
        return isSuccess;
    }

    public boolean isLogsData(Context context, UsbDeviceConnection usbConnection, UsbEndpoint endpointOne, UsbEndpoint endpointTwo, UsbEndpoint endpointThree, TextView txtCommunicationProcess) {
        boolean isSuccess = commandControllerProcessor.getLogData(context, usbConnection, endpointOne, endpointTwo, endpointThree, appConfig.GET_LOGS_DATA, txtCommunicationProcess);
        if (isSuccess) {
            //TODO - Check Success Code
            if (getSuccessCode0081(context, commandType.GET_LOGS_DATA).equals(appConfig.SUCCESS_CODE)) {
                isSuccess = true;
            } else {
                isSuccess = false;
            }
        }
        return isSuccess;
    }

    public boolean isCancel(Context context, UsbDeviceConnection usbConnection, UsbEndpoint endpointOne, UsbEndpoint endpointTwo, UsbEndpoint endpointThree, TextView txtCommunicationProcess) {
        boolean isSuccess = commandControllerProcessor.cancel(context, usbConnection, endpointOne, endpointTwo, endpointThree, txtCommunicationProcess);
        if (isSuccess) {
            //TODO - Check Success Code
            if (getSuccessCode0081(context, commandType.CANCEL).equals(appConfig.SUCCESS_CODE)) {
                isSuccess = true;
            } else {
                isSuccess = false;
            }
        }
        return isSuccess;
    }

    public boolean isBankNoteInfo(Context context, UsbDeviceConnection usbConnection, UsbEndpoint endpointOne, UsbEndpoint endpointTwo, UsbEndpoint endpointThree, TextView txtCommunicationProcess) {
        boolean isSuccess = commandControllerProcessor.getBanknoteInfo(context, usbConnection, endpointOne, endpointTwo, endpointThree, txtCommunicationProcess);
        if (isSuccess) {
            //TODO - Check Success Code
            if (getSuccessCode0081(context, commandType.GET_BANK_NOTE_INFO).equals(appConfig.SUCCESS_CODE)) {
                isSuccess = true;
            } else {
                isSuccess = false;
            }
        }
        return isSuccess;
    }

    public boolean isCassetteNoteInfo(Context context, UsbDeviceConnection usbConnection, UsbEndpoint endpointOne, UsbEndpoint endpointTwo, UsbEndpoint endpointThree, TextView txtCommunicationProcess) {
        boolean isSuccess = commandControllerProcessor.getCassetteInfo(context, usbConnection, endpointOne, endpointTwo, endpointThree, txtCommunicationProcess);
        if (isSuccess) {
            //TODO - Check Success Code
            if (getSuccessCode0081(context, commandType.GET_CASSETTE_INFO).equals(appConfig.SUCCESS_CODE)) {
                isSuccess = true;
            } else {
                isSuccess = false;
            }
        }
        return isSuccess;
    }

    public String getSuccessCode0081(Context context, String cmdType) {
        ModelPacket0081 model0081 = new ModelPacket0081();
        model0081 = sessionModel.getModelFromSession(context, packet.PKT_0081, model0081.getClass());
        String successCode = stringHelper.getLastDigit(model0081.getResponseCode(), 2);
        Log.e("TAG", cmdType + " getSuccessCode0081_responseCode: " + model0081.getResponseCode());
        Log.e("TAG", cmdType + " getSuccessCode0081_successCode: " + successCode);
        return successCode;
    }

    public void getSuccessCode008E(Context context) {
        ModelPacket008E model008E = new ModelPacket008E();
        model008E = sessionModel.getModelFromSession(context, packet.PKT_008E, model008E.getClass());
    }

}
