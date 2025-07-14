package dev.type.typeMmo.commands;

import dev.type.typeMmo.TypeMmo;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TestCommand implements CommandExecutor {
//    private TypeMmo main;

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;

            p.sendMessage("test");

        }
        return false;
    }

}
