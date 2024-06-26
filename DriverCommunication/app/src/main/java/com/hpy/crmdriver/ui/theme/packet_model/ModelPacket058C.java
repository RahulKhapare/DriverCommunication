package com.hpy.crmdriver.ui.theme.packet_model;

import com.hpy.crmdriver.ui.theme.data.Packet;
import com.hpy.crmdriver.ui.theme.data.Size;
import com.hpy.crmdriver.ui.theme.util.AppLogs;
import com.hpy.crmdriver.ui.theme.util.StringHelper;

public class ModelPacket058C {

    String packetId;
    String length;
    String noOfRejectedNotes;

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

    public String getNoOfRejectedNotes() {
        return noOfRejectedNotes;
    }

    public void setNoOfRejectedNotes(String noOfRejectedNotes) {
        this.noOfRejectedNotes = noOfRejectedNotes;
    }

    public String generatePacket() {
        return packetId + length + noOfRejectedNotes;
    }

    public String parsePacket(String responseData) {
        String returnValue = "";
        String value = responseData.replaceAll(" ", "");
        StringHelper stringHelper = new StringHelper();
        Packet packet = new Packet();
        Size size = new Size();

        AppLogs.generate(packet.PKT_058C + " Response :  " + value);


        int startPos1 = size.SIZE_0;
        int length1 = stringHelper.getMultiplyValue(size.SIZE_2);
        String packetId = stringHelper.getSubstringData(value, startPos1, startPos1 + length1);

        int startPos2 = startPos1 + length1;
        int length2 = stringHelper.getMultiplyValue(size.SIZE_2);
        String length = stringHelper.getSubstringData(value, startPos2, startPos2 + length2);

        if (packetId.equalsIgnoreCase(packet.PKT_058C)) {

            int startPos3 = startPos2 + length2;
            int length3 = stringHelper.getMultiplyValue(size.SIZE_2);
            String noOfRejectNotes = stringHelper.getSubstringData(value, startPos3, startPos3 + length3);

            setPacketId(packetId);
            setLength(length);
            setNoOfRejectedNotes(noOfRejectNotes);

            returnValue = "Packet Matched 058C";

        } else {
            returnValue = "Packet Id Not Matched 058C";
        }

        AppLogs.generate(returnValue);
        return returnValue;
    }

}
