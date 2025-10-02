package com.yourname.disablecommand;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class CommandListener implements Listener {
    
    private final DisableCommandPlugin plugin;
    
    public CommandListener(DisableCommandPlugin plugin) {
        this.plugin = plugin;
    }
    
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerCommand(PlayerCommandPreprocessEvent event) {
        Player player = event.getPlayer();
        String command = event.getMessage().toLowerCase();
        
        // Lấy tên command (bỏ dấu /)
        String commandName = command.substring(1).split(" ")[0];
        
        // Debug log
        if (plugin.isDebugEnabled()) {
            plugin.getLogger().info("Player " + player.getName() + " executed: " + command);
        }
        
        // Kiểm tra xem có bypass permission không
        if (player.hasPermission("disablecommand.bypass")) {
            return;
        }
        
        // Kiểm tra xem command có bị disable không
        if (plugin.getDisabledCommands().contains(commandName)) {
            event.setCancelled(true);
            player.sendMessage(plugin.getDisabledMessage());
            
            if (plugin.isDebugEnabled()) {
                plugin.getLogger().info("Blocked command " + commandName + " for player " + player.getName());
            }
        }
    }
}