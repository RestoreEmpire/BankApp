package com.restoreempire.view.console.machine;

public class StateHandler {

    private State state;

    public StateHandler () {
        state =  new StartState();
    }

    public void setInput(String input) {

        state.setInput(input);
        setState(state.nextState());
    }

    private void setState(State state){
        this.state = state;
    }

    public String getMessage() {
        return state.getMessage();
    }

}
