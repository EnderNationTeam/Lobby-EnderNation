package de.mxscha.endernationlobby.listener;

import de.mxscha.endernationlobby.utils.manager.BuildManager;
import de.mxscha.endernationlobby.utils.manager.locations.ConfigLocationUtil;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerMoveDeathListener implements Listener {

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        try {
            Location death = new ConfigLocationUtil("Death").loadLocation();
            Location spawn = new ConfigLocationUtil("Lobby").loadLocation();
            if (player.getLocation().getY() < death.getY()) {
                if (!BuildManager.isAllowed(player)) {
                    player.teleport(spawn);
                }
            }
        } catch (Exception e) {

        }
    }
}