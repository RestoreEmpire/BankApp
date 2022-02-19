package com.restoreempire.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.restoreempire.model.Bank;
import com.restoreempire.service.validators.Validation;

/** DAO of Bank model */
public class BankDao extends BaseDao<Bank> {

    /** Table name in database table */
    
    @Override
    public String getTableName() {
        return "bank";
    }

    public String[] getKeys(){
        return new String[]{"id", "name"};
    }

    @Override
    public void create(Bank bank) {
        insert(bank);
    }

    @Override
    public Bank read(long id) {
        Bank bank = new Bank();
        try(ResultSet rs = select(id)){
            bank.setId(rs.getLong("id"));
            bank.setName(rs.getString("name"));
            return bank;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void update(long id, Bank updating) {
        dbUpdate(id, updating);        
    }

    @Override
    public void delete(Bank bank) {
        dbDelete(bank.getId());
    }

    @Override
    public List<Bank> getAll() {
        List<Bank> list = new ArrayList<>();
        try(ResultSet rs = selectAll()) {
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
    public Map<String, Object> serialized(Bank bank){
        Map<String, Object> map = new HashMap<>();
        var keys = getKeys();
        if (bank.getId() != 0)
            map.put(keys[0], bank.getId());
        if (!Validation.isNullOrEmpty(bank.getName()))
            map.put(keys[1], bank.getName());
        return map;
    }
}
