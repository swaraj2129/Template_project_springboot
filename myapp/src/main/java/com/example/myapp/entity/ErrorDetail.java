package com.example.myapp.entity;

public class ErrorDetail {
    private String errorMessage;
    private int errorCode;
    private String dateTime;

    public ErrorDetail(int errorCode, String errorMessage, String dateTime) {
        this.errorMessage = errorMessage;
        this.errorCode = errorCode;
        this.dateTime = dateTime;
    }

    public ErrorDetail() {
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }
}
