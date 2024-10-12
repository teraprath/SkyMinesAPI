package eu.skymines.lib.lang;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;

import javax.annotation.Nonnull;

public class Lang {

    public static Component get(@Nonnull String key) {
        return MiniMessage.miniMessage().deserialize("<lang:" + key + ">");
    }

}
