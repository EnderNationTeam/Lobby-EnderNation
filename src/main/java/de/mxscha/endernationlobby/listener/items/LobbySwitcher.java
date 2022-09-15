package de.mxscha.endernationlobby.listener.items;

import de.mxscha.endernationlobby.cloudnet.Switcher;
import de.mxscha.endernationlobby.utils.manager.items.ItemCreator;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;

public class LobbySwitcher implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        try {
            Player player = event.getPlayer();
            if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
                if (event.getItem().getItemMeta().getDisplayName().equals("§8» §6§lLobby Wechsler")) {
                    event.setCancelled(true);
                    Inventory inventory = Bukkit.createInventory(null, 5*9, "§8» §6§lLobby Wechsler");
                    fill(inventory);
                    Switcher.stop();
                    player.openInventory(inventory);
                }
            }
        } catch (Exception e) {

        }
    }

    private void fill(Inventory inventory) {
        for (int i = 0; i < inventory.getSize(); i++) {
            inventory.setItem(i, new ItemCreator(Material.GRAY_STAINED_GLASS_PANE).setName("").toItemStack());
        }
    }
}
