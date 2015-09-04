package com.example.user.agregator;

public class BaseResult {

    private int errorCode;
    private String errorMessage;

    public boolean isSuccess() {
        return errorCode == 0;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

}