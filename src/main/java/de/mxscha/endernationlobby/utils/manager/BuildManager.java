package de.mxscha.endernationlobby.utils.manager;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import java.util.ArrayList;

public class BuildManager {

    public static ArrayList<Player> builders = new ArrayList<>();

    public static void allow(Player player) {
        if (!isAllowed(player)) {
            builders.add(player);
            player.sendMessage(MessageManager.Prefix + "§7Du darfst §anun §7Bauen!");
            player.getInventory().clear();
            player.setGameMode(GameMode.CREATIVE);
        }
    }

    public static void disallow(Player player) {
        if (isAllowed(player)) {
            builders.remove(player);
            player.sendMessage(MessageManager.Prefix + "§7Du darfst nun §cnicht mehr §7Bauen!");
            InventoryManager.setPlayerInventory(player);
            player.setGameMode(GameMode.SURVIVAL);
        }
        player.setAllowFlight(true);
    }

    public static boolean isAllowed(Player player) {
        return builders.contains(player);
    }
}
