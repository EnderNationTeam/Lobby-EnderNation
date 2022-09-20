package de.mxscha.endernationlobby.listener;

import de.mxscha.endernationlobby.LobbyCore;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.HashMap;

public class DoubleJumpListener implements Listener {

    private static ArrayList<Player> backlist = new ArrayList<>();

    @EventHandler
    public void onJump(PlayerToggleFlightEvent event) {
        Player player = event.getPlayer();

        // if the player not creative
        if(player.getGameMode().equals(GameMode.CREATIVE) || player.getGameMode().equals(GameMode.SPECTATOR))
            return;

        event.setCancelled(true);

        // if the player not blacklisted
        if(backlist.contains(player))
            return;

        player.setVelocity(player.getLocation().getDirection().multiply(1).setY(1));

        player.playSound(player.getLocation(), Sound.ENTITY_CAT_HISS, 40, 10);

        backlist.add(player);

        new BukkitRunnable() {
            @Override
            public void run() {
                backlist.remove(player);
            }
        }.runTaskLater(LobbyCore.getInstance(), 30);
    }
}
