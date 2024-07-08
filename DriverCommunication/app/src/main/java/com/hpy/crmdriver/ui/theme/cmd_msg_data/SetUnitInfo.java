package com.hpy.crmdriver.ui.theme.cmd_msg_data;

import android.content.Context;
import android.text.TextUtils;

import com.hpy.crmdriver.ui.theme.cmd_formatter.MessageDataLengthGenerator;
import com.hpy.crmdriver.ui.theme.cmd_generator.CommandData;
import com.hpy.crmdriver.ui.theme.data.Length;
import com.hpy.crmdriver.ui.theme.data.Packet;
import com.hpy.crmdriver.ui.theme.data.Size;
import com.hpy.crmdriver.ui.theme.packet_model.ModelPacket0001;
import com.hpy.crmdriver.ui.theme.packet_model.ModelPacket0010;
import com.hpy.crmdriver.ui.theme.packet_model.ModelPacket0081;
import com.hpy.crmdriver.ui.theme.packet_model.ModelPacket008E;
import com.hpy.crmdriver.ui.theme.packet_model.ModelPacket0510;
import com.hpy.crmdriver.ui.theme.packet_model.ModelPacket0511;
import com.hpy.crmdriver.ui.theme.packet_model.ModelPacket0512;
import com.hpy.crmdriver.ui.theme.packet_model.ModelPacket0515;
import com.hpy.crmdriver.ui.theme.packet_model.ModelPacket0517;
import com.hpy.crmdriver.ui.theme.packet_model.ModelPacket0518;
import com.hpy.crmdriver.ui.theme.packet_model.ModelPacket0521;
import com.hpy.crmdriver.ui.theme.packet_model.ModelPacket0529;
import com.hpy.crmdriver.ui.theme.packet_model.ModelPacket0581;
import com.hpy.crmdriver.ui.theme.packet_model.ModelPacket0586;
import com.hpy.crmdriver.ui.theme.session.SessionModel;
import com.hpy.crmdriver.ui.theme.util.DateCalculator;
import com.hpy.crmdriver.ui.theme.util.DenominationInfo;
import com.hpy.crmdriver.ui.theme.util.StringHelper;

public class SetUnitInfo {

    public DateCalculator dateCalculator = new DateCalculator();
    public ModelPacket0001 modelPacket0001 = new ModelPacket0001();
    public ModelPacket0010 modelPacket0010 = new ModelPacket0010();
    public ModelPacket0511 modelPacket0511 = new ModelPacket0511();
    public ModelPacket0510 modelPacket0510 = new ModelPacket0510();
    public ModelPacket0512 modelPacket0512 = new ModelPacket0512();
    public ModelPacket0515 modelPacket0515 = new ModelPacket0515();
    public ModelPacket0517 modelPacket0517 = new ModelPacket0517();
    public ModelPacket0518 modelPacket0518 = new ModelPacket0518();
    public ModelPacket0521 modelPacket0521 = new ModelPacket0521();
    public ModelPacket0529 modelPacket0529 = new ModelPacket0529();
    public ModelPacket0586 modelPacket0586 = new ModelPacket0586();

    //missing 0527 - 0528 - 052C


    public Packet packet = new Packet();
    public Length length = new Length();
    public CommandData commandData = new CommandData();
    public MessageDataLengthGenerator messageDataLengthGenerator = new MessageDataLengthGenerator();
    public Size size = new Size();
    private SessionModel sessionModel = new SessionModel();


