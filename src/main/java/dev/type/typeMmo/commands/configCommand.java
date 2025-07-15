package dev.type.typeMmo.commands;

import dev.type.typeMmo.TypeMmo;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class configCommand implements CommandExecutor {

    private TypeMmo plugin;

    public configCommand(TypeMmo plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
//            Check if user has permission
            if (p.hasPermission("typeMmo.config")) {
                FileConfiguration config = plugin.getConfig();
//                If the user passes in two arguments, update corresponding config setting if it exists
                if (args.length == 2) {
                    boolean status = Boolean.parseBoolean(args[1]);
                    if (config.contains(args[0].toLowerCase())) {
                        config.set(args[0], status);
                        p.sendMessage(args[0] + " is now set to: " + status);
                    }
                }
//                If the user passes in only one argument, and it exists, output the current config setting
                if (args.length == 1 && config.contains(args[0].toLowerCase())) {
                    p.sendMessage(args[0] + " is set to: " + String.valueOf(config.get(args[0].toLowerCase())));
                }
            } else {
                p.sendMessage(ChatColor.RED + "You do not have permission to use this command");
            }

        }
        return false;
    }
}
