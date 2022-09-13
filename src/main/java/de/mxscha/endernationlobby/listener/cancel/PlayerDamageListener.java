package de.mxscha.endernationlobby.listener.cancel;

import de.mxscha.lobby.utils.manager.BuildManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;

public class PlayerDamageListener implements Listener {

    @EventHandler
    public void onDamage(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player player) {
            if (!BuildManager.isAllowed(player)) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player player) {
            if (!BuildManager.isAllowed(player)) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onDamage(FoodLevelChangeEvent event) {
        if (event.getEntity() instanceof Player player) {
            if (!BuildManager.isAllowed(player)) {
                event.setCancelled(true);
            }
        }
    }
}
