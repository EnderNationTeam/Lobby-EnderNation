package de.mxscha.endernationlobby.listener.cancel;

import de.mxscha.lobby.utils.manager.BuildManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

public class PlayerBuildListener implements Listener {

    @EventHandler
    public void onPlace(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        if (!BuildManager.isAllowed(player)) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlace(BlockBreakEvent event) {
        Player player = event.getPlayer();
        if (!BuildManager.isAllowed(player)) {
            event.setCancelled(true);
        }
    }
}