package com.grument.bleconsole.event;


public class ConnectEvent {

    private final String deviceAddress;

    public ConnectEvent(String deviceAddress) {
        this.deviceAddress = deviceAddress;
    }

    public String getDeviceAddress() {
        return deviceAddress;
    }

    @Override
    public String toString() {
        return "ConnectEvent{" +
                "deviceAddress='" + deviceAddress + '\'' +
                '}';
    }
}
