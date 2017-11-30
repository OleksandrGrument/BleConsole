package com.grument.bleconsole.event;


import java.util.Arrays;

public class CharacteristicReadEvent {

    private final byte[] characteristicMessage;

    public CharacteristicReadEvent(byte[] characteristicMessage) {
        this.characteristicMessage = characteristicMessage;
    }

    public byte[] getCharacteristicMessage() {
        return characteristicMessage;
    }

    @Override
    public String toString() {
        return "CharacteristicReadEvent{" +
                "characteristicMessage=" + Arrays.toString(characteristicMessage) +
                '}';
    }
}
