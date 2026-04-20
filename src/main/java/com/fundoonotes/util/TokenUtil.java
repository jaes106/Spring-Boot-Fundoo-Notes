package com.fundoonotes.util;

import org.springframework.stereotype.Component;

@Component
public class TokenUtil {

    public String generateToken(Long userId) {
        return "TOKEN_" + userId;
    }

    public Long decodeToken(String token) {
        return Long.parseLong(token.split("_")[1]);
    }
}