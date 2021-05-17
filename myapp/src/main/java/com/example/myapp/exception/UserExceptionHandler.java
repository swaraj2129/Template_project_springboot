package com.example.myapp.exception;

import com.example.myapp.entity.ErrorDetail;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@ControllerAdvice
public class UserExceptionHandler {
    // Add an exception handler for UserNotFoundException

    /**
     *
     * @param exception that is thrown after an error
     * @return UserErrorResponse exception
     */
    @ExceptionHandler
    public ResponseEntity<UserErrorResponse> handleException(UserNotFoundException exception){

        List<ErrorDetail> errorDetails = new ArrayList<>();
        errorDetails.add(new ErrorDetail(HttpStatus.NOT_FOUND.value(),exception.getMessage(), LocalDateTime.now().toString()));
        UserErrorResponse error = new UserErrorResponse(errorDetails);

        //return ResponseEntity
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    // Add another exception handler .... to catch any exception (catch all)

    /**
     *
     * @param exception that is thrown after an error
     * @return UserErrorResponse exception
     */
    @ExceptionHandler
    public ResponseEntity<UserErrorResponse> handleException(Exception exception){

        // create UserErrorResponse
        List<ErrorDetail> errorDetails = new ArrayList<>();
        errorDetails.add(new ErrorDetail(HttpStatus.BAD_REQUEST.value(),exception.getMessage(), LocalDateTime.now().toString()));
        UserErrorResponse error = new UserErrorResponse(errorDetails);
        //return ResponseEntity
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

}
