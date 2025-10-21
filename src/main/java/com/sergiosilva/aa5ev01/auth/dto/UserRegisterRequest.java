package com.sergiosilva.aa5ev01.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserRegisterRequest {
    @NotBlank(message = "username es obligatorio")
    @Size(min = 3, max = 30, message = "username entre 3 y 30 caracteres")
    private String username;

    @NotBlank(message = "email es obligatorio")
    @Email(message = "email inválido")
    private String email;

    @NotBlank(message = "password es obligatorio")
    @Size(min = 8, message = "password mínimo 8 caracteres")
    private String password;

    // getters y setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}