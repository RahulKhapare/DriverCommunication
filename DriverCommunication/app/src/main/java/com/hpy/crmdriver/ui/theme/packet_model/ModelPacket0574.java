package com.hpy.crmdriver.ui.theme.packet_model;

import com.hpy.crmdriver.ui.theme.util.AppLogs;

public class ModelPacket0574 {

    String packetId;
    String length;
    String roomNo;
    String roomOperation;
    String banknoteInfo;

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

    public String getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(String roomNo) {
        this.roomNo = roomNo;
    }

    public String getRoomOperation() {
        return roomOperation;
    }

    public void setRoomOperation(String roomOperation) {
        this.roomOperation = roomOperation;
    }

    public String getBanknoteInfo() {
        return banknoteInfo;
    }

    public void setBanknoteInfo(String banknoteInfo) {
        this.banknoteInfo = banknoteInfo;
    }

    public String generatePacket() {
        return packetId + length + roomNo + roomOperation + banknoteInfo;
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
