package com.hpy.crmdriver.ui.theme.activity;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.hardware.usb.UsbConfiguration;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbDeviceConnection;
import android.hardware.usb.UsbEndpoint;
import android.hardware.usb.UsbInterface;
import android.hardware.usb.UsbManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.hpy.crmdriver.R;
import com.hpy.crmdriver.ui.theme.cmd_processor.CommandExecutor;
import com.hpy.crmdriver.ui.theme.data.Packet;
import com.hpy.crmdriver.ui.theme.packet_model.ModelPacket0081;
import com.hpy.crmdriver.ui.theme.packet_model.ModelPacket008E;
import com.hpy.crmdriver.ui.theme.session.SessionModel;
import com.hpy.crmdriver.ui.theme.util.AppConfig;
import com.hpy.crmdriver.ui.theme.util.AppLogs;
import com.hpy.crmdriver.ui.theme.util.ApplicationData;
import com.hpy.crmdriver.ui.theme.util.Click;
import com.hpy.crmdriver.ui.theme.util.DeviceDetails;
import com.hpy.crmdriver.ui.theme.util.FileAccess;
import com.hpy.crmdriver.ui.theme.util.SessionData;
import com.hpy.crmdriver.ui.theme.util.StorageType;
import com.hpy.crmdriver.ui.theme.util.ValueConvertor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class DriverCommunicationActivity extends AppCompatActivity {
    private Activity activity = DriverCommunicationActivity.this;
    private static final String TAG = "USB_COMMUNICATION";
    private static final String ACTION_USB_PERMISSION = "com.hpy.atmdriver.USB_PERMISSION";
    private static final int TIMEOUT_10 = 10000; // Timeout in milliseconds
    private static final int TIMEOUT_20 = 20000; // Timeout in milliseconds
    private UsbManager usbManager;
    private PendingIntent permissionIntent;
    private UsbDevice usbDevice;
    private UsbDeviceConnection usbConnection;
    private UsbInterface usbInterface; // Declare at the class level
    private boolean permissionRequested;
    private UsbEndpoint endpointOne;
    private UsbEndpoint endpointTwo;
    private UsbEndpoint endpointThree;
    private TextView txtCommunicationProcess;
    private TextView txtExceptionData;
    private boolean isProcessCompleted = false;

    private CommandExecutor commandExecutor = new CommandExecutor();

    private SessionModel sessionModel = new SessionModel();
    private SessionData sessionData = new SessionData();

    private Button btnResetQuick;
    private Button btnReboot;
    private Button btnOpenShutterNormal;
    private Button btnCloseShutterNormal;
    private Button btnClearAll;
    private Button btnGetStatus;
    private Button btnClearSession;
    private Button btnFirmware;
    private Button btnSetUnitInfo;
    private Button btnResetNormal;
    private Button btnSetDenominationCode;
    private Button btnGetUnitInfo;
    private Button btnPrepareTransactionDeposit;
    private Button btnOpenShutterDeposit;
    private Button btnCashCount;
    private Button btnCashRollBack;
    private Button btnStoreMoney;
    private Button btnPrepareNextTransactionDeposit;
    private Button btnPrepareTransactionDispense;
    private Button btnDispense;
    private Button btnRetract;
    private Button btnOpenShutterDispense;
    private Button btnCloseShutterDispense;
    private Button btnPrepareNextTransactionDispense;
    private Button btnProgramDownloadStart;
    private Button btnProgramDownloadSendData;
    private Button btnProgramDownloadEnd;
    private Button btnLogsData;
    private Button btnCancel;
    private Button btnGetBankNotesInfo;
    private Button btnGetCassetteInfo;
    private ToggleButton btnToggle;

    private AppConfig appConfig = new AppConfig();
    private StorageType storageType = new StorageType();

    private static final int REQUEST_PERMISSION_CODE = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usb_communication);

        btnToggle = findViewById(R.id.btnToggle);
        setModeCheck();
        btnToggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    SessionData.addValue(activity, appConfig.APP_MODE_VALUE, appConfig.APP_MODE_LIVE);
                } else {
                    SessionData.addValue(activity, appConfig.APP_MODE_VALUE, appConfig.APP_MODE_TEST);
                }
            }
        });

        //Normal Process
        btnResetQuick = findViewById(R.id.btnResetQuick);
        btnReboot = findViewById(R.id.btnReboot);
        btnOpenShutterNormal = findViewById(R.id.btnOpenShutterNormal);
        btnCloseShutterNormal = findViewById(R.id.btnCloseShutterNormal);
        btnClearAll = findViewById(R.id.btnClearAll);
        btnGetStatus = findViewById(R.id.btnGetStatus);
        btnClearSession = findViewById(R.id.btnClearSession);
        btnGetUnitInfo = findViewById(R.id.btnGetUnitInfo);

        //Initialization Process
        btnFirmware = findViewById(R.id.btnFirmware);
        btnSetUnitInfo = findViewById(R.id.btnSetUnitInfo);
        btnResetNormal = findViewById(R.id.btnResetNormal);
        btnSetDenominationCode = findViewById(R.id.btnSetDenominationCode);

        //Deposit Process
        btnPrepareTransactionDeposit = findViewById(R.id.btnPrepareTransactionDeposit);
        btnOpenShutterDeposit = findViewById(R.id.btnOpenShutterDeposit);
        btnCashCount = findViewById(R.id.btnCashCount);
        btnCashRollBack = findViewById(R.id.btnCashRollBack);
        btnStoreMoney = findViewById(R.id.btnStoreMoney);
        btnPrepareNextTransactionDeposit = findViewById(R.id.btnPrepareNextTransactionDeposit);

        //Dispense Process
        btnPrepareTransactionDispense = findViewById(R.id.btnPrepareTransactionDispense);
        btnDispense = findViewById(R.id.btnDispense);
        btnRetract = findViewById(R.id.btnRetract);
        btnOpenShutterDispense = findViewById(R.id.btnOpenShutterDispense);
        btnCloseShutterDispense = findViewById(R.id.btnCloseShutterDispense);
        btnPrepareNextTransactionDispense = findViewById(R.id.btnPrepareNextTransactionDispense);

        //Program Download
        btnProgramDownloadStart = findViewById(R.id.btnProgramDownloadStart);
        btnProgramDownloadSendData = findViewById(R.id.btnProgramDownloadSendData);
        btnProgramDownloadEnd = findViewById(R.id.btnProgramDownloadEnd);

        //Others
        btnLogsData = findViewById(R.id.btnLogsData);
        btnCancel = findViewById(R.id.btnCancel);
        btnGetBankNotesInfo = findViewById(R.id.btnGetBankNotesInfo);
        btnGetCassetteInfo = findViewById(R.id.btnGetCassetteInfo);

        txtCommunicationProcess = findViewById(R.id.txtCommunicationProcess);
        txtExceptionData = findViewById(R.id.txtExceptionData);

        usbManager = (UsbManager) getSystemService(Context.USB_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            permissionIntent = PendingIntent.getBroadcast(this, 0, new Intent(ACTION_USB_PERMISSION), PendingIntent.FLAG_IMMUTABLE);
        } else {
            permissionIntent = PendingIntent.getBroadcast(this, 0, new Intent(ACTION_USB_PERMISSION), 0);
        }

        IntentFilter filter = new IntentFilter(ACTION_USB_PERMISSION);
        filter.addAction(UsbManager.ACTION_USB_DEVICE_ATTACHED);
        filter.addAction(UsbManager.ACTION_USB_DEVICE_DETACHED);
        registerReceiver(usbReceiver, filter);

        checkForExistingDevices();
        clickListeners();

        checkStoragePermission();

    }

    private void setModeCheck() {
        String getAppModeType = SessionData.getStringValue(activity, appConfig.APP_MODE_VALUE);
        if (TextUtils.isEmpty(getAppModeType)) {
            SessionData.addValue(activity, appConfig.APP_MODE_VALUE, appConfig.APP_MODE_TEST);
            btnToggle.setChecked(false);
        } else if (getAppModeType.equalsIgnoreCase(appConfig.APP_MODE_TEST)) {
            btnToggle.setChecked(false);
        } else if (getAppModeType.equalsIgnoreCase(appConfig.APP_MODE_LIVE)) {
            btnToggle.setChecked(true);
        }
    }

    private void clickListeners() {
        btnResetQuick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Click.preventTwoClick(v);
                quickReset(btnResetQuick);
            }
        });

        btnReboot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Click.preventTwoClick(v);
                reboot(btnReboot);
            }
        });

        btnOpenShutterNormal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Click.preventTwoClick(v);
                openShutter(btnOpenShutterNormal);
            }
        });
        btnCloseShutterNormal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Click.preventTwoClick(v);
                closeShutter(btnCloseShutterNormal);
            }
        });

        btnClearAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Click.preventTwoClick(v);
                clearAllView();
            }
        });

        btnGetStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Click.preventTwoClick(v);
                getStatus(btnGetStatus);
            }
        });

        btnGetUnitInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Click.preventTwoClick(v);
                getUnitInfo(btnGetUnitInfo);
            }
        });

        btnClearSession.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Click.preventTwoClick(v);
                //TODO - Clear Data
                sessionData.clearAllSession(activity);
                sessionModel.clearAllSession(activity);
                setModeCheck();
            }
        });

        btnFirmware.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Click.preventTwoClick(v);
                firmware(btnFirmware);
            }
        });

        btnSetDenominationCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Click.preventTwoClick(v);
                setDenominationCode(btnSetDenominationCode);
            }
        });
        btnSetUnitInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Click.preventTwoClick(v);
                setUnitInfo(btnSetUnitInfo);
            }
        });

        btnResetNormal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Click.preventTwoClick(v);
                resetNormal(btnResetNormal);
            }
        });

        btnPrepareTransactionDeposit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Click.preventTwoClick(v);
                prepareTransaction(btnPrepareTransactionDeposit);
            }
        });

        btnOpenShutterDeposit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Click.preventTwoClick(v);
                openShutter(btnOpenShutterDeposit);
            }
        });

        btnCashCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Click.preventTwoClick(v);
                cashCount(btnCashCount);
            }
        });

        btnCashRollBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Click.preventTwoClick(v);
                rollBack(btnCashRollBack);
            }
        });

        btnStoreMoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Click.preventTwoClick(v);
                storeMoney(btnStoreMoney);
            }
        });

        btnPrepareNextTransactionDeposit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Click.preventTwoClick(v);
                nextTransaction(btnPrepareNextTransactionDeposit);
            }
        });

        btnPrepareTransactionDispense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Click.preventTwoClick(v);
                prepareTransaction(btnPrepareTransactionDispense);
            }
        });

        btnDispense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Click.preventTwoClick(v);
                dispense(btnDispense);
            }
        });

        btnRetract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Click.preventTwoClick(v);
                retract(btnRetract);
            }
        });

        btnOpenShutterDispense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Click.preventTwoClick(v);
                openShutter(btnOpenShutterDispense);
            }
        });

        btnCloseShutterDispense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Click.preventTwoClick(v);
                closeShutter(btnCloseShutterDispense);
            }
        });

        btnPrepareNextTransactionDispense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Click.preventTwoClick(v);
                nextTransaction(btnPrepareNextTransactionDispense);
            }
        });

        btnProgramDownloadStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Click.preventTwoClick(v);
                programDownloadStart(btnProgramDownloadStart);
            }
        });

        btnProgramDownloadSendData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Click.preventTwoClick(v);
                if (checkStoragePermission()) {
//                    checkProgramDownloadFileData();
//                    testProgramSendDownload();
                }
            }
        });

        btnProgramDownloadEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Click.preventTwoClick(v);
                programDownloadEnd(btnProgramDownloadEnd);
            }
        });

        btnLogsData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Click.preventTwoClick(v);
                getLogsData(btnLogsData);
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Click.preventTwoClick(v);
                cancel(btnCancel);
            }
        });

        btnGetBankNotesInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Click.preventTwoClick(v);
                getBankNoteInfo(btnGetBankNotesInfo);
            }
        });

        btnGetCassetteInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Click.preventTwoClick(v);
                getCassetteNoteInfo(btnGetCassetteInfo);
            }
        });

    }

    private void setExceptionData() {

        Packet packet = new Packet();
        ModelPacket0081 model0081 = new ModelPacket0081();
        ModelPacket008E model008E = new ModelPacket008E();
        model0081 = sessionModel.getModelFromSession(activity, packet.PKT_0081, model0081.getClass());
        model008E = sessionModel.getModelFromSession(activity, packet.PKT_008E, model008E.getClass());

        if (model0081 != null) {
//            txtExceptionData.setText("MODEL 0081 : " + model0081.getPacketId() + model0081.getLength() + model0081.getResponseCode());
            txtExceptionData.setText("0081 CODE : " + model0081.getResponseCode());
        }

        if (model008E != null) {
//            txtExceptionData.setText(txtExceptionData.getText().toString() + "\n\n" +
//                    "MODEL 008E : " + model008E.getPacketId() + model008E.getLength() + model008E.getErrorCode() +
//                    model008E.getReserved() + model008E.getErrorCassette() + model008E.getUnitStatus()
//                    + model008E.getRecoveryCode() + model008E.getPositionCode());
            txtExceptionData.setText(txtExceptionData.getText().toString() + "  |  " +
                    "008E CODE : " + model008E.getErrorCode());
        }
    }


    private void getStatus(Button button) {
        if (isProcessCompleted) {
            clearData();
            boolean isSuccess = commandExecutor.isGetStatus(activity, usbConnection, endpointOne, endpointTwo, endpointThree, txtCommunicationProcess);
            getColorCode(isSuccess, button);
        }
    }

    private void prepareTransaction(Button button) {
        if (isProcessCompleted) {
            clearData();
            boolean isSuccess = commandExecutor.isPrepareTransaction(activity, usbConnection, endpointOne, endpointTwo, endpointThree, txtCommunicationProcess);
            getColorCode(isSuccess, button);
        }
    }


    private void nextTransaction(Button button) {
        if (isProcessCompleted) {
            clearData();
            txtExceptionData.setText("");
            boolean isSuccess = commandExecutor.isPrepareNextTransaction(activity, usbConnection, endpointOne, endpointTwo, endpointThree, txtCommunicationProcess);
            getColorCode(isSuccess, button);
        }
    }

    private void dispense(Button button) {
        if (isProcessCompleted) {
            clearData();
            boolean isSuccess = commandExecutor.isDispense(activity, usbConnection, endpointOne, endpointTwo, endpointThree, txtCommunicationProcess);
            getColorCode(isSuccess, button);
        }
    }

    private void retract(Button button) {
        if (isProcessCompleted) {
            clearData();
            boolean isSuccess = commandExecutor.isRetract(activity, usbConnection, endpointOne, endpointTwo, endpointThree, txtCommunicationProcess);
            getColorCode(isSuccess, button);
        }
    }

    private void openShutter(Button button) {
        if (isProcessCompleted) {
            clearData();
            boolean isSuccess = commandExecutor.isOpenShutter(activity, usbConnection, endpointOne, endpointTwo, endpointThree, txtCommunicationProcess);
            getColorCode(isSuccess, button);
        }
    }

    private void cashCount(Button button) {
        if (isProcessCompleted) {
            clearData();
            boolean isSuccess = commandExecutor.isCashCount(activity, usbConnection, endpointOne, endpointTwo, endpointThree, txtCommunicationProcess);
            getColorCode(isSuccess, button);
        }
    }

    private void rollBack(Button button) {
        if (isProcessCompleted) {
            clearData();
            boolean isSuccess = commandExecutor.isCashRollback(activity, usbConnection, endpointOne, endpointTwo, endpointThree, txtCommunicationProcess);
            getColorCode(isSuccess, button);
        }
    }

    private void storeMoney(Button button) {
        if (isProcessCompleted) {
            clearData();
            boolean isSuccess = commandExecutor.isStoreMoney(activity, usbConnection, endpointOne, endpointTwo, endpointThree, txtCommunicationProcess);
            getColorCode(isSuccess, button);
        }
    }

    private void closeShutter(Button button) {
        if (isProcessCompleted) {
            clearData();
            boolean isSuccess = commandExecutor.isCloseShutter(activity, usbConnection, endpointOne, endpointTwo, endpointThree, txtCommunicationProcess);
            getColorCode(isSuccess, button);
        }
    }

    private void quickReset(Button button) {
        if (isProcessCompleted) {
            clearData();
            boolean isSuccess = commandExecutor.isResetQuick(activity, usbConnection, endpointOne, endpointTwo, endpointThree, txtCommunicationProcess);
            getColorCode(isSuccess, button);
        }
    }

    private void reboot(Button button) {
        if (isProcessCompleted) {
            clearData();
            boolean isSuccess = commandExecutor.isReboot(activity, usbConnection, endpointOne, endpointTwo, endpointThree, txtCommunicationProcess);
            getColorCode(isSuccess, button);
        }
    }

    private void firmware(Button button) {
        if (isProcessCompleted) {
            clearData();
            boolean isSuccess = commandExecutor.isFirmware(activity, usbConnection, endpointOne, endpointTwo, endpointThree, txtCommunicationProcess);
            getColorCode(isSuccess, button);
        }
    }

    private void setDenominationCode(Button button) {
        if (isProcessCompleted) {
            clearData();
            boolean isSuccess = commandExecutor.isSetDenominationCode(activity, usbConnection, endpointOne, endpointTwo, endpointThree, txtCommunicationProcess);
            getColorCode(isSuccess, button);
        }
    }

    private void setUnitInfo(Button button) {
        if (isProcessCompleted) {
            clearData();
            boolean isSuccess = commandExecutor.isSetUnitInfo(activity, usbConnection, endpointOne, endpointTwo, endpointThree, txtCommunicationProcess);
            getColorCode(isSuccess, button);
        }
    }

    private void resetNormal(Button button) {
        if (isProcessCompleted) {
            clearData();
            boolean isSuccess = commandExecutor.isResetNormal(activity, usbConnection, endpointOne, endpointTwo, endpointThree, txtCommunicationProcess);
            getColorCode(isSuccess, button);
        }
    }

    private void getUnitInfo(Button button) {
        if (isProcessCompleted) {
            clearData();
            boolean isSuccess = commandExecutor.isGetUnitInfo(activity, usbConnection, endpointOne, endpointTwo, endpointThree, txtCommunicationProcess);
            getColorCode(isSuccess, button);
        }
    }

    private void programDownloadStart(Button button) {
        if (isProcessCompleted) {
            clearData();
            boolean isSuccess = commandExecutor.isProgramDownloadStart(activity, usbConnection, endpointOne, endpointTwo, endpointThree, txtCommunicationProcess);
            getColorCode(isSuccess, button);
        }
    }

    private void programDownloadSendData(Button button) {
        if (isProcessCompleted) {
            clearData();
            boolean isSuccess = commandExecutor.isProgramDownloadSendData(activity, usbConnection, endpointOne, endpointTwo, endpointThree, txtCommunicationProcess);
            getColorCode(isSuccess, button);
        }
    }


    private void programDownloadEnd(Button button) {
        if (isProcessCompleted) {
            clearData();
            boolean isSuccess = commandExecutor.isProgramDownloadEnd(activity, usbConnection, endpointOne, endpointTwo, endpointThree, txtCommunicationProcess);
            getColorCode(isSuccess, button);
        }
    }


    private void cancel(Button button) {
        if (isProcessCompleted) {
            clearData();
            boolean isSuccess = commandExecutor.isCancel(activity, usbConnection, endpointOne, endpointTwo, endpointThree, txtCommunicationProcess);
            getColorCode(isSuccess, button);
        }
    }

    private void getLogsData(Button button) {
        if (isProcessCompleted) {
            clearData();
            boolean isSuccess = commandExecutor.isLogsData(activity, usbConnection, endpointOne, endpointTwo, endpointThree, txtCommunicationProcess);
            getColorCode(isSuccess, button);
        }
    }


    private void getBankNoteInfo(Button button) {
        if (isProcessCompleted) {
            clearData();
            boolean isSuccess = commandExecutor.isBankNoteInfo(activity, usbConnection, endpointOne, endpointTwo, endpointThree, txtCommunicationProcess);
            getColorCode(isSuccess, button);
        }
    }

    private void getCassetteNoteInfo(Button button) {
        if (isProcessCompleted) {
            clearData();
            boolean isSuccess = commandExecutor.isCassetteNoteInfo(activity, usbConnection, endpointOne, endpointTwo, endpointThree, txtCommunicationProcess);
            getColorCode(isSuccess, button);
        }
    }

    private void clearData() {
        txtCommunicationProcess.setText("");
        txtExceptionData.setText("");
    }

    private void clearAllView() {
        finish();
        Intent intent = new Intent(this, DriverCommunicationActivity.class);
        startActivity(intent);
    }


    private void getColorCode(boolean isSuccess, Button button) {
        if (isSuccess) {
            button.setBackground(getResources().getDrawable(R.drawable.button_green_bg));
        } else {
            button.setBackground(getResources().getDrawable(R.drawable.button_red_bg));
        }
        setExceptionData();
    }

    private void setColorNormal(Button button) {
        button.setBackgroundColor(getResources().getColor(R.color.grey));
    }


    private void checkAutoPermission() {
        USBPermission usbPermission = new USBPermission();
        usbPermission.checkPermission(activity);
        if (usbPermission.isConnectionEstablished) {
            Log.e(TAG, "USB Connection Enabled");
        } else {
            Log.e(TAG, "USB Connection Denied");
        }
    }

    private void checkForExistingDevices() {

        HashMap<String, UsbDevice> deviceList = usbManager.getDeviceList();
        checkLogs("Existing Devices Size: " + deviceList.size());

        for (UsbDevice device : deviceList.values()) {

            String deviceInfo = "Device Name: " + device.getDeviceName() +
                    "\nManufacturer Name: " + device.getManufacturerName() +
                    "\nProduct Name: " + device.getProductName() +
//                    "\nSerial Number: " + device.getSerialNumber() +
                    "\nVendor ID: " + device.getVendorId() +
                    "\nProduct ID: " + device.getProductId();

            String productId = device.getProductId() + "";
            String vendorId = device.getVendorId() + "";

            if (productId.equals(DeviceDetails.PROD_ID) && vendorId.equals(DeviceDetails.VENDOR_ID)) {

                checkLogs("Device Details : " + deviceInfo);

                if (usbManager.hasPermission(device)) {
                    checkLogs("Permission Accepted ");
                    usbDevice = device;
                    usbConnection = usbManager.openDevice(usbDevice);
                    if (usbConnection != null) {
                        checkUSBCommunication(productId, vendorId);
                    } else {
                        checkLogs(productId + " " + "No Details Found");
                    }
                } else {
                    checkLogs("Permission Denied : " + productId);
                    usbManager.requestPermission(device, permissionIntent);
                    permissionRequested = true;
                }
            }
        }
    }

    private final BroadcastReceiver usbReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (ACTION_USB_PERMISSION.equals(action)) {
                synchronized (this) {
                    UsbDevice device = intent.getParcelableExtra(UsbManager.EXTRA_DEVICE);
                    if (intent.getBooleanExtra(UsbManager.EXTRA_PERMISSION_GRANTED, false)) {

                        checkLogs("Permission allow for USB device: " + device.getProductId());

                        if (device != null) {
                            usbDevice = device;
                            String deviceInfo = "Device Name: " + device.getDeviceName() +
                                    "\nManufacturer Name: " + device.getManufacturerName() +
                                    "\nProduct Name: " + device.getProductName() +
//                                    "\nSerial Number: " + device.getSerialNumber() +
                                    "\nVendor ID: " + device.getVendorId() +
                                    "\nProduct ID: " + device.getProductId();

                            String productId = device.getProductId() + "";
                            String vendorId = device.getVendorId() + "";

                            checkLogs("Device Details : " + deviceInfo);

                            usbConnection = usbManager.openDevice(usbDevice);
                            if (usbConnection != null) {
                                checkUSBCommunication(productId, vendorId);
                            } else {
                                checkLogs(productId + " " + "No details found");
                            }
                        }
                    } else {
                        checkLogs("Permission denied for USB device: " + device);
                    }
                }
            } else if (UsbManager.ACTION_USB_DEVICE_ATTACHED.equals(action)) {
                checkLogs("USB Device Attached");
                UsbDevice device = intent.getParcelableExtra(UsbManager.EXTRA_DEVICE);
                if (device != null && !permissionRequested) {
                    usbManager.requestPermission(device, permissionIntent);
                }
            } else if (UsbManager.ACTION_USB_DEVICE_DETACHED.equals(action)) {
                checkLogs("USB Device Detached");
                UsbDevice device = intent.getParcelableExtra(UsbManager.EXTRA_DEVICE);
                if (device != null && usbDevice != null && usbDevice.equals(device)) {
                    usbDevice = null;
                    usbConnection.releaseInterface(usbInterface);
                    usbConnection.close();
                }
            }
        }
    };


    private void checkUSBCommunication(String productId, String vendorId) {
        if (productId.equals(DeviceDetails.PROD_ID) && vendorId.equals(DeviceDetails.VENDOR_ID)) {
            checkCommunicationSequence();
        }
    }

    private void checkCommunicationSequence() {

        checkLogs("USB Device Details : " + usbDevice.toString());
        checkLogs("USB Interface Count : " + usbDevice.getInterfaceCount());

        int connectivityIndex = 0;
        UsbInterface interfacePoint = usbDevice.getInterface(connectivityIndex);
        checkLogs("USB Interface Details : " + interfacePoint.toString());
        checkLogs("USB Endpoint Count : " + interfacePoint.getEndpointCount());

        if (interfacePoint.getEndpointCount() > 0) {

            usbInterface = interfacePoint;
            usbConnection.claimInterface(usbInterface, true);
            Log.e(TAG, "ConfigurationCount: " + usbDevice.getConfigurationCount());
            UsbConfiguration usbConfiguration = usbDevice.getConfiguration(connectivityIndex);
            Log.e(TAG, "UsbConfiguration Details: " + usbConfiguration.toString());

            if (usbConnection.claimInterface(usbInterface, true)) {

                if (usbConfiguration != null) {
                    //Set Address
//                    setDeviceAddress();
                    //Set Configuration
                    usbConnection.setConfiguration(usbConfiguration);
                    usbConnection.setInterface(usbInterface);
                    Log.e(TAG, "ConfigurationSet Successfully");
                }

                endpointOne = interfacePoint.getEndpoint(0);
                endpointTwo = interfacePoint.getEndpoint(1);
                endpointThree = interfacePoint.getEndpoint(2);

                checkLogs("Endpoint_1 Address : " + endpointOne.getAddress());
                checkLogs("Endpoint_2 Address : " + endpointTwo.getAddress());
                checkLogs("Endpoint_3 Address : " + endpointThree.getAddress());

                isProcessCompleted = true;

            }

        }

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(usbReceiver);
        if (usbConnection != null) {
            usbConnection.releaseInterface(usbInterface);
            usbConnection.close();
        }
        isProcessCompleted = false;
    }


    StringBuilder builder = new StringBuilder();

    private void checkLogs(String value) {
        Log.e(TAG, value);
        builder.append(value + "\n\n");
        txtCommunicationProcess.setText(builder.toString());
    }


    //TODO - Permission Check
    private boolean checkStoragePermission() {
        boolean isGranted = false;
        String[] permissions = null;

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.S_V2) {
            permissions = new String[]{
                    Manifest.permission.READ_MEDIA_VIDEO,
                    Manifest.permission.READ_MEDIA_IMAGES
            };
        } else {
            permissions = new String[]{
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
            };
        }

        // Check if all permissions are granted
        boolean allPermissionsGranted = true;
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(activity, permission) != PackageManager.PERMISSION_GRANTED) {
                allPermissionsGranted = false;
                break;
            }
        }

        if (!allPermissionsGranted) {
            showPermissionExplanationDialog(permissions);
        } else {
            isGranted = true;
        }

        return isGranted;
    }

    private void showPermissionExplanationDialog(String[] permissions) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Permission Needed")
                .setMessage("This permission is needed to access files on your device.")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ActivityCompat.requestPermissions(activity, permissions, REQUEST_PERMISSION_CODE);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Optionally, handle 'Cancel' button action
                    }
                })
                .show();
    }


    private void showPermissionDeniedDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Permission Denied")
                .setMessage("Without this permission, the app cannot function properly. Please grant the permission in app settings.")
                .setPositiveButton("Go to Settings", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Open app settings
                        openAppSettings();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Optionally, handle 'Cancel' button action
                    }
                })
                .show();
    }

    private void openAppSettings() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", getPackageName(), null);
        intent.setData(uri);
        startActivity(intent);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_PERMISSION_CODE: // Replace with your request code
                // Check if all permissions are granted
                boolean allPermissionsGranted = true;
                for (int result : grantResults) {
                    if (result != PackageManager.PERMISSION_GRANTED) {
                        allPermissionsGranted = false;
                        break;
                    }
                }

                if (!allPermissionsGranted) {
                    showPermissionDeniedDialog();
                }

                break;
        }
    }

    public void checkProgramDownloadFileData() {

        String folderPath = getStoragePath(storageType.External_Storage_Directory);
        String filePath = folderPath + "/HPY-20240702/ZXR60INT000003";

        String file_ZXR60 = filePath + "/ZXR60.INI";
        String file_ZXR60INT = filePath + "/ZXR60INT.pdl";

        //TODO - Check control id
        String controlID = getControlVersion(file_ZXR60);
        ApplicationData.control_ID = controlID;
        AppLogs.generate("Control_ID : " + ApplicationData.control_ID);
        if (!TextUtils.isEmpty(controlID)) {
            //TODO - Check pdl data
            String pdlContent = readPDLFile(file_ZXR60INT);
            if (!TextUtils.isEmpty(pdlContent)) {
                String pdlBinaryString = convertToBinary(pdlContent).replaceAll(" ", "");
                AppLogs.generate("PDL_BinaryString : " + pdlBinaryString);
                AppLogs.generate("PDL_BinaryString_Length : " + pdlBinaryString.length());
                sendProgramDownloadData(pdlBinaryString, pdlBinaryString.length());
            } else {
                AppLogs.generate("No data found for PDL");
            }
        } else {
            AppLogs.generate("No data found for Control ID");
        }

    }


    ValueConvertor valueConvertor = new ValueConvertor();

    public void sendProgramDownloadData(String pdlBinaryString, long length) {

        int numberSize = 7168;
        numberSize = numberSize * 2;
        long limit = length;
        long lastValue = 0;

        int startIndex = 0;

        for (int multiplier = 1; ; multiplier++) {
            long result = (long) numberSize * multiplier;

            String writingAddress = "";
            ApplicationData.writing_address = "";

            // Check if the result is greater than or equal to the limit
            if (result < limit) {
//                AppLogs.generateTAG("AppLogs", "ProgramData " + "Result " + result);
                lastValue = result;
                if (multiplier == 1) {
                    writingAddress = "0000" + "0000" + valueConvertor.decimalToHexString8(numberSize);
//                    AppLogs.generateTAG("AppLogs", "ProgramData - " + multiplier + " " + writingAddress);
                } else {
                    writingAddress = "0001" + "0000" + valueConvertor.decimalToHexString8(numberSize);
//                    AppLogs.generateTAG("AppLogs", "ProgramData - " + multiplier + " " + writingAddress);
                }

                ApplicationData.writing_address = writingAddress;

                //TODO - Read here byte*8binary for pdl size data
                if (startIndex < pdlBinaryString.length()) {
//                    int endIndex = Math.min(startIndex + numberSize*8, pdlBinaryString.length());
                    int endIndex = Math.min(startIndex + numberSize, pdlBinaryString.length());
                    String groupPDL = pdlBinaryString.substring(startIndex, endIndex);
                    startIndex += numberSize;
                    ApplicationData.pdl_data = groupPDL;
//                    AppLogs.generateTAG("AppLogs", "ProgramData " + "PDL_Group " + groupPDL);
                }

                //TODO- Send program download command
                sendProgramDownloadCommand();

            } else if (result > limit) {
                ApplicationData.writing_address = "";
                long finalValue = limit - lastValue;
                int finalNumber = (int) finalValue;
                writingAddress = "FFFF" + "0000" + valueConvertor.decimalToHexString8(finalValue);
                ApplicationData.writing_address = writingAddress;
//                AppLogs.generateTAG("AppLogs", "ProgramData - " + multiplier + " " + writingAddress);
//                AppLogs.generateTAG("AppLogs", "ProgramData " + "Final " + finalValue);

                if (startIndex < pdlBinaryString.length()) {
//                    int endIndex = Math.min(startIndex + finalNumber*8, pdlBinaryString.length());
                    int endIndex = Math.min(startIndex + finalNumber, pdlBinaryString.length());
                    String groupPDL = pdlBinaryString.substring(startIndex, endIndex);
                    ApplicationData.pdl_data = groupPDL;
//                    AppLogs.generateTAG("AppLogs", "ProgramData " + "PDL_Group " + groupPDL);
                }

                //TODO- Send program download command
                sendProgramDownloadCommand();

                break;
            }


        }

    }


    public void testProgramSendDownload() {
        //00000000
        String writingAddress = "0000" + "0000" + "00000000";
        ApplicationData.writing_address = writingAddress;
//        String groupPDL = "00101110 00000000 01011010 01011000"; (Binary To HEX)
        String groupPDL = "2E005A58";
        ApplicationData.pdl_data = groupPDL;
        boolean isSuccess = sendProgramDownloadCommand();
        if (isSuccess) {
            AppLogs.generate("SUCCESS SENS PROGRAM 1");
            testProgramSendDownload2();
        } else {
            AppLogs.generate("NO SUCCESS SENS PROGRAM 1");
        }
    }

    public void testProgramSendDownload2() {
        //00000000
        String writingAddress = "0001" + "0000" + "00000010";
        ApplicationData.writing_address = writingAddress;
//        String groupPDL = "01010010001101100011000001001001";
        String groupPDL = "52363049";
        ApplicationData.pdl_data = groupPDL;
        boolean isSuccess = sendProgramDownloadCommand();
        if (isSuccess) {
            AppLogs.generate("SUCCESS SENS PROGRAM 2");
        } else {
            AppLogs.generate("NO SUCCESS SENS PROGRAM 2");
        }
    }

    public boolean sendProgramDownloadCommand() {
        clearData();
        boolean isSuccess = commandExecutor.isProgramDownloadSendData(activity, usbConnection, endpointOne, endpointTwo, endpointThree, txtCommunicationProcess);
        return isSuccess;
    }

    private static String readPDLFile(String filePath) {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
        return content.toString();
    }

    private static String convertToBinary(String pdlContent) {
        StringBuilder binaryString = new StringBuilder();
        for (char c : pdlContent.toCharArray()) {
            String binary = String.format("%8s", Integer.toBinaryString(c)).replace(' ', '0');
            binaryString.append(binary).append(" ");
        }
        return binaryString.toString();
    }

    public String getControlVersion(String filePath) {
        String ctlIdValue = "";

        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line).append("\n");
            }
            reader.close();

            //TODO - File Content
            String fileContent = stringBuilder.toString();
            Log.e("TAG", "ProgramDownload_ZXR60_Data " + fileContent);
            String ctlIdLine = findCtlIdLine(fileContent);
            if (ctlIdLine != null) {
                ctlIdValue = extractCtlIdValue(ctlIdLine);
                if (ctlIdValue == null) {
                    AppLogs.generate("Unable to extract CTL_ID value.");
                }
            } else {
                AppLogs.generate("CTL_ID line not found.");
            }

        } catch (IOException e) {
            AppLogs.generate("ProgramDownload_Exp : " + e.getMessage());
        }

        return ctlIdValue;
    }

    public String getPDLBinaryData(String fileData) {
        String returnValue = "";

        return returnValue;
    }

    private static String findCtlIdLine(String firmwareString) {
        String[] lines = firmwareString.split("\n");
        for (String line : lines) {
            if (line.startsWith("CTL_ID")) {
                return line;
            }
        }
        return null;
    }

    private static String extractCtlIdValue(String ctlIdLine) {
        int index = ctlIdLine.indexOf('=');
        if (index != -1) {
            String valueString = ctlIdLine.substring(index + 1).trim();
            // Check if the value starts with "0x" and remove it
            if (valueString.startsWith("0x")) {
                valueString = valueString.substring(2); // Remove "0x"
            }
            return valueString;
        }
        return null;
    }

    private String getStoragePath(String type) {
        String storagePath = "";
        if (type.equals(storageType.External_Storage_Directory)) {
            File externalStorageDirectory = Environment.getExternalStorageDirectory();
            storagePath = externalStorageDirectory.getAbsolutePath();
        } else if (type.equals(storageType.External_Storage_Public_Directory)) {
            File publicDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
            storagePath = publicDirectory.getAbsolutePath();
        } else if (type.equals(storageType.Internal_Storage_Directory)) {
            File filesDir = getFilesDir();
            storagePath = filesDir.getAbsolutePath();
        }
        return storagePath;
    }

}
