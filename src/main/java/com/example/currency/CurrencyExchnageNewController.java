package com.example.currency;

import com.example.currency.currencyconverterapi.Currency;
import com.example.currency.currencyconverterapi.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.List;

@Controller
public class CurrencyExchnageNewController {

    @Autowired
    CurrencyService currencyService;

    @RequestMapping("/currency-list")
    String currencyList(Model model) throws IOException {
        List<Currency> rates = currencyService.listAll();

        model.addAttribute("rates", rates);

        return "currency-list";
    }

    @RequestMapping("/check-rate-form")
    String checkRate( Model model) throws IOException {
        model.addAttribute("currencies", currencyService.listAll());
        return "check-rate";
    }

    @RequestMapping(value="/check-rate", method={RequestMethod.GET, RequestMethod.POST})
    String checkRate(@RequestParam(value="from") String from, @RequestParam(value="to") String to, @RequestParam(value="amount") int amount, Model model) throws IOException {

        double rate = currencyService.getRate(from, to);

        model.addAttribute("from", from);
        model.addAttribute("to", to);
        model.addAttribute("amount", amount);
        model.addAttribute("result", amount * rate);
        model.addAttribute("currencies", currencyService.listAll());

        return "check-rate";
    }
}
