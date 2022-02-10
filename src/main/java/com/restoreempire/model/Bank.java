package com.restoreempire.model;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import com.restoreempire.processing.data.validators.Validation;


public class Bank extends BaseModel<Bank> {
    private String name;
    private long id;
    private final static String tableName = "bank";

    public Bank(){

    }

    public Bank(long id) {
        this.read(id);
    }

    public Bank(String bankName){
        setName(bankName);
    }
    
    public Bank(long id, String bankName){
        setName(bankName);
        setId(id);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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
            map.put("id", getId());
        if (!Validation.isNullOrEmpty(getName()))
            map.put("name", getName());
        return map;
    }

    @Override
    public void create() {
        insert(tableName, serialized());
    }

    @Override
    public void read(long id) {
        try(ResultSet rs = select(tableName,id)){
            setId(rs.getLong("id"));
            setName(rs.getString("name"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static ArrayList<Bank> getAll() {
        ArrayList<Bank> list = new ArrayList<>();
        try(ResultSet rs = selectAll(tableName)) {
            while(rs.next()){
                Bank bank = new Bank(rs.getLong("id"), rs.getString("name"));
                list.add(bank);
            }
            return list;
        } catch (Exception e) {
            return null;
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
