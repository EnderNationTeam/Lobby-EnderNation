package de.mxscha.endernationlobby.utils.manager.inventory;

import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

/**
 * @author KeksGauner
 */
public class MultiInventoryCalculator {

    private final Inventory inventory;
    private final ArrayList<ItemStack> itemStacks;
    private int current;
    private int page;


    public MultiInventoryCalculator(int size, String title, int startPoint, ArrayList<ItemStack> itemStacks) {
        this.inventory = new InventoryBuilder().setSize(size).setTitle(title).setFillMaterial(Material.GRAY_STAINED_GLASS_PANE, "§r").build();
        this.itemStacks = itemStacks;
        this.current = startPoint;
        this.page = 0;
    }

    public void show(int page) {
        this.page = page;
        setNoPage();
        // If this not the first page. give a previous back
        if(page != 0)
            setPreviousItem();

        for(ItemStack itemStack : itemStacks)
            // set the item to the inventory
            addItem(itemStack);
    }

    private void addItem(ItemStack itemStack) {

        // Check if it able to add the item
        if(isFull()) {
            return;
        }

        setItem(current, itemStack);
        this.current++;

        checkNextItem();
    }

    private void setItem(int i, ItemStack itemStack) {
        this.inventory.setItem(i, itemStack);
    }

    public Inventory build() {
        return this.inventory;
    }

    private boolean isFull() {
        return this.current > this.inventory.getSize() - 2;
    }

    private void setNoPage() {
        // set no page on both sites
        setItem(0, MultiInventoryItems.getFirstNoPageSite());
        setItem(this.inventory.getSize() - 1, MultiInventoryItems.getLastNoPageSite());
    }

    private void checkNextItem() {
        // Check is it the last item
        if(isFull()) {
            setItem(current, MultiInventoryItems.setPage(page + 1, MultiInventoryItems.getNextPage()));
        }
    }

    private void setPreviousItem() {
        // set fist item
        setItem(0, MultiInventoryItems.setPage(page - 1, MultiInventoryItems.getPreviousPage()));
    }
}
