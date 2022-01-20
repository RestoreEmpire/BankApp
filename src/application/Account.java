package application;

import java.math.BigDecimal;

import logging.Logger;
import logging.Logger.Status;
import processing.data.generators.AccountNumberGenerator;
import processing.data.validators.DataValidation;

public class Account implements Model{

    private String accountNumber;
    private Client client;
    private Bank bank;
    private BigDecimal funds = BigDecimal.ZERO;

    public Account(Client client, Bank bank) {
        this.client = client;
        this.bank = bank;
        accountNumber = new AccountNumberGenerator().generate();
        Logger.write("New account created with account number: " + accountNumber, Status.OK);
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

    public Account(Client client, Bank bank, long startingDeposit) {
        this(client, bank);
        this.funds = BigDecimal.valueOf(startingDeposit);
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
        // TODO Auto-generated method stub
        
    }
    
    @Override
    public String toString() {
        return  "Account number: " + getAccountNumber() + '\n' +
                "Client ID: " + client.getId() + '\n' +
                "Funds: " + String.format("%.2f", getFunds());
    }

}
