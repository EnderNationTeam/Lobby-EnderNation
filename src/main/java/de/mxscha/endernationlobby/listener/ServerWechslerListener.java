package de.mxscha.endernationlobby.listener;

import de.mxscha.endernationlobby.utils.manager.CloudNetManager;
import de.mxscha.endernationlobby.utils.manager.inventory.MultiInventoryCalculator;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class ServerWechslerListener implements Listener {

    // TODO: Groups
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        try {
            Player player = (Player) event.getWhoClicked();
            if (event.getCurrentItem() == null) return;
            if (!event.getCurrentItem().hasItemMeta()) return;
            if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§8» §6§lServer Wechsler")) {
                event.setCancelled(true);

                MultiInventoryCalculator inventory = new MultiInventoryCalculator(9 * 1, "§8» §6§lServer Wechsler", 1);

                if(!CloudNetManager.existCloudNet()) {
                    // We cannot use cloudnet
                    player.sendMessage("§cDas Item ist deaktiviert!");
                    return;
                }

                // We can use cloudnet
                ArrayList<String> groups = CloudNetManager.getServiceGroups();
                for (String group : groups) {

                    // Filter proxy group
                    if(group.equals("Proxy")) continue;

                    // get item
                    ItemStack item = CloudNetManager.addItemServiceMeta("group", group, CloudNetManager.getGroupItem(player, group));

                    // set the item to the inventory
                    inventory.addItem(item);
                }

                player.openInventory(inventory.build());
            }

            if (event.getView().getTitle().equals("§8» §6§lServer Wechsler")) {
                player.sendMessage("§cDas Item ist deaktiviert!");
                player.closeInventory();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
