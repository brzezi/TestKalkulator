package com.example.currency.currencyconverterapi;

public class Currency {
    final public  String  name;
    final public String id;

    public Currency(String name, String id) {
        this.name = name;
        this.id = id;
    }

    @Override
    public String toString() {
        return "Currency{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
