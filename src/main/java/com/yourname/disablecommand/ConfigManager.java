package com.yourname.disablecommand;

import org.bukkit.configuration.file.FileConfiguration;

public class ConfigManager {
    
    private final DisableCommandPlugin plugin;
    
    public ConfigManager(DisableCommandPlugin plugin) {
        this.plugin = plugin;
    }
    
    public void loadConfig() {
        plugin.saveDefaultConfig();
        plugin.reloadConfig();
    }
    
    public void reloadConfig() {
        plugin.reloadConfig();
        plugin.loadDisabledCommands();
    }
    
    public FileConfiguration getConfig() {
        return plugin.getConfig();
    }
}