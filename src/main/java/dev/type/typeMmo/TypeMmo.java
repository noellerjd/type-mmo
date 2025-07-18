package dev.type.typeMmo;

import dev.type.typeMmo.commands.TestCommand;
import dev.type.typeMmo.commands.configCommand;
import dev.type.typeMmo.commands.StatsCommand;
import dev.type.typeMmo.files.DataFile;
import dev.type.typeMmo.stats.Dexterity;
import dev.type.typeMmo.stats.PlayerJoin;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class TypeMmo extends JavaPlugin {

    private static TypeMmo plugin;

    @Override
    public void onEnable() {
        // Plugin startup logic
//        Init Global Plugin Reference
        TypeMmo.plugin = this;

//        Create File Managers
        saveDefaultConfig();
        new DataFile(this);

//        Register Commands
        getCommand("test").setExecutor(new TestCommand());
        getCommand("mmoconfig").setExecutor((new configCommand(this)));
        getCommand("stats").setExecutor(new StatsCommand());

//        Register Listeners
        Bukkit.getPluginManager().registerEvents(new PlayerJoin(this), this);
        Bukkit.getPluginManager().registerEvents(new Dexterity(this), this);


    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic



    }
}

