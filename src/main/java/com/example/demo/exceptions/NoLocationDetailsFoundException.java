package com.example.demo.exceptions;

public class NoLocationDetailsFoundException extends RuntimeException{
    
    public NoLocationDetailsFoundException(String msg){
        super(msg);
    }
}
