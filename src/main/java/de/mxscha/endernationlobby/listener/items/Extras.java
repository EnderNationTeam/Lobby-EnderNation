package de.mxscha.endernationlobby.listener.items;

import de.mxscha.lobby.utils.manager.items.ItemCreator;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;

public class Extras implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            Player player = event.getPlayer();
            try {
                if (event.getItem().getItemMeta().getDisplayName().equals("§8» §e§lExtras")) {
                    event.setCancelled(true);
                    Inventory inventory = Bukkit.createInventory(null, 9*5, "§8» §e§lExtras");
                    player.playSound(player.getLocation(), Sound.BLOCK_CHEST_OPEN, 0.5f, 2);
                    inventory.setItem(1, new ItemCreator(Material.LIME_STAINED_GLASS_PANE).setName(" ").toItemStack());
                    inventory.setItem(10, new ItemCreator(Material.GRAY_STAINED_GLASS_PANE).setName(" ").toItemStack());
                    inventory.setItem(19, new ItemCreator(Material.GRAY_STAINED_GLASS_PANE).setName(" ").toItemStack());
                    inventory.setItem(28, new ItemCreator(Material.GRAY_STAINED_GLASS_PANE).setName(" ").toItemStack());
                    inventory.setItem(37, new ItemCreator(Material.GRAY_STAINED_GLASS_PANE).setName(" ").toItemStack());
                    //
                    inventory.setItem(0, new ItemCreator(Material.PLAYER_HEAD).setSkull(
                            "§8» §6Köpfe", "JustMxscha", "§8» §aausgewählt"
                    ).toItemStack());
                    inventory.setItem(12, new ItemCreator(Material.PLAYER_HEAD).setSkull("§8» §6Paluten", "Paluten", "", "§8» §ePreis§8: §c1000 Coins", "§8» §7Klicke zum Kaufen!").toItemStack());
                    inventory.setItem(13, new ItemCreator(Material.PLAYER_HEAD).setSkull("§8» §5DnerTV", "KingJoongeDner", "", "§8» §ePreis§8: §c1000 Coins", "§8» §7Klicke zum Kaufen!").toItemStack());
                    inventory.setItem(14, new ItemCreator(Material.PLAYER_HEAD).setSkull("§8» §dBastiGHG", "BastiGHG", "", "§8» §ePreis§8: §c1000 Coins", "§8» §7Klicke zum Kaufen!").toItemStack());
                    inventory.setItem(15, new ItemCreator(Material.PLAYER_HEAD).setSkull("§8» §dBastiGHG", "BastiGHG", "", "§8» §ePreis§8: §c1000 Coins", "§8» §7Klicke zum Kaufen!").toItemStack());
                    inventory.setItem(16, new ItemCreator(Material.PLAYER_HEAD).setSkull("§8» §dBastiGHG", "BastiGHG", "", "§8» §ePreis§8: §c1000 Coins", "§8» §7Klicke zum Kaufen!").toItemStack());

                    inventory.setItem(21, new ItemCreator(Material.PLAYER_HEAD).setSkull("§8» §dBastiGHG", "BastiGHG", "", "§8» §ePreis§8: §c1000 Coins", "§8» §7Klicke zum Kaufen!").toItemStack());
                    inventory.setItem(22, new ItemCreator(Material.PLAYER_HEAD).setSkull("§8» §dBastiGHG", "BastiGHG", "", "§8» §ePreis§8: §c1000 Coins", "§8» §7Klicke zum Kaufen!").toItemStack());
                    inventory.setItem(23, new ItemCreator(Material.PLAYER_HEAD).setSkull("§8» §dBastiGHG", "BastiGHG", "", "§8» §ePreis§8: §c1000 Coins", "§8» §7Klicke zum Kaufen!").toItemStack());
                    inventory.setItem(24, new ItemCreator(Material.PLAYER_HEAD).setSkull("§8» §dBastiGHG", "BastiGHG", "", "§8» §ePreis§8: §c1000 Coins", "§8» §7Klicke zum Kaufen!").toItemStack());
                    inventory.setItem(25, new ItemCreator(Material.PLAYER_HEAD).setSkull("§8» §dBastiGHG", "BastiGHG", "", "§8» §ePreis§8: §c1000 Coins", "§8» §7Klicke zum Kaufen!").toItemStack());

                    player.openInventory(inventory);
                }
            } catch (Exception e) {

            }
        }
    }
}
