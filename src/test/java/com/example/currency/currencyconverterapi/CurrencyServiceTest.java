package com.example.currency.currencyconverterapi;

import org.junit.Assert;
import org.junit.Test;

import java.net.MalformedURLException;
import java.util.List;

import static org.junit.Assert.*;

public class CurrencyServiceTest {
    private CurrencyService currencyService = new CurrencyService();

    public CurrencyServiceTest() throws MalformedURLException {
    }

    @Test
    public void listAll() throws Exception {
        List<Currency> currencies = currencyService.listAll();


        Assert.assertTrue(currencies.size() > 0);

//        System.out.println(currencies);
    }

    @Test
    public void getRate() throws Exception {

        double rate = currencyService.getRate("EUR", "PLN");

        Assert.assertTrue(rate > 0);

//        System.out.println(rate);
    }

}