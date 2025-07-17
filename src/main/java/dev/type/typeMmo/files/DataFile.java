package dev.type.typeMmo.files;

import dev.type.typeMmo.TypeMmo;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.configuration.file.FileConfiguration;

import java.io.File;
import java.io.IOException;

public class DataFile {

    private static TypeMmo plugin;
    private static File file;
    private static YamlConfiguration datafile;



    public DataFile(TypeMmo plugin) {
        this.plugin = plugin;
        file = new File(plugin.getDataFolder(), "data.yml");
        if (!file.exists()) {
            try {
                file.createNewFile();
                System.out.println("Successfully created new data file.");
            } catch (IOException e) {
                System.out.println("An error occurred creating new data file.");
                e.printStackTrace();
            }
        }
        datafile = YamlConfiguration.loadConfiguration(file);
        save();

    }



    public static void save() {
        try {
            datafile.save(file);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("An error occurred saving the data file.");
        }
    }

    public static FileConfiguration getDataConfig() {
        return datafile;
    }

    public static void reload() {
        datafile = YamlConfiguration.loadConfiguration(file);
    }


}
