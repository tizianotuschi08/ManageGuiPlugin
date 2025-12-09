package org.example.com.manageGuiPlugin;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.Material;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.profile.PlayerProfile;
import org.bukkit.profile.PlayerTextures;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.UUID;

public class MenuManager {
    
    // Inventory del menu che verrà mostrato ai giocatori
    private final Inventory menu;
    
    // Riferimento alla classe principale del plugin
    private Main main;
    
    // Costruttore che inizializza il menu con il plugin principale
    public MenuManager(Main main) {
        // Recupera il titolo del menu dal config e applica i colori
        String title = ChatColor.translateAlternateColorCodes('&', main.getConfig().getString("Menu1.Title"));
        
        // Crea l'inventario con 45 slot e il titolo formattato
        menu = Bukkit.createInventory(null, 45, ChatColor.translateAlternateColorCodes('&', title));
        this.main = main;
        
        // Chiama il metodo per configurare gli item del menu
        setupMenu();
    }
    
    // Metodo che imposta tutti gli item del menu
    private void setupMenu(){
        
        // Recupera i materiali dal config
        Material teleportMaterial = Material.valueOf(main.getConfig().getString("Menu1.Items.Teleport.Type"));
        Material killMaterial = Material.valueOf(main.getConfig().getString("Menu1.Items.Kill.Type"));
        Material invMaterial = Material.valueOf(main.getConfig().getString("Menu1.Items.Clear-Inventory.Type"));
        Material borderMaterial = Material.valueOf(main.getConfig().getString("Menu1.Items.Borders.Type"));
        
        // Recupera i nomi e descrizioni degli item dal config e applica i colori
        String teleportName = color(main.getConfig().getString("Menu1.Items.Teleport.Name"));
        String killName = color(main.getConfig().getString("Menu1.Items.Kill.Name"));
        String invName = color(main.getConfig().getString("Menu1.Items.Clear-Inventory.Name"));
        List<String> teleportDesc =  colorListInPlace(main.getConfig().getStringList("Menu1.Items.Teleport.Description"));
        List<String> killDesc =  colorListInPlace(main.getConfig().getStringList("Menu1.Items.Kill.Description"));
        List<String> invDesc =  colorListInPlace(main.getConfig().getStringList("Menu1.Items.Clear-Inventory.Description"));
        
        // --- Configurazione item Random Teleport ---
        ItemStack teleport = new ItemStack(teleportMaterial);
        ItemMeta meta = teleport.getItemMeta();
        meta.setDisplayName(teleportName);      // Imposta il nome
        meta.setLore(teleportDesc);             // Imposta la descrizione
        teleport.setItemMeta(meta);
        menu.setItem(20, teleport);             // Posiziona nello slot 20
        
        // --- Configurazione item Kill Yourself ---
        ItemStack kill = new ItemStack(killMaterial);
        ItemMeta meta1 = kill.getItemMeta();
        meta1.setDisplayName(killName);
        meta1.setLore(killDesc);
        kill.setItemMeta(meta1);
        menu.setItem(22, kill);                 // Posiziona nello slot 22
        
        // --- Configurazione item Clear Inventory ---
        ItemStack clearInv = new ItemStack(invMaterial);
        ItemMeta meta2 = clearInv.getItemMeta();
        meta2.setDisplayName(invName);
        meta2.setLore(invDesc);
        clearInv.setItemMeta(meta2);
        menu.setItem(24, clearInv);             // Posiziona nello slot 24
        
        // --- Configurazione item Close (Player Head con skin personalizzata) ---
        ItemStack close = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta meta3 = (SkullMeta) close.getItemMeta();
        
        // Crea un profilo giocatore fittizio per impostare la skin
        PlayerProfile profile = Bukkit.createPlayerProfile(UUID.randomUUID());
        PlayerTextures texture = profile.getTextures();
        URL url;
        try {
            // URL della skin da applicare
            url = new URL("http://textures.minecraft.net/texture/797c6f29a950a9448949b3657d0dedc80709cbaa4c71451acdbe3886fc50d618");
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        
        // Applica la skin al profilo
        texture.setSkin(url);
        profile.setTextures(texture);
        meta3.setOwnerProfile(profile);
        
        // Imposta il nome dell'item
        meta3.setDisplayName(ChatColor.RED.toString() + ChatColor.BOLD + "Close");
        close.setItemMeta(meta3);
        menu.setItem(0, close);                 // Posiziona nello slot 0
        
        // --- Configurazione bordi ---
        ItemStack border = new ItemStack(borderMaterial);
        ItemMeta meta4 = border.getItemMeta();
        meta4.setDisplayName("");               // Nome vuoto per i bordi
        border.setItemMeta(meta4);
        
        // Posiziona l'item bordo in tutti gli slot indicati
        for (int i : new int[]{1,2,3,4,5,6,7,8,9,17,18,26,27,35,36,37,38,39,40,41,42,43,44}) {
            menu.setItem(i, border);
        }
    }
    
    // Metodo per applicare i colori al testo
    private String color(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }
    
    // Metodo per applicare i colori a una lista di stringhe
    private List<String> colorListInPlace(List<String> list) {
        for (int i = 0; i < list.size(); i++) {
            list.set(i, color(list.get(i)));
        }
        return list;
    }
    
    // Metodo per ottenere l'inventario del menu
    public Inventory getMenu() {
        return menu;
    }
    
    // Metodo per aprire il menu a un giocatore
    public void openMenu(Player player) {
        player.openInventory(menu);
    }
    
    // Metodo per ottenere i messaggi dal file messages.yml tramite la classe principale
    public YamlConfiguration getMessages() {
        return main.getMessages();
    }
}
