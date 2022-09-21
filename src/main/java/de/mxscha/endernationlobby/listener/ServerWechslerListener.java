package de.mxscha.endernationlobby.listener;

import de.dytanic.cloudnet.driver.service.ServiceInfoSnapshot;
import de.mxscha.endernationlobby.utils.manager.CloudNetManager;
import de.mxscha.endernationlobby.utils.manager.MessageManager;
import de.mxscha.endernationlobby.utils.manager.inventory.MultiInventoryCalculator;
import de.mxscha.endernationlobby.utils.manager.inventory.MultiInventoryItems;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

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
            // get item infos
            ItemStack itemStack = event.getCurrentItem();
            ItemMeta itemMeta = itemStack.getItemMeta();
            if(itemMeta == null) return;

            if (event.getView().getTitle().equals("§8» §6§lServer Wechsler")) {
                event.setCancelled(true);

                // If the item first site or last site
                String firstSiteName = Objects.requireNonNull(MultiInventoryItems.getPreviousPage().getItemMeta()).getDisplayName();
                String lastSiteName = Objects.requireNonNull(MultiInventoryItems.getNextPage().getItemMeta()).getDisplayName();
                if(firstSiteName.equals(itemMeta.getDisplayName()) || lastSiteName.equals(itemMeta.getDisplayName())) {
                    ArrayList<ItemStack> itemStacks = CloudNetManager.getGroupItemStackArray(player);
                    MultiInventoryCalculator inventory = new MultiInventoryCalculator(9 * 1, "§8» §6§lServer Wechsler", 1, itemStacks);
                    inventory.show(Integer.parseInt(CloudNetManager.getItemPersistentMeta("page", itemStack)));
                    player.openInventory(inventory.build());
                    return;
                }
            }

            if(event.getView().getTitle().endsWith("§8» §6§lServer Wechsler")) {
                // this is group select
                event.setCancelled(true);

                // to save items in the list to use
                ArrayList<ItemStack> itemStacks = new ArrayList<>();

                // get group to open
                String group = CloudNetManager.getItemPersistentMeta("group", itemStack);
                HashMap<Integer, ServiceInfoSnapshot> groupServer = CloudNetManager.getServiceServers(group);
                for (int serviceNumber : groupServer.keySet()) {
                    ServiceInfoSnapshot service = groupServer.get(serviceNumber);
                    String name = service.getName();

                    // get item
                    ItemStack item = CloudNetManager.addItemPersistentMeta("server", name, CloudNetManager.getServiceItem(player, service));

                    itemStacks.add(item);
                }


                MultiInventoryCalculator inventory = new MultiInventoryCalculator(9 * 1, "§8» §6§lServer Wechsler * " + group, 1, itemStacks);
                String page = CloudNetManager.getItemPersistentMeta("page", itemStack);

                if(page == null)
                    page = "0";

                inventory.show(Integer.parseInt(page));
                player.openInventory(inventory.build());
                return;
            }

            if(event.getView().getTitle().startsWith("§8» §6§lServer Wechsler")) {
                // this is a group
                event.setCancelled(true);
                // check if the material sugar. This mean you can join the server
                if(itemStack.getType() != Material.SUGAR) return;
                // get name without colors
                String serverName = ChatColor.stripColor(itemMeta.getDisplayName());
                // get server info inside the item
                String server = CloudNetManager.getItemPersistentMeta("server", itemStack);

                // Catch if this null
                if(server == null) {
                    player.sendMessage("§4Error! §7Ich kann den Server nicht finden. Report it to an Administrator!");
                    return;
                }

                CloudNetManager.sendServer(player, server);

                // send player info
                player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 0.5f, 1);
                player.sendMessage(MessageManager.Prefix + "§7Du wurdest zu §a§l" + serverName + " §7gesendet!");
                player.closeInventory();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
