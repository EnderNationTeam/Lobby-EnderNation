package de.mxscha.endernationlobby.listener;

import de.mxscha.endernationlobby.utils.manager.InventoryManager;
import de.mxscha.endernationlobby.utils.manager.MessageManager;
import de.mxscha.endernationlobby.utils.manager.items.ItemCreator;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class FlyListener implements Listener {

    private final ItemStack flyModeA = new ItemCreator(Material.FEATHER).setName("§8» §9§lFliegen §8| §aAktiviert").toItemStack();
    private final ItemStack flyModeD = new ItemCreator(Material.FEATHER).setName("§8» §9§lFliegen §8| §cDeaktiviert").toItemStack();

    @EventHandler
    public void onInventory(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        if (event.getCurrentItem() == null) return;
            if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§8» §9§lFliegen §8| §cDeaktiviert")) {
                player.setFlying(true);
                player.setAllowFlight(true);
                player.getInventory().setItem(21, flyModeA);
                player.closeInventory();
                player.sendMessage(MessageManager.Prefix + "§7Du kannst §anun §7fliegen!");
            }
            if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§8» §9§lFliegen §8| §aAktiviert")) {
                player.setFlying(false);
                player.setAllowFlight(false);
                player.getInventory().setItem(21, flyModeD);
                player.closeInventory();
                player.sendMessage(MessageManager.Prefix + "§7Du kannst nun §cnicht mehr §7fliegen!");
            }
        }
}
