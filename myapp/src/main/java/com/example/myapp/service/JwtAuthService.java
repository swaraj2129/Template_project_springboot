package com.example.myapp.service;

import com.example.myapp.entity.UserDetail;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;

@Service
public class JwtAuthService {

    public JwtAuthService(){

    }

    @Value("${secret}")
    private String key;
    public static String bearerToken = "";

    /**
     *
     * @param username is object representing details entered by user for authentication
     * @return status of authentication
     */
    public boolean check(UserDetail username){
        return username.getUname().equals("user") && username.getPassword().equals("password");
    }

    /**
     *
     * @param uname is the username
     * @param password is the password
     * @return the bearer token
     */
    public String onSuccessfulAuthentication(String uname, String password){

        String token = Jwts.builder()
                .setSubject(uname + password)
                .setIssuedAt(new Date())
                .setExpiration(java.sql.Date.valueOf(LocalDate.now().plusWeeks(2)))
                .signWith(Keys.hmacShaKeyFor(key.getBytes()))
                .compact();
      //  System.out.println("Bearer " + token);
        bearerToken = ("Bearer " + token);
       return  ( token);
    }
}
