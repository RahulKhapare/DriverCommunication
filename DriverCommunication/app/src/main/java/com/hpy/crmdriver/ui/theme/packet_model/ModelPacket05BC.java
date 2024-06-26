package com.hpy.crmdriver.ui.theme.packet_model;

import com.hpy.crmdriver.ui.theme.data.Packet;
import com.hpy.crmdriver.ui.theme.data.Size;
import com.hpy.crmdriver.ui.theme.util.AppLogs;
import com.hpy.crmdriver.ui.theme.util.StringHelper;

public class ModelPacket05BC {

    String packetId;
    String length;

    String warningCode_1;
    String reserved_1;
    String recoverCode_1;
    String positionCode_1;

    String warningCode_2;
    String reserved_2;
    String recoverCode_2;
    String positionCode_2;


    String warningCode_3;
    String reserved_3;
    String recoverCode_3;
    String positionCode_3;

    String warningCode_4;
    String reserved_4;
    String recoverCode_4;
    String positionCode_4;

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

    public String getWarningCode_1() {
        return warningCode_1;
    }

    public void setWarningCode_1(String warningCode_1) {
        this.warningCode_1 = warningCode_1;
    }

    public String getReserved_1() {
        return reserved_1;
    }

    public void setReserved_1(String reserved_1) {
        this.reserved_1 = reserved_1;
    }

    public String getRecoverCode_1() {
        return recoverCode_1;
    }

    public void setRecoverCode_1(String recoverCode_1) {
        this.recoverCode_1 = recoverCode_1;
    }

    public String getPositionCode_1() {
        return positionCode_1;
    }

    public void setPositionCode_1(String positionCode_1) {
        this.positionCode_1 = positionCode_1;
    }

    public String getWarningCode_2() {
        return warningCode_2;
    }

    public void setWarningCode_2(String warningCode_2) {
        this.warningCode_2 = warningCode_2;
    }

    public String getReserved_2() {
        return reserved_2;
    }

    public void setReserved_2(String reserved_2) {
        this.reserved_2 = reserved_2;
    }

    public String getRecoverCode_2() {
        return recoverCode_2;
    }

    public void setRecoverCode_2(String recoverCode_2) {
        this.recoverCode_2 = recoverCode_2;
    }

    public String getPositionCode_2() {
        return positionCode_2;
    }

    public void setPositionCode_2(String positionCode_2) {
        this.positionCode_2 = positionCode_2;
    }

    public String getWarningCode_3() {
        return warningCode_3;
    }

    public void setWarningCode_3(String warningCode_3) {
        this.warningCode_3 = warningCode_3;
    }

    public String getReserved_3() {
        return reserved_3;
    }

    public void setReserved_3(String reserved_3) {
        this.reserved_3 = reserved_3;
    }

    public String getRecoverCode_3() {
        return recoverCode_3;
    }

    public void setRecoverCode_3(String recoverCode_3) {
        this.recoverCode_3 = recoverCode_3;
    }

    public String getPositionCode_3() {
        return positionCode_3;
    }

    public void setPositionCode_3(String positionCode_3) {
        this.positionCode_3 = positionCode_3;
    }

    public String getWarningCode_4() {
        return warningCode_4;
    }

    public void setWarningCode_4(String warningCode_4) {
        this.warningCode_4 = warningCode_4;
    }

    public String getReserved_4() {
        return reserved_4;
    }

    public void setReserved_4(String reserved_4) {
        this.reserved_4 = reserved_4;
    }

    public String getRecoverCode_4() {
        return recoverCode_4;
    }

    public void setRecoverCode_4(String recoverCode_4) {
        this.recoverCode_4 = recoverCode_4;
    }

    public String getPositionCode_4() {
        return positionCode_4;
    }

    public void setPositionCode_4(String positionCode_4) {
        this.positionCode_4 = positionCode_4;
    }

    public String generatePacket() {
        return packetId + length +
                warningCode_1 + reserved_1 + recoverCode_1 + positionCode_1 +
                warningCode_2 + reserved_2 + recoverCode_2 + positionCode_2 +
                warningCode_3 + reserved_3 + recoverCode_3 + positionCode_3 +
                warningCode_4 + reserved_4 + recoverCode_4 + positionCode_4
                ;
    }

