package net.blockyislands.plugin.utils;

import net.blockyislands.api.BlockyAPI;
import net.blockyislands.lib.sql.SQLAdapter;
import net.blockyislands.lib.sql.SQLColumn;
import net.blockyislands.lib.sql.SQLDataType;
import net.blockyislands.lib.sql.SQLTable;
import org.bukkit.scheduler.BukkitRunnable;

import javax.annotation.Nonnull;
import java.util.UUID;

public class SQLUtils {

    private final SQLAdapter adapter;

    public SQLUtils(@Nonnull SQLAdapter adapter) {
        this.adapter = adapter;
    }

    public void createTables() {

        SQLTable players = new SQLTable("players");

        SQLColumn uuid = new SQLColumn("uuid", SQLDataType.VARCHAR).setParameter(36).setNotNull(true);
        SQLColumn balance = new SQLColumn("balance", SQLDataType.INT).setDefaultValue(0).setNotNull(true);
        SQLColumn kills = new SQLColumn("kills", SQLDataType.INT).setDefaultValue(0).setNotNull(true);
        SQLColumn deaths = new SQLColumn("deaths", SQLDataType.INT).setDefaultValue(0).setNotNull(true);

        players.addColumn(uuid).addColumn(balance).addColumn(kills).addColumn(deaths).setPrimary("uuid");

        new BukkitRunnable() {
            @Override
            public void run() {
                adapter.connect();
                adapter.addTable(players);
                adapter.disconnect();
            }
        }.runTaskAsynchronously(BlockyAPI.getPlugin());
    }

    public void register(@Nonnull UUID uuid) {
        new BukkitRunnable() {
            @Override
            public void run() {
                adapter.connect();
                if (!adapter.exists("players", uuid)) { adapter.insert("players", uuid); }
                adapter.disconnect();
            }
        }.runTaskAsynchronously(BlockyAPI.getPlugin());
    }
}
