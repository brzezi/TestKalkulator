package com.example.currency.currencyconverterapi;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class CurrencyService {
    private final String ALL_CURRENCIES_URL = "https://free.currencyconverterapi.com/api/v4/currencies";
    private final String CURRENCY_RATE_BASE_URL = "https://free.currencyconverterapi.com/api/v4/convert?compact=ultra";

    public List<Currency> listAll() throws IOException {
        String data = Resources.toString(new URL(ALL_CURRENCIES_URL), Charsets.UTF_8);

        ObjectMapper mapper = new ObjectMapper();
        JsonNode json = mapper.readTree(data);

        JsonNode results = json.get("results");

        List<Currency> currencies = new ArrayList<>();

        results.elements().forEachRemaining(c -> {
            String name = c.get("currencyName").textValue();
            String id = c.get("id").textValue();

            currencies.add(new Currency(name, id));
        });

        currencies.sort( (c1, c2) -> c1.id.compareTo(c2.id));

        return currencies;
    }

    public double getRate(String from, String to) throws IOException {
        String code = from + "_" + to;
        String url = String.format("%s&q=%s", CURRENCY_RATE_BASE_URL, code);

        String data = Resources.toString(new URL(url), Charsets.UTF_8);

        ObjectMapper mapper = new ObjectMapper();
        JsonNode json = mapper.readTree(data);

        double rate = json.get(code).doubleValue();

        return rate;
    }
}
