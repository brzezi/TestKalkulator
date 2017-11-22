package com.example.waluty;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Controller
public class KalkulatorController {
    @RequestMapping("/asd")
    @ResponseBody
    String home() {
        return "Hello World!";
    }

    @RequestMapping("/time")
    @ResponseBody
    String time() {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE;
        LocalDate now = LocalDate.now();
        return formatter.format(now);
    }

    @RequestMapping("/dodaj")
    String dodaj(@RequestParam(value="a") Long a, @RequestParam(value="b") Long b, Model model) {
        model.addAttribute("a", a);
        model.addAttribute("b", b);
        model.addAttribute("wynik", a+b);

        return "kalkulator";
    }



    @RequestMapping("/kalkulator")
    String kalkulator(Model model) {
        return "kalkulator";
    }
}
