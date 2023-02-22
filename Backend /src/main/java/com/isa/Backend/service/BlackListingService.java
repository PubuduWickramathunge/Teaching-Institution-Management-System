package com.isa.Backend.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class BlackListingService {

    private final Map<String, String> tokenBlacklist = new HashMap<>();

    public void blackListJwt(String jwt) {
        tokenBlacklist.put(jwt, jwt);
    }

    public String getJwtBlackList(String jwt) {
        return tokenBlacklist.getOrDefault(jwt, null);
    }
}
