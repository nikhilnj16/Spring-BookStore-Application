package com.bridgelabz.bookstore.user.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.stereotype.Component;

import java.util.Base64;

@Component
public class UserJwt {
    private static final String SECRET_KEY = "nikhil";

    public String createToken(String userFirstName){

        return JWT.create()
                .withClaim("userFirstName", userFirstName)
                .sign(Algorithm.HMAC256(Base64.getEncoder().encode(SECRET_KEY.getBytes())));
    }

    public String decodeToken(String token){
        try{
            return JWT.require(Algorithm.HMAC256(Base64.getEncoder().encode(SECRET_KEY.getBytes())))
                    .build()
                    .verify(token)
                    .getClaim("userFirstName")
                    .asString();
        } catch (Exception ex){
            return null;
        }
    }
}
