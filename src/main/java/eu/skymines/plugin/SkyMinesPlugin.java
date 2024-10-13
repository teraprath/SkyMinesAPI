package eu.skymines.plugin;

import eu.skymines.lib.gui.GuiListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class SkyMinesPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        this.getServer().getPluginManager().registerEvents(new GuiListener(), this);
    }

}
