package de.mxscha.endernationlobby.listener;

import de.mxscha.endernationlobby.utils.manager.CloudNetManager;
import de.mxscha.endernationlobby.utils.manager.inventory.MultiInventoryCalculator;
import de.mxscha.endernationlobby.utils.manager.inventory.MultiInventoryItems;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class ServerWechslerListener implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        try {
            Player player = (Player) event.getWhoClicked();
            if (event.getCurrentItem() == null) return;
            if (!event.getCurrentItem().hasItemMeta()) return;
            if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§8» §6§lServer Wechsler")) {
                event.setCancelled(true);

                if(!CloudNetManager.existCloudNet()) {
                    // We cannot use cloudnet
                    player.sendMessage("§cDas Item ist deaktiviert!");
                    return;
                }

                // We can use cloudnet
                ArrayList<ItemStack> itemStacks = CloudNetManager.getGroupItemStackArray(player);
                MultiInventoryCalculator inventory = new MultiInventoryCalculator(9 * 1, "§8» §6§lServer Wechsler", 1, itemStacks);
                inventory.show(0);
                player.openInventory(inventory.build());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        // in the inventory

        try {
            Player player = (Player) event.getWhoClicked();
            if (event.getCurrentItem() == null) return;
            if (!event.getCurrentItem().hasItemMeta()) return;
            if (event.getView().getTitle().equals("§8» §6§lServer Wechsler")) {
                event.setCancelled(true);
                // get item infos
                ItemStack itemStack = event.getCurrentItem();
                ItemMeta itemMeta = itemStack.getItemMeta();

                player.sendMessage("You clicked: " + itemMeta.getDisplayName());

                // If the item first site or last site
                String firstSiteName = MultiInventoryItems.getPreviousPage().getItemMeta().getDisplayName();
                String lastSiteName = MultiInventoryItems.getNextPage().getItemMeta().getDisplayName();
                if(firstSiteName.equals(itemMeta.getDisplayName()) || lastSiteName.equals(itemMeta.getDisplayName())) {
                    ArrayList<ItemStack> itemStacks = CloudNetManager.getGroupItemStackArray(player);
                    MultiInventoryCalculator inventory = new MultiInventoryCalculator(9 * 1, "§8» §6§lServer Wechsler", 1, itemStacks);
                    inventory.show(Integer.valueOf(CloudNetManager.getItemPersistentMeta("page", itemStack)));
                    player.sendMessage("Your Page: " + Integer.valueOf(CloudNetManager.getItemPersistentMeta("page", itemStack)));
                    player.openInventory(inventory.build());
                    return;
                }

                player.sendMessage("§cDas Item ist deaktiviert!");
                //player.closeInventory();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
