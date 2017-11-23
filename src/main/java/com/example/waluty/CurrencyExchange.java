package com.example.waluty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.List;

@Controller
public class CurrencyExchange {
    @Autowired
    NBPService nbpService;

    @RequestMapping("/table")
    String table(Model model) throws IOException {
        List<Rate> rates = nbpService.listRates();

        model.addAttribute("rates", rates);

        return "table";
    }
}
