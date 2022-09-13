package de.mxscha.endernationlobby.listener.cancel;

import de.mxscha.endernationlobby.utils.manager.BuildManager;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;

public class InventoryMoveItemListener implements Listener {

    @EventHandler
    public void onInventory(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        if (!BuildManager.isAllowed(player)) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onSwitch(PlayerSwapHandItemsEvent event) {
        Player player = event.getPlayer();
        if (!BuildManager.isAllowed(player)) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onSwitch(PlayerDropItemEvent event) {
        Player player = event.getPlayer();
        if (!BuildManager.isAllowed(player)) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onItemHeld(PlayerItemHeldEvent event) {
        Player player = event.getPlayer();
        if (!BuildManager.isAllowed(player)) {
            player.playSound(player.getLocation(), Sound.ENTITY_ENDER_EYE_LAUNCH, 0.40f, 2);
        }
    }
}