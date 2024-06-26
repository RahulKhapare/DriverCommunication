package com.hpy.crmdriver.ui.theme.packet_model;

import com.hpy.crmdriver.ui.theme.util.AppLogs;

public class ModelPacket05E3 {

    String packetId;
    String length;
    String site;
    String reserved1;
    String hardwareType;
    String reserved2;
    String roomInfoA;
    String roomInfoB;
    String reserved3;

    public String getPacketId() {
        return packetId;
    }

    public void setPacketId(String packetId) {
        this.packetId = packetId;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getReserved1() {
        return reserved1;
    }

    public void setReserved1(String reserved1) {
        this.reserved1 = reserved1;
    }

    public String getHardwareType() {
        return hardwareType;
    }

    public void setHardwareType(String hardwareType) {
        this.hardwareType = hardwareType;
    }

    public String getReserved2() {
        return reserved2;
    }

    public void setReserved2(String reserved2) {
        this.reserved2 = reserved2;
    }

    public String getRoomInfoA() {
        return roomInfoA;
    }

    public void setRoomInfoA(String roomInfoA) {
        this.roomInfoA = roomInfoA;
    }

    public String getRoomInfoB() {
        return roomInfoB;
    }

    public void setRoomInfoB(String roomInfoB) {
        this.roomInfoB = roomInfoB;
    }

    public String getReserved3() {
        return reserved3;
    }

    public void setReserved3(String reserved3) {
        this.reserved3 = reserved3;
    }

    public String generatePacket() {
        return packetId + length + site + reserved1 + hardwareType +
                reserved2 + roomInfoA + roomInfoB + reserved3;
    }

    public String parsePacket(String responseData) {
        String returnValue = "";

//        convert into string then make two characters
//        convert each group into hex
//        check first 4 digits are matching with your packet Id else throw exception
//        parse data anse set value to your property;

        AppLogs.generate(returnValue);
        return returnValue;
    }

}