    public String parsePacket(String responseData) {
        String returnValue = "";
        String value = responseData.replaceAll(" ", "");
        StringHelper stringHelper = new StringHelper();
        Packet packet = new Packet();
        Size size = new Size();

        AppLogs.generate(packet.PKT_05BC + " Response :  " + value);

        int startPos1 = size.SIZE_0;
        int length1 = stringHelper.getMultiplyValue(size.SIZE_2);
        String packetId = stringHelper.getSubstringData(value, startPos1, startPos1 + length1);

        int startPos2 = startPos1 + length1;
        int length2 = stringHelper.getMultiplyValue(size.SIZE_2);
        String length = stringHelper.getSubstringData(value, startPos2, startPos2 + length2);

        if (packetId.equalsIgnoreCase(packet.PKT_05BC)) {

            int startPos3 = startPos2 + length2;
            int length3 = stringHelper.getMultiplyValue(size.SIZE_4);
            String warningCode_1 = stringHelper.getSubstringData(value, startPos3, startPos3 + length3);

            int startPos4 = startPos3 + length3;
            int length4 = stringHelper.getMultiplyValue(size.SIZE_8);
            String reserved_1 = stringHelper.getSubstringData(value, startPos4, startPos4 + length4);

            int startPos5 = startPos4 + length4;
            int length5 = stringHelper.getMultiplyValue(size.SIZE_2);
            String recoverCode_1 = stringHelper.getSubstringData(value, startPos5, startPos5 + length5);

            int startPos6 = startPos5 + length5;
            int length6 = stringHelper.getMultiplyValue(size.SIZE_16);
            String positionCode_1 = stringHelper.getSubstringData(value, startPos6, startPos6 + length6);

            int startPos7 = startPos6 + length6;
            int length7 = stringHelper.getMultiplyValue(size.SIZE_4);
            String warningCode_2 = stringHelper.getSubstringData(value, startPos7, startPos7 + length7);

            int startPos8 = startPos7 + length7;
            int length8 = stringHelper.getMultiplyValue(size.SIZE_8);
            String reserved_2 = stringHelper.getSubstringData(value, startPos8, startPos8 + length8);

            int startPos9 = startPos8 + length8;
            int length9 = stringHelper.getMultiplyValue(size.SIZE_2);
            String recoverCode_2 = stringHelper.getSubstringData(value, startPos9, startPos9 + length9);

            int startPos10 = startPos9 + length9;
            int length10 = stringHelper.getMultiplyValue(size.SIZE_16);
            String positionCode_2 = stringHelper.getSubstringData(value, startPos10, startPos10 + length10);

            int startPos11 = startPos10 + length10;
            int length11 = stringHelper.getMultiplyValue(size.SIZE_4);
            String warningCode_3 = stringHelper.getSubstringData(value, startPos11, startPos11 + length11);

            int startPos12 = startPos11 + length11;
            int length12 = stringHelper.getMultiplyValue(size.SIZE_8);
            String reserved_3 = stringHelper.getSubstringData(value, startPos12, startPos12 + length12);

            int startPos13 = startPos12 + length12;
            int length13 = stringHelper.getMultiplyValue(size.SIZE_2);
            String recoverCode_3 = stringHelper.getSubstringData(value, startPos13, startPos13 + length13);

            int startPos14 = startPos13 + length13;
            int length14 = stringHelper.getMultiplyValue(size.SIZE_16);
            String positionCode_3 = stringHelper.getSubstringData(value, startPos14, startPos14 + length14);

            int startPos15 = startPos14 + length14;
            int length15 = stringHelper.getMultiplyValue(size.SIZE_4);
            String warningCode_4 = stringHelper.getSubstringData(value, startPos15, startPos15 + length15);

            int startPos16 = startPos15 + length15;
            int length16 = stringHelper.getMultiplyValue(size.SIZE_8);
            String reserved_4 = stringHelper.getSubstringData(value, startPos16, startPos16 + length16);

            int startPos17 = startPos16 + length16;
            int length17 = stringHelper.getMultiplyValue(size.SIZE_2);
            String recoverCode_4 = stringHelper.getSubstringData(value, startPos17, startPos17 + length17);

            int startPos18 = startPos17 + length17;
            int length18 = stringHelper.getMultiplyValue(size.SIZE_16);
            String positionCode_4 = stringHelper.getSubstringData(value, startPos18, startPos18 + length18);

            setPacketId(packetId);
            setLength(length);
            setWarningCode_1(warningCode_1);
            setReserved_1(reserved_1);
            setRecoverCode_1(recoverCode_1);
            setPositionCode_1(positionCode_1);
            setWarningCode_2(warningCode_2);
            setReserved_2(reserved_2);
            setRecoverCode_2(recoverCode_2);
            setPositionCode_2(positionCode_2);
            setWarningCode_3(warningCode_3);
            setReserved_3(reserved_3);
            setRecoverCode_3(recoverCode_3);
            setPositionCode_3(positionCode_3);
            setWarningCode_4(warningCode_4);
            setReserved_4(reserved_4);
            setRecoverCode_4(recoverCode_4);
            setPositionCode_4(positionCode_4);


            returnValue = "Packet Matched 05BC";
        } else {
            returnValue = "Packet Id Not Matched 05BC";
        }

        AppLogs.generate(returnValue);
        return returnValue;
    }
}
