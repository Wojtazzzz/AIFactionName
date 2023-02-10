package com.wojtazz.aifactionname;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.io.IOException;

import static com.wojtazz.aifactionname.utils.OpenAI.callToAI;

public class GuiListener implements Listener {
    private String AIModel;
    private String apiKey;
    private int maxWordsCount;
    private String loadingMessage;
    private String successMessage;
    private String errorMessage;

    public GuiListener(Config config) {
        this.AIModel = config.getAIModel();
        this.apiKey = config.getApiKey();
        this.maxWordsCount = config.getMaxWordsCount();
        this.loadingMessage = config.getLoadingMessage();
        this.successMessage = config.getSuccessMessage();
        this.errorMessage = config.getErrorMessage();
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        if (!event.getView().getTitle().equals("Wybierz jezyk")) {
            return;
        }

        if (event.getCurrentItem() == null) {
            return;
        }

        event.setCancelled(true);

        Player player = (Player) event.getWhoClicked();

        player.closeInventory();

        player.sendMessage(this.loadingMessage);
        String language = detectLanguage(event.getRawSlot());

        try {
            String randomFactionName = getRandomFactionName(language);

            player.sendMessage(this.successMessage + randomFactionName);
        } catch (IOException e) {
            player.sendMessage(this.errorMessage + e.getMessage());
        }
    }

    private String detectLanguage(int slot) {
        switch (slot) {
            default:
            case 0:
                return "pl";
            case 1:
                return "en";
        }
    }

    private String getPrompt(String language) {
        switch (language) {
            default:
            case "pl":
                return String.format("Podaj humorystyczna nazwe gildii w minecraft skladajaca sie z maksymalnie %d wyrazow", this.maxWordsCount);

            case "en":
                return String.format("Give me funny Minecraft faction name which contain max %d words", this.maxWordsCount);
        }
    }

    private String getRandomFactionName(String language) throws IOException {
        String message = getPrompt(language);

        return callToAI(this.apiKey, this.AIModel, message);
    }
}
