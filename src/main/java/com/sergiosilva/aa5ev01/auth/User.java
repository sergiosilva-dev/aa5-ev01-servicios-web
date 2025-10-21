package com.sergiosilva.aa5ev01.auth;

public class User {
    private final String id;
    private final String username;
    private final String email;
    private final String password; // Nota: en evidencia simple, sin hash.

    public User(String id, String username, String email, String password) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}