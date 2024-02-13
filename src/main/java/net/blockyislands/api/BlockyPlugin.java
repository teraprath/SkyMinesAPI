package net.blockyislands.api;

import net.blockyislands.api.listener.LoginListener;
import net.blockyislands.api.utils.SQLUtils;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class BlockyPlugin extends JavaPlugin {

    private BlockyAPI api;

    @Override
    public void onEnable() {
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
