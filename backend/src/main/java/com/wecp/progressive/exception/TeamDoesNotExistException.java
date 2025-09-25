package com.wecp.progressive.exception;

public class TeamDoesNotExistException extends RuntimeException{

    public TeamDoesNotExistException(String message)
    {
        super(message);
    }
}