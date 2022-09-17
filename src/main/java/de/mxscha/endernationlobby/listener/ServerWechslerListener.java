package de.mxscha.endernationlobby.listener;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import de.dytanic.cloudnet.driver.service.ServiceInfoSnapshot;
import de.dytanic.cloudnet.ext.bridge.BridgeServiceProperty;
import de.dytanic.cloudnet.ext.bridge.player.ServicePlayer;
import de.dytanic.cloudnet.wrapper.Wrapper;
import de.dytanic.cloudnet.wrapper.provider.service.WrapperGeneralCloudServiceProvider;
import de.mxscha.endernationlobby.LobbyCore;
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
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.Collection;
import java.util.NoSuchElementException;

public class ServerWechslerListener implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        try {
            Player player = (Player) event.getWhoClicked();
            if (event.getCurrentItem() == null) return;
            if (!event.getCurrentItem().hasItemMeta()) return;
            if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§8» §6§lServer Wechsler")) {
                event.setCancelled(true);
                Inventory inventory = Bukkit.createInventory(null, 9 * 6, "§8» §6§lServer Wechsler");
                fill(inventory);

                // Server info
                int positionX = 1; // Starting at 1
                int positionY = 0;
                int position = 0;
                WrapperGeneralCloudServiceProvider wrapperNodeInfoProvider = new WrapperGeneralCloudServiceProvider(Wrapper.getInstance());
                for(ServiceInfoSnapshot service : wrapperNodeInfoProvider.getStartedCloudServices()) {
                    // get all infos
                    String nameRaw;
                    String name;
                    int currentPlayers;
                    int maxPlayers;
                    boolean isFull;
                    boolean isStarting;
                    boolean isEmpty;

                    // No error if the server starting
                    try {
                        // We cannot get infos of the server is the server not connected
                        nameRaw = service.getName();
                        name = service.getName().replaceAll("-", " ");
                        currentPlayers = service.getProperty(BridgeServiceProperty.ONLINE_COUNT).get();
                        maxPlayers = service.getProperty(BridgeServiceProperty.MAX_PLAYERS).get();
                        isFull = service.getProperty(BridgeServiceProperty.IS_FULL).get();
                        isStarting = service.getProperty(BridgeServiceProperty.IS_STARTING).get();
                        isEmpty = service.getProperty(BridgeServiceProperty.IS_EMPTY).get();
                    }catch (NoSuchElementException ex) {
                        // NoSuchElementException – if no value is present
                        continue; // skip item
                    }


                    // Check if player on this server
                    boolean isPlayerOnline = false;

                    // Only if the server not empty and not starting
                    if(!isEmpty && !isStarting) {
                        Collection<ServicePlayer> players = service.getProperty(BridgeServiceProperty.PLAYERS).get();
                        for (ServicePlayer servicePlayer : players) // loop through all players
                            if (servicePlayer.getUniqueId().equals(player.getUniqueId())) // check if they're the uuid of the player
                                isPlayerOnline = true; // yes there is the player
                    }

                    /* What to do:
                     * Player Joined Glowestone
                     * If service online Sugar
                     * If service full Redstone
                     */

                    // Count up because it is a new service
                    if(positionX < 9) { // count y up with at 8
                        positionY++;
                        positionX = 1;
                    } else
                        positionX++;

                    // Math xd. y * 9 and x the location
                    position = (positionY * 9) + positionX;

                    // Only 42 Servers allowed
                    if(position >= 42) {
                        player.sendMessage("§Error! §7Only 42 Servers are allowed. Report it to an Administrator!");
                        continue; // Skip this service
                    }

                    ItemStack itemStack = null;

                    // set to invenory
                    // 1. is the server starting?
                    if(isStarting) {
                        // Starting
                        itemStack = new ItemCreator(Material.LIGHT_GRAY_CONCRETE)
                                .setName("§e" + name)
                                .setLore("7Players §aStartet")
                                .toItemStack();

                    } else
                        // 2. is the player on this server?
                        if(isPlayerOnline) {
                            // The Player is on this server && send normal Info
                            itemStack = new ItemCreator(Material.GLOWSTONE_DUST)
                                    .setName("§e" + name)
                                    .setLore("§7Players §a" + currentPlayers + "§7/§c" + maxPlayers)
                                    .toItemStack();
                        } else
                            // 3. is the service full?
                            if(isFull) {
                                // Full
                                itemStack = new ItemCreator(Material.REDSTONE)
                                        .setName("§c" + name)
                                        .setLore("§7Players §cServer Full")
                                        .toItemStack();

                            } else
                                // 4. is the service empty?
                                if(isEmpty) {
                                    // Empty
                                    itemStack = new ItemCreator(Material.SUGAR)
                                            .setName("§e" + name)
                                            .setLore("§7Players §7Leer")
                                            .toItemStack();

                                } else
                                // 5. Send normal server info
                                {
                                    // Normal server info
                                    itemStack = new ItemCreator(Material.SUGAR)
                                            .setName("§e" + name)
                                            .setLore("§7Players §a" + currentPlayers + "§7/§c" + maxPlayers)
                                            .toItemStack();
                                }

                    // get item meta
                    ItemMeta itemMeta = itemStack.getItemMeta();
                    // Add to item server info (name)
                    itemMeta.getPersistentDataContainer().set(NamespacedKey.fromString("server"), PersistentDataType.STRING, nameRaw);
                    // set the server info into the item
                    itemStack.setItemMeta(itemMeta);

                    // set the item to the inventory
                    inventory.setItem(position, itemStack);
                }

                player.openInventory(inventory);
            }

            if (event.getView().getTitle().equals("§8» §6§lServer Wechsler")) {
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
