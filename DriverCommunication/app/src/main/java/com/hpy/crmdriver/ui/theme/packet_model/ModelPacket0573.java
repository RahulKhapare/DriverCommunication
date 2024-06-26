package com.hpy.crmdriver.ui.theme.packet_model;

import com.hpy.crmdriver.ui.theme.util.AppLogs;

public class ModelPacket0573 {

    String packetId;
    String length;
    String site;
    String dataType;

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

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String generatePacket() {
        return packetId + length + site + dataType;
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
