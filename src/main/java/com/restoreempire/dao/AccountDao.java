package com.restoreempire.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.restoreempire.model.Account;


/** DAO of Client model */
public class AccountDao extends BaseDao<Account>{

    /** Table name in database table */
    private final static String tableName = "account";

    @Override
    public String getTableName() {
        return tableName;
    }

    @Override
    public Map<String,Object> serialized(Account account) {
        var map = new HashMap<String, Object>();
        var keys = getKeys();
        if(account.getId() != 0)
            map.put(keys[0],account.getId());
        map.put(keys[1], account.getAccountNumber());
        map.put(keys[2], account.getBankId());
        map.put(keys[3], account.getClientId());
        map.put(keys[4], account.getFunds());
        return map;
    }

    @Override
    public void create(Account account) {
        insert(account);
    }

    @Override
    public Account read(long id) {
        Account account = new Account();
        try (ResultSet rs = select(id)) {
            account.setId(rs.getLong("id"));
            account.setAccountNumber(rs.getString("account_number"));
            account.setBankId(rs.getLong("bank_id"));
            account.setClientId(rs.getLong("client_id"));
            account.setFunds(rs.getBigDecimal("funds"));
            return account;
        } catch (Exception e) {
            return null;
        }
    }

    public String[] getKeys(){
        return new String[]{"id", "account_number", "bank_id", "client_id", "funds"};
    }

    public List<Account> getAll() {
        List<Account> list = new ArrayList<>();
        try(ResultSet rs = selectAll()) {
            while(rs.next()){
                Account account = new Account(
                    rs.getLong("id"),
                    rs.getString("account_number"), 
                    rs.getLong("bank_id"),
                    rs.getLong("client_id"),
                    rs.getBigDecimal("funds")
                    );
                list.add(account);
            }
            return list;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void update(long id, Account updating) {
        dbUpdate(id, updating);
    }

    @Override
    public void delete(Account account) {
        dbDelete(account.getId());
    }

}
