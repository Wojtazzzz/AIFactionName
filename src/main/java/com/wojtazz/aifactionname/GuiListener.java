package com.wojtazz.aifactionname;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.io.IOException;

import static com.wojtazz.aifactionname.utils.OpenAI.callToAI;
import static com.wojtazz.aifactionname.utils.CreateMessage.createMessage;

public class GuiListener implements Listener {
    private final String AIModel;
    private final String apiKey;
    private final String guiName;
    private final int maxWordsCount;
    private final String loadingMessage;
    private final String successMessage;
    private final String errorMessage;

    public GuiListener(Config config) {
        this.AIModel = config.getAIModel();
        this.apiKey = config.getApiKey();
        this.guiName = config.getGuiName();
        this.maxWordsCount = config.getMaxWordsCount();
        this.loadingMessage = config.getLoadingMessage();
        this.successMessage = config.getSuccessMessage();
        this.errorMessage = config.getErrorMessage();
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        if (!event.getView().getTitle().equals(createMessage(this.guiName))) {
            return;
        }

        if (event.getCurrentItem() == null) {
            return;
        }

        event.setCancelled(true);

        Player player = (Player) event.getWhoClicked();

        player.closeInventory();

        player.sendMessage(createMessage(this.loadingMessage));
        String language = detectLanguage(event.getRawSlot());

        try {
            String randomFactionName = getRandomFactionName(language);

            player.sendMessage(createMessage(this.successMessage + " " + randomFactionName.trim()));
        } catch (IOException e) {
            player.sendMessage(createMessage(this.errorMessage + " " + e.getMessage()));
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
                return String.format("Podaj humorystyczna nazwe gildii w minecraft w jezyku polskim skladajaca sie z maksymalnie %d wyrazow, bez cudzyslowia", this.maxWordsCount);

            case "en":
                return String.format("Give me funny Minecraft faction name which contain max %d words, without quotation marks", this.maxWordsCount);
        }
    }

    private String getRandomFactionName(String language) throws IOException {
        String prompt = getPrompt(language);

        return callToAI(this.apiKey, this.AIModel, prompt);
    }
}
