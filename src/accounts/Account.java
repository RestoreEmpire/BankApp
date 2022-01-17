package accounts;

import java.math.BigDecimal;

import data.processing.DataValidation;
import data.processing.generators.AccountNumberGenerator;
import persons.clients.Client;

public class Account {

    private String accountNumber;
    private Client client;
    private BigDecimal funds = BigDecimal.ZERO;

    public Account(Client client) {
        this.client = client;
        accountNumber = new AccountNumberGenerator().generate();
    }

    public Account(Client client, long startingDeposit) {
        this(client);
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
        else
            System.err.println("Amount of the transferred money should be more than 0");
    }   
    
    public <T extends Number> void withdrawFunds(Account account, T transferSum) {
        BigDecimal currentTransfer = new BigDecimal(transferSum.toString());
        if (currentTransfer.compareTo(BigDecimal.ZERO) > 0)
            account.setFunds(account.getFunds().subtract(currentTransfer));
        else
            System.err.println("Amount of the withdrawn money should be more than 0");
    }  
    
    @Override
    public String toString() {
        return  "Account number: " + getAccountNumber() + '\n' +
                "Client ID: " + client.getId() + '\n' +
                "Funds: " + String.format("%.2f", getFunds());
    }

}
