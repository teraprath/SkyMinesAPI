package net.blockyislands.plugin;

import net.blockyislands.api.BlockyAPI;
import net.blockyislands.plugin.listener.LoginListener;
import net.blockyislands.plugin.manager.ConfigManager;
import net.blockyislands.plugin.utils.SQLUtils;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class BlockyPlugin extends JavaPlugin {

    private BlockyAPI api;
    private final ConfigManager configManager = new ConfigManager(this);

    @Override
    public void onEnable() {

        this.configManager.init();

        api = new BlockyAPI(this);
        this.api.init();

        new SQLUtils(api.getSQLAdapter()).createTables();

        registerEvents();
    }

    public BlockyAPI getAPI() {
        return this.api;
    }

    private void registerEvents() {
        final PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new LoginListener(this), this);
    }

}
