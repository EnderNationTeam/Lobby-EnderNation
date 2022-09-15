package de.mxscha.endernationlobby.cloudnet;

import de.dytanic.cloudnet.CloudNet;

import java.util.Collection;

public class Switcher {

    public static void stop() {
        CloudNet.getInstance().stop();
    }
}
