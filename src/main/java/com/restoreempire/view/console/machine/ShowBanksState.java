package com.restoreempire.view.console.machine;

public class ShowBanksState extends State {
    final private String message = """
            Bank menu
            1. Show all
            2. Show by id
            3. Create new bank
            4. Delete bank by id
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
