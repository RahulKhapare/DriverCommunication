package com.hpy.crmdriver.ui.theme.packet_model;

import com.hpy.crmdriver.ui.theme.util.AppLogs;

public class ModelPacket0541 {

    String packetId;
    String length;

    String denominationCode_1;
    String reserved_1;
    String noDispenseNote_1;

    String denominationCode_2;
    String reserved_2;
    String noDispenseNote_2;

    String denominationCode_3;
    String reserved_3;
    String noDispenseNote_3;

    String denominationCode_4;
    String reserved_4;
    String noDispenseNote_4;

    String denominationCode_5;
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

    public String getDenominationCode_2() {
        return denominationCode_2;
    }

    public void setDenominationCode_2(String denominationCode_2) {
        this.denominationCode_2 = denominationCode_2;
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

    public String getDenominationCode_3() {
        return denominationCode_3;
    }

    public void setDenominationCode_3(String denominationCode_3) {
        this.denominationCode_3 = denominationCode_3;
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

    public String getDenominationCode_4() {
        return denominationCode_4;
    }

    public void setDenominationCode_4(String denominationCode_4) {
        this.denominationCode_4 = denominationCode_4;
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

    public String getDenominationCode_5() {
        return denominationCode_5;
    }

    public void setDenominationCode_5(String denominationCode_5) {
        this.denominationCode_5 = denominationCode_5;
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
                denominationCode_1 + reserved_1 + noDispenseNote_1 +
                denominationCode_2 + reserved_2 + noDispenseNote_2 +
                denominationCode_3 + reserved_3 + noDispenseNote_3 +
                denominationCode_4 + reserved_4 + noDispenseNote_4 +
                denominationCode_5 + reserved_5 + noDispenseNote_5 +
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
