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
import com.hpy.crmdriver.ui.theme.packet_model.ModelPacket0513;
import com.hpy.crmdriver.ui.theme.packet_model.ModelPacket051A;
import com.hpy.crmdriver.ui.theme.packet_model.ModelPacket0581;
import com.hpy.crmdriver.ui.theme.session.SessionModel;
import com.hpy.crmdriver.ui.theme.util.StringHelper;

public class SetDenominationCode {

    public ModelPacket0001 modelPacket0001 = new ModelPacket0001();
    public ModelPacket0513 modelPacket0513 = new ModelPacket0513();
    public ModelPacket051A modelPacket051A = new ModelPacket051A();

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
        modelPacket0001.setCommand("6400");

        modelPacket0513.setPacketId(packet.PKT_0513);
        modelPacket0513.setLength(length.LENGTH_0102);
        modelPacket0513.setMode("80");
        modelPacket0513.setStatus("00");

        modelPacket0513.setDenominationCodeNoteId1("01");
        modelPacket0513.setOptionNoteId1("00");
        modelPacket0513.setDenominationCodeNoteId2("02");
        modelPacket0513.setOptionNoteId2("00");
        modelPacket0513.setDenominationCodeNoteId3("00");
        modelPacket0513.setOptionNoteId3("00");
        modelPacket0513.setDenominationCodeNoteId4("00");
        modelPacket0513.setOptionNoteId4("00");
        modelPacket0513.setDenominationCodeNoteId5("01");
        modelPacket0513.setOptionNoteId5("00");
        modelPacket0513.setDenominationCodeNoteId6("02");
        modelPacket0513.setOptionNoteId6("00");
        modelPacket0513.setDenominationCodeNoteId7("00");
        modelPacket0513.setOptionNoteId7("00");
        modelPacket0513.setDenominationCodeNoteId8("00");
        modelPacket0513.setOptionNoteId8("00");

        modelPacket0513.setDenominationCodeNoteId9("09");
        modelPacket0513.setOptionNoteId9("00");
        modelPacket0513.setDenominationCodeNoteId10("0A");
        modelPacket0513.setOptionNoteId10("00");
        modelPacket0513.setDenominationCodeNoteId11("09");
        modelPacket0513.setOptionNoteId11("00");
        modelPacket0513.setDenominationCodeNoteId12("0A");
        modelPacket0513.setOptionNoteId12("00");
        modelPacket0513.setDenominationCodeNoteId13("00");
        modelPacket0513.setOptionNoteId13("00");
        modelPacket0513.setDenominationCodeNoteId14("00");
        modelPacket0513.setOptionNoteId14("00");
        modelPacket0513.setDenominationCodeNoteId15("00");
        modelPacket0513.setOptionNoteId15("00");
        modelPacket0513.setDenominationCodeNoteId16("00");
        modelPacket0513.setOptionNoteId16("00");

        modelPacket0513.setDenominationCodeNoteId17("00");
        modelPacket0513.setOptionNoteId17("00");
        modelPacket0513.setDenominationCodeNoteId18("00");
        modelPacket0513.setOptionNoteId18("00");
        modelPacket0513.setDenominationCodeNoteId19("13");
        modelPacket0513.setOptionNoteId19("00");
        modelPacket0513.setDenominationCodeNoteId20("14");
        modelPacket0513.setOptionNoteId20("00");
        modelPacket0513.setDenominationCodeNoteId21("15");
        modelPacket0513.setOptionNoteId21("00");
        modelPacket0513.setDenominationCodeNoteId22("16");
        modelPacket0513.setOptionNoteId22("00");
        modelPacket0513.setDenominationCodeNoteId23("17");
        modelPacket0513.setOptionNoteId23("00");
        modelPacket0513.setDenominationCodeNoteId24("00");
        modelPacket0513.setOptionNoteId24("00");

        modelPacket0513.setDenominationCodeNoteId25("00");
        modelPacket0513.setOptionNoteId25("00");
        modelPacket0513.setDenominationCodeNoteId26("1A");
        modelPacket0513.setOptionNoteId26("00");
        modelPacket0513.setDenominationCodeNoteId27("00");
        modelPacket0513.setOptionNoteId27("00");
        modelPacket0513.setDenominationCodeNoteId28("00");
        modelPacket0513.setOptionNoteId28("00");
        modelPacket0513.setDenominationCodeNoteId29("00");
        modelPacket0513.setOptionNoteId29("00");
        modelPacket0513.setDenominationCodeNoteId30("00");
        modelPacket0513.setOptionNoteId30("00");
        modelPacket0513.setDenominationCodeNoteId31("00");
        modelPacket0513.setOptionNoteId31("00");
        modelPacket0513.setDenominationCodeNoteId32("00");
        modelPacket0513.setOptionNoteId32("00");

//        modelPacket051A.setPacketId(packet.PKT_0001);
//        modelPacket051A.setLength(length.LENGTH_0102);
//        modelPacket051A.setMode("");
//        modelPacket051A.setStatus("");
//        modelPacket051A.setDenominationCodeNoteId129("");
//        modelPacket051A.setReservedNoteId129("");
//        modelPacket051A.setDenominationCodeNoteId255("");
//        modelPacket051A.setReservedNoteId255("");

        returnValue = modelPacket0001.generatePacket() + modelPacket0513.generatePacket();
//                + modelPacket051A.generatePacket();

        String messageHeaderLength = messageDataLengthGenerator.getMessageHeaderLength(returnValue);
        returnValue = messageHeaderLength + returnValue;
        return returnValue;
    }

    public ModelPacket0081 modelPacket0081 = new ModelPacket0081();
    public ModelPacket008E modelPacket008E = new ModelPacket008E();
    public ModelPacket0581 modelPacket0581 = new ModelPacket0581();

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

        }
    }

}
