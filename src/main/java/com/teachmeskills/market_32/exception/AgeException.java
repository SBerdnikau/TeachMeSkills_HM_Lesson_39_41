package com.teachmeskills.market_32.exception;

public class AgeException extends RuntimeException{
    public AgeException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "AgeException: Age is incorrect!";
    }
}
