package model;

import java.math.BigDecimal;
import java.util.ArrayList;

import logging.Logger;
import logging.Logger.Status;
import processing.data.Parser;
import processing.data.generators.AccountNumberGenerator;
import processing.data.validators.DataValidation;

public class Account implements Model<Account>{

    private String accountNumber;
    private Bank bank;
    private Client client;
    private BigDecimal funds = BigDecimal.ZERO;
    private Parser parser = null;

    public Account(){

    }

    public Account(Client client, Bank bank) {
        this.client = client;
        this.bank = bank;
        accountNumber = new AccountNumberGenerator().generate();
        
    }
    
    public Account(Client client, Bank bank, long startingDeposit) {
        this(client, bank);
        this.funds = BigDecimal.valueOf(startingDeposit);
    }

    public Account(String accountNumber, Bank bank, Client client, BigDecimal funds){
        this.client = client;
        this.bank = bank;
        setAccountNumber(accountNumber);
        setFunds(funds);
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

    private void setFunds(BigDecimal funds) {
        this.funds = DataValidation.validateAccountFunds(funds) ? funds : this.funds;
    }

    public String getAccountNumber(){
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber){
        this.accountNumber = accountNumber;
    }

    public <T extends Number> void addFunds(T transferSum) {
        BigDecimal currentTransfer = new BigDecimal(transferSum.toString());
        if (currentTransfer.compareTo(BigDecimal.ZERO) > 0)
            setFunds(getFunds().add(currentTransfer));
        else {
            String mes = "Amount of the transferred money should be more than 0";
            System.err.println(mes);
            Logger.write(mes, Status.ERROR);
        }
    }   
    
    public <T extends Number> void withdrawFunds(T transferSum) {
        BigDecimal currentTransfer = new BigDecimal(transferSum.toString());
        if (currentTransfer.compareTo(getFunds()) < 0)
            setFunds(getFunds().subtract(currentTransfer));
        else {
            String mes = "Amount of the withdrawn money should be more than 0";
            System.err.println(mes);
            Logger.write(mes, Status.ERROR);
        }
        
    }  

    @Override
    public void create() {
        parser.writeToEnd(
            getAccountNumber(),
            getBank().toString(),
            getClient().toString(),
            getFunds().toString()
        );
        Logger.write("New account created with account number: " + accountNumber, Status.OK);
    }

    @Override
    public void delete() {
        parser.removeRow(search());
        
    }

    @Override
    public Account read(int rowIndex) {
        // TODO: реализавать связи
        // ArrayList<String> row = parser.getRow(rowIndex);
        Account account = new Account();
        return account;
    }

    @Override
    public void update(Account object) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public int search() {
        try { // TODO: засунуть в валидацию
            int result = parser.containedRow(            
                getAccountNumber(), 
                getBank().toString(),
                getClient().toString(), 
                getFunds().toString()
                );
            if (result < 0) throw new Exception("Row not found");
            return result;
        } catch (Exception e){
            return 0;
        }
    }

    @Override
    public String toString() {
        return  "Account number: " + getAccountNumber() + '\n' +
                "Client ID: " + client.getId() + '\n' +
                "Funds: " + String.format("%.2f", getFunds());
    }
}
