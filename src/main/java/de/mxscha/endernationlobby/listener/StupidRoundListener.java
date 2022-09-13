package de.mxscha.endernationlobby.listener;

import de.mxscha.lobby.utils.manager.MessageManager;
import de.mxscha.lobby.utils.manager.RegionManager;
import de.mxscha.lobby.utils.manager.locations.ConfigLocationUtil;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.ArrayList;
import java.util.HashMap;

public class StupidRoundListener implements Listener {

    // SRRegion1A
    private static final HashMap<Player, Integer> SR = new HashMap<>();
    private static final ArrayList<Player> loc1 = new ArrayList<>();
    private static final ArrayList<Player> loc2 = new ArrayList<>();
    private static final ArrayList<Player> loc3 = new ArrayList<>();
    private static final ArrayList<Player> loc4 = new ArrayList<>();
    private static final ArrayList<Player> loc5 = new ArrayList<>();
    private static final ArrayList<Player> loc6 = new ArrayList<>();
    private static final ArrayList<Player> loc7 = new ArrayList<>();
    private static final ArrayList<Player> loc8 = new ArrayList<>();

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        try {
            Location A1 = new ConfigLocationUtil("SRRegion1A").loadLocation();
            Location B1 = new ConfigLocationUtil("SRRegion1B").loadLocation();
            Location A2 = new ConfigLocationUtil("SRRegion2A").loadLocation();
            Location B2 = new ConfigLocationUtil("SRRegion2B").loadLocation();
            Location A3 = new ConfigLocationUtil("SRRegion3A").loadLocation();
            Location B3 = new ConfigLocationUtil("SRRegion3B").loadLocation();
            Location A4 = new ConfigLocationUtil("SRRegion4A").loadLocation();
            Location B4 = new ConfigLocationUtil("SRRegion4B").loadLocation();
            Location A5 = new ConfigLocationUtil("SRRegion5A").loadLocation();
            Location B5 = new ConfigLocationUtil("SRRegion5B").loadLocation();
            Location A6 = new ConfigLocationUtil("SRRegion6A").loadLocation();
            Location B6 = new ConfigLocationUtil("SRRegion6B").loadLocation();
            Location A7 = new ConfigLocationUtil("SRRegion7A").loadLocation();
            Location B7 = new ConfigLocationUtil("SRRegion7B").loadLocation();
            Location A8 = new ConfigLocationUtil("SRRegion8A").loadLocation();
            Location B8 = new ConfigLocationUtil("SRRegion8B").loadLocation();

            if (!SR.containsKey(player)) {
                if (RegionManager.isInRegion(player.getLocation(), A1, B1)) {
                    SR.put(player, 1);
                    loc1.add(player);
                    player.sendMessage("1");
                }
                if (RegionManager.isInRegion(player.getLocation(), A2, B2)) {
                    SR.put(player, 2);
                    loc2.add(player);
                    player.sendMessage("2");
                }
                if (RegionManager.isInRegion(player.getLocation(), A3, B3)) {
                    SR.put(player, 3);
                    loc3.add(player);
                    player.sendMessage("3");
                }
                if (RegionManager.isInRegion(player.getLocation(), A4, B4)) {
                    SR.put(player, 4);
                    loc4.add(player);
                    player.sendMessage("4");
                }
                if (RegionManager.isInRegion(player.getLocation(), A5, B5)) {
                    SR.put(player, 5);
                    loc5.add(player);
                    player.sendMessage("5");
                }
                if (RegionManager.isInRegion(player.getLocation(), A6, B6)) {
                    SR.put(player, 6);
                    loc6.add(player);
                    player.sendMessage("6");
                }
                if (RegionManager.isInRegion(player.getLocation(), A7, B7)) {
                    SR.put(player, 7);
                    loc7.add(player);
                    player.sendMessage("7");
                }
                if (RegionManager.isInRegion(player.getLocation(), A8, B8)) {
                    SR.put(player, 8);
                    loc8.add(player);
                    player.sendMessage("8");
                }
            } else {
                if (RegionManager.isInRegion(player.getLocation(), A1, B1)) {
                    if(SR.containsKey(player) &&  SR.containsValue(1)) {
                        if (loc1.contains(player) && loc2.contains(player) && loc3.contains(player) && loc4.contains(player)
                        && loc5.contains(player) && loc6.contains(player) && loc7.contains(player) && loc8.contains(player)) {
                            done(player);
                        } else {
                            if (loc8.contains(player)) {
                                loc1.add(player);
                            }
                        }
                    }
                }
                if (RegionManager.isInRegion(player.getLocation(), A2, B2)) {
                    if(SR.containsKey(player) &&  SR.containsValue(2)) {
                        if (loc1.contains(player) && loc2.contains(player) && loc3.contains(player) && loc4.contains(player)
                                && loc5.contains(player) && loc6.contains(player) && loc7.contains(player) && loc8.contains(player)) {
                            done(player);
                        } else {
                            if (loc1.contains(player)) {
                                loc2.add(player);
                            }
                        }
                    }
                }
                if (RegionManager.isInRegion(player.getLocation(), A3, B3)) {
                    if(SR.containsKey(player) &&  SR.containsValue(3)) {
                        if (loc1.contains(player) && loc2.contains(player) && loc3.contains(player) && loc4.contains(player)
                                && loc5.contains(player) && loc6.contains(player) && loc7.contains(player) && loc8.contains(player)) {
                            done(player);
                        } else {
                            if (loc2.contains(player)) {
                                loc3.add(player);
                            }
                        }
                    }
                }
                if (RegionManager.isInRegion(player.getLocation(), A4, B4)) {
                    if(SR.containsKey(player) &&  SR.containsValue(4)) {
                        if (loc1.contains(player) && loc2.contains(player) && loc3.contains(player) && loc4.contains(player)
                                && loc5.contains(player) && loc6.contains(player) && loc7.contains(player) && loc8.contains(player)) {
                            done(player);
                        } else {
                            if (loc3.contains(player)) {
                                loc4.add(player);
                            }
                        }
                    }
                }
                if (RegionManager.isInRegion(player.getLocation(), A5, B5)) {
                    if(SR.containsKey(player) &&  SR.containsValue(5)) {
                        if (loc1.contains(player) && loc2.contains(player) && loc3.contains(player) && loc4.contains(player)
                                && loc5.contains(player) && loc6.contains(player) && loc7.contains(player) && loc8.contains(player)) {
                            done(player);
                        } else {
                            if (loc4.contains(player)) {
                                loc5.add(player);
                            }
                        }
                    }
                }
                if (RegionManager.isInRegion(player.getLocation(), A6, B6)) {
                    if(SR.containsKey(player) &&  SR.containsValue(6)) {
                        if (loc1.contains(player) && loc2.contains(player) && loc3.contains(player) && loc4.contains(player)
                                && loc5.contains(player) && loc6.contains(player) && loc7.contains(player) && loc8.contains(player)) {
                            done(player);
                        } else {
                            if (loc5.contains(player)) {
                                loc6.add(player);
                            }
                        }
                    }
                }
                if (RegionManager.isInRegion(player.getLocation(), A7, B7)) {
                    if(SR.containsKey(player) &&  SR.containsValue(7)) {
                        if (loc1.contains(player) && loc2.contains(player) && loc3.contains(player) && loc4.contains(player)
                                && loc5.contains(player) && loc6.contains(player) && loc7.contains(player) && loc8.contains(player)) {
                            done(player);
                        } else {
                            if (loc6.contains(player)) {
                                loc7.add(player);
                            }
                        }
                    }
                }
                if (RegionManager.isInRegion(player.getLocation(), A8, B8)) {
                    if(SR.containsKey(player) &&  SR.containsValue(8)) {
                        if (loc1.contains(player) && loc2.contains(player) && loc3.contains(player) && loc4.contains(player)
                                && loc5.contains(player) && loc6.contains(player) && loc7.contains(player) && loc8.contains(player)) {
                            done(player);
                        } else {
                            if (loc7.contains(player)) {
                                loc8.add(player);
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            player.sendMessage("test");
        }
    }

    private void remove(Player player) {
        if (loc1.contains(player)) {
            loc1.remove(player);
        } else if (loc2.contains(player)) {
            loc2.remove(player);
        } else if (loc3.contains(player)) {
            loc3.remove(player);
        } else if (loc4.contains(player)) {
            loc4.remove(player);
        } else if (loc5.contains(player)) {
            loc5.remove(player);
        } else if (loc6.contains(player)) {
            loc6.remove(player);
        } else if (loc7.contains(player)) {
            loc7.remove(player);
        } else if (loc8.contains(player)) {
            loc8.remove(player);
        }
    }

    private void done(Player player) {
        remove(player);
        player.sendMessage("du hs du hast ne sinnlose runde abgeschlossen!");
    }
}
