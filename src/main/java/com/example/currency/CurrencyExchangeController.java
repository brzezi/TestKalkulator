package com.example.currency;

import com.example.currency.nbp.NBPService;
import com.example.currency.nbp.Rate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.List;

@Controller
public class CurrencyExchangeController {
    @Autowired
    NBPService nbpService;

    @RequestMapping("/table")
    String table(Model model) throws IOException {
        List<Rate> rates = nbpService.listRates();

        model.addAttribute("rates", rates);

        return "table";
    }
}
