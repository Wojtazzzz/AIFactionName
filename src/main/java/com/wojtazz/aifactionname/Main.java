package com.wojtazz.aifactionname;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {
    private Config config;
    @Override
    public void onEnable() {
        setupConfig();

        getCommand("ainame").setExecutor(new GetNameCommand());
        Bukkit.getPluginManager().registerEvents(new GuiListener(this.config), this);
    }

    private void setupConfig() {
        getConfig().options().copyDefaults();
        saveDefaultConfig();

        this.config = new Config(this);
    }
}
