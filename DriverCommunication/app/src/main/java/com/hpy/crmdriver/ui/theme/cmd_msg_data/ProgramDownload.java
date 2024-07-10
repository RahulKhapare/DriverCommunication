package com.hpy.crmdriver.ui.theme.cmd_msg_data;

import android.content.Context;
import android.text.TextUtils;

import com.hpy.crmdriver.ui.theme.cmd_formatter.MessageDataLengthGenerator;
import com.hpy.crmdriver.ui.theme.cmd_generator.CommandData;
import com.hpy.crmdriver.ui.theme.data.Length;
import com.hpy.crmdriver.ui.theme.data.Packet;
import com.hpy.crmdriver.ui.theme.data.Size;
import com.hpy.crmdriver.ui.theme.packet_model.ModelPacket0001;
import com.hpy.crmdriver.ui.theme.packet_model.ModelPacket0005;
import com.hpy.crmdriver.ui.theme.packet_model.ModelPacket0006;
import com.hpy.crmdriver.ui.theme.packet_model.ModelPacket0081;
import com.hpy.crmdriver.ui.theme.packet_model.ModelPacket008E;
import com.hpy.crmdriver.ui.theme.packet_model.ModelPacket0581;
import com.hpy.crmdriver.ui.theme.session.SessionModel;
import com.hpy.crmdriver.ui.theme.util.AppConfig;
import com.hpy.crmdriver.ui.theme.util.ApplicationData;
import com.hpy.crmdriver.ui.theme.util.KeyValue;
import com.hpy.crmdriver.ui.theme.util.SessionData;
import com.hpy.crmdriver.ui.theme.util.StringHelper;
import com.hpy.crmdriver.ui.theme.util.ValueConvertor;

public class ProgramDownload {

    public ModelPacket0001 modelPacket0001 = new ModelPacket0001();
    public ModelPacket0005 modelPacket0005 = new ModelPacket0005();
    public ModelPacket0006 modelPacket0006 = new ModelPacket0006();

    public Packet packet = new Packet();
    public Length length = new Length();
    public CommandData commandData = new CommandData();
    public MessageDataLengthGenerator messageDataLengthGenerator = new MessageDataLengthGenerator();
    public Size size = new Size();
    public ValueConvertor valueConvertor = new ValueConvertor();
    private SessionModel sessionModel = new SessionModel();
    private SessionData sessionData = new SessionData();
    private AppConfig appConfig = new AppConfig();


    public String generateCommand(Context context) {
        String returnValue = "";

        String cmdType = SessionData.getStringValue(context, appConfig.PROGRAM_DOWNLOAD_VALUE);

        modelPacket0001.setPacketId(packet.PKT_0001);
        modelPacket0001.setLength(length.LENGTH_0004);
        if (cmdType.equals(appConfig.PROGRAM_DOWNLOAD_START)) {
            modelPacket0001.setCommand("0301");
            returnValue = modelPacket0001.generatePacket();
        } else if (cmdType.equals(appConfig.PROGRAM_DOWNLOAD_SEND_DATA)) {
            modelPacket0001.setCommand("0302");

//            String controlId = ApplicationData.control_ID;
            String controlId = "0595";
            String writingAddress = ApplicationData.writing_address;
            modelPacket0005.setPacketId(packet.PKT_0005);
            modelPacket0005.setLength(length.LENGTH_000C);
            modelPacket0005.setControlId(controlId);
//            modelPacket0005.setPdlBlockType("");//added below
//            modelPacket0005.setReserved("");//added below
            modelPacket0005.setWritingAddress(writingAddress);

            String pdlData = ApplicationData.pdl_data;
            int pdlLength = (pdlData.length() / 2) + 2;
            modelPacket0006.setPacketId(packet.PKT_0006);
            //modelPacket0006.setLength(valueConvertor.decimalToHexString(pdlLength));
            modelPacket0006.setLength("0006");
            modelPacket0006.setPdlData(pdlData);

            returnValue = modelPacket0001.generatePacket() + modelPacket0005.generatePacket() + modelPacket0006.generatePacket();

        } else if (cmdType.equals(appConfig.PROGRAM_DOWNLOAD_END)) {
            modelPacket0001.setCommand("0303");
            returnValue = modelPacket0001.generatePacket();
        }


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

    private String getPDLManagementInfo() {
        String returnValue = "";

        String data =
                "0000" + "0000" + "00000000" +
                        "0000" + "0000" + "00007168" +
                        "0000" + "0000" + "00014336" +
                        "0000" + "0000" + "00014336";

        return returnValue;
    }

    private String getWritingAddress(long number, long limit) {
        String returnValue = "";
        long lessValue = 0;
        long maxValue = 0;
        for (int multiplier = 1; ; multiplier++) {
            long result = (long) number * multiplier;

            // Check if the result is greater than or equal to the limit
            if (result < limit) {
                lessValue = result;
//                AppLogs.generateTAG("ProgramData - " + multiplier, "0001 " + "0000 " + String.format("%08d", result));
                returnValue = returnValue + "0001" + "0000" + String.format("%08d", result);
            } else if (result > limit) {
                maxValue = result;
                long finalValue = maxValue - lessValue;
                returnValue = returnValue + "FFFF" + "0000" + String.format("%08d", finalValue);
//                AppLogs.generateTAG("ProgramData - " + multiplier, "FFFF " + "0000 " + String.format("%08d", finalValue));
//                AppLogs.generateTAG("ProgramData ", "Final " + String.format("%08d", finalValue));
                break;
            }
        }
        return returnValue;
    }


    private String getPDLData(long number, long limit) {
        String returnValue = "";
        long lessValue = 0;
        long maxValue = 0;
        for (int multiplier = 1; ; multiplier++) {
            long result = (long) number * multiplier;
            if (result < limit) {
                lessValue = result;
                long length = lessValue + 2;
                returnValue = returnValue + length + lessValue;
//                AppLogs.generateTAG("PDLData - ", length + " " + lessValue);
            } else if (result > limit) {
                maxValue = result;
                long finalValue = maxValue - lessValue;
                long length = finalValue + 2;
                returnValue = returnValue + length + finalValue;
//                AppLogs.generateTAG("PDLData - ", length + " " + finalValue);
                break;
            }
        }
        return returnValue;
    }
}
