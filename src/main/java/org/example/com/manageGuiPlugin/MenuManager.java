package org.example.com.manageGuiPlugin;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.Material;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.ItemFlag;

import java.util.Arrays;

public class MenuManager {

    private final Inventory menu;  // <-- IL TUO MENU SALVATO QUI

    public MenuManager() {
        menu = Bukkit.createInventory(null, 45, ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "Menu 1");

        setupMenu();
    }

    private void setupMenu() {

        // Random teleport
        ItemStack teleport = new ItemStack(Material.ENDER_EYE);
        ItemMeta meta = teleport.getItemMeta();
        meta.setDisplayName(ChatColor.LIGHT_PURPLE + "Random teleport");
        meta.setLore(Arrays.asList(ChatColor.GRAY + "Teleport to a random player!"));
        teleport.setItemMeta(meta);
        menu.setItem(20, teleport);

        // Kill yourself
        ItemStack kill = new ItemStack(Material.DIAMOND_SWORD);
        ItemMeta meta1 = kill.getItemMeta();
        meta1.setDisplayName(ChatColor.RED + "Kill yourself!");
        meta1.setLore(Arrays.asList(ChatColor.GRAY + "Kill yourself and make your life better!"));
        kill.setItemMeta(meta1);
        menu.setItem(22, kill);

        // Clear inventory
        ItemStack clearInv = new ItemStack(Material.LAVA_BUCKET);
        ItemMeta meta2 = clearInv.getItemMeta();
        meta2.setDisplayName(ChatColor.RED + "Clear your inventory!");
        meta2.setLore(Arrays.asList(ChatColor.GRAY + "Your inventory will disappear!"));
        clearInv.setItemMeta(meta2);
        menu.setItem(24, clearInv);

        // Close
        ItemStack close = new ItemStack(Material.BARRIER);
        ItemMeta meta3 = close.getItemMeta();
        meta3.setDisplayName(ChatColor.RED + "Close");
        close.setItemMeta(meta3);
        menu.setItem(0, close);

        // Borders
        ItemStack border = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
        ItemMeta meta4 = border.getItemMeta();
        meta4.setDisplayName("");
        border.setItemMeta(meta4);

        for (int i : new int[]{1,2,3,4,5,6,7,8,9,17,18,26,27,35,36,37,38,39,40,41,42,43,44}) {
            menu.setItem(i, border);
        }
    }

    public Inventory getMenu() {
        return menu;
    }

    public void openMenu(Player player) {
        player.openInventory(menu);
    }
}
