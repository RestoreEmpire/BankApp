package com.restoreempire.exceptions;

public class RowNotFoundInTableException extends RuntimeException {
    public RowNotFoundInTableException(String message) {
        super(message);
    }
}