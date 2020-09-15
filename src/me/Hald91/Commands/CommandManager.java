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

import java.util.ArrayList;
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
                    commandSender.sendMessage(ChatColor.translateAlternateColorCodes('&', main.getConfig().getString("Prefix").toString() + " " + ChatColor.GRAY + "/Warp help" + " " + ChatColor.AQUA + "To see how you use this command"));
                }
                if (args.length == 1) {
                    if (args[0].equalsIgnoreCase("List")) {
                        commandSender.sendMessage("Getting list.");
                        if (main.getConfig().getStringList("warps").isEmpty()) {
                            commandSender.sendMessage(ChatColor.translateAlternateColorCodes('&', main.getConfig().getString("Prefix").toString() + " " + ChatColor.GRAY + "No warps has been sat"));
                        }
                        for (String messages: main.getConfig().getStringList("warps")){
                            commandSender.sendMessage(messages);
                        }
                        return true;
                    }
                    if (args[0].equalsIgnoreCase("Help")) {
                        commandSender.sendMessage(ChatColor.translateAlternateColorCodes('&', main.getConfig().getString("Prefix").toString() + " " + ChatColor.GRAY + "-----------------" + ChatColor.GOLD + "Help" + ChatColor.GRAY + "---------------"));
                        commandSender.sendMessage(" ");
                        commandSender.sendMessage(ChatColor.translateAlternateColorCodes('&', main.getConfig().getString("Prefix").toString() + " " + ChatColor.GREEN + "/Warp help" + ChatColor.AQUA + " " + "To see how you use this command"));
                        commandSender.sendMessage(ChatColor.translateAlternateColorCodes('&', main.getConfig().getString("Prefix").toString() + " " + ChatColor.GREEN + "/Warp <Warp>" + ChatColor.AQUA + " " + "To see how you use this command"));
                        commandSender.sendMessage(ChatColor.translateAlternateColorCodes('&', main.getConfig().getString("Prefix").toString() + " " + ChatColor.GREEN + "/Warp reload" + ChatColor.AQUA + " " + "To reload the config"));
                        commandSender.sendMessage(ChatColor.translateAlternateColorCodes('&', main.getConfig().getString("Prefix").toString() + " " + ChatColor.GREEN + "/warp SetPrefix <Prefix>" + ChatColor.AQUA + " " + "To set your own prefix"));
                        commandSender.sendMessage(" ");
                        commandSender.sendMessage("-----------------------------------------------------");
                        return true;
                    }
                    if (args[0].equalsIgnoreCase("Reload")) {
                        if (commandSender.hasPermission("warp.admin.reload")) {
                            main.reloadConfig();
                            commandSender.sendMessage(ChatColor.translateAlternateColorCodes('&', main.getConfig().getString("Prefix").toString() + ChatColor.RED + " " + "Config" + " " + ChatColor.GRAY + "has been reload"));
                            return true;
                        }
                    }
                    if (!args[0].isEmpty()) {
                        ArrayList<String> list2 = (ArrayList<String>) main.getConfig().getStringList("warps");
                        //commandSender.sendMessage(list2.toString());
                        for (String messages1: list2){
                            if (!messages1.contains(args[0])){
                                list2.remove(messages1);
                            }else {
                                commandSender.sendMessage(messages1);
                                ((Player) commandSender).performCommand("tp" + " " + commandSender.getName() + " " + String.valueOf(messages1).replace(",", "").replace("Name: " + args[0], "").replace(" x: ", " ").replace(" y: ", " ").replace(" z: ", " ").replace("[", "").replace("]", ""));
                                commandSender.sendMessage(ChatColor.translateAlternateColorCodes('&', main.getConfig().getString("Prefix").toString() + ChatColor.GREEN + " " + "You have been teleportet to" + " " + ChatColor.GRAY + args[0]));
                                return true;
                            }
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
                                String commandPrefix = valueOf(valueOf(main.getConfig().getString("Prefix")));
                                String menuPrefix = valueOf(valueOf(main.getConfig().getString("Prefix")));
                                ArrayList<String> list = (ArrayList<String>) main.getConfig().getStringList("warps");
                                main.world.add(world);
                                main.coords.add(x);
                                main.coords.add(y);
                                main.coords.add(z);
                                main.getConfig();

                                main.getConfig().get(main.prefix.toString());
                                list.add("Name: " + args[1] + " x: " + x + " y: " + y + " z: " + z);
                                main.getConfig().set("warps", list);
                                //main.getConfig().addDefault("warps.", "Name: " + args[1] + "x: " + x + "y: " + y + "z: " + z);
                                //main.getConfig().addDefault("warps." + args[1] + "", main.world.toString().replace("[", "").replace("]", ""));
                                //main.getConfig().addDefault("warps." + args[1] + "", main.coords.toString().replace("[", "").replace("]", ""));
                                main.coords.clear();
                                main.world.clear();
                                main.saveConfig();
                                commandSender.sendMessage(ChatColor.translateAlternateColorCodes('&', main.getConfig().getString("Prefix").toString() + " " + ChatColor.GREEN + "Warp set at: " + ChatColor.GRAY + args[1]));
                            }
                        }
                    }
                    if (commandSender.hasPermission("warp.admin.del")) {
                        if (args[0].equalsIgnoreCase("Del")){
                            if (!args[1].isEmpty()) {
                                commandSender.getName();
                                commandSender.sendMessage(ChatColor.translateAlternateColorCodes('&', main.getConfig().getString("Prefix").toString() + " " + ChatColor.GRAY + args[1] + " " + ChatColor.GREEN + "Has been removed"));
                                main.getConfig().set(args[1], null);
                                main.saveConfig();
                            }
                        }
                    }
                    if (args[0].equalsIgnoreCase("SetPrefix")) {
                        if (commandSender.hasPermission("warp.admin.setprefix")) {
                            if (!args[1].isEmpty()) {
                                main.getConfig().set("Prefix", args[1]);
                                main.saveConfig();
                                commandSender.sendMessage(ChatColor.GREEN + "Your new prefix" + ChatColor.RESET + " " + ChatColor.translateAlternateColorCodes('&', main.getConfig().getString("Prefix").toString()));
                            } else {
                                commandSender.sendMessage(ChatColor.translateAlternateColorCodes('&', main.getConfig().getString("Prefix").toString() + " " + ChatColor.GREEN + "/warp SetPrefix <Your Prefix>"));

                            } return true;
                        }
                    }
                    return true;
                }
            }
        }
        return false;
    }
}





