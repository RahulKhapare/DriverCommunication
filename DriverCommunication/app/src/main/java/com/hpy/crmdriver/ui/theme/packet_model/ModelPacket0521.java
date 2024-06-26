package com.hpy.crmdriver.ui.theme.packet_model;

import com.hpy.crmdriver.ui.theme.data.Packet;
import com.hpy.crmdriver.ui.theme.data.Size;
import com.hpy.crmdriver.ui.theme.util.AppLogs;
import com.hpy.crmdriver.ui.theme.util.StringHelper;

public class ModelPacket0521 {

    String packetId;
    String length;
    String status;
    String reserved1;
    String input1;
    String input2;
    String input3;
    String input4;
    String input5;
    String input6;
    String input7;
    String input8;
    String input9;
    String input10;
    String reserved2;
    String urjb1;
    String urjb2;
    String reserved3;

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReserved1() {
        return reserved1;
    }

    public void setReserved1(String reserved1) {
        this.reserved1 = reserved1;
    }

    public String getInput1() {
        return input1;
    }

    public void setInput1(String input1) {
        this.input1 = input1;
    }

    public String getInput2() {
        return input2;
    }

    public void setInput2(String input2) {
        this.input2 = input2;
    }

    public String getInput3() {
        return input3;
    }

    public void setInput3(String input3) {
        this.input3 = input3;
    }

    public String getInput4() {
        return input4;
    }

    public void setInput4(String input4) {
        this.input4 = input4;
    }

    public String getInput5() {
        return input5;
    }

    public void setInput5(String input5) {
        this.input5 = input5;
    }

    public String getInput6() {
        return input6;
    }

    public void setInput6(String input6) {
        this.input6 = input6;
    }

    public String getInput7() {
        return input7;
    }

    public void setInput7(String input7) {
        this.input7 = input7;
    }

    public String getInput8() {
        return input8;
    }

    public void setInput8(String input8) {
        this.input8 = input8;
    }

    public String getInput9() {
        return input9;
    }

    public void setInput9(String input9) {
        this.input9 = input9;
    }

    public String getInput10() {
        return input10;
    }

    public void setInput10(String input10) {
        this.input10 = input10;
    }

    public String getReserved2() {
        return reserved2;
    }

    public void setReserved2(String reserved2) {
        this.reserved2 = reserved2;
    }

    public String getUrjb1() {
        return urjb1;
    }

    public void setUrjb1(String urjb1) {
        this.urjb1 = urjb1;
    }

    public String getUrjb2() {
        return urjb2;
    }

    public void setUrjb2(String urjb2) {
        this.urjb2 = urjb2;
    }

    public String getReserved3() {
        return reserved3;
    }

    public void setReserved3(String reserved3) {
        this.reserved3 = reserved3;
    }

    public String generatePacket() {
        return packetId + length + status + reserved1 + input1 + input2 + input3 + input4 + input5 +
                input6 + input7 + input8 + input9 + input10 +
                reserved2 + urjb1 + urjb2 + reserved3;
    }

