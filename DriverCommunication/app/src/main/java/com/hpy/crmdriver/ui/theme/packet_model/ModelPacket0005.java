package com.hpy.crmdriver.ui.theme.packet_model;

import com.hpy.crmdriver.ui.theme.util.AppLogs;

public class ModelPacket0005 {

    String packetId;
    String length;
    String controlId;
    String pdlBlockType;
    String reserved;
    String writingAddress;

    public String getPacketId() {
        return packetId;
    }

    public void setPacketId(String packetId) {
        this.packetId = packetId;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String packetLength) {
        this.length = packetLength;
    }

    public String getControlId() {
        return controlId;
    }

    public void setControlId(String controlId) {
        this.controlId = controlId;
    }

    public String getPdlBlockType() {
        return pdlBlockType;
    }

    public void setPdlBlockType(String pdlBlockType) {
        this.pdlBlockType = pdlBlockType;
    }

    public String getReserved() {
        return reserved;
    }

    public void setReserved(String reserved) {
        this.reserved = reserved;
    }

    public String getWritingAddress() {
        return writingAddress;
    }

    public void setWritingAddress(String writingAddress) {
        this.writingAddress = writingAddress;
    }

    public String generatePacket() {
        return packetId + length + controlId + pdlBlockType + reserved + writingAddress;
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
