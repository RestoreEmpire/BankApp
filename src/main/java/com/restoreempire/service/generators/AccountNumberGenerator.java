package com.restoreempire.service.generators;

/**Just generate String with random 16 digits */
public class AccountNumberGenerator extends DataGenerator {

    public String generate(){
        return buildRandomDigits(16).toString();
    }
}
