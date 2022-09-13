package de.mxscha.endernationlobby.utils;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;

public class PluginMessagesListener implements PluginMessageListener {

    private static int playerCountEndOfLife = 0;
    private static int playerCountLobby1 = 0;
    private static int playerCountLobby2 = 0;

    @Override
    public void onPluginMessageReceived(String channel, Player player, byte[] message) {
        if (!channel.equals("Bungeecord")) return;
        ByteArrayDataInput in = ByteStreams.newDataInput(message);
        String subchannel = in.readUTF();
        if (subchannel.equals("PlayerCount")) {
            String serverName = in.readUTF();
            int playerCount = in.readInt();

            Bukkit.broadcastMessage(serverName);
            if (serverName.equals("EndOfLife-1"))
                playerCountEndOfLife = playerCount;
        }
    }

    public static int getPlayerCountEndOfLife() {
        return playerCountEndOfLife;
    }

    public static int getPlayerCountLobby1() {
        return playerCountLobby1;
    }

    public static int getPlayerCountLobby2() {
        return playerCountLobby2;
    }
}