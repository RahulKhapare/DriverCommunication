package com.hpy.crmdriver.ui.theme.cmd_msg_data;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.hpy.crmdriver.ui.theme.cmd_formatter.MessageDataLengthGenerator;
import com.hpy.crmdriver.ui.theme.cmd_generator.CommandData;
import com.hpy.crmdriver.ui.theme.data.Length;
import com.hpy.crmdriver.ui.theme.data.Packet;
import com.hpy.crmdriver.ui.theme.data.Size;
import com.hpy.crmdriver.ui.theme.packet_model.ModelPacket0001;
import com.hpy.crmdriver.ui.theme.packet_model.ModelPacket0081;
import com.hpy.crmdriver.ui.theme.packet_model.ModelPacket008E;
import com.hpy.crmdriver.ui.theme.packet_model.ModelPacket0581;
import com.hpy.crmdriver.ui.theme.packet_model.ModelPacket058F;
import com.hpy.crmdriver.ui.theme.packet_model.ModelPacket0591;
import com.hpy.crmdriver.ui.theme.packet_model.ModelPacket0592;
import com.hpy.crmdriver.ui.theme.packet_model.ModelPacket0593;
import com.hpy.crmdriver.ui.theme.packet_model.ModelPacket0594;
import com.hpy.crmdriver.ui.theme.packet_model.ModelPacket0595;
import com.hpy.crmdriver.ui.theme.packet_model.ModelPacket0596;
import com.hpy.crmdriver.ui.theme.packet_model.ModelPacket0597;
import com.hpy.crmdriver.ui.theme.packet_model.ModelPacket0598;
import com.hpy.crmdriver.ui.theme.packet_model.ModelPacket0599;
import com.hpy.crmdriver.ui.theme.packet_model.ModelPacket059A;
import com.hpy.crmdriver.ui.theme.packet_model.ModelPacket059C;
import com.hpy.crmdriver.ui.theme.session.SessionModel;
import com.hpy.crmdriver.ui.theme.util.StringHelper;

public class FirmwareCommand {

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
        modelPacket0001.setCommand(commandData.CMD_0305);//0300
        returnValue = modelPacket0001.generatePacket();

