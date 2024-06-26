package com.hpy.crmdriver.ui.theme.packet_model;

import com.hpy.crmdriver.ui.theme.util.AppLogs;

public class ModelPacket05F8 {

    String packetId;
    String length;
    String notesIdentification;

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

    public String getNotesIdentification() {
        return notesIdentification;
    }

    public void setNotesIdentification(String notesIdentification) {
        this.notesIdentification = notesIdentification;
    }

    public String generatePacket() {
        return packetId + length + notesIdentification;
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
