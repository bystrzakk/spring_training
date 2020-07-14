package test.demo.spring.core.oauth2.controller;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import test.demo.spring.core.oauth2.model.User;

import java.util.Date;

@RestController
public class LoginController {

    @PostMapping("/logIn")
    public String login(@RequestBody User user) {
        long currentTimeMillis = System.currentTimeMillis();
        return Jwts.builder()
                .setSubject(user.getLogin())
                .claim("role", "user")
                .setIssuedAt(new Date(currentTimeMillis))
                .setExpiration(new Date(currentTimeMillis + 200000))
                .signWith(SignatureAlgorithm.HS256, user.getPassword())
                .compact();
    }
}
