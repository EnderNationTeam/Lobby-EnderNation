package de.mxscha.endernationlobby.utils.manager.inventory;

import de.mxscha.endernationlobby.utils.manager.items.ItemCreator;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

/**
 * @author KeksGauner
 */
public class InventoryCalculator {

    private final Inventory inventory;
    private int current;

    public InventoryCalculator(int size, String title) {
        this.inventory = new InventoryBuilder().setSize(size).setTitle(title).setFillMaterial(Material.GRAY_STAINED_GLASS_PANE, "§r").build();
        this.current = 0;
    }

    public InventoryCalculator(int size, String title, int startPoint) {
        this.inventory = new InventoryBuilder().setSize(size).setTitle(title).setFillMaterial(Material.GRAY_STAINED_GLASS_PANE, "§r").build();
        this.current = startPoint;
    }

    public void addItem(ItemStack itemStack) {
        this.current++;
        if(current > inventory.getSize() - 1) {
            this.current++;
            itemStack = new ItemCreator(Material.RED_STAINED_GLASS_PANE)
                    .setName("§3" + "Next Page")
                    .toItemStack();
        }
        inventory.setItem(current, itemStack);
    }

    public Inventory build() {
        return inventory;
    }
}
