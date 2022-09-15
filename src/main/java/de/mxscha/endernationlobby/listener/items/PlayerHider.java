package de.mxscha.endernationlobby.listener.items;

import de.mxscha.endernationlobby.LobbyCore;
import de.mxscha.endernationlobby.utils.manager.MessageManager;
import de.mxscha.endernationlobby.utils.manager.items.ItemCreator;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

public class PlayerHider implements Listener {

    private static final ItemStack team = new ItemCreator(Material.LIGHT_BLUE_DYE)
            .setName("§8» §7§lSpieler §8| §9§lTeam sichtbar")
            .setLore("§7Benutze Rechtsklick um Spieler zu verstecken & VIPs anzuzeigen!")
            .toItemStack();

    private static final ItemStack vip = new ItemCreator(Material.PURPLE_DYE)
            .setName("§8» §7§lSpieler §8| §5§lVIPs sichtbar")
            .setLore("§7Benutze Rechtsklick um Spieler zu verstecken!")
            .toItemStack();

    private static final ItemStack nobody = new ItemCreator(Material.GRAY_DYE)
            .setName("§8» §7§lSpieler §8| §c§lNiemand")
            .setLore("§7Benutze Rechtsklick um Spieler zu anzuzeigen!")
            .toItemStack();

    private static final ItemStack playerHide = new ItemCreator(Material.LIME_DYE)
            .setName("§8» §7§lSpieler §8| §a§lAlle")
            .setLore("§7Benutze Rechtsklick um Spieler zu verstecken!")
            .toItemStack();

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        try {
            Player player = event.getPlayer();
            if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
                if (event.getItem().getItemMeta().getDisplayName().equals("§8» §7§lSpieler §8| §a§lAlle")) {
                    if (!player.hasCooldown(Material.LIME_DYE)) {
                        for (Player players : Bukkit.getOnlinePlayers()) {
                            if (!(players == player)) {
                                if (!players.hasPermission("lobby.team")) {
                                    player.hidePlayer(LobbyCore.getInstance(), players);
                                }
                            }
                        }
                        player.sendMessage(MessageManager.Prefix + "§7Du siehst nun nur noch das §9Team§7!");
                        player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 0.25f, 1);
                        player.getInventory().setItem(1, team);
                        player.setCooldown(Material.LIGHT_BLUE_DYE, 12);
                    }
                } else if (event.getItem().getItemMeta().getDisplayName().equals("§8» §7§lSpieler §8| §9§lTeam sichtbar")) {
                    if (!player.hasCooldown(Material.LIGHT_BLUE_DYE)) {
                        for (Player players : Bukkit.getOnlinePlayers()) {
                            if (!(players == player)) {
                                if (!players.hasPermission("lobby.vip")) {
                                    player.hidePlayer(LobbyCore.getInstance(), players);
                                }
                            }
                        }
                        player.sendMessage(MessageManager.Prefix + "§7Du siehst nun nur noch die §5VIPs§7!");
                        player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 0.25f, 1);
                        player.getInventory().setItem(1, vip);
                        player.setCooldown(Material.PURPLE_DYE, 12);
                    }
                } else if (event.getItem().getItemMeta().getDisplayName().equals("§8» §7§lSpieler §8| §5§lVIPs sichtbar")) {
                    if (!player.hasCooldown(Material.PURPLE_DYE)) {
                        for (Player players : Bukkit.getOnlinePlayers()) {
                            if (!(players == player)) {
                                player.hidePlayer(LobbyCore.getInstance(), players);
                            }
                        }
                        player.sendMessage(MessageManager.Prefix + "§7Du siehst nun §ckeine §7Spieler mehr!");
                        player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 0.25f, 1);
                        player.getInventory().setItem(1, nobody);
                        player.setCooldown(Material.GRAY_DYE, 12);
                    }
                } else if (event.getItem().getItemMeta().getDisplayName().equals("§8» §7§lSpieler §8| §c§lNiemand")) {
                    if (!player.hasCooldown(Material.GRAY_DYE)) {
                        for (Player players : Bukkit.getOnlinePlayers()) {
                            if (!(players == player)) {
                                player.showPlayer(LobbyCore.getInstance(), players);
                            }
                        }
                        player.sendMessage(MessageManager.Prefix + "§7Alle Spieler wieder sichtbar!");
                        player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 0.25f, 1);
                        player.getInventory().setItem(1, playerHide);
                        player.setCooldown(Material.LIME_DYE, 12);
                    }
                }
            }
        } catch (Exception e) {

        }
    }
}
