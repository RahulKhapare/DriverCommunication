package com.hpy.crmdriver.ui.theme.packet_model;

import com.hpy.crmdriver.ui.theme.data.Packet;
import com.hpy.crmdriver.ui.theme.data.Size;
import com.hpy.crmdriver.ui.theme.util.AppLogs;
import com.hpy.crmdriver.ui.theme.util.StringHelper;

public class ModelPacket05C2 {

    String packetId;
    String length;
    String denominationCode_1;
    String source_1;
    String noRejectedNotes_1;

    String denominationCode_16;
    String source_16;
    String noRejectedNotes_16;

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

    public String getDenominationCode_1() {
        return denominationCode_1;
    }

    public void setDenominationCode_1(String denominationCode_1) {
        this.denominationCode_1 = denominationCode_1;
    }

    public String getSource_1() {
        return source_1;
    }

    public void setSource_1(String source_1) {
        this.source_1 = source_1;
    }

    public String getNoRejectedNotes_1() {
        return noRejectedNotes_1;
    }

    public void setNoRejectedNotes_1(String noRejectedNotes_1) {
        this.noRejectedNotes_1 = noRejectedNotes_1;
    }

    public String getDenominationCode_16() {
        return denominationCode_16;
    }

    public void setDenominationCode_16(String denominationCode_16) {
        this.denominationCode_16 = denominationCode_16;
    }

    public String getSource_16() {
        return source_16;
    }

    public void setSource_16(String source_16) {
        this.source_16 = source_16;
    }

    public String getNoRejectedNotes_16() {
        return noRejectedNotes_16;
    }

    public void setNoRejectedNotes_16(String noRejectedNotes_16) {
        this.noRejectedNotes_16 = noRejectedNotes_16;
    }

    public String generatePacket() {
        return packetId + length +
                denominationCode_1 + source_1 + noRejectedNotes_1 +
                denominationCode_16 + source_16 + noRejectedNotes_16
                ;
    }

    public String parsePacket(String responseData) {
        String returnValue = "";
        String value = responseData.replaceAll(" ", "");
        StringHelper stringHelper = new StringHelper();
        Packet packet = new Packet();
        Size size = new Size();

        AppLogs.generate(packet.PKT_05C2 + " Response :  " + value);

        int startPos1 = size.SIZE_0;
        int length1 = stringHelper.getMultiplyValue(size.SIZE_2);
        String packetId = stringHelper.getSubstringData(value, startPos1, startPos1 + length1);

        int startPos2 = startPos1 + length1;
        int length2 = stringHelper.getMultiplyValue(size.SIZE_2);
        String length = stringHelper.getSubstringData(value, startPos2, startPos2 + length2);

        if (packetId.equalsIgnoreCase(packet.PKT_05C2)) {

            int startPos3 = startPos2 + length2;
            int length3 = stringHelper.getMultiplyValue(size.SIZE_1);
            String denominationCode_1 = stringHelper.getSubstringData(value, startPos3, startPos3 + length3);

            int startPos4 = startPos3 + length3;
            int length4 = stringHelper.getMultiplyValue(size.SIZE_1);
            String source_1 = stringHelper.getSubstringData(value, startPos4, startPos4 + length4);

            int startPos5 = startPos4 + length4;
            int length5 = stringHelper.getMultiplyValue(size.SIZE_2);
            String noOfRejectedNotes_1 = stringHelper.getSubstringData(value, startPos5, startPos5 + length5);

            int startPos6 = startPos5 + length5;
            int length6 = stringHelper.getMultiplyValue(size.SIZE_1);
            String denominationCode_16 = stringHelper.getSubstringData(value, startPos6, startPos6 + length6);

            int startPos7 = startPos6 + length6;
            int length7 = stringHelper.getMultiplyValue(size.SIZE_1);
            String source_16 = stringHelper.getSubstringData(value, startPos7, startPos7 + length7);

            int startPos8 = startPos7 + length7;
            int length8 = stringHelper.getMultiplyValue(size.SIZE_2);
            String noOfRejectedNotes_16 = stringHelper.getSubstringData(value, startPos8, startPos8 + length8);

            setPacketId(packetId);
            setLength(length);
            setDenominationCode_1(denominationCode_1);
            setSource_1(source_1);
            setNoRejectedNotes_1(noOfRejectedNotes_1);
            setDenominationCode_16(denominationCode_16);
            setSource_16(source_16);
            setNoRejectedNotes_16(noOfRejectedNotes_16);

            returnValue = "Packet Matched 05C2";
        } else {
            returnValue = "Packet Id Not Matched 05C2";
        }

        AppLogs.generate(returnValue);
        return returnValue;
    }


}
