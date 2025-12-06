package org.example.com.manageGuiPlugin;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        MenuManager menuManager = new MenuManager(this);

        getCommand("menu").setExecutor(new MenuCommand(menuManager));
        Bukkit.getPluginManager().registerEvents(new MenuListener(menuManager), this);
        
        getConfig().options().copyDefaults(true);
        saveConfig();
    }


}
