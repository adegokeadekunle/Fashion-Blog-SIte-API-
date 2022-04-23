package com.example.delecouture.exceptions;

public class UnAuthorizedActionException extends  RuntimeException{
    public UnAuthorizedActionException(String s) {
        super(s);
    }
}
