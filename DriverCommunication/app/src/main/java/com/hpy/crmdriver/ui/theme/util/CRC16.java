package com.hpy.crmdriver.ui.theme.util;

public class CRC16 {

    // CRC-16 CCITT (0x1021)
    private static final int POLYNOMIAL = 0x1021;
    private static final int INITIAL_VALUE = 0xFFFF;

    // Calculate CRC-16 checksum for given byte array
    public static int calculate(byte[] bytes) {
        int crc = INITIAL_VALUE;

        for (byte b : bytes) {
            crc ^= (b & 0xFF) << 8; // XOR with next byte

            for (int i = 0; i < 8; i++) {
                if ((crc & 0x8000) != 0) {
                    crc = (crc << 1) ^ POLYNOMIAL;
                } else {
                    crc <<= 1;
                }
            }
        }

        return crc & 0xFFFF; // Ensure 16-bit checksum
    }

}
