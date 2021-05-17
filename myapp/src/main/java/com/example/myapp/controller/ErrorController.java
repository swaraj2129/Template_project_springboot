package com.example.myapp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ErrorController {
    /**
     *
     * @return message of error
     */
    @GetMapping("/invalid")
    public ResponseEntity<String> invalid(){
        return new ResponseEntity<>("{message : something is missing, please check headers or the payload}", HttpStatus.OK);
    }
}
