package dev.type.typeMmo.stats;

import dev.type.typeMmo.TypeMmo;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoin implements Listener {
    private TypeMmo plugin;

    public PlayerJoin(TypeMmo plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
//        todo: Need to implement loading of data.yml and check if user exists, else create a new entry in data.yml?
    }
}
