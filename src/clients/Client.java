package clients;
import java.math.BigDecimal;

import accounts.Account;
import data.processing.DataValidation;
import data.processing.generators.ClientIdGenerator;


public class Client {

    // TODO: больше информации о клиенте
    private String id;
    private String name = "unsetted";

    public Client(String name){
        setName(name);
        id = new ClientIdGenerator().generate();
    }
    public Client(String name, String id) {
        setId(id);
        setName(name);
    }
    
    public void setName(String name) {
        this.name = DataValidation.validateName(name) ? name : this.name;
    }

    public String getName() {
        return name;
    }

    private void setId(String id) {
        this.id = DataValidation.validateId(id) ? id : this.id;
    }

    public String getId() {
        return id;
    }

    public void setInfo(String name, String id) {
        setId(id);
        setName(name);
    }

    //TODO: клиент не должен делать изменения счёта самостоятельно, он должен делать запрос
    public <T extends Number> void addFunds(Account account, T transferSum) {
        BigDecimal currentTransfer = new BigDecimal(transferSum.toString());
        if (currentTransfer.compareTo(BigDecimal.ZERO) > 0)
            account.setFunds(account.getFunds().add(currentTransfer));
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
        return  "ID: " + id + "\n" +
                "Name: " + name;
    }
}

