package com.hpy.crmdriver.ui.theme.interrupt_formatter;

import android.content.Context;

import com.hpy.crmdriver.ui.theme.util.AppConfig;
import com.hpy.crmdriver.ui.theme.util.AppLogs;

public class InterruptStatusChecker {

    public AppConfig appConfig = new AppConfig();
    public InterruptFormatter interruptFormatter = new InterruptFormatter();
    public String ERROR_STATUS = "0";
    public String RETURN_STATUS = "0";

    public String getStatusOfByteOne(Context context) {
        String returnValue = RETURN_STATUS;
        String binaryData = interruptFormatter.getByteToBinaryBlockData(context, appConfig.POSITION_0);

        for (int i = 0; i < binaryData.length(); i++) {
            String character = String.valueOf(binaryData.charAt(i));
            checkLogs("Index: " + i + ", Digit: " + character);

            if (i == 5) {
                if (character.equals(ERROR_STATUS)) {
                    returnValue = "Notes with bad condition in CS";
                }
            } else if (i == 6) {
                if (character.equals(ERROR_STATUS)) {
                    returnValue = "Notes have been detected in CS";
                }
            } else if (i == 7) {
                if (character.equals(ERROR_STATUS)) {
                    returnValue = "Notes have been detected in shutter area";
                }
            }

        }

        AppLogs.generate(returnValue);
        return returnValue;

    }

    public String getStatusOfByteTwo(Context context) {
        String returnValue = RETURN_STATUS;
        String binaryData = interruptFormatter.getByteToBinaryBlockData(context, appConfig.POSITION_1);
        for (int i = 0; i < binaryData.length(); i++) {
            String character = String.valueOf(binaryData.charAt(i));
            checkLogs("Index: " + i + ", Digit: " + character);

            if (i == 4) {
                if (character.equals(ERROR_STATUS)) {
                    returnValue = "URJB Empty ";
                }
            } else if (i == 5) {
                if (character.equals(ERROR_STATUS)) {
                    returnValue = "URJB Full";
                }
            } else if (i == 6) {
                if (character.equals(ERROR_STATUS)) {
                    returnValue = "ESC Empty";
                }
            } else if (i == 7) {
                if (character.equals(ERROR_STATUS)) {
                    returnValue = "ESC Full";
                }
            }
        }
        AppLogs.generate(returnValue);
        return returnValue;
    }

    public String getStatusOfByteThree(Context context) {
        String returnValue = RETURN_STATUS;
        String binaryData = interruptFormatter.getByteToBinaryBlockData(context, appConfig.POSITION_2);
        for (int i = 0; i < binaryData.length(); i++) {
            String character = String.valueOf(binaryData.charAt(i));
            checkLogs("Index: " + i + ", Digit: " + character);

            if (i == 6) {
                if (character.equals(ERROR_STATUS)) {
                    returnValue = "ESC Full (For Deposit)";
                }
            } else if (i == 7) {
                if (character.equals(ERROR_STATUS)) {
                    returnValue = "Deposit reject occurred";
                }
            }
        }
        AppLogs.generate(returnValue);
        return returnValue;
    }

    public String getStatusOfByteFour(Context context) {
        String returnValue = RETURN_STATUS;
        String binaryData = interruptFormatter.getByteToBinaryBlockData(context, appConfig.POSITION_3);
        for (int i = 0; i < binaryData.length(); i++) {
            String character = String.valueOf(binaryData.charAt(i));
            checkLogs("Index: " + i + ", Digit: " + character);
        }
        AppLogs.generate(returnValue);
        return returnValue;
    }

    public String getStatusOfByteFive(Context context) {
        String returnValue = RETURN_STATUS;
        String binaryData = interruptFormatter.getByteToBinaryBlockData(context, appConfig.POSITION_4);
        for (int i = 0; i < binaryData.length(); i++) {
            String character = String.valueOf(binaryData.charAt(i));
            checkLogs("Index: " + i + ", Digit: " + character);

            if (i == 3) {
                if (character.equals(ERROR_STATUS)) {
                    returnValue = "URJB door closed/URJB in position";
                }
            } else if (i == 6) {
                if (character.equals(ERROR_STATUS)) {
                    returnValue = "Low in position";
                }
            } else if (i == 7) {
                if (character.equals(ERROR_STATUS)) {
                    returnValue = "UP in position";
                }
            }
        }
        AppLogs.generate(returnValue);
        return returnValue;
    }

