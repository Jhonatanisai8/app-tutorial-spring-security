package com.isai.app.exceptions.personalization;


import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;

public class JWTAuthenticationException
        extends AuthenticationCredentialsNotFoundException {

    public JWTAuthenticationException(String message) {
        super(message);
    }
}
