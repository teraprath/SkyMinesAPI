package eu.skymines.plugin;

import eu.skymines.lib.menu.MenuAPI;
import org.bukkit.plugin.java.JavaPlugin;

public final class SkyMinesPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        new MenuAPI(this).init();
    }

}
