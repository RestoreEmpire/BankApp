import application.Account;
import application.Bank;
import application.Client;

public class App {
// банк
// расчётные счета
// клиенты

    public static void main(String[] args) throws Exception {
        Bank bank = new Bank("Tinkoff");
        bank.create();
    }
}   
