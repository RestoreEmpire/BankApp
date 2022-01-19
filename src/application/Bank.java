package application;

import logging.Logger;

public class Bank {
    private String name;

    public Bank(String bankName) {
        setName(bankName);
        Logger.write("New bank " + bankName + " was created");
        Logger.show();
        
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
