package de.mxscha.endernationlobby.listener.items;

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
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.Collection;
import java.util.HashMap;
import java.util.NoSuchElementException;

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

                    try {
                        // We can use cloudnet
                        HashMap<Integer, ServiceInfoSnapshot> lobbyServer = getLobbyServers();

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
                            ItemStack item = getItem(player, service);

                            // get item meta
                            ItemMeta itemMeta = item.getItemMeta();
                            // Add to item server info (name)
                            itemMeta.getPersistentDataContainer().set(NamespacedKey.fromString("server"), PersistentDataType.STRING, name);
                            // set the server info into the item
                            item.setItemMeta(itemMeta);

                            // set the item to the inventory
                            inventory.setItem(count, item);
                        }

                        player.openInventory(inventory);
                    } catch (ClassCastException ex) {
                        // We cannot use cloudnet
                        player.sendMessage("§cDas Item is deaktiviert!");
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private ItemStack getItem(Player player, ServiceInfoSnapshot service) {
        String name = service.getName().replaceAll("-", " ");
        int currentPlayers = service.getProperty(BridgeServiceProperty.ONLINE_COUNT).get();
        int maxPlayers = service.getProperty(BridgeServiceProperty.MAX_PLAYERS).get();
        boolean isFull = service.getProperty(BridgeServiceProperty.IS_FULL).get();
        boolean isEmpty = service.getProperty(BridgeServiceProperty.IS_EMPTY).get();
        boolean isPlayerOnline = isPlayerThere(player, service.getProperty(BridgeServiceProperty.PLAYERS).get());

        /* What to do:
        * Player Joined Glowestone
        * If service online Sugar
        * If service full Redstone
        */

        // 1. is the player on this server?
        if(isPlayerOnline) {
            // The Player is on this server && send normal Info
            return new ItemCreator(Material.GLOWSTONE_DUST)
                    .setName("§e" + name)
                    .setLore("§7Spieler§8: §a" + currentPlayers + "§7/§c" + maxPlayers)
                    .toItemStack();
        }

        // 2. is the service full?
        if(isFull) {
            // Full
            return new ItemCreator(Material.REDSTONE)
                    .setName("§c" + name)
                    .setLore("§cServer Voll")
                    .toItemStack();
        }

        // 3. is the service empty?
        if(isEmpty) {
            // Empty
            return new ItemCreator(Material.SUGAR)
                    .setName("§e" + name)
                    .setLore("§7Leer")
                    .toItemStack();
        }

        // 4. Send normal server info
        // Normal server info
        return new ItemCreator(Material.SUGAR)
                .setName("§e" + name)
                .setLore("§7Spieler§8: §a" + currentPlayers + "§7/§c" + maxPlayers)
                .toItemStack();
    }

    private boolean isPlayerThere(Player player, Collection<ServicePlayer> servicePlayers) {
        Collection<ServicePlayer> players = servicePlayers;
        for (ServicePlayer servicePlayer : players) // loop through all players
            if (servicePlayer.getUniqueId().equals(player.getUniqueId())) // check if they're the uuid of the player
                return true; // yes there is the player
        return false; // no there is no player
    }

    private HashMap<Integer, ServiceInfoSnapshot> getLobbyServers() {
        HashMap<Integer, ServiceInfoSnapshot> lobbyServer = new HashMap<>();
        WrapperGeneralCloudServiceProvider wrapperNodeInfoProvider = new WrapperGeneralCloudServiceProvider(Wrapper.getInstance());
        for(ServiceInfoSnapshot service : wrapperNodeInfoProvider.getCloudServices("Lobby")) {
            // No error if the server starting
            try {
                // We cannot get infos of the server is the server not connected
                String name = service.getName();
                lobbyServer.put(Integer.valueOf(name.split("-")[1]), service);
            }catch (NoSuchElementException ex) {
                // NoSuchElementException – if no value is present
            }
        }
        return lobbyServer;
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
