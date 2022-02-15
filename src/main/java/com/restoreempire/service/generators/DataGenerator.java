package com.restoreempire.service.generators;

abstract class DataGenerator {

    /**
     * Build {@code StringBuilder} filled with random digits. Length of sequence is {@code len}.
     * @param len length of the sequence of random digits
     * @return {@code StringBuilder}
     */
    protected static StringBuilder buildRandomDigits(int len) {
        StringBuilder generatedClientId = new StringBuilder();
        for(int i = 0; i < len; i++){
            generatedClientId.append((int) (Math.random() * 10));
        }
        return generatedClientId;
    }

    abstract public String generate();
}
