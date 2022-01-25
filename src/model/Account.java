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
    private Parser parser = new Parser("Account");
    private String id;

    public Account(){

    }

    public Account(String id, Bank bank,Client client) {
        setClient(client);
        setBank(bank);
        setId(id);
        accountNumber = new AccountNumberGenerator().generate();
        
    }
    
    public Account(Client client, Bank bank, String id, long startingDeposit) {
        this(id, bank, client);
        setFunds(BigDecimal.valueOf(startingDeposit));
    }

    public Account(String id, String accountNumber, Bank bank, Client client, BigDecimal funds){
        setClient(client);
        setBank(bank);
        setAccountNumber(accountNumber);
        setFunds(funds);
        setId(id);
        // ID, ACCOUNT_NUMBER, BANK_ID, CLIENT_ID, FUNDS
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    private void setFunds(BigDecimal funds) {
        this.funds = DataValidation.validateAccountFunds(funds) ? funds : this.funds;
    }

    private void setFunds(String funds) {
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
            getId(),
            getAccountNumber(),
            getBank().getId(),
            getClient().getId(),
            getFunds().toString()
        );
        Logger.write("New account created with account number: " + accountNumber, Status.OK);
    }

    @Override
    public void delete() {
        parser.removeRow(search());
        
    }

    @Override
    public void read(String id) {
        ArrayList<String> row = parser.getRowById(id);
        Parser bankParser = new Parser("Bank");
        Parser clientParser = new Parser("Client");
        ArrayList<String> b = bankParser.getRowById(row.get(2));
        ArrayList<String> c = clientParser.getRowById(row.get(3));
        setId(row.get(0));
        setAccountNumber(row.get(1));
        setBank(new Bank(b.get(0), b.get(1)));
        setClient(new Client(c.get(0), c.get(1), c.get(2), c.get(3), c.get(4)));
        setFunds(row.get(4));
        // ID, SURNAME, FIRST_NAME, MIDDLE_NAME, BIRTH_DATE
        // ID, NAME
        // ID, ACCOUNT_NUMBER, BANK_ID, CLIENT_ID, FUNDS
        
    }

    @Override
    public void update(Account account) {
        parser.changeRow(search(), 
            account.getId(), 
            account.getAccountNumber(),
            account.getBank().getId(),
            account.getClient().getId(),
            account.getFunds().toString()
            );
        // ID, ACCOUNT_NUMBER, BANK_ID, CLIENT_ID, FUNDS
    }

    @Override
    public int search() {
        try { // TODO: засунуть в валидацию
            int result = parser.inTable(            
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
