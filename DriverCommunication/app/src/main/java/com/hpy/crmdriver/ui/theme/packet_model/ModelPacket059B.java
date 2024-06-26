package com.hpy.crmdriver.ui.theme.packet_model;

import com.hpy.crmdriver.ui.theme.util.AppLogs;

public class ModelPacket059B {

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

//        convert into string then make two characters
//        convert each group into hex
//        check first 4 digits are matching with your packet Id else throw exception
//        parse data anse set value to your property;

        AppLogs.generate(returnValue);
        return returnValue;
    }

}
