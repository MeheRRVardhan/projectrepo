package com.verteil.project1.controller;

import com.nimbusds.jose.jwk.source.ImmutableSecret;
import com.verteil.project1.entity.User;
import com.verteil.project1.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.Optional;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Value("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c")  // Corrected syntax for injecting secret key
    private String jwtSecretKey;

    @Autowired
    private UserRepo userRepo;

    @PostMapping("/check")
    public String login(@RequestBody LoginRequest loginRequest) {
        Optional<User> user = userRepo.findByEmail(loginRequest.getEmail());

        if (user.isPresent() && user.get().getPassword().equals(loginRequest.getPassword())) {
            String token = createJwtToken(user.get().getEmail());
            return "Login successful\nToken: " + token;
        }
        return "Invalid email or password";
    }

    static class LoginRequest {
        private String email;
        private String password;

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

    private String createJwtToken(String email) {
        if (jwtSecretKey.length() < 32) {
            throw new IllegalArgumentException("JWT secret key must be at least 32 characters long");
        }

        Instant now = Instant.now();
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("Vikram - It's Me")
                .issuedAt(now)
                .expiresAt(now.plusSeconds(24 * 3600))
                .subject(email)
                .build();

        var encoder = new NimbusJwtEncoder(new ImmutableSecret<>(jwtSecretKey.getBytes()));
        var params = JwtEncoderParameters.from(JwsHeader.with(MacAlgorithm.HS256).build(), claims);
        return encoder.encode(params).getTokenValue();
    }
}
