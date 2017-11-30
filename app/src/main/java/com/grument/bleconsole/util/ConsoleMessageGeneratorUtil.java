package com.grument.bleconsole.util;

import android.content.Context;

import com.grument.bleconsole.R;

import java.util.Arrays;
import java.util.UUID;

import static com.grument.bleconsole.util.Constants.READ_CHARACTERISTIC;


public class ConsoleMessageGeneratorUtil {

    private Context context;

    public ConsoleMessageGeneratorUtil(Context context) {
        this.context = context;
    }


    public String generateSetCharacteristicSuccessMessage(int chooseCharacteristicType, UUID uuid) {
        String bleConsoleMessage;

        if (chooseCharacteristicType == READ_CHARACTERISTIC)
            bleConsoleMessage = context.getString(R.string.read_characteristic_uuid);
        else
            bleConsoleMessage = context.getString(R.string.write_characteristic_uuid);

        bleConsoleMessage += uuid;
        bleConsoleMessage += context.getResources().getString(R.string.characteristic_set_success);

        return bleConsoleMessage;
    }

    public String generateReadCharacteristicMessage(byte[] message) {
        String bleConsoleMessage = context.getString(R.string.characteristic);
        bleConsoleMessage += Arrays.toString(message);
        bleConsoleMessage += context.getString(R.string.characteristic_read_success);
        return bleConsoleMessage;
    }

    public String generateSendCommandMessage(byte[] command) {
        String bleConsoleMessage = context.getString(R.string.command);
        bleConsoleMessage += Arrays.toString(command);
        bleConsoleMessage += context.getString(R.string.command_send_success);
        return bleConsoleMessage;
    }


}
