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
import com.hpy.crmdriver.ui.theme.util.StringHelper;

public class SetUnitInfo {

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

        modelPacket0001.setPacketId(packet.PKT_0001);
        modelPacket0001.setLength(length.LENGTH_0004);
        modelPacket0001.setCommand(commandData.CMD_0600);//check for value

        String data_0010 = "18061A0D111E";
        modelPacket0010.setPacketId(packet.PKT_0010);
        modelPacket0010.setLength(length.LENGTH_0008);
        modelPacket0010.setYear("");//convert into hexadecimal
        modelPacket0010.setMonth("");//convert into hexadecimal
        modelPacket0010.setDay("");//convert into hexadecimal
        modelPacket0010.setHour("");//convert into hexadecimal
        modelPacket0010.setMinutes("");//convert into hexadecimal
        modelPacket0010.setSeconds("");//convert into hexadecimal
        modelPacket0010.setSeconds(data_0010);//convert into hexadecimal

        String data_0511 = "0000000000FF";
        modelPacket0511.setPacketId(packet.PKT_0511);
        modelPacket0511.setLength(length.LENGTH_0008);
        modelPacket0511.setStatus("");//0000/0001
        modelPacket0511.setOperationalInfo("");
        modelPacket0511.setOperationalInfo(data_0511);

        String data_0510 = "000000000020000000008000000000000000";
        modelPacket0510.setPacketId(packet.PKT_0510);
        modelPacket0510.setLength(length.LENGTH_0014);
        modelPacket0510.setStatus("");
        modelPacket0510.setOperationalInfo("");
        modelPacket0510.setOperationalInfo(data_0510);

        String data_0512 = "000080091F00";
        modelPacket0512.setPacketId(packet.PKT_0512);
        modelPacket0512.setLength(length.LENGTH_0008);
        modelPacket0512.setStatus("");
        modelPacket0512.setHardwareConfig("");
        modelPacket0512.setHardwareConfig(data_0512);

        String data_0515 = "00000000000000000000000000000000000180000000000000000000000000000000000800000000000000000000000000000800000000000000000000000000000002000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";
        modelPacket0515.setPacketId(packet.PKT_0515);
        modelPacket0515.setLength(length.LENGTH_0144);
        modelPacket0515.setStatus("");
        modelPacket0515.setInput1("");
        modelPacket0515.setInput2("");
        modelPacket0515.setInput3("");
        modelPacket0515.setInput4("");
        modelPacket0515.setReserved("");
        modelPacket0515.setReserved(data_0515);

        String data_0518 = "0000030002000000010001000000010001000000010001000000010001000000";
        modelPacket0518.setPacketId(packet.PKT_0518);
        modelPacket0518.setLength(length.LENGTH_0022);
        modelPacket0518.setStatus("");
        modelPacket0518.setHardwareType_c1("");
        modelPacket0518.setReserved_c1("");
        modelPacket0518.setRoomA_c1("");
        modelPacket0518.setRoomB_c1("");
        modelPacket0518.setRoom_reserved_c1("");
        modelPacket0518.setHardwareType_c2("");
        modelPacket0518.setReserved_c2("");
        modelPacket0518.setRoomA_c2("");
        modelPacket0518.setRoomB_c2("");
        modelPacket0518.setRoom_reserved_c2("");
        modelPacket0518.setHardwareType_c3("");
        modelPacket0518.setReserved_c3("");
        modelPacket0518.setRoomA_c3("");
        modelPacket0518.setRoomB_c3("");
        modelPacket0518.setRoom_reserved_c3("");
        modelPacket0518.setHardwareType_c4("");
        modelPacket0518.setReserved_c4("");
        modelPacket0518.setRoomA_c4("");
        modelPacket0518.setRoomB_c4("");
        modelPacket0518.setRoom_reserved_c4("");
        modelPacket0518.setHardwareType_c5("");
        modelPacket0518.setReserved_c5("");
        modelPacket0518.setRoomA_c5("");
        modelPacket0518.setRoomB_c5("");
        modelPacket0518.setRoom_reserved_c5("");
        modelPacket0518.setRoom_reserved_c5(data_0518);

