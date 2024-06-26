package com.hpy.crmdriver.ui.theme.packet_model;

import com.hpy.crmdriver.ui.theme.data.Packet;
import com.hpy.crmdriver.ui.theme.data.Size;
import com.hpy.crmdriver.ui.theme.other.DataPacket05CStackedNotesModel;
import com.hpy.crmdriver.ui.theme.util.AppLogs;
import com.hpy.crmdriver.ui.theme.util.StringHelper;

import java.util.ArrayList;
import java.util.List;

public class ModelPacket05D3 {

    String packetId;
    String length;
    List<DataPacket05CStackedNotesModel> dataPacket05CStackedNotesModelList;

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

    public List<DataPacket05CStackedNotesModel> getDataPacket05CStackedNotesModelList() {
        return dataPacket05CStackedNotesModelList;
    }

    public void setDataPacket05CStackedNotesModelList(List<DataPacket05CStackedNotesModel> dataPacket05CStackedNotesModelList) {
        this.dataPacket05CStackedNotesModelList = dataPacket05CStackedNotesModelList;
    }

    public String generatePacket() {
        String returnValue = "";
        String packetData = packetId + length;
        String messageData = "";
        for (DataPacket05CStackedNotesModel model : dataPacket05CStackedNotesModelList) {
            messageData = messageData + model.getDenominationCode() + model.getDestination() + model.getNoOfStackedNotes();
        }
        returnValue = packetData + messageData;
        AppLogs.generate(returnValue);
        return returnValue;
    }

    public String parsePacket(String responseData) {
        String returnValue = "";
        String value = responseData.replaceAll(" ", "");
        StringHelper stringHelper = new StringHelper();
        Packet packet = new Packet();
        Size size = new Size();
        List<DataPacket05CStackedNotesModel> list = new ArrayList<>();

        AppLogs.generate(packet.PKT_05D3 + " Response :  " + value);


        //split string and user loop
        //start loop
        int startPos1 = size.SIZE_0;
        int length1 = stringHelper.getMultiplyValue(size.SIZE_2);
        String packetId = stringHelper.getSubstringData(value, startPos1, startPos1 + length1);

        int startPos2 = startPos1 + length1;
        int length2 = stringHelper.getMultiplyValue(size.SIZE_2);
        String length = stringHelper.getSubstringData(value, startPos2, startPos2 + length2);

        if (packetId.equalsIgnoreCase(packet.PKT_05D3)) {

            int startPos3 = startPos2 + length2;
            int length3 = stringHelper.getMultiplyValue(size.SIZE_1);
            String denominationCode = stringHelper.getSubstringData(value, startPos3, startPos3 + length3);

            int startPos4 = startPos3 + length3;
            int length4 = stringHelper.getMultiplyValue(size.SIZE_1);
            String destination = stringHelper.getSubstringData(value, startPos4, startPos4 + length4);

            int startPos5 = startPos4 + length4;
            int length5 = stringHelper.getMultiplyValue(size.SIZE_2);
            String noOfStakedNotes = stringHelper.getSubstringData(value, startPos5, startPos5 + length5);

            DataPacket05CStackedNotesModel model = new DataPacket05CStackedNotesModel();
            model.setDenominationCode(denominationCode);
            model.setDestination(destination);
            model.setNoOfStackedNotes(noOfStakedNotes);
            list.add(model);
            //end loop

            //add data in main list
            setPacketId(packetId);
            setLength(length);
            setDataPacket05CStackedNotesModelList(list);

            returnValue = "Packet Matched 05D3";

        } else {
            returnValue = "Packet Id Not Matched 05D3";
        }

        AppLogs.generate(returnValue);
        return returnValue;
    }

}
