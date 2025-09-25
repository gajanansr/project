package com.wecp.progressive.exception;

public class NoMatchesFoundException extends RuntimeException{

    public NoMatchesFoundException(String message)
    {
        super(message);
    }
}