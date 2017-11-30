package com.grument.bleconsole.event;


import java.util.Arrays;

public class SendCommandEvent {

    private final byte command[];

    public SendCommandEvent(byte[] command) {
        this.command = command;
    }

    public byte[] getCommand() {
        return command;
    }

    @Override
    public String toString() {
        return "SendCommandEvent{" +
                "command=" + Arrays.toString(command) +
                '}';
    }
}

