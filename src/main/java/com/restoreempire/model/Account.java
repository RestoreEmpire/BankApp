package com.restoreempire.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.HashMap;

import com.restoreempire.logging.Logger;
import com.restoreempire.processing.data.generators.AccountNumberGenerator;
import com.restoreempire.processing.data.validators.DataValidation;

public class Account extends BaseModel implements Model<Account>{

    private String accountNumber;
    private Bank bank = new Bank();
    private Client client = new Client();
    private BigDecimal funds = BigDecimal.ZERO;
    private int id;
    private final String tableName = "account";

    public Account(){

    }

    /**
     * 
     * @param bank
     * @param client
     */
    public Account(Bank bank,Client client) {
        setClient(client);
        setBank(bank);
        accountNumber = new AccountNumberGenerator().generate();
        
    }
    
    public Account(Client client, Bank bank, long startingDeposit) {
        this(bank, client);
        setFunds(BigDecimal.valueOf(startingDeposit));
    }

    public Account(int id, String accountNumber, Bank bank, Client client, BigDecimal funds){
        setClient(client);
        setBank(bank);
        setAccountNumber(accountNumber);
        setFunds(funds);
        setId(id);
        // ID, ACCOUNT_NUMBER, BANK_ID, CLIENT_ID, FUNDS
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
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
        map.put("bank_id", bank.getId());
        map.put("client_id", client.getId());
        map.put("funds", getFunds());
        return map;
    }

    @Override
    public void create() {
        insert(tableName, serialized());
    }

    @Override
    public void read(int id) {
        try (ResultSet rs = select(tableName, id)) {
                setId(rs.getInt("id"));
                setAccountNumber(rs.getString("account_number"));
                Bank bank = new Bank();
                bank.read(rs.getInt("bank_id"));
                client.read(rs.getInt("client_id"));
                setFunds(rs.getBigDecimal("funds"));
        } catch (Exception e) {
            e.printStackTrace();
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
        return  "Account number: " + getAccountNumber() + '\n' +
                "Client ID: " + client.getId() + '\n' +
                "Funds: " + String.format("%.2f", getFunds());
    }
}