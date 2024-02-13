package net.blockyislands.plugin.manager;

import net.blockyislands.lib.config.ConfigHandler;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public class ConfigManager extends ConfigHandler {

    public ConfigManager(@NotNull JavaPlugin plugin) {
        super(plugin, "config");
    }

    @Override
    public void onLoad(FileConfiguration config) {

    }

    @Override
    public void onPreSave(FileConfiguration config) {

    }
}
