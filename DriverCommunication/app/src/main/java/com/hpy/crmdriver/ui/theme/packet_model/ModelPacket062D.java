package com.hpy.crmdriver.ui.theme.packet_model;

import com.hpy.crmdriver.ui.theme.util.AppLogs;

public class ModelPacket062D {

    String packetId;
    String length;
    String transferDestination;
    String transferSource;
    String noOfNotes;
    String rejectDestination;
    String reserved;

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

    public String getTransferDestination() {
        return transferDestination;
    }

    public void setTransferDestination(String transferDestination) {
        this.transferDestination = transferDestination;
    }

    public String getTransferSource() {
        return transferSource;
    }

    public void setTransferSource(String transferSource) {
        this.transferSource = transferSource;
    }

    public String getNoOfNotes() {
        return noOfNotes;
    }

    public void setNoOfNotes(String noOfNotes) {
        this.noOfNotes = noOfNotes;
    }

    public String getRejectDestination() {
        return rejectDestination;
    }

    public void setRejectDestination(String rejectDestination) {
        this.rejectDestination = rejectDestination;
    }

    public String getReserved() {
        return reserved;
    }

    public void setReserved(String reserved) {
        this.reserved = reserved;
    }

    public String generatePacket() {
        return packetId + length + transferDestination + transferSource + noOfNotes + rejectDestination + reserved;
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
