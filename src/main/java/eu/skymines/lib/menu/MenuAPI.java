package eu.skymines.lib.menu;

import eu.skymines.lib.menu.MenuListener;
import org.bukkit.plugin.java.JavaPlugin;

public class MenuAPI {

    private final JavaPlugin plugin;


    public MenuAPI(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public void init() {
        plugin.getServer().getPluginManager().registerEvents(new MenuListener(), this.plugin);
    }

}
