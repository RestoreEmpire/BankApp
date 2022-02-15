package com.restoreempire.service.generators;

/**Just generate String with random 12 digits */
public class ClientIdGenerator extends DataGenerator {

    public String generate() {
        return buildRandomDigits(12).toString();
    }
}
