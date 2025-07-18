package dev.type.typeMmo.stats;

import dev.type.typeMmo.TypeMmo;
import dev.type.typeMmo.files.DataFile;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import static dev.type.typeMmo.files.DataFile.reload;
import static dev.type.typeMmo.files.DataFile.save;

public class PlayerJoin implements Listener {
    private TypeMmo plugin;

    public PlayerJoin(TypeMmo plugin) {
        this.plugin = plugin;
    }



    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        FileConfiguration data = DataFile.getDataConfig();
        Player player = event.getPlayer();
//        todo: Need to implement loading of data.yml and check if user exists, else create a new entry in data.yml?
        String uuid = player.getUniqueId().toString();

        if (!data.contains(uuid)) {
            data.set(uuid + ".name", player.getName());
            data.set(uuid + ".totalLevel", player.getLevel());
            data.set(uuid + ".totalXP", 0);
            data.set(uuid + ".stats.dexXP", 0);
            data.set(uuid + ".stats.dexLevel", 0);
            save();
            reload();
        }

    }
}
