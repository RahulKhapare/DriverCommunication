package com.hpy.crmdriver.ui.theme.packet_model;

import com.hpy.crmdriver.ui.theme.data.Packet;
import com.hpy.crmdriver.ui.theme.data.Size;
import com.hpy.crmdriver.ui.theme.util.AppLogs;
import com.hpy.crmdriver.ui.theme.util.StringHelper;

public class ModelPacket051A {

    String packetId;
    String length;
    String mode;
    String status;
    String denominationCodeNoteId129;
    String reservedNoteId129;

    String denominationCodeNoteId255;
    String reservedNoteId255;
    String data;

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

    public String getDenominationCodeNoteId129() {
        return denominationCodeNoteId129;
    }

    public void setDenominationCodeNoteId129(String denominationCodeNoteId129) {
        this.denominationCodeNoteId129 = denominationCodeNoteId129;
    }

    public String getReservedNoteId129() {
        return reservedNoteId129;
    }

    public void setReservedNoteId129(String reservedNoteId129) {
        this.reservedNoteId129 = reservedNoteId129;
    }

    public String getDenominationCodeNoteId255() {
        return denominationCodeNoteId255;
    }

    public void setDenominationCodeNoteId255(String denominationCodeNoteId255) {
        this.denominationCodeNoteId255 = denominationCodeNoteId255;
    }

    public String getReservedNoteId255() {
        return reservedNoteId255;
    }

    public void setReservedNoteId255(String reservedNoteId255) {
        this.reservedNoteId255 = reservedNoteId255;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String generatePacket() {
        return packetId + length + mode + status + denominationCodeNoteId129 + reservedNoteId129 + denominationCodeNoteId255 + reservedNoteId255;
    }

    public String parsePacket(String responseData) {
        String returnValue = "";
        String value = responseData.replaceAll(" ", "");
        StringHelper stringHelper = new StringHelper();
        Packet packet = new Packet();
        Size size = new Size();

        AppLogs.generate(packet.PKT_051A + " Response :  " + value);


        int startPos1 = size.SIZE_0;
        int length1 = stringHelper.getMultiplyValue(size.SIZE_2);
        String packetId = stringHelper.getSubstringData(value, startPos1, startPos1 + length1);

        int startPos2 = startPos1 + length1;
        int length2 = stringHelper.getMultiplyValue(size.SIZE_2);
        String length = stringHelper.getSubstringData(value, startPos2, startPos2 + length2);

        if (packetId.equalsIgnoreCase(packet.PKT_051A)) {

            int startPos3 = startPos2 + length2;
            int length3 = stringHelper.getMultiplyValue(size.SIZE_1);
            String mode = stringHelper.getSubstringData(value, startPos3, startPos3 + length3);

            int startPos4 = startPos3 + length3;
            int length4 = stringHelper.getMultiplyValue(size.SIZE_1);
            String status = stringHelper.getSubstringData(value, startPos4, startPos4 + length4);

            int startPos5 = startPos4 + length4;
            int length5 = stringHelper.getMultiplyValue(size.SIZE_1);
            String denominationCode_129 = stringHelper.getSubstringData(value, startPos5, startPos5 + length5);

            int startPos6 = startPos5 + length5;
            int length6 = stringHelper.getMultiplyValue(size.SIZE_1);
            String reserved_129 = stringHelper.getSubstringData(value, startPos6, startPos6 + length6);

            int startPos7 = startPos6 + length6;
            int length7 = stringHelper.getMultiplyValue(size.SIZE_1);
            String denominationCode_255 = stringHelper.getSubstringData(value, startPos7, startPos7 + length7);

            int startPos8 = startPos7 + length7;
            int length8 = stringHelper.getMultiplyValue(size.SIZE_1);
            String reserved_255 = stringHelper.getSubstringData(value, startPos8, startPos8 + length8);

            setPacketId(packetId);
            setLength(length);
            setMode(mode);
            setStatus(status);
            setDenominationCodeNoteId129(denominationCode_129);
            setReservedNoteId129(reserved_129);
            setDenominationCodeNoteId255(denominationCode_255);
            setReservedNoteId255(reserved_255);

            returnValue = "Packet Matched 051A";

        } else {
            returnValue = "Packet Id Not Matched 051A";
        }

        AppLogs.generate(returnValue);
        return returnValue;
    }

}
