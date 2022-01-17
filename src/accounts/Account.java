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

    public void setFunds(BigDecimal funds) {
        this.funds = DataValidation.validateAccountFunds(funds) ? funds : this.funds;
    }

    public String getAccountNumber(){
        return accountNumber;
    }
    
    @Override
    public String toString() {
        return  "Account number: " + getAccountNumber() + '\n' +
                "Client ID: " + client.getId() + '\n' +
                "Funds: " + getFunds();
    }

}