    public String generateCommand() {
        String returnValue = "";

        DenominationInfo denominationInfo = new DenominationInfo();

        modelPacket0001.setPacketId(packet.PKT_0001);
        modelPacket0001.setLength(length.LENGTH_0004);
        modelPacket0001.setCommand(commandData.CMD_0600);

        String year = dateCalculator.formatTwoDigitsWithHex(dateCalculator.getYear());
        String month = dateCalculator.formatTwoDigitsWithHex(dateCalculator.getMonth());
        String day = dateCalculator.formatTwoDigitsWithHex(dateCalculator.getDay());
        String hour = dateCalculator.formatTwoDigitsWithHex(dateCalculator.getHour());
        String minutes = dateCalculator.formatTwoDigitsWithHex(dateCalculator.getMinute());
        String seconds = dateCalculator.formatTwoDigitsWithHex(dateCalculator.getSecond());

        modelPacket0010.setPacketId(packet.PKT_0010);
        modelPacket0010.setLength(length.LENGTH_0008);
        modelPacket0010.setYear(year);
        modelPacket0010.setMonth(month);
        modelPacket0010.setDay(day);
        modelPacket0010.setHour(hour);
        modelPacket0010.setMinutes(minutes);
        modelPacket0010.setSeconds(seconds);

        modelPacket0511.setPacketId(packet.PKT_0511);
        modelPacket0511.setLength(length.LENGTH_0008);
        modelPacket0511.setStatus("0000");
        modelPacket0511.setOperationalInfo("000000FF");

        modelPacket0510.setPacketId(packet.PKT_0510);
        modelPacket0510.setLength(length.LENGTH_0014);
        modelPacket0510.setStatus("0000");
        modelPacket0510.setOperationalInfo("00000020000000008000000000000000");

        modelPacket0512.setPacketId(packet.PKT_0512);
        modelPacket0512.setLength(length.LENGTH_0008);
        modelPacket0512.setStatus("0000");
        modelPacket0512.setHardwareConfig("80091F00");

        modelPacket0515.setPacketId(packet.PKT_0515);
        modelPacket0515.setLength(length.LENGTH_0144);
        modelPacket0515.setStatus("0000");
        modelPacket0515.setInput1A(denominationInfo.ACCEPTANCE);
        modelPacket0515.setInput2A(denominationInfo.INR_10);
        modelPacket0515.setInput3A(denominationInfo.INR_20);
        modelPacket0515.setInput4A(denominationInfo.INR_50);
        modelPacket0515.setInput5A(denominationInfo.INR_100);

        modelPacket0515.setInput1B("00000000000000000000000000000000");
        modelPacket0515.setInput2B("00000000000000000000000000000000");
        modelPacket0515.setInput3B("00000000000000000000000000000000");
        modelPacket0515.setInput4B("00000000000000000000000000000000");
        modelPacket0515.setInput5B("00000000000000000000000000000000");
        modelPacket0515.setReserved("00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");

        //O5D0 - to check

        modelPacket0518.setPacketId(packet.PKT_0518);
        modelPacket0518.setLength(length.LENGTH_0022);

        modelPacket0518.setStatus("0000");
        modelPacket0518.setHardwareType_c1("03");
        modelPacket0518.setReserved_c1("00");
        modelPacket0518.setRoomA_c1("02");
        modelPacket0518.setRoomB_c1("00");
        modelPacket0518.setRoom_reserved_c1("0000");

        modelPacket0518.setHardwareType_c2("01");
        modelPacket0518.setReserved_c2("00");
        modelPacket0518.setRoomA_c2("01");
        modelPacket0518.setRoomB_c2("00");
        modelPacket0518.setRoom_reserved_c2("0000");

        modelPacket0518.setHardwareType_c3("01");
        modelPacket0518.setReserved_c3("00");
        modelPacket0518.setRoomA_c3("01");
        modelPacket0518.setRoomB_c3("00");
        modelPacket0518.setRoom_reserved_c3("0000");

        modelPacket0518.setHardwareType_c4("01");
        modelPacket0518.setReserved_c4("00");
        modelPacket0518.setRoomA_c4("01");
        modelPacket0518.setRoomB_c4("00");
        modelPacket0518.setRoom_reserved_c4("0000");

        modelPacket0518.setHardwareType_c5("01");
        modelPacket0518.setReserved_c5("00");
        modelPacket0518.setRoomA_c5("01");
        modelPacket0518.setRoomB_c5("00");
        modelPacket0518.setRoom_reserved_c5("0000");

        modelPacket0521.setPacketId(packet.PKT_0521);
        modelPacket0521.setLength(length.LENGTH_0024);
        modelPacket0521.setStatus("0000");
        modelPacket0521.setReserved1("000000");
        modelPacket0521.setInput1("70");
        modelPacket0521.setInput2("00");
        modelPacket0521.setInput3("00");
        modelPacket0521.setInput4("00");
        modelPacket0521.setInput5("00");
        modelPacket0521.setInput6("00");
        modelPacket0521.setInput7("00");
        modelPacket0521.setInput8("00");
        modelPacket0521.setInput9("00");
        modelPacket0521.setInput10("00");
        modelPacket0521.setReserved2("00000000000000000000");
        modelPacket0521.setUrjb1("08");
        modelPacket0521.setUrjb2("00");
        modelPacket0521.setReserved3("00000000000000");

        modelPacket0517.setPacketId(packet.PKT_0517);
        modelPacket0517.setLength(length.LENGTH_0014);
        modelPacket0517.setStatus("0000");
        modelPacket0517.setDenominationCode("FFFFFF00000000000000000000000000");

//        modelPacket0529.setPacketId(packet.PKT_0529);
        modelPacket0529.setPacketId("0729");
        modelPacket0529.setLength(length.LENGTH_002A);
        modelPacket0529.setDeposit_input1("01");
        modelPacket0529.setDeposit_input2("01");
        modelPacket0529.setDeposit_input3("01");
        modelPacket0529.setDeposit_input4("02");
        modelPacket0529.setDeposit_input5("02");
        modelPacket0529.setDeposit_input6("01");
        modelPacket0529.setDeposit_input7("01");
        modelPacket0529.setDeposit_input8("01");
        modelPacket0529.setDeposit_input9("02");
        modelPacket0529.setDeposit_input10("02");
        modelPacket0529.setDeposit_input11("00");
        modelPacket0529.setDeposit_reserved("000000000000000000");
        modelPacket0529.setDispense_input1("01");
        modelPacket0529.setDispense_input2("01");
        modelPacket0529.setDispense_input3("01");
        modelPacket0529.setDispense_input4("01");
        modelPacket0529.setDispense_input5("01");
        modelPacket0529.setDispense_input6("01");
        modelPacket0529.setDispense_input7("01");
        modelPacket0529.setDispense_input8("01");
        modelPacket0529.setDispense_input9("01");
        modelPacket0529.setDispense_input10("01");
        modelPacket0529.setDispense_input11("00");
        modelPacket0529.setDispense_reserved("000000000000000000");

        modelPacket0586.setPacketId(packet.PKT_0586);
        modelPacket0586.setLength(length.LENGTH_003E);
        modelPacket0586.setReserved_1("000000000000");
        modelPacket0586.setInput_1("FFFF");
        modelPacket0586.setInput_2("FFFF");
        modelPacket0586.setInput_3("FFFF");
        modelPacket0586.setInput_4("FFFF");
        modelPacket0586.setInput_5("FFFF");
        modelPacket0586.setInput_6("FFFF");
        modelPacket0586.setInput_7("0000");
        modelPacket0586.setInput_8("0000");
        modelPacket0586.setInput_9("0000");
        modelPacket0586.setInput_10("0000");
        modelPacket0586.setReserved_2("FFFF000000000000000000000000000000000000");
        modelPacket0586.setUrjb_1("0000");
        modelPacket0586.setUrjb_2("0000");
        modelPacket0586.setReserved_3("00000000000000000000");

        returnValue = modelPacket0001.generatePacket()
                + modelPacket0010.generatePacket()
                + modelPacket0511.generatePacket()
                + modelPacket0510.generatePacket()
                + modelPacket0512.generatePacket()
                + modelPacket0515.generatePacket()
                + modelPacket0518.generatePacket()
                + modelPacket0521.generatePacket()
                + modelPacket0517.generatePacket()
//                + modelPacket0529.generatePacket()
                + modelPacket0586.generatePacket();

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
