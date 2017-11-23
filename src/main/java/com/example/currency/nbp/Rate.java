package com.example.currency.nbp;

public class Rate{
    public String currency;
    public String code;
    public double rate;

    public Rate(String currency, String code, double rate){
        this.currency = currency;
        this.code = code;
        this.rate = rate;
    }
}
