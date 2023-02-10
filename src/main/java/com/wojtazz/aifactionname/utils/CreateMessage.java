package com.wojtazz.aifactionname.utils;

import org.bukkit.ChatColor;

public class CreateMessage {
    public static String createMessage(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }
}
