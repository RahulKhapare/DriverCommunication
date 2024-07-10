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
import com.hpy.crmdriver.ui.theme.packet_model.ModelPacket0540;
import com.hpy.crmdriver.ui.theme.packet_model.ModelPacket0541;
import com.hpy.crmdriver.ui.theme.packet_model.ModelPacket0550;
import com.hpy.crmdriver.ui.theme.packet_model.ModelPacket0581;
import com.hpy.crmdriver.ui.theme.packet_model.ModelPacket0585;
import com.hpy.crmdriver.ui.theme.packet_model.ModelPacket0586;
import com.hpy.crmdriver.ui.theme.packet_model.ModelPacket058A;
import com.hpy.crmdriver.ui.theme.packet_model.ModelPacket058B;
import com.hpy.crmdriver.ui.theme.packet_model.ModelPacket05A0;
import com.hpy.crmdriver.ui.theme.packet_model.ModelPacket05A1;
import com.hpy.crmdriver.ui.theme.packet_model.ModelPacket05A2;
import com.hpy.crmdriver.ui.theme.packet_model.ModelPacket05A3;
import com.hpy.crmdriver.ui.theme.packet_model.ModelPacket05A4;
import com.hpy.crmdriver.ui.theme.packet_model.ModelPacket05A5;
import com.hpy.crmdriver.ui.theme.packet_model.ModelPacket05A6;
import com.hpy.crmdriver.ui.theme.packet_model.ModelPacket05A7;
import com.hpy.crmdriver.ui.theme.packet_model.ModelPacket05C0;
import com.hpy.crmdriver.ui.theme.packet_model.ModelPacket05C2;
import com.hpy.crmdriver.ui.theme.packet_model.ModelPacket05C6;
import com.hpy.crmdriver.ui.theme.packet_model.ModelPacket05C7;
import com.hpy.crmdriver.ui.theme.packet_model.ModelPacket05C8;
import com.hpy.crmdriver.ui.theme.packet_model.ModelPacket05C9;
import com.hpy.crmdriver.ui.theme.packet_model.ModelPacket05CA;
import com.hpy.crmdriver.ui.theme.packet_model.ModelPacket05CB;
import com.hpy.crmdriver.ui.theme.packet_model.ModelPacket05CC;
import com.hpy.crmdriver.ui.theme.packet_model.ModelPacket05CD;
import com.hpy.crmdriver.ui.theme.packet_model.ModelPacket05D0;
import com.hpy.crmdriver.ui.theme.packet_model.ModelPacket05D1;
import com.hpy.crmdriver.ui.theme.packet_model.ModelPacket05D2;
import com.hpy.crmdriver.ui.theme.packet_model.ModelPacket05D3;
import com.hpy.crmdriver.ui.theme.packet_model.ModelPacket05DA;
import com.hpy.crmdriver.ui.theme.packet_model.ModelPacket05DC;
import com.hpy.crmdriver.ui.theme.packet_model.ModelPacket066A;
import com.hpy.crmdriver.ui.theme.session.SessionModel;
import com.hpy.crmdriver.ui.theme.util.AppConfig;
import com.hpy.crmdriver.ui.theme.util.SessionData;
import com.hpy.crmdriver.ui.theme.util.StringHelper;

public class Dispense {

    public ModelPacket0001 modelPacket0001 = new ModelPacket0001();
    public ModelPacket0541 modelPacket0541 = new ModelPacket0541();
    public ModelPacket0550 modelPacket0550 = new ModelPacket0550();
    public ModelPacket052A modelPacket052A = new ModelPacket052A();
    public ModelPacket0540 modelPacket0540 = new ModelPacket0540();

    //Missing - 0555

    public AppConfig appConfig = new AppConfig();
    public Packet packet = new Packet();
    public Length length = new Length();
    public CommandData commandData = new CommandData();
    public MessageDataLengthGenerator messageDataLengthGenerator = new MessageDataLengthGenerator();
    public Size size = new Size();
    private SessionModel sessionModel = new SessionModel();

