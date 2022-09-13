package de.mxscha.endernationlobby.commands;

import de.mxscha.endernationlobby.utils.manager.BuildManager;
import de.mxscha.endernationlobby.utils.manager.MessageManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BuildCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player player) {
            if (player.hasPermission("lobby.build")) {
                if (args.length == 0) {
                    if (BuildManager.isAllowed(player)) {
                        BuildManager.disallow(player);
                    } else
                        BuildManager.allow(player);
                } else {
                    player.sendMessage(MessageManager.Prefix + "§cMeintest du§8: §e/build§c?");
                }
            } else
                player.sendMessage(MessageManager.Prefix + "§cDazu hast du keine Rechte!");
        }
        return false;
    }
}
