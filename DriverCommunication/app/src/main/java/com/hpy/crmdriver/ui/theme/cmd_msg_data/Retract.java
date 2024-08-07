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
import com.hpy.crmdriver.ui.theme.packet_model.ModelPacket0550;
import com.hpy.crmdriver.ui.theme.packet_model.ModelPacket0551;
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
import com.hpy.crmdriver.ui.theme.packet_model.ModelPacket05A8;
import com.hpy.crmdriver.ui.theme.packet_model.ModelPacket05A9;
import com.hpy.crmdriver.ui.theme.packet_model.ModelPacket05AA;
import com.hpy.crmdriver.ui.theme.packet_model.ModelPacket05AB;
import com.hpy.crmdriver.ui.theme.packet_model.ModelPacket05CA;
import com.hpy.crmdriver.ui.theme.packet_model.ModelPacket05CB;
import com.hpy.crmdriver.ui.theme.packet_model.ModelPacket05CC;
import com.hpy.crmdriver.ui.theme.packet_model.ModelPacket05CD;
import com.hpy.crmdriver.ui.theme.packet_model.ModelPacket05D0;
import com.hpy.crmdriver.ui.theme.packet_model.ModelPacket05D1;
import com.hpy.crmdriver.ui.theme.packet_model.ModelPacket05D2;
import com.hpy.crmdriver.ui.theme.packet_model.ModelPacket05D3;
import com.hpy.crmdriver.ui.theme.session.SessionModel;
import com.hpy.crmdriver.ui.theme.util.AppConfig;
import com.hpy.crmdriver.ui.theme.util.SessionData;
import com.hpy.crmdriver.ui.theme.util.StringHelper;

public class Retract {

    public ModelPacket0001 modelPacket0001 = new ModelPacket0001();
    public ModelPacket0550 modelPacket0550 = new ModelPacket0550();
    public ModelPacket0551 modelPacket0551 = new ModelPacket0551();
    public ModelPacket052A modelPacket052A = new ModelPacket052A();

    //Missing - 0555

    public Packet packet = new Packet();
    public Length length = new Length();
    public CommandData commandData = new CommandData();
    public MessageDataLengthGenerator messageDataLengthGenerator = new MessageDataLengthGenerator();
    public Size size = new Size();
    private SessionModel sessionModel = new SessionModel();
    public AppConfig appConfig = new AppConfig();

