package com.example.waluty;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Rate{
    public String currency;
    public String code;
    public double rate;

    public Rate(String currency, String code, double rate){
        this.currency = currency;
        this.code = code;
        this.rate = rate;
    }
}

@Controller
public class Kantor {
    private String rateTableUrl = "http://api.nbp.pl/api/exchangerates/tables/a/?format=json";

    @RequestMapping("/tabela")
    String table(Model model) throws IOException {
        String str = new Scanner(new URL(rateTableUrl).openStream(), "UTF-8").useDelimiter("\\A").next();

        ObjectMapper mapper = new ObjectMapper();
        JsonNode json = mapper.readTree(str).get(0);



        JsonNode date = json.get("effectiveDate");
        JsonNode ratesJson = json.get("rates");

        List<Rate> rates = new ArrayList<>();

        ratesJson.elements().forEachRemaining( rateJson -> {
            String currency = rateJson.get("currency").textValue();
            String code = rateJson.get("code").textValue();
            double rate = rateJson.get("mid").doubleValue();
            rates.add(new Rate(currency, code, rate));
        });


        model.addAttribute("rates", rates);

        return "tabela";
    }
}
