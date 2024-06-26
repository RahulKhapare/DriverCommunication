package com.hpy.crmdriver.ui.theme.packet_model;

import com.hpy.crmdriver.ui.theme.data.Packet;
import com.hpy.crmdriver.ui.theme.data.Size;
import com.hpy.crmdriver.ui.theme.util.AppLogs;
import com.hpy.crmdriver.ui.theme.util.StringHelper;

public class ModelPacket0586 {

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
        String value = responseData.replaceAll(" ", "");
        StringHelper stringHelper = new StringHelper();
        Packet packet = new Packet();
        Size size = new Size();

        AppLogs.generate(packet.PKT_0586 + " Response :  " + value);


        int startPos1 = size.SIZE_0;
        int length1 = stringHelper.getMultiplyValue(size.SIZE_2);
        String packetId = stringHelper.getSubstringData(value, startPos1, startPos1 + length1);

        int startPos2 = startPos1 + length1;
        int length2 = stringHelper.getMultiplyValue(size.SIZE_2);
        String length = stringHelper.getSubstringData(value, startPos2, startPos2 + length2);

        if (packetId.equalsIgnoreCase(packet.PKT_0586)) {

            int startPos3 = startPos2 + length2;
            int length3 = stringHelper.getMultiplyValue(size.SIZE_6);
            String reserved_1 = stringHelper.getSubstringData(value, startPos3, startPos3 + length3);

            int startPos4 = startPos3 + length3;
            int length4 = stringHelper.getMultiplyValue(size.SIZE_2);
            String input_1 = stringHelper.getSubstringData(value, startPos4, startPos4 + length4);

            int startPos5 = startPos4 + length4;
            int length5 = stringHelper.getMultiplyValue(size.SIZE_2);
            String input_2 = stringHelper.getSubstringData(value, startPos5, startPos5 + length5);

            int startPos6 = startPos5 + length5;
            int length6 = stringHelper.getMultiplyValue(size.SIZE_2);
            String input_3 = stringHelper.getSubstringData(value, startPos6, startPos6 + length6);

            int startPos7 = startPos6 + length6;
            int length7 = stringHelper.getMultiplyValue(size.SIZE_2);
            String input_4 = stringHelper.getSubstringData(value, startPos7, startPos7 + length7);

            int startPos8 = startPos7 + length7;
            int length8 = stringHelper.getMultiplyValue(size.SIZE_2);
            String input_5 = stringHelper.getSubstringData(value, startPos8, startPos8 + length8);

            int startPos9 = startPos8 + length8;
            int length9 = stringHelper.getMultiplyValue(size.SIZE_2);
            String input_6 = stringHelper.getSubstringData(value, startPos9, startPos9 + length9);

            int startPos10 = startPos9 + length9;
            int length10 = stringHelper.getMultiplyValue(size.SIZE_2);
            String input_7 = stringHelper.getSubstringData(value, startPos10, startPos10 + length10);

            int startPos11 = startPos10 + length10;
            int length11 = stringHelper.getMultiplyValue(size.SIZE_2);
            String input_8 = stringHelper.getSubstringData(value, startPos11, startPos11 + length11);

            int startPos12 = startPos11 + length11;
            int length12 = stringHelper.getMultiplyValue(size.SIZE_2);
            String input_9 = stringHelper.getSubstringData(value, startPos12, startPos12 + length12);

            int startPos13 = startPos12 + length12;
            int length13 = stringHelper.getMultiplyValue(size.SIZE_2);
            String input_10 = stringHelper.getSubstringData(value, startPos13, startPos13 + length13);

            int startPos14 = startPos13 + length13;
            int length14 = stringHelper.getMultiplyValue(size.SIZE_20);
            String reserved_2 = stringHelper.getSubstringData(value, startPos14, startPos14 + length14);

            int startPos15 = startPos14 + length14;
            int length15 = stringHelper.getMultiplyValue(size.SIZE_2);
            String urjb_1 = stringHelper.getSubstringData(value, startPos15, startPos15 + length15);

            int startPos16 = startPos15 + length15;
            int length16 = stringHelper.getMultiplyValue(size.SIZE_2);
            String urjb_2 = stringHelper.getSubstringData(value, startPos16, startPos16 + length16);

            int startPos17 = startPos16 + length16;
            int length17 = stringHelper.getMultiplyValue(size.SIZE_10);
            String reserved_3 = stringHelper.getSubstringData(value, startPos17, startPos17 + length17);

            setPacketId(packetId);
            setLength(length);
            setReserved_1(reserved_1);
            setInput_1(input_1);
            setInput_2(input_2);
            setInput_3(input_3);
            setInput_4(input_4);
            setInput_5(input_5);
            setInput_6(input_6);
            setInput_7(input_7);
            setInput_8(input_8);
            setInput_9(input_9);
            setInput_10(input_10);
            setReserved_2(reserved_2);
            setUrjb_1(urjb_1);
            setUrjb_2(urjb_2);
            setReserved_3(reserved_3);

            returnValue = "Packet Matched 0586";

        } else {
            returnValue = "Packet Id Not Matched 0586";
        }

        AppLogs.generate(returnValue);
        return returnValue;
    }

}