    public String generateCommand(Context context) {

        String returnValue = "";

        String cmdType = SessionData.getStringValue(context, appConfig.DISPENSE_VALUE);
        if (cmdType.equals(appConfig.DISPENSE_PER_DENOMINATION)) {
            returnValue = getDispensePerDenomination(context);
        } else if (cmdType.equals(appConfig.DISPENSE_PER_ROOM)) {
            returnValue = getDispensePerRoom(context);
        }

        String messageHeaderLength = messageDataLengthGenerator.getMessageHeaderLength(returnValue);
        returnValue = messageHeaderLength + returnValue;
        return returnValue;
    }

    public String getDispensePerDenomination(Context context) {
        String returnValue = "";

        String appMode = SessionData.getStringValue(context, appConfig.APP_MODE_VALUE);

        modelPacket0001.setPacketId(packet.PKT_0001);
        modelPacket0001.setLength(length.LENGTH_0004);

        if (appMode.equalsIgnoreCase(appConfig.APP_MODE_LIVE)) {
            modelPacket0001.setCommand("2001");
        } else if (appMode.equalsIgnoreCase(appConfig.APP_MODE_TEST)) {
            modelPacket0001.setCommand("2F01");
        }

        modelPacket0541.setPacketId(packet.PKT_0541);
        modelPacket0541.setLength(length.LENGTH_0032);

        modelPacket0541.setDenominationCode_1("09");
        modelPacket0541.setReserved_1("00");
        modelPacket0541.setNoDispenseNote_1("0001");

        modelPacket0541.setDenominationCode_2("00");
        modelPacket0541.setReserved_2("00");
        modelPacket0541.setNoDispenseNote_2("0000");

        modelPacket0541.setDenominationCode_3("00");
        modelPacket0541.setReserved_3("00");
        modelPacket0541.setNoDispenseNote_3("0000");

        modelPacket0541.setDenominationCode_4("00");
        modelPacket0541.setReserved_4("00");
        modelPacket0541.setNoDispenseNote_4("0000");

        modelPacket0541.setDenominationCode_5("00");
        modelPacket0541.setReserved_5("00");
        modelPacket0541.setNoDispenseNote_5("0000");

        modelPacket0541.setReserved("0000000000000000");

//        modelPacket0550.setPacketId(packet.PKT_0550);
//        modelPacket0550.setLength(length.LENGTH_0022);
//        modelPacket0550.setReserved_1("");
//        modelPacket0550.setInput_1("");
//        modelPacket0550.setInput_2("");
//        modelPacket0550.setInput_3("");
//        modelPacket0550.setInput_4("");
//        modelPacket0550.setInput_5("");
//        modelPacket0550.setInput_6("");
//        modelPacket0550.setInput_7("");
//        modelPacket0550.setInput_8("");
//        modelPacket0550.setInput_9("");
//        modelPacket0550.setInput_10("");
//        modelPacket0550.setReserved_2("");
//        modelPacket0550.setUrjb_1("");
//        modelPacket0550.setUrjb_2("");
//        modelPacket0550.setReserved_3("");
//
//        modelPacket052A.setPacketId(packet.PKT_052A);
//        modelPacket052A.setLength(length.LENGTH_0004);
//        modelPacket052A.setRecordModeAreImage("");
//        modelPacket052A.setReserved("");

        returnValue = modelPacket0001.generatePacket() + modelPacket0541.generatePacket();
//                modelPacket0550.generatePacket() + modelPacket052A.generatePacket();

        return returnValue;
    }

