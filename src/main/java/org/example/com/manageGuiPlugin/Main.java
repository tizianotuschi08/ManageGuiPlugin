package org.example.com.manageGuiPlugin;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.IOException;

public final class Main extends JavaPlugin {
    
    // Variabile per memorizzare la configurazione del file messages.yml
    YamlConfiguration modifyMessages;
    
    // Metodo chiamato quando il plugin viene abilitato
    @Override
    public void onEnable() {
        // Crea un'istanza del MenuManager passando il plugin corrente
        MenuManager menuManager = new MenuManager(this);
        
        // Imposta il comando "menu" e associa l'esecutore al MenuCommand
        getCommand("menu").setExecutor(new MenuCommand(menuManager));
        
        // Registra il listener per intercettare eventi di click nel menu
        Bukkit.getPluginManager().registerEvents(new MenuListener(menuManager), this);
        
        // Copia i valori di default dal config.yml se non esistono
        getConfig().options().copyDefaults(true);
        
        // Salva la configurazione principale del plugin
        saveConfig();
        
        // Definisce il file messages.yml nella cartella del plugin
        File messages = new File(getDataFolder(), "messages.yml");
        
        // Se il file messages.yml non esiste, lo crea copiandolo dalle risorse del plugin
        if (!messages.exists()) {
            saveResource("messages.yml", false);
            getLogger().warning("[WARNING] Created messages.yml"); // Log informativo
        }
        
        // Carica il contenuto di messages.yml nella variabile modifyMessages
        modifyMessages = YamlConfiguration.loadConfiguration(messages);
        
    }
    
    // Metodo per ottenere la configurazione di messages.yml
    public YamlConfiguration getMessages(){
        return modifyMessages;
    }
    
    // Metodo per salvare le modifiche apportate a messages.yml
    public void saveMessages(){
        File save = new File(getDataFolder(), "messages.yml");
        try {
            // Salva il file messages.yml con le modifiche correnti
            modifyMessages.save(save);
        } catch (IOException e) {
            // Se c'è un errore nel salvataggio, stampa un messaggio di errore nel log
            getLogger().severe("[ERROR] Could not save messages.yml");
        }
    }
}
