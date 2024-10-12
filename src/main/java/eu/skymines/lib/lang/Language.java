package eu.skymines.lib.lang;

import eu.skymines.lib.config.ConfigHandler;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

public class Language extends ConfigHandler {

    private final Map<String, String> variables = new HashMap<>();
    private final Map<String, Component> messages = new HashMap<>();
    private String prefix;

    public Language(@NotNull JavaPlugin plugin) {
        super(plugin, "language");
    }

    @Override
    public void onLoad(FileConfiguration config) {

        this.messages.clear();
        this.prefix = config.getString("prefix");

        ConfigurationSection variables = config.getConfigurationSection("var");
        ConfigurationSection messages = config.getConfigurationSection("messages");

        if (variables != null) {
            for (String key : variables.getKeys(false)) {
                String value = variables.getString(key);
                if (value != null) {
                    this.variables.put(key, value);
                }
            }
        }

        if (messages != null) {
            for (String key : messages.getKeys(false)) {
                String message = messages.getString(key);
                if (message != null) {

                    if (message.contains("<prefix>")) {
                        if (prefix != null) {
                            message = message.replace("<prefix>", prefix);
                        }
                    }

                    AtomicReference<String> result = new AtomicReference<>(message);
                    this.variables.forEach((current, value) -> {
                        String placeholder = "<var:" + current + ">";
                        if (result.get().contains(placeholder)) {
                            result.set(result.get().replace(placeholder, value));
                        }
                    });

                    MiniMessage miniMessage = MiniMessage.miniMessage();
                    this.messages.put(key, miniMessage.deserialize(result.get()));
                }
            }
        }

        this.variables.clear();

    }

    @Override
    public void onPreSave(FileConfiguration config) {}

    public Component get(@Nonnull String key) {
        Component message = this.messages.get(key);
        if (message == null) {
            return Component.text(key);
        }
        return message;
    }



}
