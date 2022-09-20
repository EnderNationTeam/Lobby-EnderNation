package de.mxscha.endernationlobby;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import de.mxscha.endernationlobby.utils.PluginMessagesListener;
import de.mxscha.endernationlobby.utils.Register;
import org.bukkit.plugin.java.JavaPlugin;

public final class LobbyCore extends JavaPlugin {

    public static String Prefix;
    private static LobbyCore instance;
    private static ProtocolManager protocolManager;

    @Override
    public void onEnable() {
        Register.init(this);
        instance = this;
        this.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        this.getServer().getMessenger().registerIncomingPluginChannel(this, "BungeeCord", new PluginMessagesListener());

        if(!getServer().getPluginManager().isPluginEnabled("ProtocolLib")) {
           this.getLogger().warning("This plugin need ProtocolLib! Download it at https://www.spigotmc.org/resources/protocollib.1997/");
        } else {
            protocolManager = ProtocolLibrary.getProtocolManager();
        }
    }
    @Override
    public void onDisable() {
        this.getServer().getMessenger().unregisterOutgoingPluginChannel(this);
        this.getServer().getMessenger().unregisterIncomingPluginChannel(this);
    }

    public static LobbyCore getInstance() {
        return instance;
    }
    public static ProtocolManager getProtocolManager() {
        return protocolManager;
    }
}