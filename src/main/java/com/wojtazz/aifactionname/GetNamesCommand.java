package com.wojtazz.aifactionname;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.io.IOException;

import static com.wojtazz.aifactionname.utils.Utils.callToAI;

public class GetNamesCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        try {
            sender.sendMessage("Trwa generowanie nazwy...");

            String randomFactionName = getRandomFactionName();

            sender.sendMessage("Przykładowa nazwa gildii: " + randomFactionName);
        } catch (IOException e) {
            sender.sendMessage("ERROR: " + e.getMessage());
        }

        return true;
    }

    private String getRandomFactionName() throws IOException {
        return callToAI("podaj humorystyczną nazwę dla gildii w Minecraft składającą się z maksymalnie dwóch wyrazów");
    }
}
