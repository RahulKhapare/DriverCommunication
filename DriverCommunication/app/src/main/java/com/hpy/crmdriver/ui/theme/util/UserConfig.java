package com.hpy.crmdriver.ui.theme.util;

public class UserConfig {

    //adb uninstall com.hpynew.moneyspotatm
    //Use to connect with device on same network
    //c:
    //cd C:\Users\sysadmin\AppData\Local\Android\Sdk\platform-tools
    //adb connect 172.20.10.5
    //adb connect 192.168.1.2

    //    TODO : Push APK File To System
    //adb root
    //adb remount
    //adb push <apkpath> /system/priv-app
    //adb install <apkpath>

//    adb push D:\AndroidProjects\ATM_Driver_SDK\app\build\outputs\apk\debug\app-debug.apk /system/priv-app
//    adb install D:\AndroidProjects\ATM_Driver_SDK\app\build\outputs\apk\debug\app-debug.apk
//    adb shell pm uninstall -k --user 0 com.hpy.atmdriver


//    adb shell dmesg
//    adb logcat -b kernel

    // DOWN - 00 20 00 00 A4 01 00 00 00 16 00 01 00 04 0D 00
    // DOWN - 00 02 00 04 19 27 06 F1 00 06 1A 06 74 91 D8 70

    // UP - 00 00 00 15 00 50 00 00 C9 FF DD 01 10 10 10 80
    // UP - 10 80 00 00 00 00 00 00 00 00 00 00 00 00 04 A7

    // DOWN - 00 20 00 00 A5 01 00 00 00 16 00 01 00 04 0D 00
    // DOWN - 00 02 00 04 8E DE 06 F1 00 06 05 52 61 14 44 5B

    // DOWN - 00 20 00 00 A6 01 00 00 00 16 00 01 00 04 0D 00
    // DOWN - 00 02 00 04 22 C3 06 f1 00 06 C4 DA 6E A6 25 7C

    // DOWN - 00 20 00 00 A7 01 00 00 00 16 00 01 00 04 0D 00
    // DOWN - 00 02 00 04 BC 97 06 F1 00 06 8F EB 6C E8 C3 57


}
