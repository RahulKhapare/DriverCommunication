package com.hpy.crmdriver.ui.theme.packet_model;

import com.hpy.crmdriver.ui.theme.util.AppLogs;

public class ModelPacket0540 {

    String packetId;
    String length;
    String roomNo_1;
    String reserved_1;
    String noDispenseNote_1;

    String roomNo_10;
    String reserved_10;
    String noDispenseNote_10;
    String reserved;

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

    public String getRoomNo_1() {
        return roomNo_1;
    }

    public void setRoomNo_1(String roomNo_1) {
        this.roomNo_1 = roomNo_1;
    }

    public String getReserved_1() {
        return reserved_1;
    }

    public void setReserved_1(String reserved_1) {
        this.reserved_1 = reserved_1;
    }

    public String getNoDispenseNote_1() {
        return noDispenseNote_1;
    }

    public void setNoDispenseNote_1(String noDispenseNote_1) {
        this.noDispenseNote_1 = noDispenseNote_1;
    }

    public String getRoomNo_10() {
        return roomNo_10;
    }

    public void setRoomNo_10(String roomNo_10) {
        this.roomNo_10 = roomNo_10;
    }

    public String getReserved_10() {
        return reserved_10;
    }

    public void setReserved_10(String reserved_10) {
        this.reserved_10 = reserved_10;
    }

    public String getNoDispenseNote_10() {
        return noDispenseNote_10;
    }

    public void setNoDispenseNote_10(String noDispenseNote_10) {
        this.noDispenseNote_10 = noDispenseNote_10;
    }

    public String getReserved() {
        return reserved;
    }

    public void setReserved(String reserved) {
        this.reserved = reserved;
    }

    public String generatePacket() {
        return packetId + length + roomNo_1 + reserved_1 + noDispenseNote_1 +
                roomNo_10 + reserved_10 + noDispenseNote_10
                + reserved;
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
