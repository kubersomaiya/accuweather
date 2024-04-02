package com.example.demo.exceptions;

import java.util.Date;

class ApiError {

    private Integer errorCode;
    private String errorDesc;
    private Date date;

    public ApiError(Integer errorCode, String errorDesc, Date date) {
        this.errorCode = errorCode;
        this.errorDesc = errorDesc;
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public String getErrorDesc() {
        return errorDesc;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public void setErrorDesc(String errorDesc) {
        this.errorDesc = errorDesc;
    }
}