package net.blockyislands.api.listener;

import net.blockyislands.api.BlockyPlugin;
import net.blockyislands.api.utils.SQLUtils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

import javax.annotation.Nonnull;

public class LoginListener implements Listener {

    private final BlockyPlugin plugin;

    public LoginListener(@Nonnull BlockyPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onLogin(PlayerLoginEvent e) {
        if (e.getResult() == PlayerLoginEvent.Result.ALLOWED) {
            new SQLUtils(plugin.getAPI().getSQLAdapter()).register(e.getPlayer().getUniqueId());
        }
    }

}
