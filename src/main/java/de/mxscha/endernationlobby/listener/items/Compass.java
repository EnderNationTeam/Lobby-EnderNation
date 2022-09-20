package de.mxscha.endernationlobby.listener.items;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import de.mxscha.endernationlobby.LobbyCore;
import de.mxscha.endernationlobby.utils.PluginMessagesListener;
import de.mxscha.endernationlobby.utils.manager.MessageManager;
import de.mxscha.endernationlobby.utils.manager.items.ItemCreator;
import de.mxscha.endernationlobby.utils.manager.locations.ConfigLocationUtil;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;

public class Compass implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        try {
            Player player = event.getPlayer();
            if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
                if (event.getItem().getItemMeta().getDisplayName().equals("§8» §b§lNavigator")) {
                    event.setCancelled(true);
                    Inventory inventory = Bukkit.createInventory(null, 9*5, "§8» §b§lNavigator");
                    player.openInventory(inventory);
                    ByteArrayDataOutput out = ByteStreams.newDataOutput();
                    out.writeUTF("PlayerCount");
                    out.writeUTF("EndOfLife-1");
                    player.sendPluginMessage(LobbyCore.getInstance(), "BungeeCord", out.toByteArray());
                    int eofPlayerCount = PluginMessagesListener.getPlayerCountEndOfLife();
                    inventory.setItem(22, new ItemCreator(Material.MAGMA_CREAM)
                            .addEnchantment(Enchantment.KNOCKBACK,1,true)
                            .setName("§8» §6§lSpawn")
                            .setLore("§8● §7Klicke um dich zum §6§lSpawn §7zu teleportieren!")
                            .toItemStack());
                    inventory.setItem(3, new ItemCreator(Material.WITHER_ROSE)
                            .setName("§8» §9§lEnd§f§lOf§a§lLife")
                            .setLore("§8● §7Spieler Online§8: §b" + eofPlayerCount, "","§8● §7Klicke um dich zu §9§lEnd§f§lOf§a§lLife §7zu teleportieren!")
                            .toItemStack());
                    inventory.setItem(5, new ItemCreator(Material.IRON_SWORD)
                            .setName("§8» §c§lMurder Mystery")
                            .setLore("§8● §7Klicke um dich zu §c§lMurder Mystery §7zu teleportieren!")
                            .toItemStack());
                    inventory.setItem(10, new ItemCreator(Material.GRASS_BLOCK)
                            .setName("§8» §a§lBuild §c§lBattle")
                            .setLore("§8● §7Klicke um dich zu §a§lBuild §c§lBattle §7zu teleportieren!")
                            .toItemStack());
                    inventory.setItem(16, new ItemCreator(Material.NETHERITE_PICKAXE)
                            .setName("§8» §b§lSmash")
                            .setLore("§8● §7Klicke um dich zu §b§lSmash §7zu teleportieren!")
                            .toItemStack());
                    inventory.setItem(28, new ItemCreator(Material.RED_STAINED_GLASS_PANE)
                            .setName("§c§lIn Arbeit!")
                            .toItemStack());
                    inventory.setItem(34, new ItemCreator(Material.RED_STAINED_GLASS_PANE)
                            .setName("§c§lIn Arbeit!")
                            .toItemStack());
                    inventory.setItem(40, new ItemCreator(Material.RED_STAINED_GLASS_PANE)
                            .setName("§c§lIn Arbeit!")
                            .toItemStack());
                    player.playSound(player.getLocation(), Sound.BLOCK_CHEST_OPEN, 0.5f, 2);
                }
            }
        } catch (Exception e) {

        }
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        try {
            Player player = (Player) event.getWhoClicked();
            if (event.getView().getTitle().equals("§8» §b§lNavigator")) {
                switch (event.getCurrentItem().getItemMeta().getDisplayName()) {
                    case "§8» §6§lSpawn":
                        player.teleport(new ConfigLocationUtil("Lobby").loadLocation());
                        player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 0.5f, 1);
                        player.sendMessage(MessageManager.Prefix + "§7Du bist nun beim §6§lSpawn§7!");
                        break;
                    case "§8» §b§lSmash":
                        player.teleport(new ConfigLocationUtil("Smash").loadLocation());
                        player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 0.5f, 1);
                        player.sendMessage(MessageManager.Prefix + "§7Du bist nun bei §b§lSmash§7!");
                        break;
                    case "§8» §a§lBuild §c§lBattle":
                        player.teleport(new ConfigLocationUtil("BB").loadLocation());
                        player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 0.5f, 1);
                        player.sendMessage(MessageManager.Prefix + "§7Du bist nun bei §a§lBuild §c§lBattle§7!");
                        break;
                    case "§8» §9§lEnd§f§lOf§a§lLife":
                        player.teleport(new ConfigLocationUtil("EndOfLife").loadLocation());
                        player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 0.5f, 1);
                        player.sendMessage(MessageManager.Prefix + "§7Du bist nun bei §9§lEnd§f§lOf§a§lLife§7!");
                        break;
                    case "§8» §c§lMurder Mystery":
                        player.teleport(new ConfigLocationUtil("MM").loadLocation());
                        player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 0.5f, 1);
                        player.sendMessage(MessageManager.Prefix + "§7Du bist nun bei §c§lMurder Mystery§7!");
                        break;
                }
            }
        } catch (Exception e) {

        }
    }
}
