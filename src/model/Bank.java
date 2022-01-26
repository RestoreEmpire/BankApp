package model;

import java.util.ArrayList;

import exceptions.RowNotFoundInTableException;
import logging.Logger;
import logging.Logger.Status;
import processing.data.Parser;

public class Bank implements Model<Bank> {
    private String name;
    private String id;
    private Parser parser = new Parser("Bank");

    public Bank(){

    }

    public Bank(String bankName) {
        setName(bankName);
        Logger.write("New bank \"" + bankName + "\" was created", Status.OK);
    }

    public Bank(String id, String bankName){
        setName(bankName);
        setId(id);
        Logger.write("New bank \"" + bankName + "\" was created", Status.OK);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
        parser.writeToEnd(getId(), getName());
    }
    
    public int search() {
        try { // TODO: засунуть в валидацию
            int result = parser.inTable(String.valueOf(getId()), getName());
            if (result < 0) throw new RowNotFoundInTableException("Row not found");
            return result;
        } catch (Exception e){
            e.getMessage();
            return -1;
        }
    }

    @Override
    public void read(String id) {
        ArrayList<String> row = parser.getRowById(id);
        setId(row.get(0));
        setName(row.get(1));
        // ID, NAME
    }
    
    @Override
    public void update(Bank bank) {
        parser.changeRow(search(), bank.getId(), bank.getName());
    }

    @Override
    public void delete() {
        parser.removeRow(search());
    }

    @Override
    public String toString() {
        return name;
    }
}
