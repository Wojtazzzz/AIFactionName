package com.wojtazz.aifactionname.utils;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class CreateItemStack {
    public static ItemStack createItemStack(Material material, String itemName) {
        ItemStack itemStack = new ItemStack(material);
        ItemMeta meta = itemStack.getItemMeta();

        meta.setDisplayName(itemName);

        itemStack.setItemMeta(meta);

        return itemStack;
    }
}
