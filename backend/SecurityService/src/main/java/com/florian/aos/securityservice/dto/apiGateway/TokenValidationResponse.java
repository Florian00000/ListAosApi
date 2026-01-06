package com.florian.aos.securityservice.dto.apiGateway;

import java.util.List;

public class TokenValidationResponse {
    private boolean valid;
    private String username;
    private List<String> authorities;
    private String message;

    public TokenValidationResponse() {
    }

    public TokenValidationResponse(boolean valid, String username, List<String> authorities, String message) {
        this.valid = valid;
        this.username = username;
        this.authorities = authorities;
        this.message = message;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<String> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<String> authorities) {
        this.authorities = authorities;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
