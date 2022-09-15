package de.mxscha.endernationlobby.listener.items;

import de.dytanic.cloudnet.driver.service.ServiceInfoSnapshot;
import de.dytanic.cloudnet.ext.bridge.BridgeServiceProperty;
import de.dytanic.cloudnet.ext.bridge.player.ServicePlayer;
import de.dytanic.cloudnet.wrapper.Wrapper;
import de.dytanic.cloudnet.wrapper.provider.service.WrapperGeneralCloudServiceProvider;
import de.mxscha.endernationlobby.utils.manager.items.ItemCreator;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;

public class LobbySwitcher implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        try {
            Player player = event.getPlayer();
            if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
                if (event.getItem().getItemMeta().getDisplayName().equals("§8» §6§lLobby Wechsler")) {
                    event.setCancelled(true);
                    Inventory inventory = Bukkit.createInventory(null, InventoryType.HOPPER, "§8» §6§lLobby Wechsler");
                    fill(inventory);
                    //Switcher.stop();

                    // Begin Keksgauner - Date 15.09.2022
                    // Server info
                    int position = 0;
                    WrapperGeneralCloudServiceProvider wrapperNodeInfoProvider = new WrapperGeneralCloudServiceProvider(Wrapper.getInstance());
                    for(ServiceInfoSnapshot service : wrapperNodeInfoProvider.getCloudServices("Lobby")) {
                        // Fix of java.util.NoSuchElementException: No value present
                        // We cannot get infos of the server is the server not connected
                        if(!service.isConnected()) {
                            continue; // Server is offline
                        }
                        // get all infos
                        String name = service.getName().replaceAll("-", " ");
                        int currentPlayers = service.getProperty(BridgeServiceProperty.ONLINE_COUNT).get();
                        int maxPlayers = service.getProperty(BridgeServiceProperty.MAX_PLAYERS).get();
                        boolean isFull = service.getProperty(BridgeServiceProperty.IS_FULL).get();
                        boolean isStarting = service.getProperty(BridgeServiceProperty.IS_STARTING).get();
                        boolean isEmpty = service.getProperty(BridgeServiceProperty.IS_EMPTY).get();

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
                        position++;

                        // Only 3 Servers allowed
                        if(position >= 3) {
                            player.sendMessage("§Error! §7Only 3 Servers are allowed. Report it to an Administrator!");
                            continue; // Skip this service
                        }

                        // set to invenory
                        // 1. is the server starting?
                        if(isStarting) {
                            // Starting
                            inventory.setItem(position,
                                    new ItemCreator(Material.LIGHT_GRAY_CONCRETE)
                                            .setName(name)
                                            .setLore("7Players §aStartet")
                                            .toItemStack());

                        } else
                            // 2. is the player on this server?
                            if(isPlayerOnline) {
                                // The Player is on this server && send normal Info
                                inventory.setItem(position,
                                        new ItemCreator(Material.GLOWSTONE_DUST)
                                                .setName(name)
                                                .setLore("§cPlayers " + currentPlayers + "/" + maxPlayers)
                                                .toItemStack());
                            } else
                                // 3. is the service full?
                                if(isFull) {
                                    // Full
                                    inventory.setItem(position,
                                            new ItemCreator(Material.REDSTONE)
                                                    .setName("§c" + name)
                                                    .setLore("§7Players §cServer Full")
                                                    .toItemStack());

                                } else
                                    // 4. is the service empty?
                                    if(isEmpty) {
                                        // Empty
                                        inventory.setItem(position,
                                                new ItemCreator(Material.SUGAR)
                                                        .setName("§e" +name)
                                                        .setLore("§7Players §7Leer")
                                                        .toItemStack());

                                    } else
                                        // 5. Send normal server info
                                        {
                                            // Normal server info
                                            inventory.setItem(position,
                                                    new ItemCreator(Material.SUGAR)
                                                            .setName("§e" + name)
                                                            .setLore("§7Players §a" + currentPlayers + "§7/§c" + maxPlayers)
                                                            .toItemStack());
                                        }
                    }
                    // Keksgauner END

                    player.openInventory(inventory);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void fill(Inventory inventory) {
        for (int i = 0; i < inventory.getSize(); i++) {
            inventory.setItem(i, new ItemCreator(Material.GRAY_STAINED_GLASS_PANE).setName("").toItemStack());
        }
    }
}
