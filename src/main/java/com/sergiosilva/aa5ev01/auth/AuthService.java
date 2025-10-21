package com.sergiosilva.aa5ev01.auth;

import com.sergiosilva.aa5ev01.auth.dto.UserRegisterRequest;
import com.sergiosilva.aa5ev01.auth.dto.UserResponse;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;

@Service
public class AuthService {

    private final UserRepository repo;

    public AuthService(UserRepository repo) {
        this.repo = repo;
    }

    public UserResponse register(UserRegisterRequest req) {
        // Validaciones de verificación (negocio)
        if (repo.findByUsername(req.getUsername()).isPresent()) {
            throw new IllegalArgumentException("username ya registrado");
        }
        if (repo.findByEmail(req.getEmail()).isPresent()) {
            throw new IllegalArgumentException("email ya registrado");
        }

        // (Evidencia simple: guardamos password plano. Para producción usar hash)
        String id = UUID.randomUUID().toString();
        User user = new User(id, req.getUsername().trim(), req.getEmail().trim(), req.getPassword());
        repo.save(user);
        return new UserResponse(user.getId(), user.getUsername(), user.getEmail());
    }

    // Helper para login en la siguiente parte
    public boolean verifyCredentials(String usernameOrEmail, String password) {
        return repo.findByUsername(usernameOrEmail)
                .or(() -> repo.findByEmail(usernameOrEmail))
                .filter(u -> Objects.equals(u.getPassword(), password))
                .isPresent();
    }

    public String login(String usernameOrEmail, String password) {
        boolean ok = verifyCredentials(usernameOrEmail, password);
        if (ok) {
            return "Autenticación satisfactoria";
        }
        throw new IllegalArgumentException("Credenciales incorrectas");
    }
}