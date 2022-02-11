package com.restoreempire.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.restoreempire.model.Bank;
import com.restoreempire.processing.data.validators.Validation;

public class BankDao extends BaseDao<Bank> {

    private final static String tableName = "bank";

    @Override
    public void create(Bank bank) {
        insert(tableName, serialized(bank));
    }

    @Override
    public Bank read(long id) {
        Bank bank = new Bank();
        try(ResultSet rs = select(tableName,id)){
            bank.setId(rs.getLong("id"));
            bank.setName(rs.getString("name"));
            return bank;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void update(Bank updated, Bank updating) {
        dbUpdate(tableName, updated.getId(), serialized(updating));        
    }

    @Override
    public void delete(Bank bank) {
        dbDelete(tableName, bank.getId());
    }

    @Override
    public List<Bank> getAll() {
        List<Bank> list = new ArrayList<>();
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

    public Map<String, Object> serialized(Bank bank){
        Map<String, Object> map = new HashMap<>();
        if (bank.getId() != 0)
            map.put("id", bank.getId());
        if (!Validation.isNullOrEmpty(bank.getName()))
            map.put("name", bank.getName());
        return map;
    }
}
