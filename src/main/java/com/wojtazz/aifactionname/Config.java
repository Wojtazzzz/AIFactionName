package com.wojtazz.aifactionname;

public class Config {
    private final String AIModel;
    private final String apiKey;
    private final String guiName;
    private final int maxWordsCount;
    private final String loadingMessage;
    private final String successMessage;
    private final String errorMessage;

    public Config(Main main) {
        this.AIModel = main.getConfig().getString("model", "text-davinci-003");
        this.apiKey = main.getConfig().getString("api_key");
        this.guiName = main.getConfig().getString("gui_name");
        this.maxWordsCount = main.getConfig().getInt("max_words_count", 2);
        this.loadingMessage = main.getConfig().getString("message.loading", "Loading...");
        this.successMessage = main.getConfig().getString("message.success", "Generated name:");
        this.errorMessage = main.getConfig().getString("message.error", "Error:");
    }

    public String getAIModel() {
        return this.AIModel;
    }
    public String getApiKey() {
        return this.apiKey;
    }
    public String getGuiName() {
        return this.guiName;
    }

    public int getMaxWordsCount() {
        return this.maxWordsCount;
    }

    public String getLoadingMessage() {
        return this.loadingMessage;
    }
    public String getSuccessMessage() {
        return this.successMessage;
    }
    public String getErrorMessage() {
        return this.errorMessage;
    }
}
