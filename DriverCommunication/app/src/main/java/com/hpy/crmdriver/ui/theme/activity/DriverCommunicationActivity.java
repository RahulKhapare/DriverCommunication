package com.hpy.crmdriver.ui.theme.activity;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.usb.UsbConfiguration;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbDeviceConnection;
import android.hardware.usb.UsbEndpoint;
import android.hardware.usb.UsbInterface;
import android.hardware.usb.UsbManager;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;

import com.hpy.crmdriver.MainActivity;
import com.hpy.crmdriver.R;
import com.hpy.crmdriver.ui.theme.cmd_processor.CommandExecutor;
import com.hpy.crmdriver.ui.theme.data.Packet;
import com.hpy.crmdriver.ui.theme.packet_model.ModelPacket0081;
import com.hpy.crmdriver.ui.theme.packet_model.ModelPacket008E;
import com.hpy.crmdriver.ui.theme.session.SessionModel;
import com.hpy.crmdriver.ui.theme.util.AppConfig;
import com.hpy.crmdriver.ui.theme.util.Click;
import com.hpy.crmdriver.ui.theme.util.DeviceDetails;
import com.hpy.crmdriver.ui.theme.util.SessionData;

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
    private Button btnProgramDownload;
    private Button btnLogsData;
    private Button btnCancel;
    private Button btnGetBankNotesInfo;
    private Button btnGetCassetteInfo;
    private ToggleButton btnToggle;

    private AppConfig appConfig = new AppConfig();

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

        //Others
        btnProgramDownload = findViewById(R.id.btnProgramDownload);
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
                setAnimation(btnResetQuick);
                quickReset(btnResetQuick);
            }
        });

        btnReboot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Click.preventTwoClick(v);
                setAnimation(btnReboot);
                reboot(btnReboot);
            }
        });

        btnOpenShutterNormal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Click.preventTwoClick(v);
                setAnimation(btnOpenShutterNormal);
                openShutter(btnOpenShutterNormal);
            }
        });
        btnCloseShutterNormal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Click.preventTwoClick(v);
                setAnimation(btnCloseShutterNormal);
                closeShutter(btnCloseShutterNormal);
            }
        });

        btnClearAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Click.preventTwoClick(v);
                setAnimation(btnClearAll);
                clearAllView();
            }
        });

        btnGetStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Click.preventTwoClick(v);
                setAnimation(btnGetStatus);
                getStatus(btnGetStatus);
            }
        });

        btnGetUnitInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Click.preventTwoClick(v);
                setAnimation(btnGetUnitInfo);
                getUnitInfo(btnGetUnitInfo);
            }
        });

        btnClearSession.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Click.preventTwoClick(v);
                setAnimation(btnClearSession);
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
                setAnimation(btnFirmware);
                firmware(btnFirmware);
            }
        });

        btnSetDenominationCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Click.preventTwoClick(v);
                setAnimation(btnSetDenominationCode);
                setDenominationCode(btnSetDenominationCode);
            }
        });
        btnSetUnitInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Click.preventTwoClick(v);
                setAnimation(btnSetUnitInfo);
                setUnitInfo(btnSetUnitInfo);
            }
        });

        btnResetNormal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Click.preventTwoClick(v);
                setAnimation(btnResetNormal);
                resetNormal(btnResetNormal);
            }
        });

        btnPrepareTransactionDeposit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Click.preventTwoClick(v);
                setAnimation(btnPrepareTransactionDeposit);
                prepareTransaction(btnPrepareTransactionDeposit);
            }
        });

        btnOpenShutterDeposit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Click.preventTwoClick(v);
                setAnimation(btnOpenShutterDeposit);
                openShutter(btnOpenShutterDeposit);
            }
        });

        btnCashCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Click.preventTwoClick(v);
                setAnimation(btnCashCount);
                cashCount(btnCashCount);
            }
        });

        btnCashRollBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Click.preventTwoClick(v);
                setAnimation(btnCashRollBack);
                rollBack(btnCashRollBack);
            }
        });

        btnStoreMoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Click.preventTwoClick(v);
                setAnimation(btnStoreMoney);
                storeMoney(btnStoreMoney);
            }
        });

        btnPrepareNextTransactionDeposit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Click.preventTwoClick(v);
                setAnimation(btnPrepareNextTransactionDeposit);
                nextTransaction(btnPrepareNextTransactionDeposit);
            }
        });

        btnPrepareTransactionDispense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Click.preventTwoClick(v);
                setAnimation(btnPrepareTransactionDispense);
                prepareTransaction(btnPrepareTransactionDispense);
            }
        });

        btnDispense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Click.preventTwoClick(v);
                setAnimation(btnDispense);
                dispense(btnDispense);
            }
        });

        btnRetract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Click.preventTwoClick(v);
                setAnimation(btnRetract);
                retract(btnRetract);
            }
        });

        btnOpenShutterDispense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Click.preventTwoClick(v);
                setAnimation(btnOpenShutterDispense);
                openShutter(btnOpenShutterDispense);
            }
        });

        btnCloseShutterDispense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Click.preventTwoClick(v);
                setAnimation(btnCloseShutterDispense);
                closeShutter(btnCloseShutterDispense);
            }
        });

        btnPrepareNextTransactionDispense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Click.preventTwoClick(v);
                setAnimation(btnPrepareNextTransactionDispense);
                nextTransaction(btnPrepareNextTransactionDispense);
            }
        });

        btnProgramDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Click.preventTwoClick(v);
                setAnimation(btnProgramDownload);
                programDownload(btnProgramDownload);
            }
        });

        btnLogsData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Click.preventTwoClick(v);
                setAnimation(btnLogsData);
                getLogsData(btnLogsData);
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Click.preventTwoClick(v);
                setAnimation(btnCancel);
                cancel(btnCancel);
            }
        });

        btnGetBankNotesInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Click.preventTwoClick(v);
                setAnimation(btnGetBankNotesInfo);
                getBankNoteInfo(btnGetBankNotesInfo);
            }
        });

        btnGetCassetteInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Click.preventTwoClick(v);
                setAnimation(btnGetCassetteInfo);
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
            txtExceptionData.setText("MODEL 0081 CODE : " + model0081.getResponseCode());
        }

        if (model008E != null) {
//            txtExceptionData.setText(txtExceptionData.getText().toString() + "\n\n" +
//                    "MODEL 008E : " + model008E.getPacketId() + model008E.getLength() + model008E.getErrorCode() +
//                    model008E.getReserved() + model008E.getErrorCassette() + model008E.getUnitStatus()
//                    + model008E.getRecoveryCode() + model008E.getPositionCode());
            txtExceptionData.setText(txtExceptionData.getText().toString() + "\n\n" +
                    "MODEL 008E CODE : " + model008E.getErrorCode());
        }
    }


    private void getStatus(Button button) {
        if (isProcessCompleted) {
            txtCommunicationProcess.setText("");
            boolean isSuccess = commandExecutor.isGetStatus(activity, usbConnection, endpointOne, endpointTwo, endpointThree, txtCommunicationProcess);
            getColorCode(isSuccess, button);
        }
    }

    private void prepareTransaction(Button button) {
        if (isProcessCompleted) {
            txtCommunicationProcess.setText("");
            boolean isSuccess = commandExecutor.isPrepareTransaction(activity, usbConnection, endpointOne, endpointTwo, endpointThree, txtCommunicationProcess);
            getColorCode(isSuccess, button);
        }
    }


    private void nextTransaction(Button button) {
        if (isProcessCompleted) {
            txtCommunicationProcess.setText("");
            boolean isSuccess = commandExecutor.isPrepareNextTransaction(activity, usbConnection, endpointOne, endpointTwo, endpointThree, txtCommunicationProcess);
            getColorCode(isSuccess, button);
        }
    }

    private void dispense(Button button) {
        if (isProcessCompleted) {
            txtCommunicationProcess.setText("");
            boolean isSuccess = commandExecutor.isDispense(activity, usbConnection, endpointOne, endpointTwo, endpointThree, txtCommunicationProcess);
            getColorCode(isSuccess, button);
        }
    }

    private void retract(Button button) {
        if (isProcessCompleted) {
            txtCommunicationProcess.setText("");
            boolean isSuccess = commandExecutor.isRetract(activity, usbConnection, endpointOne, endpointTwo, endpointThree, txtCommunicationProcess);
            getColorCode(isSuccess, button);
        }
    }

    private void openShutter(Button button) {
        if (isProcessCompleted) {
            txtCommunicationProcess.setText("");
            boolean isSuccess = commandExecutor.isOpenShutter(activity, usbConnection, endpointOne, endpointTwo, endpointThree, txtCommunicationProcess);
            getColorCode(isSuccess, button);
        }
    }

    private void cashCount(Button button) {
        if (isProcessCompleted) {
            txtCommunicationProcess.setText("");
            boolean isSuccess = commandExecutor.isCashCount(activity, usbConnection, endpointOne, endpointTwo, endpointThree, txtCommunicationProcess);
            getColorCode(isSuccess, button);
        }
    }

    private void rollBack(Button button) {
        if (isProcessCompleted) {
            txtCommunicationProcess.setText("");
            boolean isSuccess = commandExecutor.isCashRollback(activity, usbConnection, endpointOne, endpointTwo, endpointThree, txtCommunicationProcess);
            getColorCode(isSuccess, button);
        }
    }

    private void storeMoney(Button button) {
        if (isProcessCompleted) {
            txtCommunicationProcess.setText("");
            boolean isSuccess = commandExecutor.isStoreMoney(activity, usbConnection, endpointOne, endpointTwo, endpointThree, txtCommunicationProcess);
            getColorCode(isSuccess, button);
        }
    }

    private void closeShutter(Button button) {
        if (isProcessCompleted) {
            txtCommunicationProcess.setText("");
            boolean isSuccess = commandExecutor.isCloseShutter(activity, usbConnection, endpointOne, endpointTwo, endpointThree, txtCommunicationProcess);
            getColorCode(isSuccess, button);
        }
    }

    private void quickReset(Button button) {
        if (isProcessCompleted) {
            txtCommunicationProcess.setText("");
            boolean isSuccess = commandExecutor.isResetQuick(activity, usbConnection, endpointOne, endpointTwo, endpointThree, txtCommunicationProcess);
            getColorCode(isSuccess, button);
        }
    }

    private void reboot(Button button) {
        if (isProcessCompleted) {
            txtCommunicationProcess.setText("");
            boolean isSuccess = commandExecutor.isReboot(activity, usbConnection, endpointOne, endpointTwo, endpointThree, txtCommunicationProcess);
            getColorCode(isSuccess, button);
        }
    }

    private void firmware(Button button) {
        if (isProcessCompleted) {
            txtCommunicationProcess.setText("");
            boolean isSuccess = commandExecutor.isFirmware(activity, usbConnection, endpointOne, endpointTwo, endpointThree, txtCommunicationProcess);
            getColorCode(isSuccess, button);
        }
    }

    private void setDenominationCode(Button button) {
        if (isProcessCompleted) {
            txtCommunicationProcess.setText("");
            boolean isSuccess = commandExecutor.isSetDenominationCode(activity, usbConnection, endpointOne, endpointTwo, endpointThree, txtCommunicationProcess);
            getColorCode(isSuccess, button);
        }
    }

    private void setUnitInfo(Button button) {
        if (isProcessCompleted) {
            txtCommunicationProcess.setText("");
            boolean isSuccess = commandExecutor.isSetUnitInfo(activity, usbConnection, endpointOne, endpointTwo, endpointThree, txtCommunicationProcess);
            getColorCode(isSuccess, button);
        }
    }

    private void resetNormal(Button button) {
        if (isProcessCompleted) {
            txtCommunicationProcess.setText("");
            boolean isSuccess = commandExecutor.isResetNormal(activity, usbConnection, endpointOne, endpointTwo, endpointThree, txtCommunicationProcess);
            getColorCode(isSuccess, button);
        }
    }

    private void getUnitInfo(Button button) {
        if (isProcessCompleted) {
            txtCommunicationProcess.setText("");
            boolean isSuccess = commandExecutor.isGetUnitInfo(activity, usbConnection, endpointOne, endpointTwo, endpointThree, txtCommunicationProcess);
            getColorCode(isSuccess, button);
        }
    }

    private void programDownload(Button button) {
        if (isProcessCompleted) {
            txtCommunicationProcess.setText("");
            Toast.makeText(activity, "Under process, don't use.", Toast.LENGTH_SHORT).show();
        }
    }

    private void cancel(Button button) {
        if (isProcessCompleted) {
            txtCommunicationProcess.setText("");
            boolean isSuccess = commandExecutor.isCancel(activity, usbConnection, endpointOne, endpointTwo, endpointThree, txtCommunicationProcess);
            getColorCode(isSuccess, button);
        }
    }

    private void getLogsData(Button button) {
        if (isProcessCompleted) {
            txtCommunicationProcess.setText("");
            boolean isSuccess = commandExecutor.isLogsData(activity, usbConnection, endpointOne, endpointTwo, endpointThree, txtCommunicationProcess);
            getColorCode(isSuccess, button);
        }
    }


    private void getBankNoteInfo(Button button) {
        if (isProcessCompleted) {
            txtCommunicationProcess.setText("");
            boolean isSuccess = commandExecutor.isBankNoteInfo(activity, usbConnection, endpointOne, endpointTwo, endpointThree, txtCommunicationProcess);
            getColorCode(isSuccess, button);
        }
    }

    private void getCassetteNoteInfo(Button button) {
        if (isProcessCompleted) {
            txtCommunicationProcess.setText("");
            boolean isSuccess = commandExecutor.isCassetteNoteInfo(activity, usbConnection, endpointOne, endpointTwo, endpointThree, txtCommunicationProcess);
            getColorCode(isSuccess, button);
        }
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

    private void setAnimation(Button button) {
//        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.button_click_animation);
//        button.startAnimation(animation);
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

//                sendCommandData();

            }

        }

    }

    private boolean setDeviceAddress() {
        boolean returnValue = false;
        int length = usbConnection.controlTransfer(0x00, 0x05, 0x0001,
                0x0000, null, 0x0000, TIMEOUT_10);
        if (length > 0) {
            checkLogs("Address Set Successfully");
            returnValue = true;
        }
        return returnValue;
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

}
