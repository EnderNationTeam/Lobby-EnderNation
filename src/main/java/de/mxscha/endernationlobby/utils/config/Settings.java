package de.mxscha.endernationlobby.utils.config;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class Settings {

    public static File file = new File("plugins//Lobby", "settings.yml");
    public static FileConfiguration configuration = YamlConfiguration.loadConfiguration(file);

    public static void setDefaults() {
        configuration.set("Prefix", "&a&lLobby &8| &f");
        save();
    }

    public static void save() {
        if (!file.exists()) {
            try {
                configuration.save(file);
                setDefaults();
                configuration.save(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                configuration.save(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
