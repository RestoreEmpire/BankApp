package com.restoreempire.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import com.restoreempire.logging.Logger;
import com.restoreempire.processing.data.generators.AccountNumberGenerator;
import com.restoreempire.processing.data.validators.DataValidation;

public class Account extends BaseModel<Account> {

    private String accountNumber;
    private Long bankId;
    private Long clientId;
    private BigDecimal funds = BigDecimal.ZERO;
    private long id;
    private final static String tableName = "account";

    public Account(){

    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public Long getBankId() {
        return bankId;
    }

    public void setBankId(Long bankId) {
        this.bankId = bankId;
    }

    public Account(long id, String accountNumber, long bankId, long clientId, BigDecimal funds){
        setClientId(clientId);
        setBankId(bankId);
        setAccountNumber(accountNumber);
        setFunds(funds);
        setId(id);
        // ID, ACCOUNT_NUMBER, BANK_ID, CLIENT_ID, FUNDS
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public BigDecimal getFunds(){
        return funds;
    }

    public void setFunds(BigDecimal funds) {
        this.funds = DataValidation.validateAccountFunds(funds) ? funds : this.funds;
    }

    public void setFunds(String funds) {
        BigDecimal bd = new BigDecimal(funds);
        setFunds(bd);
    }
    public String getAccountNumber(){
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber){
        this.accountNumber = accountNumber;
    }

    public void setRandomAccountNumber(){
        setAccountNumber(new AccountNumberGenerator().generate());
    }

    public <T extends Number> void addFunds(T transferSum) {
        BigDecimal currentTransfer = new BigDecimal(transferSum.toString());
        try {
            if (currentTransfer.compareTo(BigDecimal.ZERO) > 0)
                setFunds(getFunds().add(currentTransfer));
            else
                throw new Exception("Amount of the transferred money should be more than 0");
        } catch (Exception e) {
            e.printStackTrace();
            Logger.write(e.getMessage(), Logger.Status.ERROR);
        }
    }   
    
    public <T extends Number> void withdrawFunds(T transferSum) {
        BigDecimal currentTransfer = new BigDecimal(transferSum.toString());
        try {
            if (currentTransfer.compareTo(getFunds()) < 0)
                setFunds(getFunds().subtract(currentTransfer));
            else
                throw new Exception("Amount of the withdrawn money should be more than 0");
        } catch (Exception e) {
            e.printStackTrace();
            Logger.write(e.getMessage(), Logger.Status.ERROR);
        }
        
    }

    @Override
    public HashMap<String, Object> serialized() {
        var map = new HashMap<String, Object>();
        if(getId() != 0)
            map.put("id", getId());
        map.put("account_number", getAccountNumber());
        map.put("bank_id", getBankId());
        map.put("client_id", getClientId());
        map.put("funds", getFunds());
        return map;
    }

    @Override
    public void create() {
        insert(tableName, serialized());
    }

    @Override
    public void read(long id) {
        try (ResultSet rs = select(tableName, id)) {
                setId(rs.getLong("id"));
                setAccountNumber(rs.getString("account_number"));
                setBankId(rs.getLong("bank_id"));
                setClientId(rs.getLong("client_id"));
                setFunds(rs.getBigDecimal("funds"));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static ArrayList<Account> getAll() {
        ArrayList<Account> list = new ArrayList<>();
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
    public void update(Account account) {
        dbUpdate(tableName, getId(), account.serialized());
    }


    @Override
    public void delete() {
        dbDelete(tableName, getId());
    }

    @Override
    public String toString() {
        return  getAccountNumber();
    }
}