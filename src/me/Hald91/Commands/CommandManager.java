package me.Hald91.Commands;

import me.Hald91.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

import static java.lang.String.valueOf;

public class CommandManager implements CommandExecutor {
    public static Player player;
    private List<String> nowallowed = new ArrayList<>();

    public CommandManager(){
        nowallowed.add("&0");
        nowallowed.add("&1");
        nowallowed.add("&2");
        nowallowed.add("&3");
        nowallowed.add("&4");
        nowallowed.add("&5");
        nowallowed.add("&6");
        nowallowed.add("&7");
        nowallowed.add("&8");
        nowallowed.add("&9");
        nowallowed.add("&a");
        nowallowed.add("&b");
        nowallowed.add("&c");
        nowallowed.add("&d");
        nowallowed.add("&e");
        nowallowed.add("&f");
    }
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        Main main = JavaPlugin.getPlugin(Main.class);
        Player player = (Player) commandSender;
        if (command.getName().equalsIgnoreCase("Warp")) {
            if (commandSender.hasPermission("warp.use")) {

                if (args.length == 0) {
                    commandSender.sendMessage(ChatColor.translateAlternateColorCodes('&', main.getConfig().getString("Prefix").toString() + " " + ChatColor.AQUA + "/Warp help" + " " + ChatColor.WHITE + "To see how you use this command"));
                }
                if (args.length == 1) {
                    if (args[0].equalsIgnoreCase("List")) {
                        commandSender.sendMessage(ChatColor.AQUA + "Getting list.");
                        if (main.getConfig().getStringList("warps").isEmpty()) {
                            commandSender.sendMessage(ChatColor.translateAlternateColorCodes('&', main.getConfig().getString("Prefix").toString() + " " + ChatColor.WHITE + "no warps exists"));
                        }
                        for (String messages : main.getConfig().getStringList("warps")) {
                            commandSender.sendMessage(ChatColor.WHITE + messages);
                        }
                        return true;
                    }
                    if (args[0].equalsIgnoreCase("Help")) {
                        commandSender.sendMessage(ChatColor.GRAY + "-----------------" + ChatColor.GOLD + "Help" + ChatColor.GRAY + "---------------");
                        commandSender.sendMessage(" ");
                        commandSender.sendMessage(ChatColor.AQUA + "/Warp help" + ChatColor.WHITE + " " + "To see how you use this command");
                        commandSender.sendMessage(ChatColor.AQUA + "/Warp List" + ChatColor.WHITE + " " + "To see how many warps there has been sat");
                        commandSender.sendMessage(ChatColor.AQUA + "/Warp <Warp>" + ChatColor.WHITE + " " + "To teleport to a warp");
                        commandSender.sendMessage(ChatColor.AQUA + "/Warp reload" + ChatColor.WHITE + " " + "To reload the config");
                        commandSender.sendMessage(ChatColor.AQUA + "/Warp Set <Warp>" + ChatColor.WHITE + " " + "To set a warp");
                        commandSender.sendMessage(ChatColor.AQUA + "/Warp Del <Warp>" + ChatColor.WHITE + " " + "To delete a warp");
                        commandSender.sendMessage(ChatColor.AQUA + "/warp SetPrefix <Prefix>" + ChatColor.WHITE + " " + "To set your own prefix");
                        commandSender.sendMessage(" ");
                        commandSender.sendMessage("-----------------------------------------------------");
                        return true;
                    }
                    if (args[0].equalsIgnoreCase("Reload")) {
                        if (commandSender.hasPermission("warp.admin.reload")) {
                            main.reloadConfig();
                            commandSender.sendMessage(ChatColor.translateAlternateColorCodes('&', main.getConfig().getString("Prefix").toString() + ChatColor.RED + " " + "Config" + " " + ChatColor.WHITE + "has been reload"));
                            return true;
                        }
                    }
                    if (!args[0].isEmpty()) {
                        ArrayList<String> list2 = (ArrayList<String>) main.getConfig().getStringList("warps");
                        for (String messages1 : list2) {
                            if (messages1.contains(args[0])) {
                                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "tp" + " " + commandSender.getName() + "" + String.valueOf(messages1.toString()).replace(",", "").replace("Name: " + args[0], "").replace(" x: ", " ").replace(" y: ", " ").replace(" z: ", " ").replace("[", "").replace("]", "").replace("&", ""));
                                commandSender.sendMessage(ChatColor.translateAlternateColorCodes('&', main.getConfig().getString("Prefix").toString() + ChatColor.WHITE + " " + "You have been teleportet to" + " " + ChatColor.AQUA + args[0]));
                            }
                        }
                    }
                }
            }
            if (args.length == 2) {
                if (commandSender.hasPermission("warp.admin.set")) {
                    if (args[0].equalsIgnoreCase("Set")) {
                        if (!args[1].isEmpty()) {

                            if (args[1].contains(nowallowed.toString())) {
                                commandSender.sendMessage(ChatColor.translateAlternateColorCodes('&', main.getConfig().getString("Prefix").toString() + " " + ChatColor.WHITE + "using color codes on the warp name arent allow."));
                            } else {

                                String x = valueOf(player.getLocation().getX());
                                String y = valueOf(player.getLocation().getY());
                                String z = valueOf(player.getLocation().getZ());
                                ArrayList<String> list = (ArrayList<String>) main.getConfig().getStringList("warps");
                                ArrayList<String> prefix = (ArrayList<String>) main.getConfig().getStringList("Prefix");

                                main.getConfig();
                                list.add("Name: " + args[1] + " x: " + x + " y: " + y + " z: " + z);

                                main.getConfig().set("warps", list);

                                main.getConfig().options().header("###########################\n" +
                                        "#####                 #####\n" +
                                        "####                   ####\n" +
                                        "###   Author: HALD91    ###\n" +
                                        "###   Version: 1.2.7.3  ###\n" +
                                        "####                   ####\n" +
                                        "#####                 #####\n" +
                                        "###########################\n" +
                                        "\n" +
                                        "\n" +
                                        "Set your own prefix here on commands in chat or on the warp menu.");
                                main.saveConfig();
                                commandSender.sendMessage(ChatColor.translateAlternateColorCodes('&', main.getConfig().getString("Prefix").toString() + " " + ChatColor.AQUA + args[1] + ChatColor.WHITE + " " + "set at:" + " " + "X:" + " " + ChatColor.GRAY + x + " " + ChatColor.WHITE + "Y:" + " " + ChatColor.GRAY + y + " " + ChatColor.WHITE + "Z:" + " " + ChatColor.GRAY + z));
                            }
                            return true;
                        }
                    }
                    if (commandSender.hasPermission("warp.admin.del")) {
                        if (args[0].equalsIgnoreCase("Del")) {
                            if (!args[1].isEmpty()) {
                                if (main.getConfig().getStringList("warps").isEmpty()) {
                                    commandSender.sendMessage(ChatColor.translateAlternateColorCodes('&', main.getConfig().getString("Prefix").toString() + " " + ChatColor.GRAY + "no warps exists"));
                                } else {
                                    ArrayList<String> list3 = (ArrayList<String>) main.getConfig().getStringList("warps");
                                    for (String messages2 : list3) {
                                        if (messages2.contains(args[1])) {
                                            list3.remove(messages2);
                                            main.getConfig().set("warps", list3);
                                            main.saveConfig();
                                            commandSender.sendMessage(ChatColor.translateAlternateColorCodes('&', main.getConfig().getString("Prefix").toString() + " " + ChatColor.AQUA + args[1] + ChatColor.WHITE + " " + "has been deleted"));
                                            break;
                                        }
                                    }
                                }
                                return true;
                            }
                        }
                        if (args[0].equalsIgnoreCase("SetPrefix")) {
                            if (commandSender.hasPermission("warp.admin.setprefix")) {
                                if (!args[1].isEmpty()) {
                                    main.getConfig().set("Prefix", args[1]);
                                    main.saveConfig();
                                    commandSender.sendMessage(ChatColor.WHITE + "Your new prefix" + ChatColor.RESET + " " + ChatColor.translateAlternateColorCodes('&', main.getConfig().getString("Prefix").toString()));
                                }
                                return true;
                            }
                        }
                        return true;
                    }
                }
            }
        }return false;
    }

}





