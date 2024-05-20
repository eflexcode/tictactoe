package com.ifeanyi.tictactoe.exception;

public class InvalidMoveException extends Exception{

    private final static long serialVersionId = 1L;

    public InvalidMoveException(String message) {
        super(message);
    }
}