    public String getStatusOfByteSix(Context context) {
        String returnValue = RETURN_STATUS;
        String binaryData = interruptFormatter.getByteToBinaryBlockData(context, appConfig.POSITION_5);
        for (int i = 0; i < binaryData.length(); i++) {
            String character = String.valueOf(binaryData.charAt(i));
            checkLogs("Index: " + i + ", Digit: " + character);
            if (i == 0) {
                if (character.equals(ERROR_STATUS)) {
                    returnValue = "LT in position";
                }
            } else if (i == 3) {
                if (character.equals(ERROR_STATUS)) {
                    returnValue = "Cassette 5 in position";
                }
            } else if (i == 4) {
                if (character.equals(ERROR_STATUS)) {
                    returnValue = "Cassette 4 in position";
                }
            } else if (i == 5) {
                if (character.equals(ERROR_STATUS)) {
                    returnValue = "Cassette 3 in position";
                }
            } else if (i == 6) {
                if (character.equals(ERROR_STATUS)) {
                    returnValue = "Cassette 2 in position";
                }
            } else if (i == 7) {
                if (character.equals(ERROR_STATUS)) {
                    returnValue = "Cassette 1 in position";
                }
            }
        }
        AppLogs.generate(returnValue);
        return returnValue;
    }

    public String getStatusOfByteSeven(Context context) {
        String returnValue = RETURN_STATUS;
        String binaryData = interruptFormatter.getByteToBinaryBlockData(context, appConfig.POSITION_6);
        for (int i = 0; i < binaryData.length(); i++) {
            String character = String.valueOf(binaryData.charAt(i));
            checkLogs("Index: " + i + ", Digit: " + character);
            if (i == 0) {
                if (character.equals(ERROR_STATUS)) {
                    returnValue = "BV is closed";
                }
            } else if (i == 1) {
                if (character.equals(ERROR_STATUS)) {
                    returnValue = "BV Fan is stopped";
                }
            } else if (i == 4) {
                if (character.equals(ERROR_STATUS)) {
                    returnValue = "CS in position";
                }
            } else if (i == 5) {
                if (character.equals(ERROR_STATUS)) {
                    returnValue = "End of ESC stack area";
                }
            } else if (i == 6) {
                if (character.equals(ERROR_STATUS)) {
                    returnValue = "ESC in position";
                }
            } else if (i == 7) {
                if (character.equals(ERROR_STATUS)) {
                    returnValue = "ESC door closed";
                }
            }
        }
        AppLogs.generate(returnValue);
        return returnValue;
    }

    public String getStatusOfByteEight(Context context) {
        String returnValue = RETURN_STATUS;
        String binaryData = interruptFormatter.getByteToBinaryBlockData(context, appConfig.POSITION_7);
        for (int i = 0; i < binaryData.length(); i++) {
            String character = String.valueOf(binaryData.charAt(i));
            checkLogs("Index: " + i + ", Digit: " + character);
            if (i == 4) {
                if (character.equals(ERROR_STATUS)) {
                    returnValue = "Shutter Status";
                }
            }
        }
        AppLogs.generate(returnValue);
        return returnValue;
    }

    public String getStatusOfByteNine(Context context) {
        String returnValue = RETURN_STATUS;
        String binaryData = interruptFormatter.getByteToBinaryBlockData(context, appConfig.POSITION_8);
        for (int i = 0; i < binaryData.length(); i++) {
            String character = String.valueOf(binaryData.charAt(i));
            checkLogs("Index: " + i + ", Digit: " + character);
            if (i == 7) {
                if (character.equals(ERROR_STATUS)) {
                    returnValue = "Cassette 1 Status";
                }
            }
        }
        AppLogs.generate(returnValue);
        return returnValue;
    }

    public String getStatusOfByteTen(Context context) {
        String returnValue = RETURN_STATUS;
        String binaryData = interruptFormatter.getByteToBinaryBlockData(context, appConfig.POSITION_9);
        for (int i = 0; i < binaryData.length(); i++) {
            String character = String.valueOf(binaryData.charAt(i));
            checkLogs("Index: " + i + ", Digit: " + character);
            if (i == 7) {
                if (character.equals(ERROR_STATUS)) {
                    returnValue = "Cassette 2 Status";
                }
            }
        }
        AppLogs.generate(returnValue);
        return returnValue;
    }

