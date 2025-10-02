package com.yourname.disablecommand;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class DisableCommandPlugin extends JavaPlugin {
    
    private ConfigManager configManager;
    private CommandListener commandListener;
    private List<String> disabledCommands;
    
    @Override
    public void onEnable() {
        // Khởi tạo config manager
        configManager = new ConfigManager(this);
        configManager.loadConfig();
        
        // Load danh sách command bị disable
        loadDisabledCommands();
        
        // Register command executor
        DisableCommandExecutor commandExecutor = new DisableCommandExecutor(this);
        if (getCommand("dc") != null) {
            getCommand("dc").setExecutor(commandExecutor);
            getCommand("dc").setTabCompleter(commandExecutor);
        }
        
        // Register event listener
        commandListener = new CommandListener(this);
        getServer().getPluginManager().registerEvents(commandListener, this);
        
        getLogger().info("DisableCommand plugin đã được kích hoạt!");
    }
    
    @Override
    public void onDisable() {
        getLogger().info("DisableCommand plugin đã được tắt!");
    }
    
    public void loadDisabledCommands() {
        disabledCommands = new ArrayList<>(getConfig().getStringList("disabled-commands"));
    }
    
    public List<String> getDisabledCommands() {
        return disabledCommands;
    }
    
    public void addDisabledCommand(String command) {
        if (!disabledCommands.contains(command.toLowerCase())) {
            disabledCommands.add(command.toLowerCase());
            getConfig().set("disabled-commands", disabledCommands);
            saveConfig();
        }
    }
    
    public boolean removeDisabledCommand(String command) {
        boolean removed = disabledCommands.remove(command.toLowerCase());
        if (removed) {
            getConfig().set("disabled-commands", disabledCommands);
            saveConfig();
        }
        return removed;
    }
    
    public String getDisabledMessage() {
        String message = getConfig().getString("disabled-message", "&cCommand này đã bị disable!");
        return ChatColor.translateAlternateColorCodes('&', message != null ? message : "&cCommand này đã bị disable!");
    }
    
    public String getNoPermissionMessage() {
        String message = getConfig().getString("no-permission-message", "&cBạn không có quyền sử dụng command này!");
        return ChatColor.translateAlternateColorCodes('&', message != null ? message : "&cBạn không có quyền sử dụng command này!");
    }
    
    public boolean isDebugEnabled() {
        return getConfig().getBoolean("debug", false);
    }
    
    public ConfigManager getConfigManager() {
        return configManager;
    }
}