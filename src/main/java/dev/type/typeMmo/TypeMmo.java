package dev.type.typeMmo;

import dev.type.typeMmo.commands.TestCommand;
import dev.type.typeMmo.commands.configCommand;
import dev.type.typeMmo.files.DataFile;
import dev.type.typeMmo.stats.Acrobatics;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
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
        getCommand("config").setExecutor((new configCommand(this)));

//        Register Listeners
        Bukkit.getPluginManager().registerEvents(new Acrobatics(this), this);


    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic



    }
}

