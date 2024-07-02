package com.hpy.crmdriver.ui.theme.cmd_msg_data;

import android.content.Context;
import android.text.TextUtils;

import com.hpy.crmdriver.ui.theme.cmd_formatter.MessageDataLengthGenerator;
import com.hpy.crmdriver.ui.theme.cmd_generator.CommandData;
import com.hpy.crmdriver.ui.theme.data.Length;
import com.hpy.crmdriver.ui.theme.data.Packet;
import com.hpy.crmdriver.ui.theme.data.Size;
import com.hpy.crmdriver.ui.theme.packet_model.ModelPacket0001;
import com.hpy.crmdriver.ui.theme.packet_model.ModelPacket0081;
import com.hpy.crmdriver.ui.theme.packet_model.ModelPacket008E;
import com.hpy.crmdriver.ui.theme.packet_model.ModelPacket0581;
import com.hpy.crmdriver.ui.theme.packet_model.ModelPacket0582;
import com.hpy.crmdriver.ui.theme.packet_model.ModelPacket058D;
import com.hpy.crmdriver.ui.theme.packet_model.ModelPacket058F;
import com.hpy.crmdriver.ui.theme.session.SessionModel;
import com.hpy.crmdriver.ui.theme.util.StringHelper;

public class GetBankNoteInfo {

    public ModelPacket0001 modelPacket0001 = new ModelPacket0001();

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
        modelPacket0001.setCommand("6201");

        returnValue = modelPacket0001.generatePacket();

        String messageHeaderLength = messageDataLengthGenerator.getMessageHeaderLength(returnValue);
        returnValue = messageHeaderLength + returnValue;
        return returnValue;
    }

    public ModelPacket0081 modelPacket0081 = new ModelPacket0081();
    public ModelPacket008E modelPacket008E = new ModelPacket008E();
    public ModelPacket0581 modelPacket0581 = new ModelPacket0581();
    public ModelPacket058F modelPacket058F = new ModelPacket058F();
    public ModelPacket0582 modelPacket0582 = new ModelPacket0582();
    public ModelPacket058D modelPacket058D = new ModelPacket058D();

    //Missing - 068D

    public void parseCommandResponse(Context context, String responseData) {
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
            int length5 = stringHelper.getMultiplyValue(size.SIZE_20);
            String validatorId = stringHelper.getSubstringData(value, startPos5, startPos5 + length5);
            modelPacket058F.parsePacket(validatorId);
            sessionModel.saveModelToSession(context, packet.PKT_058F, modelPacket058F);

            int startPos6 = startPos5 + length5;
            int length6 = stringHelper.getMultiplyValue(size.SIZE_6);
            String firmwareSpecificFunction = stringHelper.getSubstringData(value, startPos6, startPos6 + length6);
            modelPacket0582.parsePacket(firmwareSpecificFunction);
            sessionModel.saveModelToSession(context, packet.PKT_0582, modelPacket0582);

            int startPos7 = startPos6 + length6;
            int length7 = stringHelper.getMultiplyValue(size.SIZE_2036);
            String bankNote_Id_1_127 = stringHelper.getSubstringData(value, startPos7, startPos7 + length7);
            modelPacket058D.parsePacket(bankNote_Id_1_127);
            sessionModel.saveModelToSession(context, packet.PKT_058D, modelPacket058D);

            int startPos8 = startPos7 + length7;
            int length8 = stringHelper.getMultiplyValue(size.SIZE_2052);
            String bankNote_Id_129_255 = stringHelper.getSubstringData(value, startPos8, startPos8 + length8);
            //Missing - 068D

        }
    }

}