    public String generateCommand(Context context) {
        String returnValue = "";

        String appMode = SessionData.getStringValue(context, appConfig.APP_MODE_VALUE);
        String amdType = SessionData.getStringValue(context, appConfig.RETRACT_VALUE);

        modelPacket0001.setPacketId(packet.PKT_0001);
        modelPacket0001.setLength(length.LENGTH_0004);

        if (amdType.equalsIgnoreCase(appConfig.RETRACT_CS)) {
            if (appMode.equalsIgnoreCase(appConfig.APP_MODE_LIVE)) {
                modelPacket0001.setCommand("4010");
            } else if (appMode.equalsIgnoreCase(appConfig.APP_MODE_TEST)) {
                modelPacket0001.setCommand("4F10");
            }
        } else if (amdType.equalsIgnoreCase(appConfig.RETRACT_ESC)) {
            if (appMode.equalsIgnoreCase(appConfig.APP_MODE_LIVE)) {
                modelPacket0001.setCommand("4020");
            } else if (appMode.equalsIgnoreCase(appConfig.APP_MODE_TEST)) {
                modelPacket0001.setCommand("4F20");
            }
        } else if (amdType.equalsIgnoreCase(appConfig.RETRACT_ESC_CS)) {
            if (appMode.equalsIgnoreCase(appConfig.APP_MODE_LIVE)) {
                modelPacket0001.setCommand("4030");
            } else if (appMode.equalsIgnoreCase(appConfig.APP_MODE_TEST)) {
                modelPacket0001.setCommand("4F30");
            }
        } else if (amdType.equalsIgnoreCase(appConfig.RETRACT_ESC_DISPENSE_REJECT)) {
            if (appMode.equalsIgnoreCase(appConfig.APP_MODE_LIVE)) {
                modelPacket0001.setCommand("40A0");
            } else if (appMode.equalsIgnoreCase(appConfig.APP_MODE_TEST)) {
                modelPacket0001.setCommand("4FA0");
            }
        }

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
//        modelPacket0551.setPacketId(packet.PKT_0551);
//        modelPacket0551.setLength(length.LENGTH_0004);
//        modelPacket0551.setDestinationForReject("");
//
//        modelPacket052A.setPacketId(packet.PKT_052A);
//        modelPacket052A.setLength(length.LENGTH_0004);
//        modelPacket052A.setRecordModeAreImage("");
//        modelPacket052A.setReserved("");

        returnValue = modelPacket0001.generatePacket();
//                + modelPacket0550.generatePacket() +
//                modelPacket0551.generatePacket() + modelPacket052A.generatePacket();

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
    public ModelPacket05A0 modelPacket05A0 = new ModelPacket05A0();
    public ModelPacket05A1 modelPacket05A1 = new ModelPacket05A1();
    public ModelPacket05A2 modelPacket05A2 = new ModelPacket05A2();
    public ModelPacket05A3 modelPacket05A3 = new ModelPacket05A3();
    public ModelPacket05A4 modelPacket05A4 = new ModelPacket05A4();
    public ModelPacket05A5 modelPacket05A5 = new ModelPacket05A5();
    public ModelPacket05A6 modelPacket05A6 = new ModelPacket05A6();
    public ModelPacket05A7 modelPacket05A7 = new ModelPacket05A7();
    public ModelPacket05A8 modelPacket05A8 = new ModelPacket05A8();
    public ModelPacket05A9 modelPacket05A9 = new ModelPacket05A9();
    public ModelPacket05AA modelPacket05AA = new ModelPacket05AA();
    public ModelPacket05AB modelPacket05AB = new ModelPacket05AB();
    public ModelPacket05CA modelPacket05CA = new ModelPacket05CA();
    public ModelPacket05CB modelPacket05CB = new ModelPacket05CB();
    public ModelPacket05CC modelPacket05CC = new ModelPacket05CC();
    public ModelPacket05CD modelPacket05CD = new ModelPacket05CD();
    public ModelPacket0585 modelPacket0585 = new ModelPacket0585();


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

            //TODO - Check for data
            int startPos8 = startPos7 + length7;
            int length8 = stringHelper.getMultiplyValue(1);
            String noOfStakeNotesPerDenominationAndDestination = stringHelper.getSubstringData(value, startPos8, startPos8 + length8);
            modelPacket05D0.parsePacket(noOfStakeNotesPerDenominationAndDestination);
//            modelPacket05D1.parsePacket(noOfStakeNotesPerDenominationAndDestination);
//            modelPacket05D2.parsePacket(noOfStakeNotesPerDenominationAndDestination);
//            modelPacket05D3.parsePacket(noOfStakeNotesPerDenominationAndDestination);
            sessionModel.saveModelToSession(context, packet.PKT_05D0, modelPacket05D0);

            //TODO - Check for data
            int startPos9 = startPos8 + length8;
            int length9 = stringHelper.getMultiplyValue(1);
            String noOfCat2PerDenominationAndDestination = stringHelper.getSubstringData(value, startPos9, startPos9 + length9);
            modelPacket05A0.parsePacket(noOfCat2PerDenominationAndDestination);
//            modelPacket05A1.parsePacket(noOfCat2PerDenominationAndDestination);
//            modelPacket05A2.parsePacket(noOfCat2PerDenominationAndDestination);
//            modelPacket05A3.parsePacket(noOfCat2PerDenominationAndDestination);
            sessionModel.saveModelToSession(context, packet.PKT_05A0, modelPacket05A0);

            //TODO - Check for data
            int startPos10 = startPos9 + length9;
            int length10 = stringHelper.getMultiplyValue(1);
            String noOfCat3PerDenominationAndDestination = stringHelper.getSubstringData(value, startPos10, startPos10 + length10);
            modelPacket05A4.parsePacket(noOfCat3PerDenominationAndDestination);
//            modelPacket05A5.parsePacket(noOfCat3PerDenominationAndDestination);
//            modelPacket05A6.parsePacket(noOfCat3PerDenominationAndDestination);
//            modelPacket05A7.parsePacket(noOfCat3PerDenominationAndDestination);
            sessionModel.saveModelToSession(context, packet.PKT_05A4, modelPacket05A4);

            //TODO - Check for data
            int startPos11 = startPos10 + length10;
            int length11 = stringHelper.getMultiplyValue(1);
            String noOfUnitPerDenominationAndDestination = stringHelper.getSubstringData(value, startPos11, startPos11 + length11);
            modelPacket05A8.parsePacket(noOfCat3PerDenominationAndDestination);
//            modelPacket05A9.parsePacket(noOfCat3PerDenominationAndDestination);
//            modelPacket05AA.parsePacket(noOfCat3PerDenominationAndDestination);
//            modelPacket05AB.parsePacket(noOfCat3PerDenominationAndDestination);
            sessionModel.saveModelToSession(context, packet.PKT_05A8, modelPacket05A8);

            //TODO - Check for data
            int startPos12 = startPos11 + length11;
            int length12 = stringHelper.getMultiplyValue(1);
            String noOfRejectedNotesPerDenominationAndDestinationByBVInformation = stringHelper.getSubstringData(value, startPos12, startPos12 + length12);
            modelPacket05CA.parsePacket(noOfRejectedNotesPerDenominationAndDestinationByBVInformation);
//            modelPacket05CB.parsePacket(noOfRejectedNotesPerDenominationAndDestinationByBVInformation);
//            modelPacket05CC.parsePacket(noOfRejectedNotesPerDenominationAndDestinationByBVInformation);
//            modelPacket05CD.parsePacket(noOfRejectedNotesPerDenominationAndDestinationByBVInformation);
            sessionModel.saveModelToSession(context, packet.PKT_05CA, modelPacket05CA);

            int startPos13 = startPos12 + length12;
            int length13 = stringHelper.getMultiplyValue(size.SIZE_4100);
            String maintenanceInformation = stringHelper.getSubstringData(value, startPos13, startPos13 + length13);
            modelPacket0585.parsePacket(maintenanceInformation);
            sessionModel.saveModelToSession(context, packet.PKT_0585, modelPacket0585);
        }
    }

}