    public String getDispensePerRoom(Context context) {
        String returnValue = "";

        String appMode = SessionData.getStringValue(context, appConfig.APP_MODE_VALUE);

        modelPacket0001.setPacketId(packet.PKT_0001);
        modelPacket0001.setLength(length.LENGTH_0004);

        if (appMode.equalsIgnoreCase(appConfig.APP_MODE_TEST)) {
            modelPacket0001.setCommand("2F11");
        } else if (appMode.equalsIgnoreCase(appConfig.APP_MODE_LIVE)) {
            modelPacket0001.setCommand("2011");
        }

        modelPacket0540.setPacketId(packet.PKT_0540);
        modelPacket0540.setLength(length.LENGTH_0032);

        modelPacket0540.setRoomNo_1("2A");
        modelPacket0540.setReserved_1("00");
        modelPacket0540.setNoDispenseNote_1("0003");

        modelPacket0540.setRoomNo_2("3A");
        modelPacket0540.setReserved_2("00");
        modelPacket0540.setNoDispenseNote_2("0002");

        modelPacket0540.setRoomNo_3("4A");
        modelPacket0540.setReserved_3("00");
        modelPacket0540.setNoDispenseNote_3("0003");

        modelPacket0540.setRoomNo_4("00");
        modelPacket0540.setReserved_4("00");
        modelPacket0540.setNoDispenseNote_4("0000");

        modelPacket0540.setRoomNo_5("00");
        modelPacket0540.setReserved_5("00");
        modelPacket0540.setNoDispenseNote_5("0000");

        modelPacket0540.setReserved("0000000000000000");

//        modelPacket0550.setPacketId(packet.PKT_0550);
//        modelPacket0550.setLength(length.LENGTH_0022);
//        modelPacket0550.setReserved_1("");
//        modelPacket0550.setInput_1("");
//        modelPacket0550.setInput_2("");
//        modelPacket0550.setInput_3("");
//        modelPacket0550.setInput_4("");
//        modelPacket0550.setInput_5("");
//        modelPacket0550.setInput_6("");
//        modelPacket0550.setInput_7("");
//        modelPacket0550.setInput_8("");
//        modelPacket0550.setInput_9("");
//        modelPacket0550.setInput_10("");
//        modelPacket0550.setReserved_2("");
//        modelPacket0550.setUrjb_1("");
//        modelPacket0550.setUrjb_2("");
//        modelPacket0550.setReserved_3("");
//
//        modelPacket052A.setPacketId(packet.PKT_052A);
//        modelPacket052A.setLength(length.LENGTH_0004);
//        modelPacket052A.setRecordModeAreImage("");
//        modelPacket052A.setReserved("");

        returnValue = modelPacket0001.generatePacket() + modelPacket0540.generatePacket();
//                + modelPacket0550.generatePacket() + modelPacket052A.generatePacket();

        return returnValue;
    }

    public ModelPacket0081 modelPacket0081 = new ModelPacket0081();
    public ModelPacket008E modelPacket008E = new ModelPacket008E();
    public ModelPacket0581 modelPacket0581 = new ModelPacket0581();
    public ModelPacket0586 modelPacket0586 = new ModelPacket0586();
    public ModelPacket058A modelPacket058A = new ModelPacket058A();
    public ModelPacket058B modelPacket058B = new ModelPacket058B();
    public ModelPacket05C0 modelPacket05C0 = new ModelPacket05C0();
    public ModelPacket05D0 modelPacket05D0 = new ModelPacket05D0();
    public ModelPacket05D1 modelPacket05D1 = new ModelPacket05D1();
    public ModelPacket05D2 modelPacket05D2 = new ModelPacket05D2();
    public ModelPacket05D3 modelPacket05D3 = new ModelPacket05D3();
    public ModelPacket05C2 modelPacket05C2 = new ModelPacket05C2();
    public ModelPacket05C6 modelPacket05C6 = new ModelPacket05C6();
    public ModelPacket05C7 modelPacket05C7 = new ModelPacket05C7();
    public ModelPacket05C8 modelPacket05C8 = new ModelPacket05C8();
    public ModelPacket05C9 modelPacket05C9 = new ModelPacket05C9();
    public ModelPacket05CA modelPacket05CA = new ModelPacket05CA();
    public ModelPacket05CB modelPacket05CB = new ModelPacket05CB();
    public ModelPacket05CC modelPacket05CC = new ModelPacket05CC();
    public ModelPacket05CD modelPacket05CD = new ModelPacket05CD();
    public ModelPacket05DA modelPacket05DA = new ModelPacket05DA();
    public ModelPacket05DC modelPacket05DC = new ModelPacket05DC();
    public ModelPacket05A0 modelPacket05A0 = new ModelPacket05A0();
    public ModelPacket05A1 modelPacket05A1 = new ModelPacket05A1();
    public ModelPacket05A2 modelPacket05A2 = new ModelPacket05A2();
    public ModelPacket05A3 modelPacket05A3 = new ModelPacket05A3();
    public ModelPacket05A4 modelPacket05A4 = new ModelPacket05A4();
    public ModelPacket05A5 modelPacket05A5 = new ModelPacket05A5();
    public ModelPacket05A6 modelPacket05A6 = new ModelPacket05A6();
    public ModelPacket05A7 modelPacket05A7 = new ModelPacket05A7();
    public ModelPacket0585 modelPacket0585 = new ModelPacket0585();