        String messageHeaderLength = messageDataLengthGenerator.getMessageHeaderLength(returnValue);
        returnValue = messageHeaderLength + returnValue;
        return returnValue;
    }


    public ModelPacket0081 modelPacket0081 = new ModelPacket0081();
    public ModelPacket008E modelPacket008E = new ModelPacket008E();
    public ModelPacket0581 modelPacket0581 = new ModelPacket0581();
    public ModelPacket058F modelPacket058F = new ModelPacket058F();
    public ModelPacket0591 modelPacket0591 = new ModelPacket0591();
    public ModelPacket0592 modelPacket0592 = new ModelPacket0592();
    public ModelPacket0593 modelPacket0593 = new ModelPacket0593();
    public ModelPacket0594 modelPacket0594 = new ModelPacket0594();
    public ModelPacket0595 modelPacket0595 = new ModelPacket0595();
    public ModelPacket0596 modelPacket0596 = new ModelPacket0596();
    public ModelPacket0597 modelPacket0597 = new ModelPacket0597();
    public ModelPacket0598 modelPacket0598 = new ModelPacket0598();
    public ModelPacket0599 modelPacket0599 = new ModelPacket0599();
    public ModelPacket059A modelPacket059A = new ModelPacket059A();
    public ModelPacket059C modelPacket059C = new ModelPacket059C();

    public void parseCommandResponse(Context context, String responseData) {
        String value = responseData;
        StringHelper stringHelper = new StringHelper();

        Log.e("TAG", "FirmwareCommandResponse: " + responseData);

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
            int length6 = stringHelper.getMultiplyValue(size.SIZE_32);
            String firmwareVersionBoot = stringHelper.getSubstringData(value, startPos6, startPos6 + length6);
            modelPacket0591.parsePacket(firmwareVersionBoot);
            sessionModel.saveModelToSession(context, packet.PKT_0591, modelPacket0591);

            int startPos7 = startPos6 + length6;
            int length7 = stringHelper.getMultiplyValue(size.SIZE_32);
            String firmwareVersionFPGA = stringHelper.getSubstringData(value, startPos7, startPos7 + length7);
            modelPacket0592.parsePacket(firmwareVersionFPGA);
            sessionModel.saveModelToSession(context, packet.PKT_0593, modelPacket0593);

            int startPos8 = startPos7 + length7;
            int length8 = stringHelper.getMultiplyValue(size.SIZE_32);
            String firmwareVersionMechanicalControl = stringHelper.getSubstringData(value, startPos8, startPos8 + length8);
            modelPacket0593.parsePacket(firmwareVersionMechanicalControl);
            sessionModel.saveModelToSession(context, packet.PKT_0593, modelPacket0593);

            int startPos9 = startPos8 + length8;
            int length9 = stringHelper.getMultiplyValue(size.SIZE_32);
            String firmwareVersionAuthentication = stringHelper.getSubstringData(value, startPos9, startPos9 + length9);
            modelPacket0594.parsePacket(firmwareVersionAuthentication);
            sessionModel.saveModelToSession(context, packet.PKT_0594, modelPacket0594);

            int startPos10 = startPos9 + length9;
            int length10 = stringHelper.getMultiplyValue(size.SIZE_32);
            String firmwareVersionBillValidator_1 = stringHelper.getSubstringData(value, startPos10, startPos10 + length10);
            modelPacket0595.parsePacket(firmwareVersionBillValidator_1);
            sessionModel.saveModelToSession(context, packet.PKT_0595, modelPacket0595);

            int startPos11 = startPos10 + length10;
            int length11 = stringHelper.getMultiplyValue(size.SIZE_32);
            String firmwareVersionBillValidator_2 = stringHelper.getSubstringData(value, startPos11, startPos11 + length11);
            modelPacket0596.parsePacket(firmwareVersionBillValidator_2);
            sessionModel.saveModelToSession(context, packet.PKT_0596, modelPacket0596);

            int startPos12 = startPos11 + length11;
            int length12 = stringHelper.getMultiplyValue(size.SIZE_32);
            String firmwareVersionBillValidator_3 = stringHelper.getSubstringData(value, startPos12, startPos12 + length12);
            modelPacket0597.parsePacket(firmwareVersionBillValidator_3);
            sessionModel.saveModelToSession(context, packet.PKT_0597, modelPacket0597);

            int startPos13 = startPos12 + length12;
            int length13 = stringHelper.getMultiplyValue(size.SIZE_32);
            String firmwareVersionBillValidator_4 = stringHelper.getSubstringData(value, startPos13, startPos13 + length13);
            modelPacket0598.parsePacket(firmwareVersionBillValidator_4);
            sessionModel.saveModelToSession(context, packet.PKT_0598, modelPacket0598);

            int startPos14 = startPos13 + length13;
            int length14 = stringHelper.getMultiplyValue(size.SIZE_32);
            String firmwareVersionUPFPGA = stringHelper.getSubstringData(value, startPos14, startPos14 + length14);
            modelPacket0599.parsePacket(firmwareVersionUPFPGA);
            sessionModel.saveModelToSession(context, packet.PKT_0599, modelPacket0599);

            int startPos15 = startPos14 + length14;
            int length15 = stringHelper.getMultiplyValue(size.SIZE_32);
            String firmwareVersionLOWFPGA = stringHelper.getSubstringData(value, startPos15, startPos15 + length15);
            modelPacket059A.parsePacket(firmwareVersionLOWFPGA);
            sessionModel.saveModelToSession(context, packet.PKT_059A, modelPacket059A);

            int startPos16 = startPos15 + length15;
            int length16 = stringHelper.getMultiplyValue(size.SIZE_20);
            String bv_xr_Information = stringHelper.getSubstringData(value, startPos16, startPos16 + length16);
            modelPacket059C.parsePacket(bv_xr_Information);
            sessionModel.saveModelToSession(context, packet.PKT_059C, modelPacket059C);

        }
    }
}
