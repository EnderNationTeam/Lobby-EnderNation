package de.mxscha.endernationlobby.listener;

import de.mxscha.endernationlobby.LobbyCore;
import org.bukkit.GameMode;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.HashMap;

public class DoubleJumpListener implements Listener {

    private static HashMap<Player, Double> onlyY = new HashMap<>();;

    @EventHandler
    public void cleanup(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        onlyY.remove(player);
    }

    @EventHandler
    public void onJump(PlayerToggleFlightEvent event) {
        Player player = event.getPlayer();

        // if the player not creative
        if(player.getGameMode().equals(GameMode.CREATIVE) || player.getGameMode().equals(GameMode.SPECTATOR)) return;
        // if player not flying
        if(FlyListener.isFly(player)) return;

        event.setCancelled(true);

        player.setVelocity(player.getLocation().getDirection().multiply(1).setY(1));
        player.playSound(player.getLocation(), Sound.ENTITY_CAT_HISS, 40, 10);

        addCooldownAndEffect(player);

    }

    private void addCooldownAndEffect(Player player) {
        // add effect
        onlyY.put(player, player.getLocation().getY());
        BukkitTask bukkitTask = new BukkitRunnable() {
            @Override
            public void run() {
                double y = onlyY.get(player);
                if(y < player.getLocation().getY()) {
                    onlyY.put(player, player.getLocation().getY());
                    player.getWorld().spawnParticle(Particle.CLOUD, player.getLocation().clone().add(0, 0.5, 0), 0, 0 ,0,0);
                }
            }
        }.runTaskTimer(LobbyCore.getInstance(), 1, 1);

        // fly allow and stop bukkit Task
        player.setAllowFlight(false);
        new BukkitRunnable() {
            @Override
            public void run() {
                bukkitTask.cancel();
                player.setAllowFlight(true);
            }
        }.runTaskLater(LobbyCore.getInstance(), 28);
    }
}
