package com.example.demo.exceptions;

public class NoForecastDetailsFoundException extends RuntimeException{
    
    public NoForecastDetailsFoundException(String msg){
        super(msg);
    }
}