    public String getStatusOfByteEleven(Context context) {
        String returnValue = RETURN_STATUS;
        String binaryData = interruptFormatter.getByteToBinaryBlockData(context, appConfig.POSITION_10);
        for (int i = 0; i < binaryData.length(); i++) {
            String character = String.valueOf(binaryData.charAt(i));
            checkLogs("Index: " + i + ", Digit: " + character);
            if (i == 7) {
                if (character.equals(ERROR_STATUS)) {
                    returnValue = "Cassette 3 Status";
                }
            }
        }
        AppLogs.generate(returnValue);
        return returnValue;
    }

    public String getStatusOfByteTwelve(Context context) {
        String returnValue = RETURN_STATUS;
        String binaryData = interruptFormatter.getByteToBinaryBlockData(context, appConfig.POSITION_11);
        for (int i = 0; i < binaryData.length(); i++) {
            String character = String.valueOf(binaryData.charAt(i));
            checkLogs("Index: " + i + ", Digit: " + character);
            if (i == 7) {
                if (character.equals(ERROR_STATUS)) {
                    returnValue = "Cassette 4 Status";
                }
            }
        }
        AppLogs.generate(returnValue);
        return returnValue;
    }

    public String getStatusOfByteThirteen(Context context) {
        String returnValue = RETURN_STATUS;
        String binaryData = interruptFormatter.getByteToBinaryBlockData(context, appConfig.POSITION_12);
        for (int i = 0; i < binaryData.length(); i++) {
            String character = String.valueOf(binaryData.charAt(i));
            checkLogs("Index: " + i + ", Digit: " + character);
            if (i == 7) {
                if (character.equals(ERROR_STATUS)) {
                    returnValue = "Cassette 5 Status";
                }
            }
        }
        AppLogs.generate(returnValue);
        return returnValue;
    }

    public String getStatusOfByteFourteen(Context context) {
        String returnValue = RETURN_STATUS;
        String binaryData = interruptFormatter.getByteToBinaryBlockData(context, appConfig.POSITION_13);
        for (int i = 0; i < binaryData.length(); i++) {
            String character = String.valueOf(binaryData.charAt(i));
            checkLogs("Index: " + i + ", Digit: " + character);
            if (i == 0) {
                if (character.equals(ERROR_STATUS)) {
                    returnValue = "Need to execute Get Cassette Info";
                }
            } else if (i == 1) {
                if (character.equals(ERROR_STATUS)) {
                    returnValue = "Need to execute Reset";
                }
            } else if (i == 2) {
                if (character.equals(ERROR_STATUS)) {
                    returnValue = "Need to acquire access log";
                }
            } else if (i == 3) {
                if (character.equals(ERROR_STATUS)) {
                    returnValue = "Need to acquire warning information";
                }
            } else if (i == 4) {
                if (character.equals(ERROR_STATUS)) {
                    returnValue = "Notes have been detected in ESC";
                }
            } else if (i == 5) {
                if (character.equals(ERROR_STATUS)) {
                    returnValue = "Notes have been detected in CS";
                }
            } else if (i == 6) {
                if (character.equals(ERROR_STATUS)) {
                    returnValue = "Notes have removed forcibly from CS";
                }
            } else if (i == 7) {
                if (character.equals(ERROR_STATUS)) {
                    returnValue = "Need to monitor shutter";
                }
            }
        }
        AppLogs.generate(returnValue);
        return returnValue;
    }

    public String getStatusOfByteFifteen(Context context) {
        String returnValue = RETURN_STATUS;
        String binaryData = interruptFormatter.getByteToBinaryBlockData(context, appConfig.POSITION_14);
        for (int i = 0; i < binaryData.length(); i++) {
            String character = String.valueOf(binaryData.charAt(i));
            checkLogs("Index: " + i + ", Digit: " + character);
        }
        AppLogs.generate(returnValue);
        return returnValue;
    }

    public String getStatusOfByteSixteen(Context context) {
        String returnValue = RETURN_STATUS;
        String binaryData = interruptFormatter.getByteToBinaryBlockData(context, appConfig.POSITION_15);
        for (int i = 0; i < binaryData.length(); i++) {
            String character = String.valueOf(binaryData.charAt(i));
            checkLogs("Index: " + i + ", Digit: " + character);
            if (i == 7) {
                if (character.equals(ERROR_STATUS)) {
                    returnValue = "Warning is held";
                }
            }
        }
        AppLogs.generate(returnValue);
        return returnValue;
    }

