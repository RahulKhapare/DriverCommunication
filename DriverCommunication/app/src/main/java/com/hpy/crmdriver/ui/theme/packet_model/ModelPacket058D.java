package com.hpy.crmdriver.ui.theme.packet_model;

import com.hpy.crmdriver.ui.theme.data.Packet;
import com.hpy.crmdriver.ui.theme.data.Size;
import com.hpy.crmdriver.ui.theme.util.AppLogs;
import com.hpy.crmdriver.ui.theme.util.StringHelper;

public class ModelPacket058D {

    String packetId;
    String length;
    String noteId_1;
    String noteId_127;

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

    public String getNoteId_1() {
        return noteId_1;
    }

    public void setNoteId_1(String noteId_1) {
        this.noteId_1 = noteId_1;
    }

    public String getNoteId_127() {
        return noteId_127;
    }

    public void setNoteId_127(String noteId_127) {
        this.noteId_127 = noteId_127;
    }

    public String generatePacket() {
        return packetId + length + noteId_1 + noteId_127;
    }

    public String parsePacket(String responseData) {
        String returnValue = "";
        String value = responseData.replaceAll(" ", "");
        StringHelper stringHelper = new StringHelper();
        Packet packet = new Packet();
        Size size = new Size();

        AppLogs.generate(packet.PKT_058D + " Response :  " + value);

        int startPos1 = size.SIZE_0;
        int length1 = stringHelper.getMultiplyValue(size.SIZE_2);
        String packetId = stringHelper.getSubstringData(value, startPos1, startPos1 + length1);

        int startPos2 = startPos1 + length1;
        int length2 = stringHelper.getMultiplyValue(size.SIZE_2);
        String length = stringHelper.getSubstringData(value, startPos2, startPos2 + length2);

        if (packetId.equalsIgnoreCase(packet.PKT_058D)) {

            int startPos3 = startPos2 + length2;
            int length3 = stringHelper.getMultiplyValue(size.SIZE_16);
            String setNoteId_1 = stringHelper.getSubstringData(value, startPos3, startPos3 + length3);

            int startPos4 = startPos3 + length3;
            int length4 = stringHelper.getMultiplyValue(size.SIZE_16);
            String setNoteId_127 = stringHelper.getSubstringData(value, startPos4, startPos4 + length4);

            setPacketId(packetId);
            setLength(length);
            setNoteId_1(setNoteId_1);
            setNoteId_127(setNoteId_127);

            returnValue = "Packet Matched 058D";

        } else {
            returnValue = "Packet Id Not Matched 058D";
        }

        AppLogs.generate(returnValue);
        return returnValue;
    }


}