        String data_0521 = "00000000007000000000000000000000000000000000000000080000000000000000";
        modelPacket0521.setPacketId(packet.PKT_0521);
        modelPacket0521.setLength(length.LENGTH_0024);
        modelPacket0521.setStatus("");
        modelPacket0521.setReserved1("");
        modelPacket0521.setInput1("");
        modelPacket0521.setInput2("");
        modelPacket0521.setInput3("");
        modelPacket0521.setInput4("");
        modelPacket0521.setInput5("");
        modelPacket0521.setInput6("");
        modelPacket0521.setInput7("");
        modelPacket0521.setInput8("");
        modelPacket0521.setInput9("");
        modelPacket0521.setInput10("");
        modelPacket0521.setReserved2("");
        modelPacket0521.setUrjb1("");
        modelPacket0521.setUrjb2("");
        modelPacket0521.setReserved3("");
        modelPacket0521.setReserved3(data_0521);

        String data_0517 = "0000FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF";
        modelPacket0517.setPacketId(packet.PKT_0517);
        modelPacket0517.setLength(length.LENGTH_0014);
        modelPacket0517.setStatus("");
        modelPacket0517.setDenominationCode("");
        modelPacket0517.setDenominationCode(data_0517);

        String data_0529 = "01010102020101010202000000000000000000000101010101010101010100000000000000000000";
//        modelPacket0529.setPacketId(packet.PKT_0529);
        modelPacket0529.setPacketId("0729");
        modelPacket0529.setLength(length.LENGTH_002A);
        modelPacket0529.setDeposit_input1("");
        modelPacket0529.setDeposit_input2("");
        modelPacket0529.setDeposit_input3("");
        modelPacket0529.setDeposit_input4("");
        modelPacket0529.setDeposit_input5("");
        modelPacket0529.setDeposit_input6("");
        modelPacket0529.setDeposit_input7("");
        modelPacket0529.setDeposit_input8("");
        modelPacket0529.setDeposit_input9("");
        modelPacket0529.setDeposit_input10("");
        modelPacket0529.setDeposit_input11("");
        modelPacket0529.setDeposit_reserved("");
        modelPacket0529.setDispense_input1("");
        modelPacket0529.setDispense_input2("");
        modelPacket0529.setDispense_input3("");
        modelPacket0529.setDispense_input4("");
        modelPacket0529.setDispense_input5("");
        modelPacket0529.setDispense_input6("");
        modelPacket0529.setDispense_input7("");
        modelPacket0529.setDispense_input8("");
        modelPacket0529.setDispense_input9("");
        modelPacket0529.setDispense_input10("");
        modelPacket0529.setDispense_input11("");
        modelPacket0529.setDispense_reserved("");
        modelPacket0529.setDispense_reserved(data_0529);

        String data_0586 = "000000000000FFFFFFFFFFFFFFFFFFFFFFFF0000000000000000FFFF0000000000000000000000000000000000000000000000000000000000000000";
        modelPacket0586.setPacketId(packet.PKT_0586);
        modelPacket0586.setLength(length.LENGTH_003E);
        modelPacket0586.setReserved_1("");
        modelPacket0586.setInput_1("");
        modelPacket0586.setInput_2("");
        modelPacket0586.setInput_3("");
        modelPacket0586.setInput_4("");
        modelPacket0586.setInput_5("");
        modelPacket0586.setInput_6("");
        modelPacket0586.setInput_7("");
        modelPacket0586.setInput_8("");
        modelPacket0586.setInput_9("");
        modelPacket0586.setInput_10("");
        modelPacket0586.setReserved_2("");
        modelPacket0586.setUrjb_1("");
        modelPacket0586.setUrjb_2("");
        modelPacket0586.setReserved_3("");
        modelPacket0586.setReserved_3(data_0586);

        returnValue = modelPacket0001.generatePacket()
                + modelPacket0010.generatePacket()
                + modelPacket0511.generatePacket()
                + modelPacket0510.generatePacket()
                + modelPacket0512.generatePacket()
                + modelPacket0515.generatePacket()
                + modelPacket0518.generatePacket()
                + modelPacket0521.generatePacket()
                + modelPacket0517.generatePacket()
                + modelPacket0529.generatePacket()
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
