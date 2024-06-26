package com.hpy.crmdriver.ui.theme.cmd_msg_data;

import com.hpy.crmdriver.ui.theme.cmd_formatter.MessageDataLengthGenerator;
import com.hpy.crmdriver.ui.theme.cmd_generator.CommandData;
import com.hpy.crmdriver.ui.theme.data.Length;
import com.hpy.crmdriver.ui.theme.data.Packet;

public class Testcommand {

    public Packet packet = new Packet();
    public Length length = new Length();
    public CommandData commandData = new CommandData();
    public MessageDataLengthGenerator messageDataLengthGenerator = new MessageDataLengthGenerator();


    public String generateCommand() {
        String returnValue = "";

        String messageHeaderLength = messageDataLengthGenerator.getMessageHeaderLength(returnValue);
        returnValue = messageHeaderLength + returnValue;
        return returnValue;
    }

    public String parseCommandResponse(String responseData) {
        String returnValue = "";
//        data is in TLV format  - TLV format means Tag + Length + value;

        //parse and set to packet model

        return returnValue;
    }

}
