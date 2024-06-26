package com.hpy.crmdriver.ui.theme.packet_model;

import com.hpy.crmdriver.ui.theme.util.AppLogs;

public class ModelPacket0529 {

    String packetId;
    String length;
    String deposit_input1;
    String deposit_input2;
    String deposit_input3;
    String deposit_input4;
    String deposit_input5;
    String deposit_input6;
    String deposit_input7;
    String deposit_input8;
    String deposit_input9;
    String deposit_input10;
    String deposit_input11;
    String deposit_reserved;

    String dispense_input1;
    String dispense_input2;
    String dispense_input3;
    String dispense_input4;
    String dispense_input5;
    String dispense_input6;
    String dispense_input7;
    String dispense_input8;
    String dispense_input9;
    String dispense_input10;
    String dispense_input11;
    String dispense_reserved;

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

    public String getDeposit_input1() {
        return deposit_input1;
    }

    public void setDeposit_input1(String deposit_input1) {
        this.deposit_input1 = deposit_input1;
    }

    public String getDeposit_input2() {
        return deposit_input2;
    }

    public void setDeposit_input2(String deposit_input2) {
        this.deposit_input2 = deposit_input2;
    }

    public String getDeposit_input3() {
        return deposit_input3;
    }

    public void setDeposit_input3(String deposit_input3) {
        this.deposit_input3 = deposit_input3;
    }

    public String getDeposit_input4() {
        return deposit_input4;
    }

    public void setDeposit_input4(String deposit_input4) {
        this.deposit_input4 = deposit_input4;
    }

    public String getDeposit_input5() {
        return deposit_input5;
    }

    public void setDeposit_input5(String deposit_input5) {
        this.deposit_input5 = deposit_input5;
    }

    public String getDeposit_input6() {
        return deposit_input6;
    }

    public void setDeposit_input6(String deposit_input6) {
        this.deposit_input6 = deposit_input6;
    }

    public String getDeposit_input7() {
        return deposit_input7;
    }

    public void setDeposit_input7(String deposit_input7) {
        this.deposit_input7 = deposit_input7;
    }

    public String getDeposit_input8() {
        return deposit_input8;
    }

    public void setDeposit_input8(String deposit_input8) {
        this.deposit_input8 = deposit_input8;
    }

    public String getDeposit_input9() {
        return deposit_input9;
    }

    public void setDeposit_input9(String deposit_input9) {
        this.deposit_input9 = deposit_input9;
    }

    public String getDeposit_input10() {
        return deposit_input10;
    }

    public void setDeposit_input10(String deposit_input10) {
        this.deposit_input10 = deposit_input10;
    }

    public String getDeposit_input11() {
        return deposit_input11;
    }

    public void setDeposit_input11(String deposit_input11) {
        this.deposit_input11 = deposit_input11;
    }

    public String getDeposit_reserved() {
        return deposit_reserved;
    }

    public void setDeposit_reserved(String deposit_reserved) {
        this.deposit_reserved = deposit_reserved;
    }

    public String getDispense_input1() {
        return dispense_input1;
    }

    public void setDispense_input1(String dispense_input1) {
        this.dispense_input1 = dispense_input1;
    }

    public String getDispense_input2() {
        return dispense_input2;
    }

    public void setDispense_input2(String dispense_input2) {
        this.dispense_input2 = dispense_input2;
    }

    public String getDispense_input3() {
        return dispense_input3;
    }

    public void setDispense_input3(String dispense_input3) {
        this.dispense_input3 = dispense_input3;
    }

    public String getDispense_input4() {
        return dispense_input4;
    }

    public void setDispense_input4(String dispense_input4) {
        this.dispense_input4 = dispense_input4;
    }

    public String getDispense_input5() {
        return dispense_input5;
    }

    public void setDispense_input5(String dispense_input5) {
        this.dispense_input5 = dispense_input5;
    }

    public String getDispense_input6() {
        return dispense_input6;
    }

    public void setDispense_input6(String dispense_input6) {
        this.dispense_input6 = dispense_input6;
    }

    public String getDispense_input7() {
        return dispense_input7;
    }

    public void setDispense_input7(String dispense_input7) {
        this.dispense_input7 = dispense_input7;
    }

    public String getDispense_input8() {
        return dispense_input8;
    }

    public void setDispense_input8(String dispense_input8) {
        this.dispense_input8 = dispense_input8;
    }

    public String getDispense_input9() {
        return dispense_input9;
    }

    public void setDispense_input9(String dispense_input9) {
        this.dispense_input9 = dispense_input9;
    }

    public String getDispense_input10() {
        return dispense_input10;
    }

    public void setDispense_input10(String dispense_input10) {
        this.dispense_input10 = dispense_input10;
    }

    public String getDispense_input11() {
        return dispense_input11;
    }

    public void setDispense_input11(String dispense_input11) {
        this.dispense_input11 = dispense_input11;
    }

    public String getDispense_reserved() {
        return dispense_reserved;
    }

    public void setDispense_reserved(String dispense_reserved) {
        this.dispense_reserved = dispense_reserved;
    }

    public String generatePacket() {
        return packetId + length +
                deposit_input1 + deposit_input2 + deposit_input3 + deposit_input4 + deposit_input5 + deposit_input6 +
                deposit_input7 + deposit_input8 + deposit_input9 + deposit_input10 + deposit_input11 + deposit_reserved +
                dispense_input1 + dispense_input2 + dispense_input3 + dispense_input4 + dispense_input5 + dispense_input6 +
                dispense_input7 + dispense_input8 + dispense_input9 + dispense_input10 + dispense_input11 + dispense_reserved
                ;
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
