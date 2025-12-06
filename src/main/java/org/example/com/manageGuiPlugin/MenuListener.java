package org.example.com.manageGuiPlugin;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.Random;

public class MenuListener implements Listener {

    private MenuManager menu;
    public MenuListener(MenuManager menu) {
        this.menu = menu;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {

        if(!(event.getInventory().equals(menu.getMenu()))) return;
        if(event.getCurrentItem() == null) return;

        event.setCancelled(true);
        Player player =  (Player) event.getWhoClicked();

        switch(event.getRawSlot()) {
            case 0:
                break;
            case 20: // Random teleport
                Random random = new Random();
                Player target = (Player) Bukkit.getOnlinePlayers().toArray()[random.nextInt(Bukkit.getOnlinePlayers().size())];

                player.teleport(target);
                player.sendMessage(ChatColor.GRAY + "You have been teleported to " + ChatColor.AQUA + target.getName() + "!");
                target.sendMessage(ChatColor.AQUA + player.getName() + ChatColor.GRAY+ " has been teleported to you!");
                break;
            case 22: // Kill yourself
                player.setHealth(0);
                player.sendMessage(ChatColor.RED + "You killed yourself!");
                break;
            case 24: // Clear inventory
                player.closeInventory();
                player.getInventory().clear();
                player.sendMessage(ChatColor.GREEN + "You cleared your inventory!");
                return;
            default:
                return;
        }
        player.closeInventory();
    }
}
