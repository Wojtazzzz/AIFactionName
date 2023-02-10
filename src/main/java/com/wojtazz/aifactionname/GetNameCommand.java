package com.wojtazz.aifactionname;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import static com.wojtazz.aifactionname.utils.CreateItemStack.createItemStack;
import static com.wojtazz.aifactionname.utils.CreateMessage.createMessage;

public class GetNameCommand implements CommandExecutor {
    private final String guiName;

    public GetNameCommand(Config config) {
        this.guiName = config.getGuiName();
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            return false;
        }

        Player player = (Player) sender;

        Inventory inventory = Bukkit.createInventory(player, 9,  createMessage(this.guiName));
        inventory.setItem(0, createItemStack(Material.DIAMOND, "Polski"));
        inventory.setItem(1, createItemStack(Material.EMERALD, "English"));

        player.openInventory(inventory);

        return true;
    }


}
