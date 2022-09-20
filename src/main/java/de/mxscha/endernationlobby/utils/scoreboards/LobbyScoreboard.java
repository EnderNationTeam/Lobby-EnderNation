package de.mxscha.endernationlobby.utils.scoreboards;

import de.dytanic.cloudnet.driver.CloudNetDriver;
import de.dytanic.cloudnet.driver.permission.IPermissionGroup;
import de.dytanic.cloudnet.driver.permission.IPermissionUser;
import de.mxscha.coinsystem.CoinCore;
import de.mxscha.endernationlobby.utils.scoreboards.tablist.TablistManager;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class LobbyScoreboard extends ScoreboardBuilder {

    private int id;

    public LobbyScoreboard(Player player) {
        super(player, "        §3§lEnder§b§lNation          ");
        this.id = 0;
        TablistManager.setTablist(player);
    }

    public void createScoreboard() {
        setScore("§8§m                               ", 7);
        setScore("§8● §7Dein Rang§8:", 6);

        // CloudNet Permission group
        IPermissionUser user = CloudNetDriver.getInstance().getPermissionManagement().getUser(player.getUniqueId());

        if (user == null) {
            return;
        }
        try {
            // We can use cloudnet
            IPermissionGroup group = CloudNetDriver.getInstance().getPermissionManagement().getHighestPermissionGroup(user);

            setScore("  §8» " + ChatColor.translateAlternateColorCodes('&', group.getPrefix()), 5);
        } catch (ClassCastException ex) {
            // We cannot use cloudnet
            if (player.hasPermission("rang.owner")) {
                setScore("  §8» §4§lOwner", 5);
            } else if (player.hasPermission("rang.manager")) {
                setScore("  §8» §c§lManager", 5);
            } else if (player.hasPermission("rang.admin")) {
                setScore("  §8» §c§lAdmin", 5);
            } else if (player.hasPermission("rang.developer")) {
                setScore("  §8» §b§lDeveloper", 5);
            } else if (player.hasPermission("rang.srmoderator")) {
                setScore("  §8» §c§lSr§5§lModerator", 5);
            } else if (player.hasPermission("rang.moderator")) {
                setScore("  §8» §5§lModerator", 5);
            } else if (player.hasPermission("rang.supporter")) {
                setScore("  §8» §e§lSupporter", 5);
            } else if (player.hasPermission("rang.builder")) {
                setScore("  §8» §1§lBuilder", 5);
            } else if (player.hasPermission("rang.youtuberplus")) {
                setScore("  §8» §5§lYou§f§lTuber§d§l+", 5);
            } else if (player.hasPermission("rang.youtuber")) {
                setScore("  §8» §5§lYou§f§lTuber", 5);
            } else if (player.hasPermission("rang.enderhacker")) {
                setScore("  §8» §3§lEnder§b§lHacker", 5);
            } else if (player.hasPermission("rang.enderhero")) {
                setScore("  §8» §3§lEnder§5§lHero", 5);
            } else if (player.hasPermission("rang.enderking")) {
                setScore("  §8» &3Ender§6§lKing", 5);
            } else if (player.hasPermission("rang.spieler") || player.hasPermission("rang.default")) {
                setScore("  §8» §7§lSpieler", 5);
            }
        }

        setScore("§a", 4);
        setScore("§8● §7Deine Coins§8:", 3);
        setScore("  §8» §c" + CoinCore.getInstance().getApi().getCoins(player.getUniqueId()), 2);
        setScore("§a§8§m                               ", 1);
    }

    public void update() {

    }
}
