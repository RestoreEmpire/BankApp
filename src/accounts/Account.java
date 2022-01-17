package accounts;

import java.math.BigDecimal;
import clients.Client;
import data.processing.DataValidation;
import data.processing.generators.AccountNumberGenerator;

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
    
    @Override
    public String toString() {
        return  "Account number: " + accountNumber + '\n' +
                "Client data: " + '\n' + 
                client.toString() + '\n' +
                "Funds: " + funds;
    }

}
