package com.hpy.crmdriver.ui.theme.packet_model;

import com.hpy.crmdriver.ui.theme.data.Packet;
import com.hpy.crmdriver.ui.theme.data.Size;
import com.hpy.crmdriver.ui.theme.util.AppLogs;
import com.hpy.crmdriver.ui.theme.util.StringHelper;

public class ModelPacket0512 {

    String packetId;
    String length;
    String status;
    String hardwareConfig;

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

    public String getHardwareConfig() {
        return hardwareConfig;
    }

    public void setHardwareConfig(String hardwareConfig) {
        this.hardwareConfig = hardwareConfig;
    }

    public String generatePacket() {
        return packetId + length + status + hardwareConfig;
    }


    public String parsePacket(String responseData) {
        String returnValue = "";
        String value = responseData.replaceAll(" ", "");
        StringHelper stringHelper = new StringHelper();
        Packet packet = new Packet();
        Size size = new Size();

        AppLogs.generate(packet.PKT_0512 + " Response :  " + value);


        int startPos1 = size.SIZE_0;
        int length1 = stringHelper.getMultiplyValue(size.SIZE_2);
        String packetId = stringHelper.getSubstringData(value, startPos1, startPos1 + length1);

        int startPos2 = startPos1 + length1;
        int length2 = stringHelper.getMultiplyValue(size.SIZE_2);
        String length = stringHelper.getSubstringData(value, startPos2, startPos2 + length2);

        if (packetId.equalsIgnoreCase(packet.PKT_0512)) {

            int startPos3 = startPos2 + length2;
            int length3 = stringHelper.getMultiplyValue(size.SIZE_2);
            String status = stringHelper.getSubstringData(value, startPos3, startPos3 + length3);

            int startPos4 = startPos3 + length3;
            int length4 = stringHelper.getMultiplyValue(size.SIZE_4);
            String hardwareConfig = stringHelper.getSubstringData(value, startPos4, startPos4 + length4);

            setPacketId(packetId);
            setLength(length);
            setStatus(status);
            setHardwareConfig(hardwareConfig);

            returnValue = "Packet Matched 0512";

        } else {
            returnValue = "Packet Id Not Matched 0512";
        }

        AppLogs.generate(returnValue);
        return returnValue;
    }

}
