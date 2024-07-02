package com.hpy.crmdriver.ui.theme.packet_model;

import com.hpy.crmdriver.ui.theme.util.AppLogs;

public class ModelPacket0540 {

    String packetId;
    String length;
    String roomNo_1;
    String reserved_1;
    String noDispenseNote_1;

    String roomNo_2;
    String reserved_2;
    String noDispenseNote_2;

    String roomNo_3;
    String reserved_3;
    String noDispenseNote_3;

    String roomNo_4;
    String reserved_4;
    String noDispenseNote_4;

    String roomNo_5;
    String reserved_5;
    String noDispenseNote_5;

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

    public String getRoomNo_2() {
        return roomNo_2;
    }

    public void setRoomNo_2(String roomNo_2) {
        this.roomNo_2 = roomNo_2;
    }

    public String getReserved_2() {
        return reserved_2;
    }

    public void setReserved_2(String reserved_2) {
        this.reserved_2 = reserved_2;
    }

    public String getNoDispenseNote_2() {
        return noDispenseNote_2;
    }

    public void setNoDispenseNote_2(String noDispenseNote_2) {
        this.noDispenseNote_2 = noDispenseNote_2;
    }

    public String getRoomNo_3() {
        return roomNo_3;
    }

    public void setRoomNo_3(String roomNo_3) {
        this.roomNo_3 = roomNo_3;
    }

    public String getReserved_3() {
        return reserved_3;
    }

    public void setReserved_3(String reserved_3) {
        this.reserved_3 = reserved_3;
    }

    public String getNoDispenseNote_3() {
        return noDispenseNote_3;
    }

    public void setNoDispenseNote_3(String noDispenseNote_3) {
        this.noDispenseNote_3 = noDispenseNote_3;
    }

    public String getRoomNo_4() {
        return roomNo_4;
    }

    public void setRoomNo_4(String roomNo_4) {
        this.roomNo_4 = roomNo_4;
    }

    public String getReserved_4() {
        return reserved_4;
    }

    public void setReserved_4(String reserved_4) {
        this.reserved_4 = reserved_4;
    }

    public String getNoDispenseNote_4() {
        return noDispenseNote_4;
    }

    public void setNoDispenseNote_4(String noDispenseNote_4) {
        this.noDispenseNote_4 = noDispenseNote_4;
    }

    public String getRoomNo_5() {
        return roomNo_5;
    }

    public void setRoomNo_5(String roomNo_5) {
        this.roomNo_5 = roomNo_5;
    }

    public String getReserved_5() {
        return reserved_5;
    }

    public void setReserved_5(String reserved_5) {
        this.reserved_5 = reserved_5;
    }

    public String getNoDispenseNote_5() {
        return noDispenseNote_5;
    }

    public void setNoDispenseNote_5(String noDispenseNote_5) {
        this.noDispenseNote_5 = noDispenseNote_5;
    }

    public String getReserved() {
        return reserved;
    }

    public void setReserved(String reserved) {
        this.reserved = reserved;
    }

    public String generatePacket() {
        return packetId + length +
                //FOR XR - H
                roomNo_1 + reserved_1 + noDispenseNote_1 +
                roomNo_2 + reserved_2 + noDispenseNote_2 +
                roomNo_3 + reserved_3 + noDispenseNote_3 +
                roomNo_4 + reserved_4 + noDispenseNote_4 +
                roomNo_5 + reserved_5 + noDispenseNote_5 +
                //FOR XR - V
                "0000000000000000000000000000000000000000" +
                reserved;
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
