package com.grument.bleconsole.util;

enum BleStandardServices {

    /*
        According to specification - https://www.bluetooth.com/specifications/gatt/services
    */

    GENERIC_ACCESS("Generic Access", "0x1800"),
    ALERT_NOTIFICATION("Alert Notification Service", "0x1811"),
    AUTOMATION_IO("Automation IO", "0x1815"),
    BATTERY_SERVICE("Battery Service", "0x180F"),
    BLOOD_PRESSURE("Blood Pressure", "0x1810"),
    BODY_COMPOSITION("Body Composition", "0x181B"),
    BOND_MANAGEMENT_SERVICE("Bond Management Service", "0x181E"),
    CONTINUOUS_GLUCOSE_MONITORING("Continuous Glucose Monitoring", "0x181F"),
    CURRENT_TIME_SERVICE("Current Time Service", "0x1805"),
    CYCLING_POWER("Cycling Power", "0x1818"),
    CYCLING_SPEED_AND_CADENCE("Cycling Speed and Cadence", "0x1816"),
    DEVICE_INFORMATION("Device Information", "0x180A"),
    ENVIRONMENTAL_SENSING("Environmental Sensing", "0x181A"),
    FITNESS_MACHINE("Fitness Machine", "0x1826"),
    GENERIC_ATTRIBUTE("Generic Attribute", "0x1801"),
    GLUCOSE("Glucose", "0x1808"),
    HEALTH_THERMOMETER("Health Thermometer", "0x1809"),
    HEART_RATE("Heart Rate", "0x180D"),
    HTTP_PROXY("HTTP Proxy", "0x1823"),
    HUMAN_INTERFACE_DEVICE("Human Interface Device", "0x1812"),
    IMMEDIATE_ALERT("Immediate Alert", "0x1802"),
    INDOOR_POSITIONING("Indoor Positioning", "0x1821"),
    INTERNET_PROTOCOL_SUPPORT_SERVICE("Internet Protocol Support Service", "0x1820"),
    LINK_LOSS("Link Loss", "0x1803"),
    LOCATION_AND_NAVIGATION("Location and Navigation", "0x1819"),
    MESH_PROVISIONING_SERVICE("Mesh Provisioning Service", "0x1827"),
    MESH_PROXY_SERVICE("Mesh Proxy Service", "0x1828"),
    NEXT_DST_CHANGE_SERVICE("Next DST Change Service", "0x1807"),
    OBJECT_TRANSFER_SERVICE("Object Transfer Service", "0x1825"),
    PHONE_ALERT_STATUS_SERVICE("Phone Alert Status Service", "0x180E"),
    PULSE_OXIMETER_SERVICE("Pulse Oximeter Service", "0x1822"),
    REFERENCE_TIME_UPDATE_SERVICE("Reference Time Update Service", "0x1806"),
    RUNNING_SPEED_AND_CADENCE("Running Speed and Cadence", "0x1814"),
    SCAN_PARAMETERS("Scan Parameters", "0x1813"),
    TRANSPORT_DISCOVERY("Transport Discovery", "0x1824"),
    TX_POWER("Tx Power", "0x1804"),
    USER_DATA("User Data", "0x181C"),
    WEIGHT_SCALE("Weight Scale", "0x181D");


    private final String serviceName;

    private final String hexAssignedNumber;

    BleStandardServices(String serviceName, String hexAssignedNumber) {
        this.hexAssignedNumber = hexAssignedNumber;
        this.serviceName = serviceName;
    }

    public String getHexAssignedNumber() {
        return hexAssignedNumber;
    }

    public String getServiceName() {
        return serviceName;
    }
}
