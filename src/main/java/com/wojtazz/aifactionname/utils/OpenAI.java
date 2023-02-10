package com.wojtazz.aifactionname.utils;

import org.apache.http.client.HttpResponseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

public class OpenAI {
    private static final String API_KEY = "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";
    private static final String API_URL = "https://api.openai.com/v1/completions";
    public static String callToAI(String model, String prompt) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();

        HttpPost httpPost = new HttpPost(API_URL);

        httpPost.addHeader("Content-Type", "application/json");
        httpPost.addHeader("Authorization", "Bearer " + API_KEY);

        JSONObject requestBody = new JSONObject();
        requestBody.put("model", model);
        requestBody.put("prompt", prompt);

        StringEntity entity = new StringEntity(requestBody.toString());
        httpPost.setEntity(entity);

        CloseableHttpResponse response = httpClient.execute(httpPost);

        if (response.getStatusLine().getStatusCode() != 200) {
            throw new HttpResponseException(response.getStatusLine().getStatusCode(), "API request failed");
        }

        String responseString = EntityUtils.toString(response.getEntity());
        JSONObject responseJson = new JSONObject(responseString);

        JSONArray choices = responseJson.getJSONArray("choices");
        JSONObject firstChoice = choices.getJSONObject(0);

        String AITextResponse = firstChoice.getString("text");

        return AITextResponse;
    }
}
