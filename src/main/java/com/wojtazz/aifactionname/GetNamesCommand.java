package com.wojtazz.aifactionname;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

public class GetNamesCommand implements CommandExecutor {
    private static final String API_KEY = "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";
    private static final String API_URL = "https://api.openai.com/v1/completions";

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        try {
            fetchNames();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return true;
    }

    private void fetchNames() throws IOException, InterruptedException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(API_URL);

        httpPost.addHeader("Content-Type", "application/json");
        httpPost.addHeader("Authorization", "Bearer " + API_KEY);

        JSONObject requestBody = new JSONObject();
        requestBody.put("model", "text-davinci-003");
        requestBody.put("prompt", "podaj mi humorystyczną nazwę gildii w minecraft");

        StringEntity entity = new StringEntity(requestBody.toString());
        httpPost.setEntity(entity);

        CloseableHttpResponse response = httpClient.execute(httpPost);

        if (response.getStatusLine().getStatusCode() == 200) {
            String responseString = EntityUtils.toString(response.getEntity());
            JSONObject responseJson = new JSONObject(responseString);

            JSONArray choices = responseJson.getJSONArray("choices");
            JSONObject firstChoice = choices.getJSONObject(0);

            String factionName = firstChoice.getString("text");

            System.out.println("Generated text: " + factionName);
        } else {
            System.out.println("API request failed with status code: " + response.getStatusLine().getStatusCode());
        }

        httpClient.close();
    }
}
