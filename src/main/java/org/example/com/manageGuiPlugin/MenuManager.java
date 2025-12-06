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
import java.util.List;

public class MenuManager {

    private final Inventory menu;  // <-- IL TUO MENU SALVATO QUI
    private Main main;

    public MenuManager(Main main) {
        String title = ChatColor.translateAlternateColorCodes('&', main.getConfig().getString("Menu1.Title"));
        menu = Bukkit.createInventory(null, 45, ChatColor.translateAlternateColorCodes('&', title));
        this.main = main;
        setupMenu();
    }

    private void setupMenu() {
        
        Material cancelMaterial = Material.valueOf(main.getConfig().getString("Menu1.Items.Cancel.Type"));
        Material teleportMaterial = Material.valueOf(main.getConfig().getString("Menu1.Items.Teleport.Type"));
        Material killMaterial = Material.valueOf(main.getConfig().getString("Menu1.Items.Kill.Type"));
        Material invMaterial = Material.valueOf(main.getConfig().getString("Menu1.Items.Clear-Inventory.Type"));
        Material borderMaterial = Material.valueOf(main.getConfig().getString("Menu1.Items.Borders.Type"));
        String cancelName = color(main.getConfig().getString("Menu1.Items.Cancel.Name"));
        String teleportName = color(main.getConfig().getString("Menu1.Items.Teleport.Name"));
        String killName = color(main.getConfig().getString("Menu1.Items.Kill.Name"));
        String invName = color(main.getConfig().getString("Menu1.Items.Clear-Inventory.Name"));
        List<String> teleportDesc =  colorListInPlace(main.getConfig().getStringList("Menu1.Items.Teleport.Description"));
        List<String> killDesc =  colorListInPlace(main.getConfig().getStringList("Menu1.Items.Kill.Description"));
        List<String> invDesc =  colorListInPlace(main.getConfig().getStringList("Menu1.Items.Clear-Inventory.Description"));
        
        // Random teleport
        ItemStack teleport = new ItemStack(teleportMaterial);
        ItemMeta meta = teleport.getItemMeta();
        meta.setDisplayName(teleportName);
        meta.setLore(teleportDesc);
        teleport.setItemMeta(meta);
        menu.setItem(20, teleport);

        // Kill yourself
        ItemStack kill = new ItemStack(killMaterial);
        ItemMeta meta1 = kill.getItemMeta();
        meta1.setDisplayName(killName);
        meta1.setLore(killDesc);
        kill.setItemMeta(meta1);
        menu.setItem(22, kill);

        // Clear inventory
        ItemStack clearInv = new ItemStack(invMaterial);
        ItemMeta meta2 = clearInv.getItemMeta();
        meta2.setDisplayName(invName);
        meta2.setLore(invDesc);
        clearInv.setItemMeta(meta2);
        menu.setItem(24, clearInv);

        // Close
        ItemStack close = new ItemStack(cancelMaterial);
        ItemMeta meta3 = close.getItemMeta();
        meta3.setDisplayName(cancelName);
        close.setItemMeta(meta3);
        menu.setItem(0, close);

        // Borders
        ItemStack border = new ItemStack(borderMaterial);
        ItemMeta meta4 = border.getItemMeta();
        meta4.setDisplayName("");
        border.setItemMeta(meta4);

        for (int i : new int[]{1,2,3,4,5,6,7,8,9,17,18,26,27,35,36,37,38,39,40,41,42,43,44}) {
            menu.setItem(i, border);
        }
    }
    
    private String color(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }
    
    private List<String> colorListInPlace(List<String> list) {
        for (int i = 0; i < list.size(); i++) {
            list.set(i, color(list.get(i)));
        }
        return list;
    }

    public Inventory getMenu() {
        return menu;
    }

    public void openMenu(Player player) {
        player.openInventory(menu);
    }
}
