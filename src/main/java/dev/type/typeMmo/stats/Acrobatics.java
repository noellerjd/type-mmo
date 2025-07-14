package dev.type.typeMmo.stats;

import com.destroystokyo.paper.event.player.PlayerJumpEvent;
import dev.type.typeMmo.TypeMmo;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.HashMap;

public class Acrobatics implements Listener {

    private TypeMmo plugin;

    public Acrobatics(TypeMmo plugin) {
        this.plugin = plugin;
    }


    Map<Player, Long> timeMap = new HashMap<>();


    @EventHandler
    public void onPlayerMove(PlayerJumpEvent event) {
        FileConfiguration config = plugin.getConfig();
        if (!config.getBoolean("general.disable-acrobatics")) {

        Player player = event.getPlayer();

        player.sendMessage("Acrobatics");

        timeMap.put(player, System.currentTimeMillis());
        }
    }

    @EventHandler public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();


        if (timeMap.containsKey(player) && player.isOnGround())  {
            Bukkit.getScheduler().runTaskLater(plugin, ()-> {
//                Remove player from set
//                Print Statements
                player.sendMessage( player.displayName() + " is on the ground");

                if (!player.isDead()) {
                    long time1 = timeMap.get(player);
                    long time2 = System.currentTimeMillis();

                    long diff = time2 - time1;

                    player.sendMessage("Time in air: " + diff);
                }
                timeMap.remove(player);
            }, 1L);
        }
    }
}
