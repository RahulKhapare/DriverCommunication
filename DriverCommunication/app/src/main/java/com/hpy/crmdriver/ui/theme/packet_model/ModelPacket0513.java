package com.hpy.crmdriver.ui.theme.packet_model;

import com.hpy.crmdriver.ui.theme.data.Packet;
import com.hpy.crmdriver.ui.theme.data.Size;
import com.hpy.crmdriver.ui.theme.util.AppLogs;
import com.hpy.crmdriver.ui.theme.util.StringHelper;

public class ModelPacket0513 {

    String packetId;
    String length;
    String mode;
    String status;
    String denominationCodeNoteId1;
    String optionNoteId1;
    String denominationCodeNoteId127;
    String optionNoteId127;

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

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDenominationCodeNoteId1() {
        return denominationCodeNoteId1;
    }

    public void setDenominationCodeNoteId1(String denominationCodeNoteId1) {
        this.denominationCodeNoteId1 = denominationCodeNoteId1;
    }

    public String getOptionNoteId1() {
        return optionNoteId1;
    }

    public void setOptionNoteId1(String optionNoteId1) {
        this.optionNoteId1 = optionNoteId1;
    }

    public String getDenominationCodeNoteId127() {
        return denominationCodeNoteId127;
    }

    public void setDenominationCodeNoteId127(String denominationCodeNoteId127) {
        this.denominationCodeNoteId127 = denominationCodeNoteId127;
    }

    public String getOptionNoteId127() {
        return optionNoteId127;
    }

    public void setOptionNoteId127(String optionNoteId127) {
        this.optionNoteId127 = optionNoteId127;
    }

    public String generatePacket() {
        return packetId + length + mode + status + denominationCodeNoteId1 + optionNoteId1 + denominationCodeNoteId127 + optionNoteId127;
    }

    public String parsePacket(String responseData) {
        String returnValue = "";
        String value = responseData.replaceAll(" ", "");
        StringHelper stringHelper = new StringHelper();
        Packet packet = new Packet();
        Size size = new Size();

        AppLogs.generate(packet.PKT_0513 + " Response :  " + value);


        int startPos1 = size.SIZE_0;
        int length1 = stringHelper.getMultiplyValue(size.SIZE_2);
        String packetId = stringHelper.getSubstringData(value, startPos1, startPos1 + length1);

        int startPos2 = startPos1 + length1;
        int length2 = stringHelper.getMultiplyValue(size.SIZE_2);
        String length = stringHelper.getSubstringData(value, startPos2, startPos2 + length2);

        if (packetId.equalsIgnoreCase(packet.PKT_0513)) {

            int startPos3 = startPos2 + length2;
            int length3 = stringHelper.getMultiplyValue(size.SIZE_1);
            String mode = stringHelper.getSubstringData(value, startPos3, startPos3 + length3);

            int startPos4 = startPos3 + length3;
            int length4 = stringHelper.getMultiplyValue(size.SIZE_1);
            String status = stringHelper.getSubstringData(value, startPos4, startPos4 + length4);

            int startPos5 = startPos4 + length4;
            int length5 = stringHelper.getMultiplyValue(size.SIZE_1);
            String denominationCode_1 = stringHelper.getSubstringData(value, startPos5, startPos5 + length5);

            int startPos6 = startPos5 + length5;
            int length6 = stringHelper.getMultiplyValue(size.SIZE_1);
            String option_1 = stringHelper.getSubstringData(value, startPos6, startPos6 + length6);

            int startPos7 = startPos6 + length6;
            int length7 = stringHelper.getMultiplyValue(size.SIZE_1);
            String denominationCode_2 = stringHelper.getSubstringData(value, startPos7, startPos7 + length7);

            int startPos8 = startPos7 + length7;
            int length8 = stringHelper.getMultiplyValue(size.SIZE_1);
            String option_2 = stringHelper.getSubstringData(value, startPos8, startPos8 + length8);


            setPacketId(packetId);
            setLength(length);
            setMode(mode);
            setStatus(status);
            setDenominationCodeNoteId1(denominationCode_1);
            setOptionNoteId1(option_1);
            setDenominationCodeNoteId127(denominationCode_2);
            setOptionNoteId127(option_2);

            returnValue = "Packet Matched 0513";

        } else {
            returnValue = "Packet Id Not Matched 0513";
        }

        AppLogs.generate(returnValue);
        return returnValue;
    }

}
