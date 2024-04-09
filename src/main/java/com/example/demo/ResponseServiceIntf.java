package com.example.demo;

public interface ResponseServiceIntf {

    SuccessDTO getSuccessResponse(int code, String message, Object data);

    ErrorDTO getErrorResponse(int code, String message);

}