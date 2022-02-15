package com.restoreempire.service.generators;

public class AccountNumberGenerator extends DataGenerator {

    public String generate(){
        return buildRandomDigits(16).toString();
    }
}
