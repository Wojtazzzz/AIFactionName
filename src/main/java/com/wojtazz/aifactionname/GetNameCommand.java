package com.wojtazz.aifactionname;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.io.IOException;

import static com.wojtazz.aifactionname.utils.OpenAI.callToAI;

public class GetNameCommand implements CommandExecutor {
    private String AIModel;
    private String apiKey;
    private int maxWordsCount;
    private String loadingMessage;
    private String successMessage;
    private String errorMessage;

    public GetNameCommand(Config config) {
        this.AIModel = config.getAIModel();
        this.apiKey = config.getApiKey();
        this.maxWordsCount = config.getMaxWordsCount();
        this.loadingMessage = config.getLoadingMessage();
        this.successMessage = config.getSuccessMessage();
        this.errorMessage = config.getErrorMessage();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        try {
            sender.sendMessage(this.loadingMessage);

            String randomFactionName = getRandomFactionName();

            sender.sendMessage(this.successMessage + randomFactionName);
        } catch (IOException e) {
            sender.sendMessage(this.errorMessage + e.getMessage());
        }

        return true;
    }

    private String getRandomFactionName() throws IOException {
        String message = String.format("Podaj humorystyczna nazwe gildii w minecraft skladajaca sie z %d wyrazow", this.maxWordsCount);

        return callToAI(this.apiKey, this.AIModel, message);
    }
}
