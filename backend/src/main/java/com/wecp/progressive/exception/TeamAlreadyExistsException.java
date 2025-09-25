package com.wecp.progressive.exception;

public class TeamAlreadyExistsException extends RuntimeException{

    public TeamAlreadyExistsException(String message)
    {
        super(message);
    }
}