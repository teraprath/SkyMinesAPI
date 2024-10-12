package eu.skymines.lib.menu;

import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import java.util.function.Consumer;

public class MenuIcon {

    private final ItemStack itemStack;
    private boolean fixed = true;
    private Consumer<InventoryClickEvent> action;

    public MenuIcon(@Nonnull ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    public MenuIcon setFixed(boolean fixed) {
        this.fixed = fixed;
        return this;
    }

    public MenuIcon onClick(@Nonnull Consumer<InventoryClickEvent> onClick) {
        this.action = onClick;
        return this;
    }

    public boolean isFixed() {
        return this.fixed;
    }

    public ItemStack getItemStack() {
        return this.itemStack;
    }

    public Consumer<InventoryClickEvent> getAction() {
        return this.action;
    }
}
