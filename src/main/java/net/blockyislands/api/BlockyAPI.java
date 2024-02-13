package net.blockyislands.api;

import net.blockyislands.lib.sql.SQLAdapter;
import net.blockyislands.lib.sql.SQLAuth;
import net.blockyislands.lib.sql.SQLType;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.UUID;

public class BlockyAPI implements IBlockyAPI {

    private static JavaPlugin plugin;
    private SQLAdapter sqlAdapter;

    public BlockyAPI(@NotNull JavaPlugin plugin) {
        plugin = plugin;
    }

    public IBlockyAPI init() {

        SQLAuth auth;
        File file;

        if (!plugin.getName().equals("BlockyAPI")) {

            Plugin apiPlugin = Bukkit.getServer().getPluginManager().getPlugin("BlockyAPI");
            apiPlugin.getLogger().info("Plugin initialized: " + plugin.getName() + " version " + plugin.getDescription().getVersion() + " by " + plugin.getDescription().getAuthors().toString().replace("[", "").replace("]", ""));
            file = new File(apiPlugin.getDataFolder(), "config.yml");

        } else {

            file = new File(plugin.getDataFolder(), "config.yml");

        }

        FileConfiguration configuration = YamlConfiguration.loadConfiguration(file);
        String host = configuration.getString("database.host");
        int port = configuration.getInt("database.port");
        String database = configuration.getString("database.database");
        String user = configuration.getString("database.user");
        String password = configuration.getString("database.password");
        auth = new SQLAuth(host, port, user, database, password);

        this.sqlAdapter = new SQLAdapter(plugin, SQLType.MYSQL, auth) {
            @Override
            public void onConnect() {}

            @Override
            public void onDisconnect() {}
        };

        return this;
    }

    public static JavaPlugin getPlugin() {
        return plugin;
    }


    @Override
    public void setBalance(@NotNull UUID uuid, int amount) {
        new BukkitRunnable() {
            @Override
            public void run() {

            }
        }.runTaskAsynchronously(plugin);
    }

    @Override
    public int getBalance(@NotNull UUID uuid) {
        return 0;
    }

    @Override
    public void setKills(@NotNull UUID uuid, int amount) {

    }

    @Override
    public int getKills(@NotNull UUID uuid) {
        return 0;
    }

    @Override
    public void setDeaths(@NotNull UUID uuid, int amount) {

    }

    @Override
    public int getDeaths(@NotNull UUID uuid) {
        return 0;
    }

    public SQLAdapter getSQLAdapter() {
        return sqlAdapter;
    }
}
