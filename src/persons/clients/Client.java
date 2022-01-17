package persons.clients;

import java.math.BigDecimal;

import accounts.Account;
import data.processing.generators.ClientIdGenerator;
import persons.Person;

public class Client extends Person {

    // TODO: больше информации о клиенте
    private String id;

    public Client(String firstName, String surname, String middlename, String birthDate) {
        setAllInfo(firstName, surname, middlename, birthDate);
        setId();
    }

    public void setId() {
        id = new ClientIdGenerator().generate();
    }

    public String getId() {
        return id;
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
        return  "ID: " + id + '\n' +
                "Name: " + getFullName() + '\n' +
                "Birth Date: " + getBirthDate();
    }
}

