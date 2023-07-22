package com.example.currencyconverter.ConnectionApi;

import com.example.currencyconverter.ratescurrency.Rates;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ApiService {
    private static final String API_URL = "https://economia.awesomeapi.com.br/json/last/USD-BRL,EUR-BRL,GBP-BRL,ARS-BRL,CLP-BRL";

    public Rates.rates getExchangeRates() throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API_URL))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            Gson gson = new Gson();
            JsonElement jsonElement = gson.fromJson(response.body(), JsonElement.class);
            JsonObject jsonObject = jsonElement.getAsJsonObject();

            if (jsonObject.has("USDBRL") && jsonObject.has("EURBRL") &&
                    jsonObject.has("GBPBRL") && jsonObject.has("ARSBRL") &&
                    jsonObject.has("CLPBRL")) {
                double usd = jsonObject.get("USDBRL").getAsJsonObject().get("high").getAsDouble();
                double eur = jsonObject.get("EURBRL").getAsJsonObject().get("high").getAsDouble();
                double gbp = jsonObject.get("GBPBRL").getAsJsonObject().get("high").getAsDouble();
                double ars = jsonObject.get("ARSBRL").getAsJsonObject().get("high").getAsDouble();
                double clp = jsonObject.get("CLPBRL").getAsJsonObject().get("high").getAsDouble();

                return new Rates.rates(usd, eur, gbp, ars, clp);
            }
        }

        return null;
    }
}