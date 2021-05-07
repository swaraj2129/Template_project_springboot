package com.example.myapp.service;


import java.util.Date;
import java.util.Map;

import com.example.myapp.dto.UserDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

//import com.mycom.happyhouse.dto.UserDto;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public  class  JwtService {

    public static final Logger logger = LoggerFactory.getLogger(JwtService.class);

    private String signature = "VUETOKEN";
    private Long expireMin = 5L;

    //     Upon successful login, JWTToken is created and returned based on user information.
    public  String  create ( UserDto userDto ) {
        JwtBuilder jwtBuilder = Jwts.builder();
//        JWT Token = Header + Payload + Signature

//         Header setting
        jwtBuilder.setHeaderParam( " typ " , " JWT " ); // Fixed value as token type.

//         Payload setting
        jwtBuilder
                .setSubject( " Login Token " ) // Set the subject of the token
                .setExpiration( new  Date ( System . currentTimeMillis() +  1000  *  60  * expireMin)) // Set the expiration date
                .claim( " user " , userDto) . claim( " greeting " , " Welcome "  + userDto.getFirstName()); // Set the information you want to contain.

//         signature setting
        jwtBuilder.signWith(SignatureAlgorithm.HS256, signature.getBytes());

//         last serialization processing
        String jwt = jwtBuilder.compact();
        logger.info("jwt : {}", jwt);
        return jwt;
    }

    // Check     that the received token was properly generated, and if there is a problem, a RuntimeException occurs.
    public void checkValid(String jwt) {
//         OK if no exception occurs
        Jwts.parser().setSigningKey(signature.getBytes()).parseClaimsJws(jwt);
    }

    //     Analyze JWT Token and return necessary information.
    public Map<String, Object> get(String jwt) {
        Jws<Claims> claims = null;
        try {
            claims = Jwts.parser().setSigningKey(signature.getBytes()).parseClaimsJws(jwt);
        } catch (final Exception e) {
            throw new RuntimeException();
        }

        logger.info("claims : {}", claims);
        // Claims is an implementation of Map.
        return claims.getBody();
    }
}