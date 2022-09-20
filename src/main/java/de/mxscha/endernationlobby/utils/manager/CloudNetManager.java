package de.mxscha.endernationlobby.utils.manager;

import de.dytanic.cloudnet.driver.service.ServiceInfoSnapshot;
import de.dytanic.cloudnet.ext.bridge.BridgeServiceProperty;
import de.dytanic.cloudnet.ext.bridge.player.ServicePlayer;
import de.dytanic.cloudnet.wrapper.Wrapper;
import de.dytanic.cloudnet.wrapper.provider.service.WrapperGeneralCloudServiceProvider;
import de.mxscha.endernationlobby.utils.manager.items.ItemCreator;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.NoSuchElementException;

public class CloudNetManager {

    public static boolean existCloudNet() {
        try {
            new WrapperGeneralCloudServiceProvider(Wrapper.getInstance());
            return true;
        } catch (NoSuchMethodError ex) {
            return false;
        }

    }

    public static ArrayList<String> getServiceGroups() {
        ArrayList<String> groups = new ArrayList<>();
        WrapperGeneralCloudServiceProvider wrapperNodeInfoProvider = new WrapperGeneralCloudServiceProvider(Wrapper.getInstance());
        for(ServiceInfoSnapshot service : wrapperNodeInfoProvider.getCloudServices()) {
            // No error if the server starting
            try {
                // We cannot get infos of the server is the server not connected
                String name = service.getName();
                String group = String.valueOf(name.split("-")[0]);

                if(!groups.contains(group))
                    groups.add(group);
            }catch (NoSuchElementException ex) {
                // NoSuchElementException – if no value is present
            }
        }

        // fake groups for testing
        groups.add("FakeGroup1");
        groups.add("FakeGroup2");
        groups.add("FakeGroup3");
        groups.add("FakeGroup4");
        groups.add("FakeGroup5");
        groups.add("FakeGroup6");
        groups.add("FakeGroup7");
        groups.add("FakeGroup8");
        groups.add("FakeGroup9");
        groups.add("FakeGroup10");
        groups.add("FakeGroup11");
        groups.add("FakeGroup12");
        return groups;
    }

    public static HashMap<Integer, ServiceInfoSnapshot> getServiceServers(String serviceGroup) {
        HashMap<Integer, ServiceInfoSnapshot> serviceServer = new HashMap<>();
        WrapperGeneralCloudServiceProvider wrapperNodeInfoProvider = new WrapperGeneralCloudServiceProvider(Wrapper.getInstance());
        for(ServiceInfoSnapshot service : wrapperNodeInfoProvider.getCloudServices(serviceGroup)) {
            // No error if the server starting
            try {
                // We cannot get infos of the server is the server not connected
                String name = service.getName();
                serviceServer.put(Integer.valueOf(name.split("-")[1]), service);
            }catch (NoSuchElementException ex) {
                // NoSuchElementException – if no value is present
            }
        }
        return serviceServer;
    }

    public static boolean isPlayerExist(Player player, Collection<ServicePlayer> servicePlayers) {
        Collection<ServicePlayer> players = servicePlayers;
        for (ServicePlayer servicePlayer : players) // loop through all players
            if (servicePlayer.getUniqueId().equals(player.getUniqueId())) // check if they're the uuid of the player
                return true; // yes there is the player
        return false; // no there is no player
    }

    public static ItemStack getGroupItem(Player player, ServiceInfoSnapshot service) {
        String name = service.getName().replaceAll("-", " ");
        int currentPlayers = service.getProperty(BridgeServiceProperty.ONLINE_COUNT).get();
        int maxPlayers = service.getProperty(BridgeServiceProperty.MAX_PLAYERS).get();
        boolean isFull = service.getProperty(BridgeServiceProperty.IS_FULL).get();
        boolean isEmpty = service.getProperty(BridgeServiceProperty.IS_EMPTY).get();
        boolean isPlayerOnline = isPlayerExist(player, service.getProperty(BridgeServiceProperty.PLAYERS).get());

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

    public static ItemStack getGroupItem(Player player, String group) {
        String name = group.replaceAll("-", " ");
        int currentPlayers = 0;
        int maxPlayers = 0;
        boolean isFull = false;
        boolean isEmpty = false;
        boolean isPlayerOnline = false;

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

    public static ItemStack addItemServiceMeta(String key, String value, ItemStack item) {
        // get item meta
        ItemMeta itemMeta = item.getItemMeta();
        // Add to item server info (name)
        itemMeta.getPersistentDataContainer().set(NamespacedKey.fromString(key), PersistentDataType.STRING, value);
        // set the server info into the item
        item.setItemMeta(itemMeta);
        return item;
    }
}
