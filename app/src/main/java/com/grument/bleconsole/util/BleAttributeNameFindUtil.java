package com.grument.bleconsole.util;

import java.util.UUID;


public class BleAttributeNameFindUtil {

    private static final long BT_UUID_LOWER_BITS = 0x800000805F9B34FBl;
    private static final long BT_UUID_UPPER_BITS = 0x1000l;
    private static final BleStandardServices[] BLE_STANDARD_SERVICES = BleStandardServices.values();
    private static final BleStandardProperties[] BLE_STANDARD_PROPERTIES = BleStandardProperties.values();

    private static UUID get16BitBTUUID(long uuid) {
        return new UUID(BT_UUID_UPPER_BITS + (uuid << 32), BT_UUID_LOWER_BITS);
    }

    private static String getShortUuidAsString(UUID uuid) {
        long result = uuid.getMostSignificantBits();
        result = result - BT_UUID_UPPER_BITS;
        result = result >> 32;
        String stringResult = "0x" + Long.toHexString(result).toUpperCase();
        return stringResult;
    }

    public static String getNameFromServiceUuid(UUID uuid) {
        String hexAssignedNumber = getShortUuidAsString(uuid);

        for (BleStandardServices bleStandardServices : BLE_STANDARD_SERVICES) {
            if (bleStandardServices.getHexAssignedNumber().equals(hexAssignedNumber))
                return bleStandardServices.getServiceName();
        }
        return hexAssignedNumber;

    }

    public static String getNameFromPropertyId(int propertyId) {
        for (BleStandardProperties bleStandardProperties : BLE_STANDARD_PROPERTIES) {
            if (bleStandardProperties.getPropertyValue() == propertyId)
                return bleStandardProperties.getPropertyName();
        }
        return "Property code - " + propertyId;
    }

    public static byte[] parseStringConsoleCommand(String stringConsoleCommand) {

        byte[] byteConsoleCommand = null;

        if (stringConsoleCommand.isEmpty()) {
            return byteConsoleCommand;
        }

        try {
            stringConsoleCommand = stringConsoleCommand.replaceAll("\\s+", "");
            String[] tokens = stringConsoleCommand.split(",");
            byteConsoleCommand = new byte[tokens.length];

            for (int i = 0; i < byteConsoleCommand.length; i++) {
                byteConsoleCommand[i] = Byte.parseByte(tokens[i]);
            }

        } catch (Exception ignored) {
        }


        return byteConsoleCommand;
    }
}
