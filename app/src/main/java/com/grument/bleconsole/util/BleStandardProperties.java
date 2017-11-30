package com.grument.bleconsole.util;


public enum BleStandardProperties {

    PROPERTY_BROADCAST(1, "PROPERTY_BROADCAST"),
    PROPERTY_EXTENDED_PROPS(128, "PROPERTY_EXTENDED_PROPS"),
    PROPERTY_INDICATE(32, "PROPERTY_INDICATE"),
    PROPERTY_NOTIFY(16, "PROPERTY_NOTIFY"),
    PROPERTY_READ(2, "PROPERTY_READ"),
    PROPERTY_SIGNED_WRITE(64, "PROPERTY_SIGNED_WRITE"),
    PROPERTY_WRITE(8, "PROPERTY_WRITE"),
    PROPERTY_WRITE_NO_RESPONSE(4, "PROPERTY_WRITE_NO_RESPONSE");

    private final int propertyValue;

    private final String propertyName;

    BleStandardProperties(int propertyValue, String propertyName) {
        this.propertyValue = propertyValue;
        this.propertyName = propertyName;
    }

    public int getPropertyValue() {
        return propertyValue;
    }

    public String getPropertyName() {
        return propertyName;
    }
}
