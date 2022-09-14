package de.mxscha.endernationlobby;

import de.mxscha.endernationlobby.utils.PluginMessagesListener;
import de.mxscha.endernationlobby.utils.Register;
import org.bukkit.plugin.java.JavaPlugin;

public final class LobbyCore extends JavaPlugin {

    public static String Prefix;
    private static LobbyCore instance;

    @Override
    public void onEnable() {
        Register.init(this);
        instance = this;
        this.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        this.getServer().getMessenger().registerIncomingPluginChannel(this, "BungeeCord", new PluginMessagesListener());
    }
    @Override
    public void onDisable() {
        this.getServer().getMessenger().unregisterOutgoingPluginChannel(this);
        this.getServer().getMessenger().unregisterIncomingPluginChannel(this);
    }

    public static LobbyCore getInstance() {
        return instance;
    }
}