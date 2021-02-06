package ru.netology;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class App
{
    public static void main( String[] args ) throws IOException
    {
        CloseableHttpClient httpClient = HttpClientBuilder.create()
                .setDefaultRequestConfig(
                        RequestConfig.custom()
                        .setConnectTimeout(5000)
                        .setSocketTimeout(30000)
                        .setRedirectsEnabled(false)
                        .build()
                ).build();

        HttpGet request = new HttpGet("https://cat-fact.herokuapp.com/facts");
        CloseableHttpResponse response = httpClient.execute(request);

        String body = new String(response.getEntity().getContent().readAllBytes());
        Gson gson = new Gson();

        Type listType = new TypeToken<List<CatFacts>>(){}.getType();
        List<CatFacts> facts = gson.fromJson(body, listType);

        System.out.println("Before filtering and sorting");
        facts.forEach(System.out::println);

        System.out.println("\nAfter");
        facts.stream()
                .filter(fact -> fact.isUsed())
                .sorted((a, b) -> a.getText().length() < b.getText().length() ? 1 : -1)
                .forEach(System.out::println);
    }
}
