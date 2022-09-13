package de.mxscha.endernationlobby.utils;

import de.mxscha.endernationlobby.LobbyCore;
import de.mxscha.endernationlobby.commands.*;
import de.mxscha.endernationlobby.listener.*;
import de.mxscha.endernationlobby.listener.cancel.*;
import de.mxscha.endernationlobby.listener.items.Compass;
import de.mxscha.endernationlobby.listener.items.Extras;
import de.mxscha.endernationlobby.listener.items.PlayerHider;
import de.mxscha.endernationlobby.utils.config.Settings;
import de.mxscha.endernationlobby.utils.manager.MessageManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;

public class Register {

    public static void init(LobbyCore core) {
        Settings.save();
        PluginManager manager = Bukkit.getPluginManager();
        core.getCommand("setup").setExecutor(new SetupCommand());
        core.getCommand("build").setExecutor(new BuildCommand());
        manager.registerEvents(new PlayerConnectionListener(), core);
        manager.registerEvents(new PlayerMoveDeathListener(), core);
        manager.registerEvents(new PlayerBuildListener(), core);
        manager.registerEvents(new InventoryMoveItemListener(), core);
        manager.registerEvents(new PlayerChatEvent(), core);
        manager.registerEvents(new PlayerDamageListener(), core);
        manager.registerEvents(new PlayerTeleportToGamesListener(), core);
        manager.registerEvents(new PlayerHider(), core);
        manager.registerEvents(new Compass(), core);
        manager.registerEvents(new StupidRoundListener(), core);
        manager.registerEvents(new Extras(), core);
        // Settings
        MessageManager.Prefix = Settings.configuration.getString("Prefix").replace("&", "§");
    }
}