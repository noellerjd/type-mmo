package dev.type.typeMmo.stats;

import com.destroystokyo.paper.event.player.PlayerJumpEvent;
import dev.type.typeMmo.TypeMmo;
import dev.type.typeMmo.files.DataFile;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.Map;
import java.util.HashMap;

import static dev.type.typeMmo.files.DataFile.reload;
import static dev.type.typeMmo.files.DataFile.save;

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
        if (config.getBoolean("general.acrobatics") ) {
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
        FileConfiguration data = DataFile.getDataConfig();
//        Check if player is in water/swimming.
        if(player.isSwimming() || player.isInWater()) {
            timeMap.remove(player);
        }

//        Check if player is in timeMap hash and if they are on the ground again.
        if (timeMap.containsKey(player) && player.isOnGround())  {
//            Delay calculation to work with player death trigger.
            Bukkit.getScheduler().runTaskLater(plugin, ()-> {
//                If the player hasn't died, calculate time in air
//                todo: create more rebust checks to prevent ladder/hitting head as a way to level quickly
                if (!player.isDead()) {
                    long time1 = timeMap.get(player);
                    long time2 = System.currentTimeMillis();

                    long diff = time2 - time1;
                    if (diff > 600) {

                        player.sendMessage("Time in air: " + diff);
//                    Remove player from set after calculation
                        timeMap.remove(player);
                        String uuid = player.getUniqueId().toString();
                        int currentXP = data.getInt(uuid + ".stats.acrobaticsXP");
                        int xpGained = (int) (diff / 100);
                        int newXP = currentXP + xpGained;
                        int totalXP = data.getInt(uuid + ".totalXP");
                        int newTotalXp = totalXP + xpGained;

                        data.set(uuid + ".totalXP", newTotalXp);
                        data.set(uuid + ".stats.acrobaticsXP", newXP);
                        save();
                        reload();
                    }
                } else {
//                    Remove player from set if they are dead
                    timeMap.remove(player);
                }

            }, 1L);
        }
    }
}
