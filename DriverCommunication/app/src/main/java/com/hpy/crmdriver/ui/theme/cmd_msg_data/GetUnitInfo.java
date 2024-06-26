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
import com.hpy.crmdriver.ui.theme.packet_model.ModelPacket0510;
import com.hpy.crmdriver.ui.theme.packet_model.ModelPacket0511;
import com.hpy.crmdriver.ui.theme.packet_model.ModelPacket0512;
import com.hpy.crmdriver.ui.theme.packet_model.ModelPacket0513;
import com.hpy.crmdriver.ui.theme.packet_model.ModelPacket0515;
import com.hpy.crmdriver.ui.theme.packet_model.ModelPacket0517;
import com.hpy.crmdriver.ui.theme.packet_model.ModelPacket0518;
import com.hpy.crmdriver.ui.theme.packet_model.ModelPacket051A;
import com.hpy.crmdriver.ui.theme.packet_model.ModelPacket0521;
import com.hpy.crmdriver.ui.theme.packet_model.ModelPacket0529;
import com.hpy.crmdriver.ui.theme.packet_model.ModelPacket0581;
import com.hpy.crmdriver.ui.theme.packet_model.ModelPacket0586;
import com.hpy.crmdriver.ui.theme.session.SessionModel;
import com.hpy.crmdriver.ui.theme.util.StringHelper;

public class GetUnitInfo {

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
        modelPacket0001.setCommand(commandData.CMD_0500);//check for value

        returnValue = modelPacket0001.generatePacket();

        String messageHeaderLength = messageDataLengthGenerator.getMessageHeaderLength(returnValue);
        returnValue = messageHeaderLength + returnValue;

        return returnValue;
    }

    public ModelPacket0081 modelPacket0081 = new ModelPacket0081();
    public ModelPacket008E modelPacket008E = new ModelPacket008E();
    public ModelPacket0581 modelPacket0581 = new ModelPacket0581();
    public ModelPacket0586 modelPacket0586 = new ModelPacket0586();
    public ModelPacket0513 modelPacket0513 = new ModelPacket0513();
    public ModelPacket051A modelPacket051A = new ModelPacket051A();
    public ModelPacket0511 modelPacket0511 = new ModelPacket0511();
    public ModelPacket0510 modelPacket0510 = new ModelPacket0510();
    public ModelPacket0512 modelPacket0512 = new ModelPacket0512();
    public ModelPacket0515 modelPacket0515 = new ModelPacket0515();
    public ModelPacket0517 modelPacket0517 = new ModelPacket0517();
    public ModelPacket0518 modelPacket0518 = new ModelPacket0518();
    public ModelPacket0521 modelPacket0521 = new ModelPacket0521();

    //Missing - 052C

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
            int length5 = stringHelper.getMultiplyValue(size.SIZE_64);
            String noOfTotalStakeNotes = stringHelper.getSubstringData(value, startPos5, startPos5 + length5);
            modelPacket0586.parsePacket(noOfTotalStakeNotes);
            sessionModel.saveModelToSession(context, packet.PKT_0586, modelPacket0586);

            int startPos6 = startPos5 + length5;
            int length6 = stringHelper.getMultiplyValue(size.SIZE_260);
            String denominationCodeSettingId_1_127 = stringHelper.getSubstringData(value, startPos6, startPos6 + length6);
            modelPacket0513.parsePacket(denominationCodeSettingId_1_127);
            sessionModel.saveModelToSession(context, packet.PKT_0513, modelPacket0513);

            int startPos7 = startPos6 + length6;
            int length7 = stringHelper.getMultiplyValue(size.SIZE_262);
            String denominationCodeSettingId_128_255 = stringHelper.getSubstringData(value, startPos7, startPos7 + length7);
            modelPacket051A.parsePacket(denominationCodeSettingId_128_255);
            sessionModel.saveModelToSession(context, packet.PKT_051A, modelPacket051A);

            int startPos8 = startPos7 + length7;
            int length8 = stringHelper.getMultiplyValue(size.SIZE_10);
            String operationalInformation_1 = stringHelper.getSubstringData(value, startPos8, startPos8 + length8);
            modelPacket0511.parsePacket(operationalInformation_1);
            sessionModel.saveModelToSession(context, packet.PKT_0511, modelPacket0511);

            int startPos9 = startPos8 + length8;
            int length9 = stringHelper.getMultiplyValue(size.SIZE_22);
            String operationalInformation_2 = stringHelper.getSubstringData(value, startPos9, startPos9 + length9);
            modelPacket0510.parsePacket(operationalInformation_2);
            sessionModel.saveModelToSession(context, packet.PKT_0510, modelPacket0510);

            int startPos10 = startPos9 + length9;
            int length10 = stringHelper.getMultiplyValue(size.SIZE_10);
            String hardwareConfiguration = stringHelper.getSubstringData(value, startPos10, startPos10 + length10);
            modelPacket0512.parsePacket(hardwareConfiguration);
            sessionModel.saveModelToSession(context, packet.PKT_0512, modelPacket0512);

            int startPos11 = startPos10 + length10;
            int length11 = stringHelper.getMultiplyValue(size.SIZE_326);
            String cassetteDenominationCode = stringHelper.getSubstringData(value, startPos11, startPos11 + length11);
            modelPacket0515.parsePacket(cassetteDenominationCode);
            sessionModel.saveModelToSession(context, packet.PKT_0515, modelPacket0515);

            int startPos12 = startPos11 + length11;
            int length12 = stringHelper.getMultiplyValue(size.SIZE_22);
            String acceptableDenominationCode = stringHelper.getSubstringData(value, startPos12, startPos12 + length12);
            modelPacket0517.parsePacket(acceptableDenominationCode);
            sessionModel.saveModelToSession(context, packet.PKT_0517, modelPacket0517);

            int startPos13 = startPos12 + length12;
            int length13 = stringHelper.getMultiplyValue(size.SIZE_36);
            String cassetteType = stringHelper.getSubstringData(value, startPos13, startPos13 + length13);
            modelPacket0518.parsePacket(cassetteType);
            sessionModel.saveModelToSession(context, packet.PKT_0518, modelPacket0518);

            int startPos14 = startPos13 + length13;
            int length14 = stringHelper.getMultiplyValue(size.SIZE_38);
            String noteHandlingInformation = stringHelper.getSubstringData(value, startPos14, startPos14 + length14);
            modelPacket0521.parsePacket(noteHandlingInformation);
            sessionModel.saveModelToSession(context, packet.PKT_0521, modelPacket0521);

            int startPos15 = startPos14 + length14;
            int length15 = stringHelper.getMultiplyValue(size.SIZE_20);
            String fixedNoSettingForEachRejectFactor = stringHelper.getSubstringData(value, startPos15, startPos15 + length15);
            //Missing Packet - 052C
        }
    }

}
