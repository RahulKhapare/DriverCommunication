package com.hpy.crmdriver.ui.theme.packet_model;

import com.hpy.crmdriver.ui.theme.util.AppLogs;

public class ModelPacket0550 {

    String packetId;
    String length;
    String reserved_1;
    String input_1;
    String input_2;
    String input_3;
    String input_4;
    String input_5;
    String input_6;
    String input_7;
    String input_8;
    String input_9;
    String input_10;
    String reserved_2;
    String urjb_1;
    String urjb_2;
    String reserved_3;

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

    public String getReserved_1() {
        return reserved_1;
    }

    public void setReserved_1(String reserved_1) {
        this.reserved_1 = reserved_1;
    }

    public String getInput_1() {
        return input_1;
    }

    public void setInput_1(String input_1) {
        this.input_1 = input_1;
    }

    public String getInput_2() {
        return input_2;
    }

    public void setInput_2(String input_2) {
        this.input_2 = input_2;
    }

    public String getInput_3() {
        return input_3;
    }

    public void setInput_3(String input_3) {
        this.input_3 = input_3;
    }

    public String getInput_4() {
        return input_4;
    }

    public void setInput_4(String input_4) {
        this.input_4 = input_4;
    }

    public String getInput_5() {
        return input_5;
    }

    public void setInput_5(String input_5) {
        this.input_5 = input_5;
    }

    public String getInput_6() {
        return input_6;
    }

    public void setInput_6(String input_6) {
        this.input_6 = input_6;
    }

    public String getInput_7() {
        return input_7;
    }

    public void setInput_7(String input_7) {
        this.input_7 = input_7;
    }

    public String getInput_8() {
        return input_8;
    }

    public void setInput_8(String input_8) {
        this.input_8 = input_8;
    }

    public String getInput_9() {
        return input_9;
    }

    public void setInput_9(String input_9) {
        this.input_9 = input_9;
    }

    public String getInput_10() {
        return input_10;
    }

    public void setInput_10(String input_10) {
        this.input_10 = input_10;
    }

    public String getReserved_2() {
        return reserved_2;
    }

    public void setReserved_2(String reserved_2) {
        this.reserved_2 = reserved_2;
    }

    public String getUrjb_1() {
        return urjb_1;
    }

    public void setUrjb_1(String urjb_1) {
        this.urjb_1 = urjb_1;
    }

    public String getUrjb_2() {
        return urjb_2;
    }

    public void setUrjb_2(String urjb_2) {
        this.urjb_2 = urjb_2;
    }

    public String getReserved_3() {
        return reserved_3;
    }

    public void setReserved_3(String reserved_3) {
        this.reserved_3 = reserved_3;
    }

    public String generatePacket() {
        return packetId + length + reserved_1 +
                input_1 + input_2 + input_3 + input_4 + input_5 + input_6 + input_7 + input_8 + input_9 + input_10 +
                reserved_2 + urjb_1 + urjb_2 + reserved_3;
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
