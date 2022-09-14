package de.mxscha.endernationlobby.listener;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import de.mxscha.endernationlobby.LobbyCore;
import de.mxscha.endernationlobby.utils.manager.BuildManager;
import de.mxscha.endernationlobby.utils.manager.MessageManager;
import de.mxscha.endernationlobby.utils.manager.RegionManager;
import de.mxscha.endernationlobby.utils.manager.locations.ConfigLocationUtil;
import org.bukkit.Location;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;

public class PlayerTeleportToGamesListener implements Listener {

    private ArrayList<Player> teleport = new ArrayList<>();

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        try {
            Location bedwarsA = new ConfigLocationUtil("BedWarsRegionA").loadLocation();
            Location bedwarsB = new ConfigLocationUtil("BedWarsRegionB").loadLocation();
            Location TTTA = new ConfigLocationUtil("TTTRegionA").loadLocation();
            Location TTTB = new ConfigLocationUtil("TTTRegionB").loadLocation();
            Location EnfOfLifeA = new ConfigLocationUtil("EndOfLifeRegionA").loadLocation();
            Location EndOfLifeB = new ConfigLocationUtil("EndOfLifeRegionB").loadLocation();
            ByteArrayDataOutput out;
            if (RegionManager.isInRegion(player.getLocation(), bedwarsA, bedwarsB)) {
                if (!BuildManager.isAllowed(player)) {
                    if (!teleport.contains(player)) {
                        teleport.add(player);
                        out = ByteStreams.newDataOutput();
                        out.writeUTF("Connect");
                        out.writeUTF("BW-1");
                        player.sendPluginMessage(LobbyCore.getInstance(), "BungeeCord", out.toByteArray());
                        player.sendMessage(MessageManager.Prefix + "§7Du wurdest zu §b§fBedWars §7gesendet!");
                        remove(player);
                    }
                }
            }

            if (RegionManager.isInRegion(player.getLocation(), TTTA, TTTB)) {
                if (!BuildManager.isAllowed(player)) {
                    player.sendMessage("du bist in TTT");
                }
            }

            if (RegionManager.isInRegion(player.getLocation(), EnfOfLifeA, EndOfLifeB)) {
                if (!BuildManager.isAllowed(player)) {
                    if (!teleport.contains(player)) {
                        teleport.add(player);
                        out = ByteStreams.newDataOutput();
                        out.writeUTF("Connect");
                        out.writeUTF("EndOfLife-1");
                        player.sendPluginMessage(LobbyCore.getInstance(), "BungeeCord", out.toByteArray());
                        player.sendMessage(MessageManager.Prefix + "§7Du wurdest zu §9§lEnd§f§lOf§a§lLife §7gesendet!");
                        remove(player);
                    }
                }
            }
        } catch (Exception e) {

        }
    }

    private void remove(Player player) {
        if (teleport.contains(player)) {
            new BukkitRunnable() {
                @Override
                public void run() {
                    teleport.remove(player);
                }
            }.runTaskLater(LobbyCore.getInstance(), 20);
        }
    }
}