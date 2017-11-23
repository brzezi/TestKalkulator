package com.example.currency.nbp;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Service
public class NBPService {
    private String rateTableUrl = "http://api.nbp.pl/api/exchangerates/tables/a/?format=json";

    public List<Rate> listRates() throws IOException {
        String str = new Scanner(new URL(rateTableUrl).openStream(), "UTF-8").useDelimiter("\\A").next();

        ObjectMapper mapper = new ObjectMapper();
        JsonNode json = mapper.readTree(str).get(0);

        JsonNode ratesJson = json.get("rates");

        List<Rate> rates = new ArrayList<>();

        ratesJson.elements().forEachRemaining( rateJson -> {
            String currency = rateJson.get("currency").textValue();
            String code = rateJson.get("code").textValue();
            double rate = rateJson.get("mid").doubleValue();
            rates.add(new Rate(currency, code, rate));
        });

        return rates;
    }
}
