package com.hpy.crmdriver.ui.theme.packet_model;

import com.hpy.crmdriver.ui.theme.data.Packet;
import com.hpy.crmdriver.ui.theme.data.Size;
import com.hpy.crmdriver.ui.theme.other.DataPacket05ACatModel;
import com.hpy.crmdriver.ui.theme.util.AppLogs;
import com.hpy.crmdriver.ui.theme.util.StringHelper;

import java.util.ArrayList;
import java.util.List;

public class ModelPacket05A5 {

    String packetId;
    String length;
    List<DataPacket05ACatModel> dataPacket05ACatModelList;

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

    public List<DataPacket05ACatModel> getDataPacket05ACatModelList() {
        return dataPacket05ACatModelList;
    }

    public void setDataPacket05ACatModelList(List<DataPacket05ACatModel> dataPacket05ACatModelList) {
        this.dataPacket05ACatModelList = dataPacket05ACatModelList;
    }

    public String generatePacket() {
        String returnValue = "";
        String packetData = packetId + length;
        String messageData = "";
        for (DataPacket05ACatModel model : dataPacket05ACatModelList) {
            messageData = messageData + model.getDenominationCode() + model.getDestination() + model.getNoOfNotes();
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
        List<DataPacket05ACatModel> list = new ArrayList<>();

        AppLogs.generate(packet.PKT_05A5 + " Response :  " + value);


        //split string and user loop
        //start loop
        int startPos1 = size.SIZE_0;
        int length1 = stringHelper.getMultiplyValue(size.SIZE_2);
        String packetId = stringHelper.getSubstringData(value, startPos1, startPos1 + length1);

        int startPos2 = startPos1 + length1;
        int length2 = stringHelper.getMultiplyValue(size.SIZE_2);
        String length = stringHelper.getSubstringData(value, startPos2, startPos2 + length2);

        if (packetId.equalsIgnoreCase(packet.PKT_05A5)) {

            int startPos3 = startPos2 + length2;
            int length3 = stringHelper.getMultiplyValue(size.SIZE_1);
            String denominationCode = stringHelper.getSubstringData(value, startPos3, startPos3 + length3);

            int startPos4 = startPos3 + length3;
            int length4 = stringHelper.getMultiplyValue(size.SIZE_1);
            String destination = stringHelper.getSubstringData(value, startPos4, startPos4 + length4);

            int startPos5 = startPos4 + length4;
            int length5 = stringHelper.getMultiplyValue(size.SIZE_2);
            String noOfNotes = stringHelper.getSubstringData(value, startPos5, startPos5 + length5);

            DataPacket05ACatModel model = new DataPacket05ACatModel();
            model.setDenominationCode(denominationCode);
            model.setDestination(destination);
            model.setNoOfNotes(noOfNotes);
            list.add(model);
            //end loop

            //add data in main list
            setPacketId(packetId);
            setLength(length);
            setDataPacket05ACatModelList(list);

            returnValue = "Packet Matched 05A5";

        } else {
            returnValue = "Packet Id Not Matched 05A5";
        }

        AppLogs.generate(returnValue);
        return returnValue;
    }

}
