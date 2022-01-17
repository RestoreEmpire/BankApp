import persons.clients.Client;
import persons.employees.Manager;
import accounts.Account;

public class App {
// банк
// расчётные счета
// клиенты

    public static void main(String[] args) throws Exception {
        Manager manager = new Manager();
        Client client = manager.registerClient("Николай", "Николаевич", "Николаев", "1990-10-17");
        System.out.println(client);
        Account account = manager.registerNewAccount(client);
        System.out.println(account);
        account.addFunds(123);
        System.out.println(account.getFunds());
        account.addFunds(215);
        System.out.println(account.getFunds());
        System.out.println(account);
        
    }
}   
