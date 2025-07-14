package dev.type.typeMmo.files;

import dev.type.typeMmo.TypeMmo;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class DataFile {

    private static TypeMmo plugin;
    private static File file;
    private static YamlConfiguration config;

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

        config = YamlConfiguration.loadConfiguration(file);
        save();

    }



    public static void save() {
        try {
            config.save(file);
            System.out.println("Successfully saved data file.");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("An error occurred saving the data file.");
        }
    }


}
