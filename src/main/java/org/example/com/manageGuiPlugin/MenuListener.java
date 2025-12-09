package org.example.com.manageGuiPlugin;

import com.google.gson.internal.bind.util.ISO8601Utils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.Random;

public class MenuListener implements Listener {
    
    // Riferimento al gestore del menu, necessario per accedere al menu e ai messaggi
    private MenuManager menu;
    
    // Costruttore che inizializza il listener con un MenuManager esistente
    public MenuListener(MenuManager menu) {
        this.menu = menu;
    }
    
    // Metodo che intercetta gli eventi di click nell'inventario
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        
        // Controlla se l'inventario cliccato è quello gestito dal MenuManager
        if(!(event.getInventory().equals(menu.getMenu()))) return;
        
        // Evita errori se l'item cliccato è nullo
        if(event.getCurrentItem() == null) return;
        
        // Blocca l'azione di default del click (es. spostamento oggetti)
        event.setCancelled(true);
        
        // Ottiene il giocatore che ha cliccato
        Player player =  (Player) event.getWhoClicked();
        
        // Gestione dei click in base alla posizione nello slot dell'inventario
        switch(event.getRawSlot()) {
            case 0:
                // Slot 0: al momento non fa nulla
                break;
            case 20: // Random teleport
                // Crea un generatore di numeri casuali
                Random random = new Random();
                
                // Seleziona un giocatore casuale tra quelli online
                Player target = (Player) Bukkit.getOnlinePlayers().toArray()[random.nextInt(Bukkit.getOnlinePlayers().size())];
                
                // Teletrasporta il giocatore cliccante verso il giocatore casuale scelto
                player.teleport(target);
                
                // Invia un messaggio al giocatore che ha cliccato, indicando chi è stato teletrasportato
                player.sendMessage(color(menu.getMessages().getString("Prefix")
                        + menu.getMessages().getString("Teleport.TeleportPlayer")
                        + " " + menu.getMessages().getString("Teleport.TargetColor")
                        + target.getName() + "."));
                
                // Invia un messaggio al giocatore target, informandolo che qualcuno si è teletrasportato verso di lui
                target.sendMessage(color(menu.getMessages().getString("Prefix")
                        + menu.getMessages().getString("Teleport.TargetColor")
                        + player.getName() + " "
                        + menu.getMessages().getString("Teleport.TeleportTarget") + "."));
                break;
            case 22: // Kill yourself
                // Imposta la salute del giocatore a 0, facendolo "morire"
                player.setHealth(0);
                
                // Invia un messaggio di conferma al giocatore
                player.sendMessage(color(menu.getMessages().getString("Prefix")
                        + menu.getMessages().getString("KillYourSelf")));
                break;
            case 24: // Clear inventory
                // Chiude l'inventario del giocatore
                player.closeInventory();
                
                // Pulisce tutto l'inventario del giocatore
                player.getInventory().clear();
                
                // Invia un messaggio di conferma al giocatore
                player.sendMessage(color(menu.getMessages().getString("Prefix")
                        + menu.getMessages().getString("ClearInventory")));
                
                // Esce dal metodo per evitare ulteriori operazioni
                return;
            default:
                // Per tutti gli altri slot non fare nulla
                return;
        }
        
        // Chiude l'inventario del giocatore dopo aver eseguito l'azione
        player.closeInventory();
    }
    
    // Metodo privato per applicare i colori ai messaggi usando i codici '&'
    private String color(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }
}
