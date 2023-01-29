package com.wojtazz.aifactionname;

import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        getCommand("generatename").setExecutor(new GetNamesCommand());

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
