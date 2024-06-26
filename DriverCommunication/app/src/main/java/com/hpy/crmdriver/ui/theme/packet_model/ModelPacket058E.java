package com.hpy.crmdriver.ui.theme.packet_model;

import com.hpy.crmdriver.ui.theme.data.Packet;
import com.hpy.crmdriver.ui.theme.data.Size;
import com.hpy.crmdriver.ui.theme.util.AppLogs;
import com.hpy.crmdriver.ui.theme.util.StringHelper;

public class ModelPacket058E {

    String packetId;
    String length;
    String hardwareTye_c1;
    String reserved1_c1;
    String roomA_c1;
    String roomB_c1;
    String reserved2_c1;

    String hardwareTye_c5;
    String reserved1_c5;
    String roomA_c5;
    String roomB_c5;
    String reserved2_c5;

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

    public String getHardwareTye_c1() {
        return hardwareTye_c1;
    }

    public void setHardwareTye_c1(String hardwareTye_c1) {
        this.hardwareTye_c1 = hardwareTye_c1;
    }

    public String getReserved1_c1() {
        return reserved1_c1;
    }

    public void setReserved1_c1(String reserved1_c1) {
        this.reserved1_c1 = reserved1_c1;
    }

    public String getRoomA_c1() {
        return roomA_c1;
    }

    public void setRoomA_c1(String roomA_c1) {
        this.roomA_c1 = roomA_c1;
    }

    public String getRoomB_c1() {
        return roomB_c1;
    }

    public void setRoomB_c1(String roomB_c1) {
        this.roomB_c1 = roomB_c1;
    }

    public String getReserved2_c1() {
        return reserved2_c1;
    }

    public void setReserved2_c1(String reserved2_c1) {
        this.reserved2_c1 = reserved2_c1;
    }

    public String getHardwareTye_c5() {
        return hardwareTye_c5;
    }

    public void setHardwareTye_c5(String hardwareTye_c5) {
        this.hardwareTye_c5 = hardwareTye_c5;
    }

    public String getReserved1_c5() {
        return reserved1_c5;
    }

    public void setReserved1_c5(String reserved1_c5) {
        this.reserved1_c5 = reserved1_c5;
    }

    public String getRoomA_c5() {
        return roomA_c5;
    }

    public void setRoomA_c5(String roomA_c5) {
        this.roomA_c5 = roomA_c5;
    }

    public String getRoomB_c5() {
        return roomB_c5;
    }

    public void setRoomB_c5(String roomB_c5) {
        this.roomB_c5 = roomB_c5;
    }

    public String getReserved2_c5() {
        return reserved2_c5;
    }

    public void setReserved2_c5(String reserved2_c5) {
        this.reserved2_c5 = reserved2_c5;
    }

    public String generatePacket() {
        return packetId + length +
                hardwareTye_c1 + reserved1_c1 + roomA_c1 + roomB_c1 + reserved2_c1 +
                hardwareTye_c5 + reserved1_c5 + roomA_c5 + roomB_c5 + reserved2_c5
                ;
    }

    public String parsePacket(String responseData) {
        String returnValue = "";
        String value = responseData.replaceAll(" ", "");
        StringHelper stringHelper = new StringHelper();
        Packet packet = new Packet();
        Size size = new Size();

        AppLogs.generate(packet.PKT_058E + " Response :  " + value);

        int startPos1 = size.SIZE_0;
        int length1 = stringHelper.getMultiplyValue(size.SIZE_2);
        String packetId = stringHelper.getSubstringData(value, startPos1, startPos1 + length1);

        int startPos2 = startPos1 + length1;
        int length2 = stringHelper.getMultiplyValue(size.SIZE_2);
        String length = stringHelper.getSubstringData(value, startPos2, startPos2 + length2);

        if (packetId.equalsIgnoreCase(packet.PKT_058E)) {

            int startPos3 = startPos2 + length2;
            int length3 = stringHelper.getMultiplyValue(size.SIZE_1);
            String hardwareTye_c1 = stringHelper.getSubstringData(value, startPos3, startPos3 + length3);

            int startPos4 = startPos3 + length3;
            int length4 = stringHelper.getMultiplyValue(size.SIZE_1);
            String reserved1_c1 = stringHelper.getSubstringData(value, startPos4, startPos4 + length4);

            int startPos5 = startPos4 + length4;
            int length5 = stringHelper.getMultiplyValue(size.SIZE_12);
            String roomA_c1 = stringHelper.getSubstringData(value, startPos5, startPos5 + length5);

            int startPos6 = startPos5 + length5;
            int length6 = stringHelper.getMultiplyValue(size.SIZE_12);
            String roomB_c1 = stringHelper.getSubstringData(value, startPos6, startPos6 + length6);

            int startPos7 = startPos6 + length6;
            int length7 = stringHelper.getMultiplyValue(size.SIZE_24);
            String reserved2_c1 = stringHelper.getSubstringData(value, startPos7, startPos7 + length7);

            int startPos8 = startPos7 + length7;
            int length8 = stringHelper.getMultiplyValue(size.SIZE_1);
            String hardwareTye_c5 = stringHelper.getSubstringData(value, startPos8, startPos8 + length8);

            int startPos9 = startPos8 + length8;
            int length9 = stringHelper.getMultiplyValue(size.SIZE_1);
            String reserved1_c5 = stringHelper.getSubstringData(value, startPos9, startPos9 + length9);

            int startPos10 = startPos9 + length9;
            int length10 = stringHelper.getMultiplyValue(size.SIZE_12);
            String roomA_c5 = stringHelper.getSubstringData(value, startPos10, startPos10 + length10);

            int startPos11 = startPos10 + length10;
            int length11 = stringHelper.getMultiplyValue(size.SIZE_12);
            String roomB_c5 = stringHelper.getSubstringData(value, startPos11, startPos11 + length11);

            int startPos12 = startPos11 + length11;
            int length12 = stringHelper.getMultiplyValue(size.SIZE_24);
            String reserved2_c5 = stringHelper.getSubstringData(value, startPos12, startPos12 + length12);

            setPacketId(packetId);
            setLength(length);

            setHardwareTye_c1(hardwareTye_c1);
            setReserved1_c1(reserved1_c1);
            setRoomA_c1(roomA_c1);
            setRoomB_c1(roomB_c1);
            setReserved2_c1(reserved2_c1);

            setHardwareTye_c5(hardwareTye_c5);
            setReserved1_c5(reserved1_c5);
            setRoomA_c5(roomA_c5);
            setRoomB_c5(roomB_c5);
            setReserved2_c5(reserved2_c5);

            returnValue = "Packet Matched 058E";

        } else {
            returnValue = "Packet Id Not Matched 058E";
        }

        AppLogs.generate(returnValue);
        return returnValue;
    }


}
