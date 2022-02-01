package com.restoreempire.view.console.machine;

public class StartState extends State {

    final private String message = """
                                    Main menu
                                    1. Bank
                                    2. Client
                                    3. Account
                                    0. Exit
                                    """;

    public StartState() {

    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public void setInput(String input){
        switch (input){
            case "1":
                setState(new ShowBanksState());
                break;
            case "2":
                setState(new ShowClientsState());
                break;
            case "3":
                setState(new ShowAccountsState());
                break;
            case "0":
                System.exit(1);
            default:
                setState(new StartState());
                break;
        }
    }
}
