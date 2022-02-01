package com.restoreempire.view.console.machine;

public abstract class  State {

    protected State nextState;

    protected void setState(State state) {
        nextState = state;
    }

    public abstract String getMessage();

    public State nextState() {
        return nextState;
    }

    public abstract void setInput(String input);
}
