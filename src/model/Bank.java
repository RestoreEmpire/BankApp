package model;

import java.util.ArrayList;

import logging.Logger;
import logging.Logger.Status;
import processing.data.Parser;

public class Bank implements Model<Bank> {
    private String name;
    private int id;
    private static int idCounter = 1; // TODO: заменить
    private Parser parser = new Parser("Bank");

    public Bank(){

    }

    public Bank(String bankName) {
        setName(bankName);
        setId(idCounter++);
        Logger.write("New bank \"" + bankName + "\" was created", Status.OK);
    }

    public Bank(int id, String bankName){
        setName(name);
        setId(id);
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
        parser.writeToEnd(String.valueOf(getId()), getName());
    }
    
    public int search() {
        try { // TODO: засунуть в валидацию
            int result = parser.containedRow(String.valueOf(getId()), getName());
            if (result < 0) throw new Exception("Row not found");
            return result;
        } catch (Exception e){
            return 0;
        }
    }

    @Override
    public Bank read(int rowIndex) {
        ArrayList<String> row = parser.getRow(rowIndex);
        Bank bank = new Bank(Integer.parseInt(row.get(0)), row.get(1));
        return bank;
    }
    
    @Override
    public void update(Bank bank) {
        parser.changeRow(search(), Integer.toString(bank.getId()), bank.getName());
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
