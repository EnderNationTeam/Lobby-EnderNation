package de.mxscha.endernationlobby.utils.manager.inventory;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

/**
 * @author KeksGauner
 */
public class InventoryBuilder {

    private String title;
    private Integer size;
    private InventoryType type;
    private Material fillMaterial;
    private String fillMaterialName;

    public String getTitle() {
        return title;
    }

    public InventoryBuilder setTitle(String title) {
        this.title = ChatColor.translateAlternateColorCodes('&', title);
        return this;
    }

    public Integer getSize() {
        return size;
    }

    public InventoryBuilder setSize(Integer size) {
        this.size = size;
        return this;
    }

    public InventoryType getType() {
        return type;
    }

    public InventoryBuilder setType(InventoryType type) {
        this.type = type;
        return this;
    }

    public Material getFillMaterial() {
        return fillMaterial;
    }

    public InventoryBuilder setFillMaterial(Material material) {
        this.fillMaterial = material;
        return this;
    }

    public InventoryBuilder setFillMaterial(Material material, String itemName) {
        this.fillMaterial = material;
        this.fillMaterialName = ChatColor.translateAlternateColorCodes('&',itemName);
        return this;
    }

    public Inventory build() {
        /**
         * This is only a Dummy
         */
        Inventory inventory = Bukkit.createInventory(null,9);

        if(size != null)
            inventory = Bukkit.createInventory(null, size);
        if(size != null && title != null)
            inventory = Bukkit.createInventory(null, size, title);
        if(type != null)
            inventory = Bukkit.createInventory(null, type);
        if(type != null && title != null)
            inventory = Bukkit.createInventory(null, type, title);

        if(fillMaterial != null) {
            for (int i = 0; i < inventory.getSize(); i++) {
                if (inventory.getItem(i) == null) {
                    ItemStack item = new ItemStack(fillMaterial);
                    ItemMeta meta = item.getItemMeta();

                    if(fillMaterialName != null)
                        meta.setDisplayName(fillMaterialName);
                    else
                        meta.setDisplayName("§1");

                    item.setItemMeta(meta);
                    inventory.setItem(i, item);
                }
            }
        }

        return inventory;
    }
}
