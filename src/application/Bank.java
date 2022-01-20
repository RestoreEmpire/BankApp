package application;

import logging.Logger;
import logging.Logger.Status;
import processing.data.Parser;

public class Bank implements Model {
    private String name;
    private int id;
    private static int idCounter = 1;

    public Bank(String bankName) {
        setName(bankName);
        setId(idCounter++);
        Logger.write("New bank \"" + bankName + "\" was created", Status.OK);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void create() {
        Parser parser = new Parser(path, "Bank");
        parser.writeToEnd(new String[]{String.valueOf(id), name});
        
    }
    @Override
    public String toString() {
        return name;
    }
}
