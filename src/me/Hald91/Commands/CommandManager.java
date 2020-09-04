package me.Hald91.Commands;

import me.Hald91.Main;
import me.Hald91.Warps.WarpMenu;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public class CommandManager implements CommandExecutor {
    public List<String> world = main.config.getStringList;
    public static Player player;

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        Main main = JavaPlugin.getPlugin(Main.class);
        Player player = null;
        if (command.getName().equalsIgnoreCase("Warp")) {
            if (commandSender.hasPermission("warp.use")){
                if (args.length == 0){
                    new WarpMenu().openInventory2((HumanEntity) commandSender);
                }
                if (args.length == 1) {
                    if (args[0].equalsIgnoreCase("Help")) {
                        commandSender.sendMessage(ChatColor.translateAlternateColorCodes('&', main.getConfig().getString("WarpCommandPrefix").toString() + " " + ChatColor.GRAY + "-----------------" + ChatColor.GOLD + "Help" + ChatColor.GRAY + "---------------"));
                        commandSender.sendMessage(" ");
                        commandSender.sendMessage(ChatColor.translateAlternateColorCodes('&', main.getConfig().getString("WarpCommandPrefix").toString() + " " + ChatColor.GREEN + "/Warp help" + ChatColor.AQUA + "to see how you use this command"));
                        commandSender.sendMessage(ChatColor.translateAlternateColorCodes('&', main.getConfig().getString("WarpCommandPrefix").toString() + " " + ChatColor.GREEN + "/Warp reload" + ChatColor.AQUA + "to reload the config"));
                        commandSender.sendMessage(" ");
                    }
                    if (args[0].equalsIgnoreCase("Reload")) {
                        main.reloadConfig();
                        commandSender.sendMessage(ChatColor.translateAlternateColorCodes('&', main.getConfig().getString("WarpCommandPrefix").toString() + " " + ChatColor.GREEN + "The config has been reload."));

                    }
                    return true;
                }
                if (args.length <= 3){
                    if (args[0].equalsIgnoreCase("Set")){
                        if (args.length == 2) {
                            if (player != null) {
                                String x = String.valueOf(player.getLocation().getX());
                                String y = String.valueOf(player.getLocation().getY());
                                String z = String.valueOf(player.getLocation().getZ());
                                String world = String.valueOf(player.getWorld().getName());
                                main.getConfig().set(args[0] + "", main.world.toString().replace("[", "").replace("]", ""));
                            }
                        }
                    }
                }return true;
            }else {
                commandSender.sendMessage(ChatColor.translateAlternateColorCodes('&', main.getConfig().getString("WarpCommandPrefix").toString() + " " + ChatColor.RED + "It seems like you don't have permission to use this command."));
            }
        }


        return false;
    }
}
