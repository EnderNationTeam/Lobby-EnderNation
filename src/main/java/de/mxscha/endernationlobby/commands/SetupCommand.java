package de.mxscha.endernationlobby.commands;

import de.mxscha.endernationlobby.utils.manager.MessageManager;
import de.mxscha.endernationlobby.utils.manager.locations.ConfigLocationUtil;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetupCommand implements CommandExecutor {

    // set Spawn <Name> <Slot> <Item-Type> <ItemName> <ItemLore>
    // setup set StupidRounds place <1-8> <a-b>

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player player) {
            if (player.hasPermission("lobby.setup")) {
                switch (args.length) {
                    case 0:
                        player.sendMessage("§7§m                                                                              ");
                        player.sendMessage(" ");
                        player.sendMessage(" §8| §a/setup set Lobby §8● §7Setze den Lobby Spawn");
                        player.sendMessage(" §8| §a/setup set Spawn <Game> §8● §7Setze den Spawn für die Spielmodie!");
                        player.sendMessage(" §8| §a/setup set Region <Game> <a, b> §8● §7Setze die Region für die Spielmodie!");
                        player.sendMessage(" ");
                        player.sendMessage("§7§m                                                                               ");
                        break;
                    case 1:
                        break;
                    case 2:
                        if (args[0].equalsIgnoreCase("set")) {
                            if (args[1].equalsIgnoreCase("Lobby")) {
                                new ConfigLocationUtil(player.getLocation(), "Lobby").saveLocation();
                                player.sendMessage(MessageManager.Prefix + "§7Du hast den §aLobby Spawn §7gesetzt!");
                                player.playSound(player, Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
                            } else if (args[1].equalsIgnoreCase("Death")) {
                                new ConfigLocationUtil(player.getLocation(), "Death").saveLocation();
                                player.sendMessage(MessageManager.Prefix + "§7Du hast die §aTodeshöhe §7gesetzt!");
                                player.playSound(player, Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
                            }
                        }
                        break;
                    case 3:
                        if (args[0].equalsIgnoreCase("set")) {
                            if (args[1].equalsIgnoreCase("Spawn")) {
                                if (args[2].equalsIgnoreCase("bedwars")) {
                                    new ConfigLocationUtil(player.getLocation(), "BedWars").saveLocation();
                                    player.sendMessage(MessageManager.Prefix + "§7Du hast den §b§lBedWars Spawn §7gesetzt!");
                                    player.playSound(player, Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
                                } else if (args[2].equalsIgnoreCase("ttt")) {
                                    new ConfigLocationUtil(player.getLocation(), "TTT").saveLocation();
                                    player.sendMessage(MessageManager.Prefix + "§7Du hast den §c§lTTT Spawn §7gesetzt!");
                                    player.playSound(player, Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
                                } else if (args[2].equalsIgnoreCase("EndOfLife")) {
                                    new ConfigLocationUtil(player.getLocation(), "EndOfLife").saveLocation();
                                    player.sendMessage(MessageManager.Prefix + "§7Du hast den §9§lEndOfLife Spawn §7gesetzt!");
                                    player.playSound(player, Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
                                }
                            }
                        }
                        break;
                    case 4:
                        if (args[0].equalsIgnoreCase("set")) {
                            if (args[1].equalsIgnoreCase("Region")) {
                                if (args[2].equalsIgnoreCase("BedWars")) {
                                    if (args[3].equalsIgnoreCase("a")) {
                                        new ConfigLocationUtil(player.getLocation(), "BedWarsRegionA").saveLocation();
                                        player.sendMessage(MessageManager.Prefix + "§7Du hast Position §aA §7für die Region §b§lBedWars §7gesetzt!");
                                        player.playSound(player, Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
                                    } else if (args[3].equalsIgnoreCase("b")) {
                                        new ConfigLocationUtil(player.getLocation(), "BedWarsRegionB").saveLocation();
                                        player.sendMessage(MessageManager.Prefix + "§7Du hast Position §cB §7für die Region §b§lBedWars §7gesetzt!");
                                        player.playSound(player, Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
                                    }
                                } else if (args[2].equalsIgnoreCase("TTT")) {
                                    if (args[3].equalsIgnoreCase("a")) {
                                        new ConfigLocationUtil(player.getLocation(), "TTTRegionA").saveLocation();
                                        player.sendMessage(MessageManager.Prefix + "§7Du hast Position §aA §7für die Region §c§lTTT §7gesetzt!");
                                        player.playSound(player, Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
                                    } else if (args[3].equalsIgnoreCase("b")) {
                                        new ConfigLocationUtil(player.getLocation(), "TTTRegionB").saveLocation();
                                        player.sendMessage(MessageManager.Prefix + "§7Du hast Position §cB §7für die Region §c§lTTT §7gesetzt!");
                                        player.playSound(player, Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
                                    }
                                } else if (args[2].equalsIgnoreCase("EndOfLife")) {
                                    if (args[3].equalsIgnoreCase("a")) {
                                        new ConfigLocationUtil(player.getLocation(), "EndOfLifeRegionA").saveLocation();
                                        player.sendMessage(MessageManager.Prefix + "§7Du hast Position §aA §7für die Region §9§lEndOfLife §7gesetzt!");
                                        player.playSound(player, Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
                                    } else if (args[3].equalsIgnoreCase("b")) {
                                        new ConfigLocationUtil(player.getLocation(), "EndOfLifeRegionB").saveLocation();
                                        player.sendMessage(MessageManager.Prefix + "§7Du hast Position §cB §7für die Region §9§lEndOfLife §7gesetzt!");
                                        player.playSound(player, Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
                                    }
                                }
                            }
                        }
                        break;
                    case 5:
                        if (args[0].equalsIgnoreCase("set")) {
                            if (args[1].equalsIgnoreCase("StupidRounds")) {
                                if (args[2].equalsIgnoreCase("place")) {
                                    if (args[3].equalsIgnoreCase("1")) {
                                        if (args[4].equalsIgnoreCase("a")) {
                                            new ConfigLocationUtil(player.getLocation(), "SRRegion1A").saveLocation();
                                            player.sendMessage(MessageManager.Prefix + "§7Du hast Position §cA §7für den Platz §a1 §7der Sinnlosen Runden gesetzt!");
                                            player.playSound(player, Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
                                        } else if (args[4].equalsIgnoreCase("b")) {
                                            new ConfigLocationUtil(player.getLocation(), "SRRegion1B").saveLocation();
                                            player.sendMessage(MessageManager.Prefix + "§7Du hast Position §cB §7für den Platz §a1 §7der Sinnlosen Runden gesetzt!");
                                            player.playSound(player, Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
                                        }
                                    } else if (args[3].equalsIgnoreCase("2")) {
                                        if (args[4].equalsIgnoreCase("a")) {
                                            new ConfigLocationUtil(player.getLocation(), "SRRegion2A").saveLocation();
                                            player.sendMessage(MessageManager.Prefix + "§7Du hast Position §cA §7für den Platz §a2 §7der Sinnlosen Runden gesetzt!");
                                            player.playSound(player, Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
                                        } else if (args[4].equalsIgnoreCase("b")) {
                                            new ConfigLocationUtil(player.getLocation(), "SRRegion2B").saveLocation();
                                            player.sendMessage(MessageManager.Prefix + "§7Du hast Position §cB §7für den Platz §a2 §7der Sinnlosen Runden gesetzt!");
                                            player.playSound(player, Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
                                        }
                                    } else if (args[3].equalsIgnoreCase("3")) {
                                        if (args[4].equalsIgnoreCase("a")) {
                                            new ConfigLocationUtil(player.getLocation(), "SRRegion3A").saveLocation();
                                            player.sendMessage(MessageManager.Prefix + "§7Du hast Position §cA §7für den Platz §a3 §7der Sinnlosen Runden gesetzt!");
                                            player.playSound(player, Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
                                        } else if (args[4].equalsIgnoreCase("b")) {
                                            new ConfigLocationUtil(player.getLocation(), "SRRegion3B").saveLocation();
                                            player.sendMessage(MessageManager.Prefix + "§7Du hast Position §cB §7für den Platz §a3 §7der Sinnlosen Runden gesetzt!");
                                            player.playSound(player, Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
                                        }
                                    } else if (args[3].equalsIgnoreCase("4")) {
                                        if (args[4].equalsIgnoreCase("a")) {
                                            new ConfigLocationUtil(player.getLocation(), "SRRegion4A").saveLocation();
                                            player.sendMessage(MessageManager.Prefix + "§7Du hast Position §cA §7für den Platz §a4 §7der Sinnlosen Runden gesetzt!");
                                            player.playSound(player, Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
                                        } else if (args[4].equalsIgnoreCase("b")) {
                                            new ConfigLocationUtil(player.getLocation(), "SRRegion4B").saveLocation();
                                            player.sendMessage(MessageManager.Prefix + "§7Du hast Position §cB §7für den Platz §a4 §7der Sinnlosen Runden gesetzt!");
                                            player.playSound(player, Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
                                        }
                                    } else if (args[3].equalsIgnoreCase("5")) {
                                        if (args[4].equalsIgnoreCase("a")) {
                                            new ConfigLocationUtil(player.getLocation(), "SRRegion5A").saveLocation();
                                            player.sendMessage(MessageManager.Prefix + "§7Du hast Position §cA §7für den Platz §a5 §7der Sinnlosen Runden gesetzt!");
                                            player.playSound(player, Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
                                        } else if (args[4].equalsIgnoreCase("b")) {
                                            new ConfigLocationUtil(player.getLocation(), "SRRegion5B").saveLocation();
                                            player.sendMessage(MessageManager.Prefix + "§7Du hast Position §cB §7für den Platz §a5 §7der Sinnlosen Runden gesetzt!");
                                            player.playSound(player, Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
                                        }
                                    } else if (args[3].equalsIgnoreCase("6")) {
                                        if (args[4].equalsIgnoreCase("a")) {
                                            new ConfigLocationUtil(player.getLocation(), "SRRegion6A").saveLocation();
                                            player.sendMessage(MessageManager.Prefix + "§7Du hast Position §cA §7für den Platz §a6 §7der Sinnlosen Runden gesetzt!");
                                            player.playSound(player, Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
                                        } else if (args[4].equalsIgnoreCase("b")) {
                                            new ConfigLocationUtil(player.getLocation(), "SRRegion6B").saveLocation();
                                            player.sendMessage(MessageManager.Prefix + "§7Du hast Position §cB §7für den Platz §a6 §7der Sinnlosen Runden gesetzt!");
                                            player.playSound(player, Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
                                        }
                                    } else if (args[3].equalsIgnoreCase("7")) {
                                        if (args[4].equalsIgnoreCase("a")) {
                                            new ConfigLocationUtil(player.getLocation(), "SRRegion7A").saveLocation();
                                            player.sendMessage(MessageManager.Prefix + "§7Du hast Position §cA §7für den Platz §a7 §7der Sinnlosen Runden gesetzt!");
                                            player.playSound(player, Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
                                        } else if (args[4].equalsIgnoreCase("b")) {
                                            new ConfigLocationUtil(player.getLocation(), "SRRegion7B").saveLocation();
                                            player.sendMessage(MessageManager.Prefix + "§7Du hast Position §cB §7für den Platz §a7 §7der Sinnlosen Runden gesetzt!");
                                            player.playSound(player, Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
                                        }
                                    } else if (args[3].equalsIgnoreCase("8")) {
                                        if (args[4].equalsIgnoreCase("a")) {
                                            new ConfigLocationUtil(player.getLocation(), "SRRegion8A").saveLocation();
                                            player.sendMessage(MessageManager.Prefix + "§7Du hast Position §cA §7für den Platz §a8 §7der Sinnlosen Runden gesetzt!");
                                            player.playSound(player, Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
                                        } else if (args[4].equalsIgnoreCase("b")) {
                                            new ConfigLocationUtil(player.getLocation(), "SRRegion8B").saveLocation();
                                            player.sendMessage(MessageManager.Prefix + "§7Du hast Position §cB §7für den Platz §a8 §7der Sinnlosen Runden gesetzt!");
                                            player.playSound(player, Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
                                        }
                                    }
                                }
                            }
                        }
                        break;
                }
            } else
                player.sendMessage(MessageManager.Prefix + "§cDazu hast du keine Rechte!");
        }
        return false;
    }
}