    public String parsePacket(String responseData) {
        String returnValue = "";
        String value = responseData.replaceAll(" ", "");
        StringHelper stringHelper = new StringHelper();
        Packet packet = new Packet();
        Size size = new Size();

        AppLogs.generate(packet.PKT_0521 + " Response :  " + value);


        int startPos1 = size.SIZE_0;
        int length1 = stringHelper.getMultiplyValue(size.SIZE_2);
        String packetId = stringHelper.getSubstringData(value, startPos1, startPos1 + length1);

        int startPos2 = startPos1 + length1;
        int length2 = stringHelper.getMultiplyValue(size.SIZE_2);
        String length = stringHelper.getSubstringData(value, startPos2, startPos2 + length2);

        if (packetId.equalsIgnoreCase(packet.PKT_0521)) {

            int startPos3 = startPos2 + length2;
            int length3 = stringHelper.getMultiplyValue(size.SIZE_2);
            String status = stringHelper.getSubstringData(value, startPos3, startPos3 + length3);

            int startPos4 = startPos3 + length3;
            int length4 = stringHelper.getMultiplyValue(size.SIZE_3);
            String reserved_1 = stringHelper.getSubstringData(value, startPos4, startPos4 + length4);

            int startPos5 = startPos4 + length4;
            int length5 = stringHelper.getMultiplyValue(size.SIZE_1);
            String input_1 = stringHelper.getSubstringData(value, startPos5, startPos5 + length5);

            int startPos6 = startPos5 + length5;
            int length6 = stringHelper.getMultiplyValue(size.SIZE_1);
            String input_2 = stringHelper.getSubstringData(value, startPos6, startPos6 + length6);

            int startPos7 = startPos6 + length6;
            int length7 = stringHelper.getMultiplyValue(size.SIZE_1);
            String input_3 = stringHelper.getSubstringData(value, startPos7, startPos7 + length7);

            int startPos8 = startPos7 + length7;
            int length8 = stringHelper.getMultiplyValue(size.SIZE_1);
            String input_4 = stringHelper.getSubstringData(value, startPos8, startPos8 + length8);

            int startPos9 = startPos8 + length8;
            int length9 = stringHelper.getMultiplyValue(size.SIZE_1);
            String input_5 = stringHelper.getSubstringData(value, startPos9, startPos9 + length9);

            int startPos10 = startPos9 + length9;
            int length10 = stringHelper.getMultiplyValue(size.SIZE_1);
            String input_6 = stringHelper.getSubstringData(value, startPos10, startPos10 + length10);

            int startPos11 = startPos10 + length10;
            int length11 = stringHelper.getMultiplyValue(size.SIZE_1);
            String input_7 = stringHelper.getSubstringData(value, startPos11, startPos11 + length11);

            int startPos12 = startPos11 + length11;
            int length12 = stringHelper.getMultiplyValue(size.SIZE_1);
            String input_8 = stringHelper.getSubstringData(value, startPos12, startPos12 + length12);

            int startPos13 = startPos12 + length12;
            int length13 = stringHelper.getMultiplyValue(size.SIZE_1);
            String input_9 = stringHelper.getSubstringData(value, startPos13, startPos13 + length13);

            int startPos14 = startPos13 + length13;
            int length14 = stringHelper.getMultiplyValue(size.SIZE_1);
            String input_10 = stringHelper.getSubstringData(value, startPos14, startPos14 + length14);

            int startPos15 = startPos14 + length14;
            int length15 = stringHelper.getMultiplyValue(size.SIZE_10);
            String reserved_2 = stringHelper.getSubstringData(value, startPos15, startPos15 + length15);

            int startPos16 = startPos15 + length15;
            int length16 = stringHelper.getMultiplyValue(size.SIZE_1);
            String urjb_1 = stringHelper.getSubstringData(value, startPos16, startPos16 + length16);

            int startPos17 = startPos16 + length16;
            int length17 = stringHelper.getMultiplyValue(size.SIZE_1);
            String urjb_2 = stringHelper.getSubstringData(value, startPos17, startPos17 + length17);

            int startPos18 = startPos17 + length17;
            int length18 = stringHelper.getMultiplyValue(size.SIZE_7);
            String reserved_3 = stringHelper.getSubstringData(value, startPos18, startPos18 + length18);


            setPacketId(packetId);
            setLength(length);
            setStatus(status);
            setReserved1(reserved_1);
            setInput1(input_1);
            setInput2(input_2);
            setInput3(input_3);
            setInput4(input_4);
            setInput5(input_5);
            setInput6(input_6);
            setInput7(input_7);
            setInput8(input_8);
            setInput9(input_9);
            setInput10(input_10);
            setReserved2(reserved_2);
            setUrjb1(urjb_1);
            setUrjb2(urjb_2);
            setReserved3(reserved_3);

            returnValue = "Packet Matched 0521";
        } else {
            returnValue = "Packet Id Not Matched 0521";
        }

        AppLogs.generate(returnValue);
        return returnValue;
    }
}
