package api.controller;

import api.dto.LoginRequest;
import api.service.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Optional;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    @Value("${jwt.secret}")
    private String JWT_SECRET;
    private final UserService userService;
    private final PasswordEncoder encoder;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest authenticationRequest) {
        return userService.getUserByLogin(authenticationRequest.getUsername())
                .map(user -> {
                    String encodedPassword = encoder.encode(authenticationRequest.getPassword());
                    String dbPassword = user.getPassword();
                    String token = null;

                    if (dbPassword.equals(encodedPassword)) {
                        token = generateToken(user.getUsername());
                    }

                    return Optional.ofNullable(token);
                })
                .map(token -> token
                        .map(tokenValue -> new ResponseEntity<>(tokenValue, HttpStatus.OK))
                        .orElseGet(() -> new ResponseEntity<>("Invalid credentials", HttpStatus.FORBIDDEN)))
                .orElseGet(() -> new ResponseEntity<>("Invalid credentials", HttpStatus.FORBIDDEN));
    }

    @PostMapping("/register")
    public ResponseEntity register() {
        return null;
    }

    private String generateToken(String username) {
        long currentTimeMillis = System.currentTimeMillis();
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date(currentTimeMillis))
                .setExpiration(new Date(currentTimeMillis + (1000 * 60 * 60 * 24)))
                .signWith(SignatureAlgorithm.HS256, JWT_SECRET.getBytes())
                .claim("username", username)
                .compact();
    }
}
