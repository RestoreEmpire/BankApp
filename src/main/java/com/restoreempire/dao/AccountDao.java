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
    protected Map<String, Object> serialized(Account account) {
        var map = new HashMap<String, Object>();
        if(account.getId() != 0)
            map.put("id", account.getId());
        map.put("account_number", account.getAccountNumber());
        map.put("bank_id", account.getBankId());
        map.put("client_id", account.getClientId());
        map.put("funds", account.getFunds());
        return map;
    }

    @Override
    public void create(Account account) {
        insert(tableName, serialized(account));
    }

    @Override
    public Account read(long id) {
        Account account = new Account();
        try (ResultSet rs = select(tableName, id)) {
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

    public List<Account> getAll() {
        List<Account> list = new ArrayList<>();
        try(ResultSet rs = selectAll(tableName)) {
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
    public void update(Account updated, Account updating) {
        dbUpdate(tableName, updated.getId(), serialized(updating));
    }

    @Override
    public void delete(Account account) {
        dbDelete(tableName, account.getId());
    }

}
