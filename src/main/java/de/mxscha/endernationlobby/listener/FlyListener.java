package de.mxscha.endernationlobby.listener;

import de.mxscha.endernationlobby.utils.manager.MessageManager;
import de.mxscha.endernationlobby.utils.manager.items.ItemCreator;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class FlyListener implements Listener {

    private final ItemStack flyModeA = new ItemCreator(Material.FEATHER).setName("§8» §9§lFliegen §8| §aAktiviert").toItemStack();
    private final ItemStack flyModeD = new ItemCreator(Material.FEATHER).setName("§8» §9§lFliegen §8| §cDeaktiviert").toItemStack();

    private static ArrayList<Player> isFly = new ArrayList<>();;

    public static boolean isPlayerFly(Player player) {
        if(isFly.contains(player))
            return true;
        return false;
    }

    @EventHandler
    public void cleanup(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        isFly.remove(player);
    }

    @EventHandler
    public void onInventory(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        if (event.getCurrentItem() == null) return;
        if (!event.getCurrentItem().hasItemMeta()) return;

        if(event.getCurrentItem().getItemMeta().getDisplayName().equals("§8» §9§lFliegen §8| §cDeaktiviert") ||
                event.getCurrentItem().getItemMeta().getDisplayName().equals("§8» §9§lFliegen §8| §aAktiviert")) {

            if (isFly.contains(player)) {
                isFly.remove(player);
                //player.setAllowFlight(false);
                player.setFlying(false);
                player.getInventory().setItem(21, flyModeD);
                player.closeInventory();
                player.sendMessage(MessageManager.Prefix + "§7Du kannst nun §cnicht mehr §7fliegen!");
            } else {
                isFly.add(player);
                player.setAllowFlight(true);
                player.setFlying(true);
                player.getInventory().setItem(21, flyModeA);
                player.closeInventory();
                player.sendMessage(MessageManager.Prefix + "§7Du kannst §anun §7fliegen!");
            }
        }
    }
}
