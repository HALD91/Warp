package me.Hald91.Warps;

import me.Hald91.Commands.CommandManager;
import me.Hald91.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;
import java.util.Objects;

public class WarpMenu implements Listener {
    Main main = JavaPlugin.getPlugin(Main.class);
    public final Inventory warp;
    public Player pe;



    /*
        Use this to open WarpMenu.
     */
    public void openInventory2(HumanEntity ent){
        ent.openInventory(warp);
    }

    /*
        Creates WarpMenu.
     */
    public WarpMenu() {
        warp = Bukkit.createInventory(null, 27, "" + ChatColor.translateAlternateColorCodes('&', main.getConfig().getString("WarpMenuPrefix").toString()));
        setitem();
    }

    /*
        Set items into the WarpMenu.
     */
    public void setitem(){
        warp.setItem(0, createguiItem(Material.BLUE_STAINED_GLASS_PANE, " "));
        warp.setItem(1, createguiItem(Material.BLUE_STAINED_GLASS_PANE, " "));
        warp.setItem(2, createguiItem(Material.BLUE_STAINED_GLASS_PANE, " "));
        warp.setItem(3, createguiItem(Material.BLUE_STAINED_GLASS_PANE, " "));
        warp.setItem(4, createguiItem(Material.BLUE_STAINED_GLASS_PANE, " "));
        warp.setItem(5, createguiItem(Material.BLUE_STAINED_GLASS_PANE, " "));
        warp.setItem(6, createguiItem(Material.BLUE_STAINED_GLASS_PANE, " "));
        warp.setItem(7, createguiItem(Material.BLUE_STAINED_GLASS_PANE, " "));
        warp.setItem(8, createguiItem(Material.BLUE_STAINED_GLASS_PANE, " "));
        warp.setItem(9, createguiItem(Material.BLUE_STAINED_GLASS_PANE, " "));
        warp.setItem(10, createguiItem(Material.GRASS_BLOCK,ChatColor.GREEN + "Survival"));
        warp.setItem(13, createguiItem(Material.BLUE_STAINED_GLASS_PANE, " "));

        warp.setItem(17, createguiItem(Material.BLUE_STAINED_GLASS_PANE, " "));
        warp.setItem(18, createguiItem(Material.BLUE_STAINED_GLASS_PANE, " "));
        warp.setItem(19, createguiItem(Material.BLUE_STAINED_GLASS_PANE, " "));
        warp.setItem(20, createguiItem(Material.BLUE_STAINED_GLASS_PANE, " "));
        warp.setItem(21, createguiItem(Material.BLUE_STAINED_GLASS_PANE, " "));
        warp.setItem(22, createguiItem(Material.BLUE_STAINED_GLASS_PANE, " "));
        warp.setItem(23, createguiItem(Material.BLUE_STAINED_GLASS_PANE, " "));
        warp.setItem(24, createguiItem(Material.BLUE_STAINED_GLASS_PANE, " "));
        warp.setItem(25, createguiItem(Material.BLUE_STAINED_GLASS_PANE, " "));
        warp.setItem(26, createguiItem(Material.BLUE_STAINED_GLASS_PANE, " "));
    }


    protected ItemStack createguiItem(final Material material, final String name, final String... lore){
        final ItemStack item = new ItemStack(material, 1);
        final ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(name);

        meta.setLore(Arrays.asList(lore));

        item.setItemMeta(meta);

        return item;
    }

    /*
        All Events ( Inventory click and Item click.
     */


    @EventHandler
    public void interact2(final InventoryClickEvent event) {
        final Player p3 = (Player) event.getWhoClicked();
         if (event.getView().getTitle().equalsIgnoreCase("" + ChatColor.translateAlternateColorCodes('&', main.getConfig().getString("WarpMenuPrefix").toString()))) {
                if (p3.getPlayer() != null) {
                    if (event.getCurrentItem() != null) {
                        switch (event.getCurrentItem().getType()) {
                            case GRASS_BLOCK:
                                if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GREEN + "Survival")) {
                                    event.setCancelled(true);
                                    p3.closeInventory();
                                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), ("tp" + p3.getPlayer().getName() + " " + String.valueOf(main.getConfig().getString("test")).replace(",", "")));
                                }
                                break;
                            case BLUE_STAINED_GLASS_PANE:
                                if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(" ")) {
                                    event.setCancelled(true);
                                }
                                break;
                            case BONE_MEAL:
                                if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.RED + "Chat")) {
                                    event.setCancelled(true);
                                    p3.closeInventory();
                                }
                                break;
                        }
                    }
                }
            }
        }


    /*
        Inventory click.
     */
    @EventHandler
    public void onInvontoryClick(final InventoryDragEvent e){
        if (e.getInventory() == warp){
            e.setCancelled(true);
        }
    }




}
