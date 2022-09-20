package de.mxscha.endernationlobby.listener;

import de.dytanic.cloudnet.driver.CloudNetDriver;
import de.dytanic.cloudnet.driver.permission.IPermissionGroup;
import de.dytanic.cloudnet.driver.permission.IPermissionUser;
import de.mxscha.endernationlobby.utils.manager.CloudNetManager;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class PlayerChatEvent implements Listener {

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        String msg;
        if (player.hasPermission("chat.color")) {
            msg = event.getMessage().replace('&', '§');
        } else {
            msg = event.getMessage();
        }

        // CloudNet Permission group
        IPermissionUser user = CloudNetDriver.getInstance().getPermissionManagement().getUser(player.getUniqueId());

        if (user == null) {
            return;
        }

        if(CloudNetManager.existCloudNet()) {
            // We can use Cloudnet
            IPermissionGroup group = CloudNetDriver.getInstance().getPermissionManagement().getHighestPermissionGroup(user);

            event.setFormat(ChatColor.translateAlternateColorCodes('&', group.getPrefix()) + "§8» §7" + player.getName() + "§8: §7" + msg);
        } else {
            // We cannot use cloudnet
            if (player.hasPermission("rang.owner")) {
                event.setFormat("§4§lOwner §8» §7" + player.getName() + "§8: §f" + msg);
            } else
            if (player.hasPermission("rang.manager")) {
                event.setFormat("§c§lManager §8» §7 §7" + player.getName() + "§8: §f" + msg);
            }  else
            if (player.hasPermission("rang.admin")) {
                event.setFormat("§c§lAdmin §8» §7" + player.getName() + "§8: §f" + msg);
            } else
            if (player.hasPermission("rang.developer")) {
                event.setFormat("§b§lDeveloper §8» §7" + player.getName() + "§8: §f" + msg);
            } else
            if (player.hasPermission("rang.srmoderator")) {
                event.setFormat("§c§lSr§5§lModerator §8» §7" + player.getName() + "§8: §f" + msg);
            } else
            if (player.hasPermission("rang.moderator")) {
                event.setFormat("§5§lModerator §8» §7" + player.getName() + "§8: §f" + msg);
            } else
            if (player.hasPermission("rang.supporter")) {
                event.setFormat("§e§lSupporter §8» §7" + player.getName() + "§8: §f" + msg);
            } else
            if (player.hasPermission("rang.builder")) {
                event.setFormat("§1§lBuilder §8» §7" + player.getName() + "§8: §f" + msg);
            } else
            if (player.hasPermission("rang.youtuberplus")) {
                event.setFormat("§c§lYou§f§lTuber§d§l+ §8» §7" + player.getName() + "§8: §f" + msg);
            } else
            if (player.hasPermission("rang.youtuber")) {
                event.setFormat("§c§lYou§f§lTuber §8» §7" + player.getName() + "§8: §f" + msg);
            } else
            if (player.hasPermission("rang.enderhacker")) {
                event.setFormat("§3§lEnder§b§lHacker §8» §7" + player.getName() + "§8: §f" + msg);
            } else
            if (player.hasPermission("rang.enderhero")) {
                event.setFormat("§3§lEnder§5§lHero §8» §7" + player.getName() + "§8: §f" + msg);
            } else
            if (player.hasPermission("rang.enderking")) {
                event.setFormat("&3Ender§6§lKing §8» §7" + player.getName() + "§8: §f" + msg);
            } else
            if (player.hasPermission("rang.default")) {
                event.setFormat("§7§lSpieler §8» §7" + player.getName() + "§8: §7" + msg);
            }
        }
    }
}
