package de.mxscha.endernationlobby.listener;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import de.dytanic.cloudnet.driver.service.ServiceInfoSnapshot;
import de.mxscha.endernationlobby.LobbyCore;
import de.mxscha.endernationlobby.utils.manager.CloudNetManager;
import de.mxscha.endernationlobby.utils.manager.MessageManager;
import de.mxscha.endernationlobby.utils.manager.inventory.InventoryCalculator;
import de.mxscha.endernationlobby.utils.manager.items.ItemCreator;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

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

                InventoryCalculator inventory = new InventoryCalculator(9 * 1, "§8» §6§lServer Wechsler", 1);

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
                player.closeInventory();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
