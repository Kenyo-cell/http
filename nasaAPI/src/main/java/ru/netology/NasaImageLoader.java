package ru.netology;

import com.google.gson.Gson;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.*;

public class NasaImageLoader {
    private String key = "i2GNAk6kinYtnKpc5rKiPCHOfimfJ89EUe8aj3vx";
    private String URL = "https://api.nasa.gov/planetary/apod?api_key=" + key;
    private String path = "D:\\";
    private CloseableHttpClient httpClient;
    private HttpGet request;
    private CloseableHttpResponse response;

    public NasaImageLoader() {}

    public void start() throws IOException {
        httpClient = HttpClientBuilder.create()
                .setDefaultRequestConfig(RequestConfig.custom()
                        .setSocketTimeout(30000)
                        .setRedirectsEnabled(false)
                        .setConnectTimeout(5000)
                        .build())
                .build();

        executeRequest(URL);

        Gson gson = new Gson();
        NasaImage image = gson.fromJson(
                new String(response.getEntity().getContent().readAllBytes()),
                NasaImage.class
        );

        executeRequest(image.getHdUrl());

        String name = image.getHdUrl().split("/")[image.getHdUrl().split("/").length - 1];
        writeImage(name);
    }

    private void executeRequest(String url) throws IOException {
        request = new HttpGet(url);
        response = httpClient.execute(request);
    }

    private void writeImage(String name) throws IOException {
        byte[] imageBytes = response.getEntity().getContent().readAllBytes();

        FileOutputStream out = new FileOutputStream(new File(path + name));
        out.write(imageBytes);
        out.flush();
    }
}
