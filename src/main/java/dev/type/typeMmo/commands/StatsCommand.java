package dev.type.typeMmo.commands;

import dev.type.typeMmo.files.DataFile;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class StatsCommand implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            FileConfiguration data = DataFile.getDataConfig();
            String uuid = p.getUniqueId().toString();
            ConfigurationSection playerInfo = data.getConfigurationSection(uuid);

            if (data.contains(uuid)) {
                int totalLevel = playerInfo.getInt("totalLevel");
                int totalXP = playerInfo.getInt("totalXP");
                int acrobaticsLevel = playerInfo.getInt("stats.acrobatics");
                int acrobaticsXP = playerInfo.getInt("stats.acrobaticsXP");

                p.sendMessage(ChatColor.GOLD + "Total Level: " + totalLevel + "\nTotal XP: " + totalXP);
                p.sendMessage("Acrobatics Level: " + acrobaticsLevel + "\nAcrobatics XP: " + acrobaticsXP);

            } else {
                p.sendMessage(ChatColor.RED + "There was an error loading your data.");
            }



        }
        return false;
    }
}
