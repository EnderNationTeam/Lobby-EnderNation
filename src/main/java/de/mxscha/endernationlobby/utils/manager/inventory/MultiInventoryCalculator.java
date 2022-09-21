package de.mxscha.endernationlobby.utils.manager.inventory;

import de.mxscha.endernationlobby.utils.manager.items.ItemCreator;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

/**
 * @author KeksGauner
 */
public class MultiInventoryCalculator {

    private final Inventory inventory;
    private int current;

    public MultiInventoryCalculator(int size, String title) {
        this.inventory = new InventoryBuilder().setSize(size).setTitle(title).setFillMaterial(Material.GRAY_STAINED_GLASS_PANE, "§r").build();
        this.current = 0;
    }

    public MultiInventoryCalculator(int size, String title, int startPoint) {
        this.inventory = new InventoryBuilder().setSize(size).setTitle(title).setFillMaterial(Material.GRAY_STAINED_GLASS_PANE, "§r").build();
        this.current = startPoint;
    }

    public void addItem(ItemStack itemStack) {

        // Check if it able to add the item
        if(isFull()) {
            return;
        }

        setItem(current, itemStack);
        this.current++;

        checkLastItem();
    }

    public void setItem(int i, ItemStack itemStack) {
        this.inventory.setItem(i, itemStack);
    }

    public Inventory build() {
        return this.inventory;
    }

    private boolean isFull() {
        return this.current > this.inventory.getSize() - 2;
    }

    private void checkLastItem() {
        // Check is it the last item
        if(isFull()) {
            ItemStack itemStack = new ItemCreator(Material.RED_STAINED_GLASS_PANE)
                    .setName("§3" + "Next Page")
                    .toItemStack();
            setItem(current, itemStack);
        }
    }
}
