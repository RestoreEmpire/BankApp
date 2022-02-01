package com.restoreempire;


import com.restoreempire.model.Bank;
import com.restoreempire.model.Client;

public class App {

    public static void main(String[] args)  {
        var bank = new Bank("Tinkoff");
        bank.read(1);
        System.out.println(bank);

    }


}
