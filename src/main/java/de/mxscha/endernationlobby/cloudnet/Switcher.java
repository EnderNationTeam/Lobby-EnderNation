package de.mxscha.endernationlobby.cloudnet;

import de.dytanic.cloudnet.CloudNet;

public class Switcher {

    public static void stop() {
        CloudNet.getInstance().stop();
    }
}
