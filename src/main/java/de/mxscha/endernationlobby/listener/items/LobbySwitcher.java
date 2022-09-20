package de.mxscha.endernationlobby.listener.items;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import de.dytanic.cloudnet.driver.service.ServiceInfoSnapshot;
import de.mxscha.endernationlobby.LobbyCore;
import de.mxscha.endernationlobby.utils.manager.CloudNetManager;
import de.mxscha.endernationlobby.utils.manager.MessageManager;
import de.mxscha.endernationlobby.utils.manager.items.ItemCreator;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.HashMap;

public class LobbySwitcher implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        try {
            Player player = event.getPlayer();
            if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
                if (event.getItem().getItemMeta().getDisplayName().equals("§8» §6§lLobby Wechsler")) {
                    event.setCancelled(true);
                    // Create inventory
                    Inventory inventory = Bukkit.createInventory(null, InventoryType.HOPPER, "§8» §6§lLobby Wechsler");
                    fill(inventory);

                    if(CloudNetManager.existCloudNet()) {
                        // We cannot use cloudnet
                        player.sendMessage("§cDas Item is deaktiviert!");
                        return;
                    }

                    // We can use cloudnet
                    HashMap<Integer, ServiceInfoSnapshot> lobbyServer = CloudNetManager.getServiceServers("Lobby");

                    int count = 0;
                    for (int serviceNumber : lobbyServer.keySet()) {
                        ServiceInfoSnapshot service = lobbyServer.get(serviceNumber);
                        String name = service.getName();

                        // Count up because it is a new service
                        count++;

                        // Only 3 Servers allowed
                        if (count > 3) {
                            player.sendMessage("§4Error! §7Only 3 Servers are allowed. Report it to an Administrator!");
                            continue; // Skip this service
                        }

                        // get item
                        ItemStack item = CloudNetManager.addItemServiceMeta(name, CloudNetManager.getItem(player, service));

                        // set the item to the inventory
                        inventory.setItem(count, item);
                    }
                    player.openInventory(inventory);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        try {
            Player player = (Player) event.getWhoClicked();
            if (event.getView().getTitle().equals("§8» §6§lLobby Wechsler")) {
                // get item infos
                ItemStack itemStack = event.getCurrentItem();
                ItemMeta itemMeta = itemStack.getItemMeta();
                // check if the material sugar. This mean you can join the server
                if(itemStack.getType() != Material.SUGAR) return;
                // get name without colors
                String serverName = ChatColor.stripColor(itemMeta.getDisplayName());
                // get server info inside the item
                String server = itemMeta.getPersistentDataContainer().get(NamespacedKey.fromString("server"), PersistentDataType.STRING);
                // send to server
                ByteArrayDataOutput out;
                out = ByteStreams.newDataOutput();
                out.writeUTF("Connect");
                out.writeUTF(server);
                player.sendPluginMessage(LobbyCore.getInstance(), "BungeeCord", out.toByteArray());
                // send player info
                player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 0.5f, 1);
                player.sendMessage(MessageManager.Prefix + "§7Du wurdest zu §a§l" + serverName + " §7gesendet!");
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
