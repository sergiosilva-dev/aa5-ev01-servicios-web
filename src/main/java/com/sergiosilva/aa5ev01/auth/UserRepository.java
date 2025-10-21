package com.sergiosilva.aa5ev01.auth;

import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class UserRepository {
    private final Map<String, User> byId = new ConcurrentHashMap<>();
    private final Map<String, String> usernameIndex = new ConcurrentHashMap<>();
    private final Map<String, String> emailIndex = new ConcurrentHashMap<>();

    public Optional<User> findByUsername(String username) {
        String id = usernameIndex.get(username.toLowerCase());
        return Optional.ofNullable(id).map(byId::get);
    }

    public Optional<User> findByEmail(String email) {
        String id = emailIndex.get(email.toLowerCase());
        return Optional.ofNullable(id).map(byId::get);
    }

    public User save(User user) {
        byId.put(user.getId(), user);
        usernameIndex.put(user.getUsername().toLowerCase(), user.getId());
        emailIndex.put(user.getEmail().toLowerCase(), user.getId());
        return user;
    }
}