    //Missing - 06A0,06A1,06A2,06A3

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
            int length6 = stringHelper.getMultiplyValue(size.SIZE_64);
            String noOfStakeNotes = stringHelper.getSubstringData(value, startPos6, startPos6 + length6);
            modelPacket058A.parsePacket(noOfStakeNotes);
            sessionModel.saveModelToSession(context, packet.PKT_058A, modelPacket058A);

            int startPos7 = startPos6 + length6;
            int length7 = stringHelper.getMultiplyValue(size.SIZE_36);
            String noOfFedNotes = stringHelper.getSubstringData(value, startPos7, startPos7 + length7);
            modelPacket058B.parsePacket(noOfFedNotes);
            sessionModel.saveModelToSession(context, packet.PKT_058B, modelPacket058B);

            int startPos8 = startPos7 + length7;
            int length8 = stringHelper.getMultiplyValue(size.SIZE_28);
            String noOfDispenseNotesPerRoom = stringHelper.getSubstringData(value, startPos8, startPos8 + length8);
            modelPacket05C0.parsePacket(noOfDispenseNotesPerRoom);
            sessionModel.saveModelToSession(context, packet.PKT_05C0, modelPacket05C0);

            //TODO - Check for data
            int startPos9 = startPos8 + length8;
            int length9 = stringHelper.getMultiplyValue(2);
            String noOfStakedNotesPerDenominationAndDestination = stringHelper.getSubstringData(value, startPos9, startPos9 + length9);
            modelPacket05D0.parsePacket(noOfStakedNotesPerDenominationAndDestination);
//            modelPacket05D1.parsePacket(noOfStakedNotesPerDenominationAndDestination);
//            modelPacket05D2.parsePacket(noOfStakedNotesPerDenominationAndDestination);
//            modelPacket05D3.parsePacket(noOfStakedNotesPerDenominationAndDestination);
            sessionModel.saveModelToSession(context, packet.PKT_05D0, modelPacket05D0);

            //TODO - Check for data
            int startPos10 = startPos9 + length9;
            int length10 = stringHelper.getMultiplyValue(size.SIZE_68);
            String noOfRejectedNotesPerDenominationAndSource = stringHelper.getSubstringData(value, startPos10, startPos10 + length10);
            modelPacket05C2.parsePacket(noOfRejectedNotesPerDenominationAndSource);
            sessionModel.saveModelToSession(context, packet.PKT_05C2, modelPacket05C2);


            //TODO - Check for data
            int startPos11 = startPos10 + length10;
            int length11 = stringHelper.getMultiplyValue(2);
            String noOfRejectedNotesPerBVDenominationAndDestination = stringHelper.getSubstringData(value, startPos11, startPos11 + length11);
            modelPacket05C6.parsePacket(noOfRejectedNotesPerBVDenominationAndDestination);
//            modelPacket05C7.parsePacket(noOfRejectedNotesPerBVDenominationAndDestination);
//            modelPacket05C8.parsePacket(noOfRejectedNotesPerBVDenominationAndDestination);
//            modelPacket05C9.parsePacket(noOfRejectedNotesPerBVDenominationAndDestination);
            sessionModel.saveModelToSession(context, packet.PKT_05C6, modelPacket05C6);

