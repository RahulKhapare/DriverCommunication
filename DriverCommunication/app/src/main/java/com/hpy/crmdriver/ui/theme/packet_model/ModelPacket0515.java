package com.hpy.crmdriver.ui.theme.packet_model;

import com.hpy.crmdriver.ui.theme.data.Packet;
import com.hpy.crmdriver.ui.theme.data.Size;
import com.hpy.crmdriver.ui.theme.util.AppLogs;
import com.hpy.crmdriver.ui.theme.util.StringHelper;

public class ModelPacket0515 {

    String packetId;
    String length;
    String status;
    String input1A;
    String input2A;
    String input3A;
    String input4A;
    String input5A;
    String input1B;
    String input2B;
    String input3B;
    String input4B;
    String input5B;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getInput1A() {
        return input1A;
    }

    public void setInput1A(String input1A) {
        this.input1A = input1A;
    }

    public String getInput2A() {
        return input2A;
    }

    public void setInput2A(String input2A) {
        this.input2A = input2A;
    }

    public String getInput3A() {
        return input3A;
    }

    public void setInput3A(String input3A) {
        this.input3A = input3A;
    }

    public String getInput4A() {
        return input4A;
    }

    public void setInput4A(String input4A) {
        this.input4A = input4A;
    }

    public String getInput5A() {
        return input5A;
    }

    public void setInput5A(String input5A) {
        this.input5A = input5A;
    }

    public String getInput1B() {
        return input1B;
    }

    public void setInput1B(String input1B) {
        this.input1B = input1B;
    }

    public String getInput2B() {
        return input2B;
    }

    public void setInput2B(String input2B) {
        this.input2B = input2B;
    }

    public String getInput3B() {
        return input3B;
    }

    public void setInput3B(String input3B) {
        this.input3B = input3B;
    }

    public String getInput4B() {
        return input4B;
    }

    public void setInput4B(String input4B) {
        this.input4B = input4B;
    }

    public String getInput5B() {
        return input5B;
    }

    public void setInput5B(String input5B) {
        this.input5B = input5B;
    }

    public String getReserved() {
        return reserved;
    }

    public void setReserved(String reserved) {
        this.reserved = reserved;
    }

    public String generatePacket() {
        return packetId + length + status + input1A + input2A + input3A + input4A + input5A +
                input1B + input2B + input3B + input4B + input5B + reserved;
    }

    public String parsePacket(String responseData) {
        String returnValue = "";
        String value = responseData.replaceAll(" ", "");
        StringHelper stringHelper = new StringHelper();
        Packet packet = new Packet();
        Size size = new Size();

        AppLogs.generate(packet.PKT_0515 + " Response :  " + value);

        int startPos1 = size.SIZE_0;
        int length1 = stringHelper.getMultiplyValue(size.SIZE_2);
        String packetId = stringHelper.getSubstringData(value, startPos1, startPos1 + length1);

        int startPos2 = startPos1 + length1;
        int length2 = stringHelper.getMultiplyValue(size.SIZE_2);
        String length = stringHelper.getSubstringData(value, startPos2, startPos2 + length2);

        if (packetId.equalsIgnoreCase(packet.PKT_0515)) {

            int startPos3 = startPos2 + length2;
            int length3 = stringHelper.getMultiplyValue(size.SIZE_2);
            String status = stringHelper.getSubstringData(value, startPos3, startPos3 + length3);

            int startPos4 = startPos3 + length3;
            int length4 = stringHelper.getMultiplyValue(size.SIZE_16);
            String input1A = stringHelper.getSubstringData(value, startPos4, startPos4 + length4);

            int startPos5 = startPos4 + length4;
            int length5 = stringHelper.getMultiplyValue(size.SIZE_16);
            String input2A = stringHelper.getSubstringData(value, startPos5, startPos5 + length5);

            int startPos6 = startPos5 + length5;
            int length6 = stringHelper.getMultiplyValue(size.SIZE_16);
            String input3A = stringHelper.getSubstringData(value, startPos6, startPos6 + length6);

            int startPos7 = startPos6 + length6;
            int length7 = stringHelper.getMultiplyValue(size.SIZE_16);
            String input4A = stringHelper.getSubstringData(value, startPos7, startPos7 + length7);

            int startPos8 = startPos7 + length7;
            int length8 = stringHelper.getMultiplyValue(size.SIZE_16);
            String input5A = stringHelper.getSubstringData(value, startPos8, startPos8 + length8);

            int startPos9 = startPos8 + length8;
            int length9 = stringHelper.getMultiplyValue(size.SIZE_16);
            String input1B = stringHelper.getSubstringData(value, startPos9, startPos9 + length9);

            int startPos10 = startPos9 + length9;
            int length10 = stringHelper.getMultiplyValue(size.SIZE_16);
            String input2B = stringHelper.getSubstringData(value, startPos10, startPos10 + length10);

            int startPos11 = startPos10 + length10;
            int length11 = stringHelper.getMultiplyValue(size.SIZE_16);
            String input3B = stringHelper.getSubstringData(value, startPos11, startPos11 + length11);

            int startPos12 = startPos11 + length11;
            int length12 = stringHelper.getMultiplyValue(size.SIZE_16);
            String input4B = stringHelper.getSubstringData(value, startPos12, startPos12 + length12);

            int startPos13 = startPos12 + length12;
            int length13 = stringHelper.getMultiplyValue(size.SIZE_16);
            String input5B = stringHelper.getSubstringData(value, startPos13, startPos13 + length13);


            int startPos14 = startPos13 + length13;
            int length14 = stringHelper.getMultiplyValue(size.SIZE_160);
            String reserved = stringHelper.getSubstringData(value, startPos14, startPos14 + length14);

            setPacketId(packetId);
            setLength(length);
            setStatus(status);
            setInput1A(input1A);
            setInput2A(input2A);
            setInput3A(input3A);
            setInput4A(input4A);
            setInput5A(input5A);
            setInput1B(input1B);
            setInput2B(input2B);
            setInput3B(input3B);
            setInput4B(input4B);
            setInput5B(input5B);
            setReserved(reserved);

            returnValue = "Packet Matched 0515";

        } else {
            returnValue = "Packet Id Not Matched 0515";
        }


        AppLogs.generate(returnValue);
        return returnValue;
    }

}
