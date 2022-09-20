package de.mxscha.endernationlobby.utils.manager;

import de.mxscha.endernationlobby.utils.manager.items.ItemCreator;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class InventoryManager {

    static Player player;

    private static final ItemStack compass = new ItemCreator(Material.RECOVERY_COMPASS).setName("§8» §b§lNavigator").setLore("§7Teleportiere dich zu den verschiedenen Spielmodi!").toItemStack();
    private static final ItemStack playerHide = new ItemCreator(Material.LIME_DYE).setName("§8» §7§lSpieler §8| §a§lAlle").setLore("§7Benutze Rechtsklick um Spieler zu verstecken!").toItemStack();
    private static final ItemStack extras = new ItemCreator(Material.PLAYER_HEAD).setSkull("§8» §e§lExtras", "ElMarcosFTW", "§7Nutze deine Cosmetics um zu flexxen!").toItemStack();
    private static final ItemStack switcher = new ItemCreator(Material.NETHER_STAR).setName("§8» §6§lLobby Wechsler").setLore("§7Wechsle zwischen den Lobbys!").toItemStack();

    // Admin items
    private static final ItemStack flyMode = new ItemCreator(Material.FEATHER).setName("§8» §9§lFliegen §8| §cDeaktiviert").toItemStack();
    private static final ItemStack teamServerSwitcher = new ItemCreator(Material.COMMAND_BLOCK_MINECART).setName("§8» §6§lServer Wechsler").toItemStack();

    public static void setPlayerInventory(Player player) {
        Inventory inventory = player.getInventory();
        inventory.clear();
        inventory.setItem(0, compass);
        inventory.setItem(1, playerHide);
        //inventory.setItem(4, extras);
        inventory.setItem(7, switcher);
        inventory.setItem(8, new ItemCreator(Material.PLAYER_HEAD)
                .setSkull("§8» §a§lProfil",
                        player,
                        "§7Lade Freunde in eine Party ein!")
                .toItemStack());
        player.setLevel(2022);
        player.setExp(0);
        player.getInventory().setHeldItemSlot(0);
    }

    public static void addAdminItems(Player player) {
        Inventory inventory = player.getInventory();
        inventory.setItem(21, flyMode);
        inventory.setItem(22, teamServerSwitcher);
    }
}