package com.example.myapp.controller;

import com.example.myapp.entity.UserDetail;
import com.example.myapp.event.AuditEventPublisher;
import com.example.myapp.service.JwtAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class AuthController {

    @Autowired
    JwtAuthService jwtAuthService;
    @Autowired
    private AuditEventPublisher publisher;

    /*@Autowired
    /**
     *
     * @param userDetail object
     * @return
     */


    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody @Valid UserDetail userDetail) {

        if (jwtAuthService.check(userDetail)) {
            String token = jwtAuthService.onSuccessfulAuthentication(userDetail.uname, userDetail.password);
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("Bearer",
                    token);
            //publisher.publishEvent("User " + userDetail.getUname() +   " successfully registered");
            return ResponseEntity.ok()
                    .headers(responseHeaders)
                    .body("Response with header using ResponseEntity");
        } else
            publisher.publishEvent("User " + userDetail.getUname() +   " not registered");
            return new ResponseEntity("wrong credentials", HttpStatus.BAD_REQUEST);
    }

}
