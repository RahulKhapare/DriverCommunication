package com.hpy.crmdriver.ui.theme.packet_model;

import com.hpy.crmdriver.ui.theme.util.AppLogs;

public class ModelPacket0541 {

    String packetId;
    String length;
    String denominationCode_1;
    String reserved_1;
    String noDispenseNote_1;

    String denominationCode_10;
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

    public String getDenominationCode_1() {
        return denominationCode_1;
    }

    public void setDenominationCode_1(String denominationCode_1) {
        this.denominationCode_1 = denominationCode_1;
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

    public String getDenominationCode_10() {
        return denominationCode_10;
    }

    public void setDenominationCode_10(String denominationCode_10) {
        this.denominationCode_10 = denominationCode_10;
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
        return packetId + length + denominationCode_1 + reserved_1 + noDispenseNote_1 +
                denominationCode_10 + reserved_10 + noDispenseNote_10
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
