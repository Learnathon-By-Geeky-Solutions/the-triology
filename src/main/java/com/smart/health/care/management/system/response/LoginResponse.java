package com.Smart.Health.Care.Management.System.Response;

public class LoginResponse {
    private String token;
    private long expiresIn;

    // Getters
    public String getToken() {
        return token;
    }

    public long getExpiresIn() {
        return expiresIn;
    }

    // Setters with method chaining
    public LoginResponse setToken(String token) {
        this.token = token;
        return this;
    }

    public LoginResponse setExpiresIn(long expiresIn) {
        this.expiresIn = expiresIn;
        return this;
    }
}
