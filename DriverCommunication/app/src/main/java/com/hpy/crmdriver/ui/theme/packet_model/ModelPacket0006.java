package com.hpy.crmdriver.ui.theme.packet_model;

import com.hpy.crmdriver.ui.theme.util.AppLogs;

public class ModelPacket0006 {

    String packetId;
    String length;
    String pdlData;

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

    public String getPdlData() {
        return pdlData;
    }

    public void setPdlData(String pdlData) {
        this.pdlData = pdlData;
    }

    public String generatePacket() {
        return packetId + length + pdlData;
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
