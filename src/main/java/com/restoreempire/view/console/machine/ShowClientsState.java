package com.restoreempire.view.console.machine;

public class ShowClientsState extends State {

    private final String message =  """
                                    Client menu
                                    1.Show all
                                    2.Find by id
                                    3. Create new client
                                    4. Delete client by id
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
                setState(new StartState());
                break;
            case "2":
                setState(new StartState());
                break;
            case "3":
                setState(new StartState());
                break;
            case "4":
                setState(new StartState());
                break;
            default:
                System.err.println("Wrong input");
                setState(new ShowClientsState());
                break;
        }
    }
}
