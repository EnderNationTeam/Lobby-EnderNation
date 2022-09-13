package de.mxscha.endernationlobby.listener;

import de.mxscha.lobby.utils.manager.InventoryManager;
import de.mxscha.lobby.utils.manager.MessageManager;
import de.mxscha.lobby.utils.manager.locations.ConfigLocationUtil;
import de.mxscha.lobby.utils.scoreboards.LobbyScoreboard;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerConnectionListener implements Listener {

    @EventHandler
    public void onLogin(PlayerLoginEvent event) {
        Player player = event.getPlayer();
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        try {
            Location spawn = new ConfigLocationUtil("Lobby").loadLocation();
            player.teleport(spawn);
            player.setLastDeathLocation(spawn);
        } catch (Exception e) {
            Bukkit.getConsoleSender().sendMessage(MessageManager.Prefix + "§cDer Lobby Spawn ist nicht gesetzt!");
        }
        event.setJoinMessage(null);
        InventoryManager.setPlayerInventory(player);
        new LobbyScoreboard(player);
        player.setGameMode(GameMode.SURVIVAL);
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        event.setQuitMessage(null);
    }
}