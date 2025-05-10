package com.mvp.ordermanagement.controller;

import com.mvp.ordermanagement.config.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private JwtUtil jwtUtil;

    /**
     * Authenticate a user and generate a JWT token.
     *
     * @param request the authentication request containing username and password
     * @return the generated JWT token
     */
    @PostMapping("/login")
    public ResponseEntity<?> authenticate(@RequestBody HashMap<String, String> request) {
        // In a real-world scenario, validate user credentials from a database
        String username = request.get("username");

        // Générer le token avec le nom d'utilisateur
        String token = jwtUtil.generateToken(username, List.of("ROLE_USER"));
        return ResponseEntity.ok(new HashMap<>() {{ put("token", token); }});
    }

}
