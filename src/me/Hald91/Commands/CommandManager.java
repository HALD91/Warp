package me.Hald91.Commands;

import me.Hald91.Main;
import me.Hald91.Warps.WarpMenu;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

import static java.lang.String.valueOf;

public class CommandManager implements CommandExecutor {
    public static Player player;

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        Main main = JavaPlugin.getPlugin(Main.class);
        Player player = (Player) commandSender;
        if (command.getName().equalsIgnoreCase("Warp")) {
            if (commandSender.hasPermission("warp.use")) {


                if (args.length == 0) {
                    new WarpMenu().openInventory2((HumanEntity) commandSender);
                }
                if (args.length == 1) {
                    if (!args[0].isEmpty()) {
                        ((Player) commandSender).performCommand("tp"+ " " + commandSender.getName() + " " + String.valueOf(main.getConfig().getString(args[0] + "")).replace(",", ""));
                        commandSender.sendMessage(ChatColor.translateAlternateColorCodes('&', main.getConfig().getString("WarpCommandPrefix").toString() + " " + "You have been teleportet to" + " " + valueOf(main.getConfig().getString(args[0] + ""))));
                    }
                    if (args[0].equalsIgnoreCase("Help")) {
                        commandSender.sendMessage(ChatColor.translateAlternateColorCodes('&', main.getConfig().getString("WarpCommandPrefix").toString() + " " + ChatColor.GRAY + "-----------------" + ChatColor.GOLD + "Help" + ChatColor.GRAY + "---------------"));
                        commandSender.sendMessage(" ");
                        commandSender.sendMessage(ChatColor.translateAlternateColorCodes('&', main.getConfig().getString("WarpCommandPrefix").toString() + " " + ChatColor.GREEN + "/Warp help" + ChatColor.AQUA + " " + "To see how you use this command"));
                        commandSender.sendMessage(ChatColor.translateAlternateColorCodes('&', main.getConfig().getString("WarpCommandPrefix").toString() + " " + ChatColor.GREEN + "/Warp reload" + ChatColor.AQUA + " " + "To reload the config"));
                        commandSender.sendMessage(ChatColor.translateAlternateColorCodes('&', main.getConfig().getString("WarpCommandPrefix").toString() + " " + ChatColor.GREEN + "/warp SetPrefix WarpCommandPrefix/WarpMenuPrefix" + ChatColor.AQUA + " " + "To prefix on the menu or in chat commands"));
                        commandSender.sendMessage(" ");
                    }
                    if (commandSender.hasPermission("warp.admin.reload")) {
                        if (args[0].equalsIgnoreCase("Reload")) {
                            main.reloadConfig();
                            commandSender.sendMessage(ChatColor.translateAlternateColorCodes('&', main.getConfig().getString("WarpCommandPrefix").toString() + " " + ChatColor.GREEN + "The config has been reload."));

                        }
                    }
                    return true;

                }
                if (args.length == 2) {
                    if (commandSender.hasPermission("warp.admin.set")) {
                        if (args[0].equalsIgnoreCase("Set")) {
                            if (!args[1].isEmpty()) {
                                commandSender.getName();
                                String x = valueOf(player.getLocation().getX());
                                String y = valueOf(player.getLocation().getY());
                                String z = valueOf(player.getLocation().getZ());
                                String world = valueOf(player.getWorld().getName());
                                String commandPrefix = valueOf(valueOf(main.getConfig().getString("WarpCommandPrefix")));
                                String menuPrefix = valueOf(valueOf(main.getConfig().getString("WarpMenuPrefix")));
                                main.world.add(world);
                                main.coords.add(x);
                                main.coords.add(y);
                                main.coords.add(z);
                                main.getConfig();
                                main.getConfig().set("##", "Set your own prefix here on commands in chat or on the warp menu.");
                                main.getConfig().get(main.menuPrefix.toString());
                                main.getConfig().get(main.commandPrefix.toString());
                                main.getConfig().set("." + args[1] + "", main.world.toString().replace("[", "").replace("]", ""));
                                main.getConfig().set("." + args[1] + "", main.coords.toString().replace("[", "").replace("]", ""));
                                main.coords.clear();
                                main.world.clear();
                                main.saveConfig();
                                commandSender.sendMessage(ChatColor.translateAlternateColorCodes('&', main.getConfig().getString("WarpCommandPrefix").toString() + " " + ChatColor.YELLOW + "spawn set at: " + ChatColor.RED + valueOf(main.getConfig().getString(args[1])).replace(",", "") + ChatColor.YELLOW + " at world: " + ChatColor.RED + valueOf(main.getConfig().getString(args[1])).replace(",", "")));
                            }
                        }
                    }return true;
                }
                if (args.length == 4) {
                    if (args[0].equalsIgnoreCase("SetPrefix")) {
                        if (commandSender.hasPermission("warp.admin.setprefix")) {
                            if (args[1].equalsIgnoreCase("WarpCommandPrefix")) {
                                if (!args[2].isEmpty()){
                                    main.getConfig().set(valueOf(main.getConfig().getString("WarpCommandPrefix")), args[3]);
                                    main.saveConfig();
                                    commandSender.sendMessage(ChatColor.GREEN + "Your new prefix" + ChatColor.RESET + " " + ChatColor.translateAlternateColorCodes('&', main.getConfig().getString("WarpCommandPrefix").toString()));
                                }else {
                                    commandSender.sendMessage(ChatColor.translateAlternateColorCodes('&', main.getConfig().getString("WarpCommandPrefix").toString() + " " + ChatColor.GREEN + "/warp SetPrefix WarpCommandPrefix/WarpMenuPrefix"));

                                }
                            } else {
                                if (args[1].equalsIgnoreCase("WarpMenuPrefix")) {
                                    if (!args[2].isEmpty()) {
                                        main.getConfig().set(valueOf(main.getConfig().getString("WarpMenuPrefix")), args[3]);
                                        main.saveConfig();
                                        commandSender.sendMessage(ChatColor.GREEN + "Your new prefix" + ChatColor.RESET + " " + ChatColor.translateAlternateColorCodes('&', main.getConfig().getString("WarpMenuPrefix").toString()));
                                    } else {
                                        commandSender.sendMessage(ChatColor.translateAlternateColorCodes('&', main.getConfig().getString("WarpCommandPrefix").toString() + " " + ChatColor.GREEN + "/warp SetPrefix WarpCommandPrefix/WarpMenuPrefix"));
                                    }
                                } else {
                                    if (!args[1].isEmpty()){
                                        commandSender.sendMessage(ChatColor.translateAlternateColorCodes('&', main.getConfig().getString("WarpCommandPrefix").toString() + " " + ChatColor.GREEN + "/warp SetPrefix WarpCommandPrefix/WarpMenuPrefix"));
                                    }
                                }
                            }
                        }
                    } else {
                        commandSender.sendMessage(ChatColor.translateAlternateColorCodes('&', main.getConfig().getString("WarpCommandPrefix").toString() + " " + ChatColor.GREEN + "/warp SetPrefix WarpCommandPrefix/WarpMenuPrefix"));
                    }
                }
            }else {
            commandSender.sendMessage(ChatColor.translateAlternateColorCodes('&', main.getConfig().getString("WarpCommandPrefix").toString() + " " + ChatColor.RED + "It seems like you don't have permission to use this command."));
        } return true;

    }return true;
    }
}



