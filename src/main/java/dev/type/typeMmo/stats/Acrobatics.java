package dev.type.typeMmo.stats;

import com.destroystokyo.paper.event.player.PlayerJumpEvent;
import dev.type.typeMmo.TypeMmo;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.Map;
import java.util.HashMap;

public class Acrobatics implements Listener {

    private TypeMmo plugin;

    public Acrobatics(TypeMmo plugin) {
        this.plugin = plugin;
    }


    Map<Player, Long> timeMap = new HashMap<>();


//    Trigger when player jumps
    @EventHandler
    public void onPlayerMove(PlayerJumpEvent event) {
        FileConfiguration config = plugin.getConfig();
        Player player = event.getPlayer();
//        Check if acrobatics is disabled in config
        if (!config.getBoolean("general.disable-acrobatics") ) {
            if (!player.isSwimming() && !player.isFlying()) {
                player.sendMessage("Acrobatics");
                timeMap.put(player, System.currentTimeMillis());
            }

        }
    }
//    Check if player quits mid-jump
    @EventHandler
    public void onPlayerQuitEvent(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        timeMap.remove(player);
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
//        Check if player is in water/swimming.
        if(player.isSwimming() || player.isInWater()) {
            timeMap.remove(player);
        }

//        Check if player is in timeMap hash and if they are on the ground again.
        if (timeMap.containsKey(player) && player.isOnGround())  {
//            Delay calculation to work with player death trigger.
            Bukkit.getScheduler().runTaskLater(plugin, ()-> {
//                If the player hasn't died, calculate time in air
                if (!player.isDead()) {
                    long time1 = timeMap.get(player);
                    long time2 = System.currentTimeMillis();

                    long diff = time2 - time1;

                    player.sendMessage("Time in air: " + diff);
                }
//                Remove player from set
                timeMap.remove(player);
            }, 1L);
        }
    }
}
