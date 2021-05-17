package com.example.myapp.entity;


import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.util.List;

public class Response<T> implements Serializable {
    private T entity;

    private int status;

    // list of error details objects -> error code + error message
    private List<ErrorDetail> errorDetails;

    public Response(T entity,List<ErrorDetail> errorDetails) {
        this.entity = entity;
        this.errorDetails = errorDetails;
    }

    public Response(T entity,int status) {
        this.entity = entity;
        this.status = status;
    }

    public Response(List<ErrorDetail> errorDetails){
        this.errorDetails = errorDetails;
    }

//    public Response(T entity, HttpStatus notFound) {
//        this.entity = entity;
//        this.status = notFound.value();

    public void setStatus(int status) {
        this.status = status;
    }

    public void setErrorDetails(List<ErrorDetail> errorDetails) {
        this.errorDetails = errorDetails;
    }
//    }

    public T getEntity() {
        return entity;
    }

    public void setEntity(T entity) {
        this.entity = entity;
    }
    public List<ErrorDetail> getErrorDetails() {
        return errorDetails;
    }

    public void setErrors(List<ErrorDetail> errorDetails) {
        this.errorDetails = errorDetails;
    }

    public int getStatus() {
        return status;
    }
}

