package com.hpy.crmdriver.ui.theme.packet_model;

import com.hpy.crmdriver.ui.theme.data.Packet;
import com.hpy.crmdriver.ui.theme.data.Size;
import com.hpy.crmdriver.ui.theme.util.AppLogs;
import com.hpy.crmdriver.ui.theme.util.StringHelper;

public class ModelPacket059A {

    String packetId;
    String length;
    String name;
    String ver;
    String fpgaType;
    String fpgaInternalVER;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVer() {
        return ver;
    }

    public void setVer(String ver) {
        this.ver = ver;
    }

    public String getFpgaType() {
        return fpgaType;
    }

    public void setFpgaType(String fpgaType) {
        this.fpgaType = fpgaType;
    }

    public String getFpgaInternalVER() {
        return fpgaInternalVER;
    }

    public void setFpgaInternalVER(String fpgaInternalVER) {
        this.fpgaInternalVER = fpgaInternalVER;
    }

    public String generatePacket() {
        return packetId + length + name + ver + fpgaType + fpgaInternalVER;
    }

    public String parsePacket(String responseData) {
        String returnValue = "";
        String value = responseData.replaceAll(" ", "");
        StringHelper stringHelper = new StringHelper();
        Packet packet = new Packet();
        Size size = new Size();

        AppLogs.generate(packet.PKT_059A + " Response :  " + value);


        int startPos1 = size.SIZE_0;
        int length1 = stringHelper.getMultiplyValue(size.SIZE_2);
        String packetId = stringHelper.getSubstringData(value, startPos1, startPos1 + length1);

        int startPos2 = startPos1 + length1;
        int length2 = stringHelper.getMultiplyValue(size.SIZE_2);
        String length = stringHelper.getSubstringData(value, startPos2, startPos2 + length2);

        if (packetId.equalsIgnoreCase(packet.PKT_059A)) {

            int startPos3 = startPos2 + length2;
            int length3 = stringHelper.getMultiplyValue(size.SIZE_16);
            String name = stringHelper.getSubstringData(value, startPos3, startPos3 + length3);

            int startPos4 = startPos3 + length3;
            int length4 = stringHelper.getMultiplyValue(size.SIZE_8);
            String ver = stringHelper.getSubstringData(value, startPos4, startPos4 + length4);

            int startPos5 = startPos4 + length4;
            int length5 = stringHelper.getMultiplyValue(size.SIZE_1);
            String fpgaType = stringHelper.getSubstringData(value, startPos5, startPos5 + length5);

            int startPos6 = startPos5 + length5;
            int length6 = stringHelper.getMultiplyValue(size.SIZE_3);
            String fpgaInternalVer = stringHelper.getSubstringData(value, startPos6, startPos6 + length6);

            setPacketId(packetId);
            setLength(length);
            setName(name);
            setVer(ver);
            setFpgaType(fpgaType);
            setFpgaInternalVER(fpgaInternalVer);

            returnValue = "Packet Matched 059A";

        } else {
            returnValue = "Packet Id Not Matched 059A";
        }

        AppLogs.generate(returnValue);
        return returnValue;
    }

}