    public String getStatusOfByteSeventeen(Context context) {
        String returnValue = RETURN_STATUS;
        String binaryData = interruptFormatter.getByteToBinaryBlockData(context, appConfig.POSITION_16);
        for (int i = 0; i < binaryData.length(); i++) {
            String character = String.valueOf(binaryData.charAt(i));
            checkLogs("Index: " + i + ", Digit: " + character);
            if (i == 5) {
                if (character.equals(ERROR_STATUS)) {
                    returnValue = "Energy Saving Mode";
                }
            }
        }
        AppLogs.generate(returnValue);
        return returnValue;
    }

    public String getStatusOfByteEighteen(Context context) {
        String returnValue = RETURN_STATUS;
        String binaryData = interruptFormatter.getByteToBinaryBlockData(context, appConfig.POSITION_17);
        for (int i = 0; i < binaryData.length(); i++) {
            String character = String.valueOf(binaryData.charAt(i));
            checkLogs("Index: " + i + ", Digit: " + character);
        }
        AppLogs.generate(returnValue);
        return returnValue;
    }

    public String getStatusOfByteNineteen(Context context) {
        String returnValue = RETURN_STATUS;
        String binaryData = interruptFormatter.getByteToBinaryBlockData(context, appConfig.POSITION_18);
        for (int i = 0; i < binaryData.length(); i++) {
            String character = String.valueOf(binaryData.charAt(i));
            checkLogs("Index: " + i + ", Digit: " + character);
        }
        AppLogs.generate(returnValue);
        return returnValue;
    }

    public String getStatusOfByteTwenty(Context context) {
        String returnValue = RETURN_STATUS;
        String binaryData = interruptFormatter.getByteToBinaryBlockData(context, appConfig.POSITION_19);
        for (int i = 0; i < binaryData.length(); i++) {
            String character = String.valueOf(binaryData.charAt(i));
            checkLogs("Index: " + i + ", Digit: " + character);
        }
        AppLogs.generate(returnValue);
        return returnValue;
    }

    public String getStatusOfByteTwentyOne(Context context) {
        String returnValue = RETURN_STATUS;
        String binaryData = interruptFormatter.getByteToBinaryBlockData(context, appConfig.POSITION_20);
        for (int i = 0; i < binaryData.length(); i++) {
            String character = String.valueOf(binaryData.charAt(i));
            checkLogs("Index: " + i + ", Digit: " + character);
            if (i == 3) {
                if (character.equals(ERROR_STATUS)) {
                    returnValue = "Cassette 5 Unlock";
                }
            } else if (i == 4) {
                if (character.equals(ERROR_STATUS)) {
                    returnValue = "Cassette 4 Unlock";
                }
            } else if (i == 5) {
                if (character.equals(ERROR_STATUS)) {
                    returnValue = "Cassette 3 Unlock";
                }
            } else if (i == 6) {
                if (character.equals(ERROR_STATUS)) {
                    returnValue = "Cassette 2 Unlock";
                }
            } else if (i == 7) {
                if (character.equals(ERROR_STATUS)) {
                    returnValue = "Cassette 1 Unlock";
                }
            }
        }
        AppLogs.generate(returnValue);
        return returnValue;
    }

    public String getStatusOfByteTwentyTwo(Context context) {
        String returnValue = RETURN_STATUS;
        String binaryData = interruptFormatter.getByteToBinaryBlockData(context, appConfig.POSITION_21);
        for (int i = 0; i < binaryData.length(); i++) {
            String character = String.valueOf(binaryData.charAt(i));
            checkLogs("Index: " + i + ", Digit: " + character);
        }
        AppLogs.generate(returnValue);
        return returnValue;
    }

    public String getStatusOfByteTwentyThree(Context context) {
        String returnValue = RETURN_STATUS;
        String binaryData = interruptFormatter.getByteToBinaryBlockData(context, appConfig.POSITION_22);
        for (int i = 0; i < binaryData.length(); i++) {
            String character = String.valueOf(binaryData.charAt(i));
            checkLogs("Index: " + i + ", Digit: " + character);
        }
        AppLogs.generate(returnValue);
        return returnValue;
    }

    public String getStatusOfByteTwentyFour(Context context) {
        String returnValue = RETURN_STATUS;
        String binaryData = interruptFormatter.getByteToBinaryBlockData(context, appConfig.POSITION_23);
        for (int i = 0; i < binaryData.length(); i++) {
            String character = String.valueOf(binaryData.charAt(i));
            checkLogs("Index: " + i + ", Digit: " + character);
        }
        AppLogs.generate(returnValue);
        return returnValue;
    }

    public void checkLogs(String message) {
        AppLogs.generate(message);
    }
}
