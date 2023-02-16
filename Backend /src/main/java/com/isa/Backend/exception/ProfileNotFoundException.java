package com.isa.Backend.exception;

public class ProfileNotFoundException extends RuntimeException {
    public ProfileNotFoundException(String email) {

        super("Profile not found for email: " + email);
    }
}