            //TODO - Check for data
            int startPos12 = startPos11 + length11;
            int length12 = stringHelper.getMultiplyValue(2);
            String noOfRejectedNotesPerDenominationAndDestinationFollowedByBV = stringHelper.getSubstringData(value, startPos12, startPos12 + length12);
            modelPacket05CA.parsePacket(noOfRejectedNotesPerDenominationAndDestinationFollowedByBV);
//            modelPacket05CB.parsePacket(noOfRejectedNotesPerDenominationAndDestinationFollowedByBV);
//            modelPacket05CC.parsePacket(noOfRejectedNotesPerDenominationAndDestinationFollowedByBV);
//            modelPacket05CD.parsePacket(noOfRejectedNotesPerDenominationAndDestinationFollowedByBV);
            sessionModel.saveModelToSession(context, packet.PKT_05CA, modelPacket05CA);

            int startPos13 = startPos12 + length12;
            int length13 = stringHelper.getMultiplyValue(size.SIZE_6);
            String misFeedRoom = stringHelper.getSubstringData(value, startPos13, startPos13 + length13);
            modelPacket05DA.parsePacket(misFeedRoom);
            sessionModel.saveModelToSession(context, packet.PKT_05DA, modelPacket05DA);

            int startPos14 = startPos13 + length13;
            int length14 = stringHelper.getMultiplyValue(size.SIZE_6);
            String reliabilityOfNoFedNote = stringHelper.getSubstringData(value, startPos14, startPos14 + length14);
            modelPacket05DC.parsePacket(reliabilityOfNoFedNote);
            sessionModel.saveModelToSession(context, packet.PKT_05DC, modelPacket05DC);

            //TODO - Check for data
            int startPos15 = startPos14 + length14;
            int length15 = stringHelper.getMultiplyValue(2);
            String noOfCategory2PerDenominationAndDestination = stringHelper.getSubstringData(value, startPos15, startPos15 + length15);
            modelPacket05A0.parsePacket(noOfCategory2PerDenominationAndDestination);
//            modelPacket05A1.parsePacket(noOfCategory2PerDenominationAndDestination);
//            modelPacket05A2.parsePacket(noOfCategory2PerDenominationAndDestination);
//            modelPacket05A3.parsePacket(noOfCategory2PerDenominationAndDestination);
            sessionModel.saveModelToSession(context, packet.PKT_05A0, modelPacket05A0);

            //TODO - Check for data
            int startPos16 = startPos15 + length15;
            int length16 = stringHelper.getMultiplyValue(2);
            String noOfCategory3PerDenominationAndDestination = stringHelper.getSubstringData(value, startPos16, startPos16 + length16);
            modelPacket05A4.parsePacket(noOfCategory3PerDenominationAndDestination);
//            modelPacket05A5.parsePacket(noOfCategory3PerDenominationAndDestination);
//            modelPacket05A6.parsePacket(noOfCategory3PerDenominationAndDestination);
//            modelPacket05A7.parsePacket(noOfCategory3PerDenominationAndDestination);
            sessionModel.saveModelToSession(context, packet.PKT_05A4, modelPacket05A4);

            //TODO - Check for data
            int startPos17 = startPos16 + length16;
            int length17 = stringHelper.getMultiplyValue(2);
            String noOfRejectedNotePerDenominationAndDestinationByBVOrSource = stringHelper.getSubstringData(value, startPos17, startPos17 + length17);
            //Missing -  06A0,06A1,06A2,06A3

            int startPos18 = startPos17 + length17;
            int length18 = stringHelper.getMultiplyValue(size.SIZE_4100);
            String maintenanceInformation = stringHelper.getSubstringData(value, startPos18, startPos18 + length18);
            modelPacket0585.parsePacket(maintenanceInformation);
            sessionModel.saveModelToSession(context, packet.PKT_0585, modelPacket0585);

        }
    }
}
