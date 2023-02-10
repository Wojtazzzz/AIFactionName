package com.wojtazz.aifactionname;

public class Config {
    private String AIModel;
    private int maxWordsCount;
    private String loadingMessage;
    private String successMessage;
    private String errorMessage;

    public Config(Main main) {
        this.AIModel = main.getConfig().getString("model", "text-davinci-003");
        this.maxWordsCount = main.getConfig().getInt("max_words_count", 2);
        this.loadingMessage = main.getConfig().getString("message.loading", "Trwa generowanie nazwy...");
        this.successMessage = main.getConfig().getString("message.success", "Przykładowa nazwa gildii: ");
        this.errorMessage = main.getConfig().getString("message.error", "Błąd: ");
    }

    public String getAIModel() {
        return this.AIModel;
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
