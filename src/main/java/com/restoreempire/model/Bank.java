package com.restoreempire.model;

import java.sql.ResultSet;

import java.util.HashMap;


import com.restoreempire.logging.Logger;
import com.restoreempire.logging.Logger.Status;

public class Bank extends BaseModel implements Model<Bank> {
    private String name;
    private int id;
    private final String tableName = "bank";

    public Bank(){

    }

    public Bank(String bankName) {
        setName(bankName);
        Logger.write("New bank \"" + bankName + "\" was created", Status.OK);
    }

    public Bank(int id, String bankName){
        setName(bankName);
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

    public HashMap<String, Object> serialized(){

        var map = new HashMap<String, Object>();
        if (getId() != 0)
            map.put("id", String.valueOf(getId()));
        map.put("name", getName());
        return map;
    }

    @Override
    public void create() {
        insert(tableName, serialized());
    }

    @Override
    public void read(int id) {
        try(ResultSet rs = select(tableName,id)){
            setId(rs.getInt("id"));
            setName(rs.getString("name"));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void update(Bank bank) {
        dbUpdate(tableName, getId(), bank.serialized());
    }

    @Override
    public void delete() {
        dbDelete(tableName, getId());
    }

    @Override
    public String toString() {
        return name;
    }
}
