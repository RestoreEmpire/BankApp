package com.restoreempire.exceptions;

public class NameValidationException extends ValidationException {
    private String nameType;
    
    public NameValidationException(String message, String type){
        super(message);
        nameType = type;
    }

    @Override
    public String getMessage() {
        return String.format("%s %s", nameType, super.getMessage());
    }


}