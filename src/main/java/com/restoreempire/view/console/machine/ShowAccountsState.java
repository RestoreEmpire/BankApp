package com.restoreempire.view.console.machine;

public class ShowAccountsState extends State {

    private final String message = """
            Account menu
            1. Show all
            2. Search by id
            3. Create new Account
            4. Delete account by id
            0. Back to main menu
            """;

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public void setInput(String input) {
        switch (input) {
            case "0":
                setState(new StartState());
                break;
            case "1":
                break;
            case "2":
                break;
            case "3":
                break;
            case "4":
                break;
            default:
                System.err.println("Wrong input");
                setState(new ShowBanksState());
                break;

        }
    }
}
