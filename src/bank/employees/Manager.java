package bank.employees;

import clients.Client;

public class Manager {

    public Client registerClient(String name){
        return new Client(name);
    }
    
    public void changeClientInfo(){
        
    }
}
