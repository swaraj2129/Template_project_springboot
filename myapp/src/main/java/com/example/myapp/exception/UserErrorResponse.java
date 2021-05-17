package com.example.myapp.exception;

import com.example.myapp.entity.ErrorDetail;

import java.util.List;

public class UserErrorResponse {


    private List<ErrorDetail> errorDetails;

    public  UserErrorResponse(){

    }
    public UserErrorResponse(List<ErrorDetail> errorDetails){
        this.errorDetails = errorDetails;
    }

    public List<ErrorDetail> getErrorDetails() {
        return errorDetails;
    }

    public void setErrorDetails(List<ErrorDetail> errorDetails) {
        this.errorDetails = errorDetails;
    }
}
