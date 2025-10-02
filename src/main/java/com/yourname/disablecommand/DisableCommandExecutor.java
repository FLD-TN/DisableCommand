package com.yourname.disablecommand;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

public class DisableCommandExecutor implements CommandExecutor, TabCompleter {
    
    private final DisableCommandPlugin plugin;
    
    public DisableCommandExecutor(DisableCommandPlugin plugin) {
        this.plugin = plugin;
    }
    
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("disablecommand.admin")) {
            sender.sendMessage(plugin.getNoPermissionMessage());
            return true;
        }
        
        if (args.length == 0) {
            sendHelp(sender);
            return true;
        }
        
        String subCommand = args[0].toLowerCase();
        
        switch (subCommand) {
            case "add":
                if (args.length < 2) {
                    sender.sendMessage(ChatColor.RED + "Sử dụng: /dc add <command>");
                    return true;
                }
                String commandToAdd = args[1].toLowerCase();
                plugin.addDisabledCommand(commandToAdd);
                sender.sendMessage(ChatColor.GREEN + "Đã thêm command '" + commandToAdd + "' vào danh sách disable!");
                break;
                
            case "remove":
                if (args.length < 2) {
                    sender.sendMessage(ChatColor.RED + "Sử dụng: /dc remove <command>");
                    return true;
                }
                String commandToRemove = args[1].toLowerCase();
                boolean removed = plugin.removeDisabledCommand(commandToRemove);
                if (removed) {
                    sender.sendMessage(ChatColor.GREEN + "Đã xóa command '" + commandToRemove + "' khỏi danh sách disable!");
                } else {
                    sender.sendMessage(ChatColor.RED + "Command '" + commandToRemove + "' không có trong danh sách disable!");
                }
                break;
                
            case "list":
                List<String> disabledCommands = plugin.getDisabledCommands();
                if (disabledCommands.isEmpty()) {
                    sender.sendMessage(ChatColor.YELLOW + "Không có command nào bị disable!");
                } else {
                    sender.sendMessage(ChatColor.GOLD + "=== Danh sách command bị disable ===");
                    for (String cmd : disabledCommands) {
                        sender.sendMessage(ChatColor.YELLOW + "- " + cmd);
                    }
                }
                break;
                
            case "reload":
                plugin.getConfigManager().reloadConfig();
                sender.sendMessage(ChatColor.GREEN + "Đã reload config thành công!");
                break;
                
            default:
                sendHelp(sender);
                break;
        }
        
        return true;
    }
    
    private void sendHelp(CommandSender sender) {
        sender.sendMessage(ChatColor.GOLD + "=== DisableCommand Plugin ===");
        sender.sendMessage(ChatColor.YELLOW + "Author: FLD-TN");
        sender.sendMessage(ChatColor.YELLOW + "Version: 1.0.0");
        sender.sendMessage(ChatColor.YELLOW + "DisableCommand Help:");
        sender.sendMessage(ChatColor.BLUE + "/dc add <command> - Thêm command vào danh sách disable");
        sender.sendMessage(ChatColor.BLUE + "/dc remove <command> - Xóa command khỏi danh sách disable");
        sender.sendMessage(ChatColor.BLUE + "/dc list - Xem danh sách command bị disable");
        sender.sendMessage(ChatColor.BLUE + "/dc reload - Reload config");
    }
    
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (!sender.hasPermission("disablecommand.admin")) {
            return new ArrayList<>();
        }
        
        if (args.length == 1) {
            return Arrays.asList("add", "remove", "list", "reload");
        }
        
        if (args.length == 2 && args[0].equalsIgnoreCase("remove")) {
            return plugin.getDisabledCommands();
        }
        
        return new ArrayList<>();
    }
}