import accounts.Account;
import clients.Client;

public class App {
// банк
// расчётные счета
// клиенты

    public static void main(String[] args) throws Exception {
        // Client client = new Client("Рахат Лукумов", "1234123412341234");
        // System.out.println(client);
        // Account account = new Account(client, "123", 123);
        // System.out.println(account);
        Client client = new Client("Никита");
        Account account = new Account(client);
        System.out.println(account.getFunds());
        client.addFunds(account, 100);
        System.out.println(account.getFunds());
        client.withdrawFunds(account, 100);
        System.out.println(account.getFunds());

    }
}   
