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
import com.hpy.crmdriver.ui.theme.packet_model.ModelPacket052A;
import com.hpy.crmdriver.ui.theme.packet_model.ModelPacket0581;
import com.hpy.crmdriver.ui.theme.packet_model.ModelPacket0585;
import com.hpy.crmdriver.ui.theme.packet_model.ModelPacket0586;
import com.hpy.crmdriver.ui.theme.packet_model.ModelPacket058A;
import com.hpy.crmdriver.ui.theme.packet_model.ModelPacket058B;
import com.hpy.crmdriver.ui.theme.packet_model.ModelPacket05D0;
import com.hpy.crmdriver.ui.theme.packet_model.ModelPacket05D1;
import com.hpy.crmdriver.ui.theme.packet_model.ModelPacket05D2;
import com.hpy.crmdriver.ui.theme.packet_model.ModelPacket05D3;
import com.hpy.crmdriver.ui.theme.packet_model.ModelPacket062D;
import com.hpy.crmdriver.ui.theme.session.SessionModel;
import com.hpy.crmdriver.ui.theme.util.StringHelper;

public class Transfer {

    public ModelPacket0001 modelPacket0001 = new ModelPacket0001();
    public ModelPacket062D modelPacket062D = new ModelPacket062D();
    public ModelPacket052A modelPacket052A = new ModelPacket052A();

    //Missing - 0555

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

        modelPacket062D.setPacketId(packet.PKT_062D);
        modelPacket062D.setLength(length.LENGTH_0008);
        modelPacket062D.setTransferDestination("");
        modelPacket062D.setTransferSource("");
        modelPacket062D.setNoOfNotes("");
        modelPacket062D.setRejectDestination("");
        modelPacket062D.setReserved("");

        modelPacket052A.setPacketId(packet.PKT_052A);
        modelPacket052A.setLength(length.LENGTH_0004);
        modelPacket052A.setRecordModeAreImage("");
        modelPacket052A.setReserved("");

        returnValue = modelPacket0001.generatePacket() + modelPacket062D.generatePacket() + modelPacket052A.generatePacket();

        String messageHeaderLength = messageDataLengthGenerator.getMessageHeaderLength(returnValue);
        returnValue = messageHeaderLength + returnValue;
        return returnValue;
    }

    public ModelPacket0081 modelPacket0081 = new ModelPacket0081();
    public ModelPacket008E modelPacket008E = new ModelPacket008E();
    public ModelPacket0581 modelPacket0581 = new ModelPacket0581();
    public ModelPacket0586 modelPacket0586 = new ModelPacket0586();
    public ModelPacket058A modelPacket058A = new ModelPacket058A();
    public ModelPacket058B modelPacket058B = new ModelPacket058B();
    public ModelPacket05D0 modelPacket05D0 = new ModelPacket05D0();
    public ModelPacket05D1 modelPacket05D1 = new ModelPacket05D1();
    public ModelPacket05D2 modelPacket05D2 = new ModelPacket05D2();
    public ModelPacket05D3 modelPacket05D3 = new ModelPacket05D3();
    public ModelPacket0585 modelPacket0585 = new ModelPacket0585();

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
            int length5 = stringHelper.getMultiplyValue(size.SIZE_64);
            String noOfTotalStakeNotes = stringHelper.getSubstringData(value, startPos5, startPos5 + length5);
            modelPacket0586.parsePacket(noOfTotalStakeNotes);
            sessionModel.saveModelToSession(context, packet.PKT_0586, modelPacket0586);

            int startPos6 = startPos5 + length5;
            int length6 = stringHelper.getMultiplyValue(size.SIZE_64);
            String noOfStakeNotes = stringHelper.getSubstringData(value, startPos6, startPos6 + length6);
            modelPacket058A.parsePacket(noOfStakeNotes);
            sessionModel.saveModelToSession(context, packet.PKT_058A, modelPacket058A);

            int startPos7 = startPos6 + length6;
            int length7 = stringHelper.getMultiplyValue(size.SIZE_36);
            String noOfFedNotes = stringHelper.getSubstringData(value, startPos7, startPos7 + length7);
            modelPacket058B.parsePacket(noOfFedNotes);
            sessionModel.saveModelToSession(context, packet.PKT_058B, modelPacket058B);

            //TODO - Check for data
            int startPos8 = startPos7 + length7;
            int length8 = stringHelper.getMultiplyValue(size.SIZE_2);
            String noOfStakeNotesPerDenominationAndDestination = stringHelper.getSubstringData(value, startPos8, startPos8 + length8);
            modelPacket05D0.parsePacket(noOfStakeNotesPerDenominationAndDestination);
//            modelPacket05D1.parsePacket(noOfStakeNotesPerDenominationAndDestination);
//            modelPacket05D2.parsePacket(noOfStakeNotesPerDenominationAndDestination);
//            modelPacket05D3.parsePacket(noOfStakeNotesPerDenominationAndDestination);
            sessionModel.saveModelToSession(context, packet.PKT_05D0, modelPacket05D0);

            int startPos9 = startPos8 + length8;
            int length9 = stringHelper.getMultiplyValue(size.SIZE_4100);
            String maintenanceInformation = stringHelper.getSubstringData(value, startPos9, startPos9 + length9);
            modelPacket0585.parsePacket(maintenanceInformation);
            sessionModel.saveModelToSession(context, packet.PKT_0585, modelPacket0585);
        }
    }

}
