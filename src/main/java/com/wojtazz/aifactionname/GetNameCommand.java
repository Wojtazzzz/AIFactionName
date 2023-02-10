package com.wojtazz.aifactionname;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static com.wojtazz.aifactionname.utils.CreateItemStack.createItemStack;
import static com.wojtazz.aifactionname.utils.CreateMessage.createMessage;

public class GetNameCommand implements CommandExecutor {
    private final String guiName;
    private final String cooldownMessage;
    private final Cache<UUID, Long> cooldown = CacheBuilder.newBuilder().expireAfterWrite(5, TimeUnit.SECONDS).build();

    public GetNameCommand(Config config) {
        this.guiName = config.getGuiName();
        this.cooldownMessage = config.getCooldownMessage();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            return false;
        }

        Player player = (Player) sender;

        if (cooldown.asMap().containsKey(player.getUniqueId())) {
            player.sendMessage(createMessage(this.cooldownMessage));
            return true;
        }

        Inventory inventory = Bukkit.createInventory(player, 9,  createMessage(this.guiName));
        inventory.setItem(0, createItemStack(Material.DIAMOND, "Polski"));
        inventory.setItem(1, createItemStack(Material.EMERALD, "English"));

        player.openInventory(inventory);

        cooldown.put(player.getUniqueId(), System.currentTimeMillis() + 5000);

        return true;
    }


}
