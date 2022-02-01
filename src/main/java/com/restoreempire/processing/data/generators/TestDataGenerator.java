package com.restoreempire.processing.data.generators;

import java.math.BigDecimal;

public class TestDataGenerator {

    static String[] names = new String[]{"Artem", "Anton", "Boris", "Nikolay", "Viktor"};
    static String[] surnames = new String[]{"Artemov", "Antonov", "Borisos", "Nikolaev", "Viktorov"};
    static String[] middlenames = new String[]{"Artemovich", "Antonovcih", "Borisovich", "Nikolaevich", "Viktorovich"};


    public static String[] FillNames(){
        String[] result = new String[3];
        result[0] = surnames[(int) (Math.random() * surnames.length)];
        result[1] = names[(int) (Math.random() * names.length)];
        result[2] = middlenames[(int) (Math.random() * middlenames.length)];
        return  result;
    }

    public static BigDecimal randomFunds(int range){
        return  BigDecimal.valueOf((int) (Math.random() * range));
    }

}
