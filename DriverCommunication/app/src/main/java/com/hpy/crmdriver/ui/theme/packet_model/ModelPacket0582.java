package com.hpy.crmdriver.ui.theme.packet_model;

import com.hpy.crmdriver.ui.theme.data.Packet;
import com.hpy.crmdriver.ui.theme.data.Size;
import com.hpy.crmdriver.ui.theme.util.AppLogs;
import com.hpy.crmdriver.ui.theme.util.StringHelper;

public class ModelPacket0582 {

    String packetId;
    String length;
    String firmwareSpecificFunction;

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

    public String getFirmwareSpecificFunction() {
        return firmwareSpecificFunction;
    }

    public void setFirmwareSpecificFunction(String firmwareSpecificFunction) {
        this.firmwareSpecificFunction = firmwareSpecificFunction;
    }

    public String generatePacket() {
        return packetId + length + firmwareSpecificFunction;
    }

    public String parsePacket(String responseData) {
        String returnValue = "";
        String value = responseData.replaceAll(" ", "");
        StringHelper stringHelper = new StringHelper();
        Packet packet = new Packet();
        Size size = new Size();

        AppLogs.generate(packet.PKT_0582 + " Response :  " + value);


        int startPos1 = size.SIZE_0;
        int length1 = stringHelper.getMultiplyValue(size.SIZE_2);
        String packetId = stringHelper.getSubstringData(value, startPos1, startPos1 + length1);

        int startPos2 = startPos1 + length1;
        int length2 = stringHelper.getMultiplyValue(size.SIZE_2);
        String length = stringHelper.getSubstringData(value, startPos2, startPos2 + length2);

        if (packetId.equalsIgnoreCase(packet.PKT_0582)) {

            int startPos3 = startPos2 + length2;
            int length3 = stringHelper.getMultiplyValue(size.SIZE_2);
            String firmwareSpecificFunction = stringHelper.getSubstringData(value, startPos3, startPos3 + length3);

            setPacketId(packetId);
            setLength(length);
            setFirmwareSpecificFunction(firmwareSpecificFunction);

            returnValue = "Packet Matched 0582";

        } else {
            returnValue = "Packet Id Not Matched 0582";
        }

        AppLogs.generate(returnValue);
        return returnValue;
    }


}
