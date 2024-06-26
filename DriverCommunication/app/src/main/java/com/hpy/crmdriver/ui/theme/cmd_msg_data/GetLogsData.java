package com.hpy.crmdriver.ui.theme.cmd_msg_data;

import android.content.Context;
import android.text.TextUtils;

import com.hpy.crmdriver.ui.theme.cmd_formatter.MessageDataLengthGenerator;
import com.hpy.crmdriver.ui.theme.cmd_generator.CommandData;
import com.hpy.crmdriver.ui.theme.data.Length;
import com.hpy.crmdriver.ui.theme.data.Packet;
import com.hpy.crmdriver.ui.theme.data.Size;
import com.hpy.crmdriver.ui.theme.packet_model.ModelPacket0001;
import com.hpy.crmdriver.ui.theme.packet_model.ModelPacket0002;
import com.hpy.crmdriver.ui.theme.packet_model.ModelPacket0081;
import com.hpy.crmdriver.ui.theme.packet_model.ModelPacket008E;
import com.hpy.crmdriver.ui.theme.packet_model.ModelPacket0525;
import com.hpy.crmdriver.ui.theme.packet_model.ModelPacket0581;
import com.hpy.crmdriver.ui.theme.session.SessionModel;
import com.hpy.crmdriver.ui.theme.util.StringHelper;

public class GetLogsData {

    public ModelPacket0001 modelPacket0001 = new ModelPacket0001();
    public ModelPacket0002 modelPacket0002 = new ModelPacket0002();
    public ModelPacket0525 modelPacket0525 = new ModelPacket0525();

    public Packet packet = new Packet();
    public Length length = new Length();
    public CommandData commandData = new CommandData();
    public MessageDataLengthGenerator messageDataLengthGenerator = new MessageDataLengthGenerator();
    public Size size = new Size();
    private SessionModel sessionModel = new SessionModel();


    public String generateCommand() {
        String returnValue = "";

        modelPacket0001.setPacketId(packet.PKT_0001);
        modelPacket0001.setLength(length.LENGTH_0004);
        modelPacket0001.setCommand("");

        modelPacket0002.setPacketId(packet.PKT_0002);
        modelPacket0002.setLength(length.LENGTH_0004);
        modelPacket0002.setAct("");

        modelPacket0525.setPacketId(packet.PKT_0525);
        modelPacket0525.setLength(length.LENGTH_0004);
        modelPacket0525.setYear("");
        modelPacket0525.setMonth("");

        returnValue = modelPacket0001.generatePacket() + modelPacket0002.generatePacket() + modelPacket0525.generatePacket();

        String messageHeaderLength = messageDataLengthGenerator.getMessageHeaderLength(returnValue);
        returnValue = messageHeaderLength + returnValue;
        return returnValue;
    }

    public ModelPacket0081 modelPacket0081 = new ModelPacket0081();
    public ModelPacket008E modelPacket008E = new ModelPacket008E();
    public ModelPacket0581 modelPacket0581 = new ModelPacket0581();

    //Missing - 05F0,05F1,05F2,05F3,05F4,05F5,05F6,05F7

    public void parseCommandResponse(Context context,String responseData) {
        String value = responseData;
        StringHelper stringHelper = new StringHelper();

        if (!TextUtils.isEmpty(value)) {
            //remove first digit
            value = responseData.replaceAll(" ", "");

            int startPos0 = size.SIZE_0;
            int length0 = stringHelper.getMultiplyValue(size.SIZE_8);
            String extraData = stringHelper.getSubstringData(value, startPos0, startPos0 + length0);

            int startPos1 = startPos0 + length0;
            int length1 = stringHelper.getMultiplyValue(size.SIZE_2);
            String messageLength = stringHelper.getSubstringData(value, startPos1, startPos1 + length1);

            int startPos2 = startPos1 + length1;
            int length2 = stringHelper.getMultiplyValue(size.SIZE_6);
            String resp = stringHelper.getSubstringData(value, startPos2, startPos2 + length2);
            modelPacket0081.parsePacket(resp);
            sessionModel.saveModelToSession(context, packet.PKT_0081, modelPacket0081);

            int startPos3 = startPos2 + length2;
            int length3 = stringHelper.getMultiplyValue(size.SIZE_34);
            String commandBlock = stringHelper.getSubstringData(value, startPos3, startPos3 + length3);
            modelPacket008E.parsePacket(commandBlock);
            sessionModel.saveModelToSession(context, packet.PKT_008E, modelPacket008E);

            int startPos4 = startPos3 + length3;
            int length4 = stringHelper.getMultiplyValue(size.SIZE_28);
            String statusInformation = stringHelper.getSubstringData(value, startPos4, startPos4 + length4);
            modelPacket0581.parsePacket(statusInformation);
            sessionModel.saveModelToSession(context, packet.PKT_0581, modelPacket0581);

            int startPos5 = startPos4 + length4;
            int length5 = stringHelper.getMultiplyValue(size.SIZE_72);
            String darkLightStatusLog = stringHelper.getSubstringData(value, startPos5, startPos5 + length5);
            //Missing - 05F0

            int startPos6 = startPos5 + length5;
            int length6 = stringHelper.getMultiplyValue(size.SIZE_422);
            String lightLevelLogs = stringHelper.getSubstringData(value, startPos6, startPos6 + length6);
            //Missing - 05F1

            int startPos7 = startPos6 + length6;
            int length7 = stringHelper.getMultiplyValue(size.SIZE_7174);
            String sensorMonitorLogs = stringHelper.getSubstringData(value, startPos7, startPos7 + length7);
            //Missing - 05F2
            int startPos8 = startPos7 + length7;
            int length8 = stringHelper.getMultiplyValue(size.SIZE_7174);
            String internalCommandLogs = stringHelper.getSubstringData(value, startPos8, startPos8 + length8);
            //Missing - 05F3

            int startPos9 = startPos8 + length8;
            int length9 = stringHelper.getMultiplyValue(size.SIZE_7174);
            String noteHandlingLogs = stringHelper.getSubstringData(value, startPos9, startPos9 + length9);
            //Missing - 05F4

            int startPos10 = startPos9 + length9;
            int length10 = stringHelper.getMultiplyValue(size.SIZE_7174);
            String motorControlLogs = stringHelper.getSubstringData(value, startPos10, startPos10 + length10);
            //Missing - 05F5

            int startPos11 = startPos10 + length10;
            int length11 = stringHelper.getMultiplyValue(size.SIZE_7176);
            String logDataOther = stringHelper.getSubstringData(value, startPos11, startPos11 + length11);
            //Missing - 05F6

            int startPos12 = startPos11 + length11;
            int length12 = stringHelper.getMultiplyValue(size.SIZE_2164);
            String sensorLevelLogs = stringHelper.getSubstringData(value, startPos12, startPos12 + length12);
            //Missing - 05F7

        }
    }

}
