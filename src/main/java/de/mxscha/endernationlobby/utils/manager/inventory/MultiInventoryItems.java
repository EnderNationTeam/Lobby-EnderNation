package de.mxscha.endernationlobby.utils.manager.inventory;

import de.mxscha.endernationlobby.utils.manager.CloudNetManager;
import de.mxscha.endernationlobby.utils.manager.items.ItemCreator;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class MultiInventoryItems {

    public static ItemStack getFirstNoPageSite() {
        return new ItemCreator(Material.RED_STAINED_GLASS_PANE)
                .setName("§3" + "NoPage")
                .toItemStack();
    }

    public static ItemStack getLastNoPageSite() {
        return new ItemCreator(Material.RED_STAINED_GLASS_PANE)
                .setName("§3" + "NoPage")
                .toItemStack();
    }
    public static ItemStack getNextPage() {
        return new ItemCreator(Material.GREEN_STAINED_GLASS_PANE)
                .setName("§3" + "Nächste Seite")
                .toItemStack();
    }
    public static ItemStack getPreviousPage() {
        return new ItemCreator(Material.GREEN_STAINED_GLASS_PANE)
                .setName("§a" + "Vorherige Seite")
                .toItemStack();
    }

    public static ItemStack setPage(int page, ItemStack itemStack) {
        return CloudNetManager.addItemPersistentMeta("page", String.valueOf(page), itemStack);
    }
}
