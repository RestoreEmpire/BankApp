package com.restoreempire.processing.data.generators;

public class AccountNumberGenerator extends DataGenerator {

    public String generate(){
        return buildRandomDigits(16).toString();
    }
}
