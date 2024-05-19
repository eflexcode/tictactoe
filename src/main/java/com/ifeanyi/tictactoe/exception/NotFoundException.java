package com.ifeanyi.tictactoe.exception;

public class NotFoundException extends Exception{

    private final static long serialVersionId = 1L;

    public NotFoundException(String message) {
        super(message);
    }
}
