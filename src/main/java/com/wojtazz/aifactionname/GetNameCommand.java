package com.wojtazz.aifactionname;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import static com.wojtazz.aifactionname.utils.CreateItemStack.createItemStack;

public class GetNameCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            return false;
        }

        Player player = (Player) sender;

        Inventory inventory = Bukkit.createInventory(player, 9, "Wybierz jezyk");
        inventory.setItem(0, createItemStack(Material.DIAMOND, "Jezyk Polski"));
        inventory.setItem(1, createItemStack(Material.EMERALD, "Jezyk Angielski"));

        player.openInventory(inventory);

        return true;
    }


}
