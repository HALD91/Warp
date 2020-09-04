package me.Hald91;

import me.Hald91.Commands.CommandManager;
import me.Hald91.Warps.WarpMenu;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    public FileConfiguration config = getConfig();


    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents((Listener)new WarpMenu(), (Plugin) this);
        getCommand("Warp").setExecutor(new CommandManager());
    }

    @Override
    public void onDisable() {
    }
}
