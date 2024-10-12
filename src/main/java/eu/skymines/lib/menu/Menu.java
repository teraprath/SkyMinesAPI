package eu.skymines.lib.menu;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.Map;

public abstract class Menu implements InventoryHolder {

    private Inventory inventory;
    private Component title;
    private final int size;
    private final Map<Integer, MenuIcon> items = new HashMap<>();

    public Menu(@Nonnull Component title, @Nonnegative int rows) {
        this.title = title;
        this.size = rows * 9;
        this.inventory = Bukkit.createInventory(this, this.size, title);
    }

    public void setTitle(@Nonnull Component title) {
        this.title = title;
        this.inventory = Bukkit.createInventory(this, this.size, title);
    }

    public void setItem(@Nonnegative int slot, @Nonnull MenuIcon icon) {
        this.items.put(slot, icon);
    }

    public void update() {
        for (int slot = 0; slot < inventory.getSize(); slot++) {
            if (this.items.get(slot) != null) {
                this.inventory.setItem(slot, this.items.get(slot).getItemStack());
            }
        }
    }

    public void open(@Nonnull Player player) {
        player.openInventory(this.inventory);
    }

    public abstract void onOpen(InventoryOpenEvent event);

    public abstract void onClose(InventoryCloseEvent event);

    public void handleClick(InventoryClickEvent event) {

        int slot = event.getRawSlot();

        if (this.items.get(slot) == null) {
            event.setCancelled(true);
            return;
        }

        if (this.items.get(slot).isFixed()) {
            event.setCancelled(true);
        }

        if (this.items.get(slot).getAction() != null) {
            this.items.get(slot).getAction().accept(event);
        }
    }

    @Override
    public Inventory getInventory() {
        return this.inventory;
    }
}

