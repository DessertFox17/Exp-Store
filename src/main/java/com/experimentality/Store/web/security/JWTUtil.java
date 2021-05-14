package com.experimentality.Store.web.security;

import com.experimentality.Store.domain.dto.SecurityUserDto;
import com.experimentality.Store.domain.service.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JWTUtil {

    @Autowired
    UserService userService;

    private static final String KEY = "@Warl0rd581@0nF1r3Q";

    public String generateToken(UserDetails userDetails){

        Map<String, Object> map = new HashMap<>();
        SecurityUserDto user = userService.getByEmail(userDetails.getUsername());

        map.put("usId", user.getUsId());
        map.put("name", user.getName());
        map.put("phoneNumber", user.getPhoneNumber());
        map.put("address", user.getAddress());

        return Jwts.builder()
                .addClaims(map)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(SignatureAlgorithm.HS256, KEY).compact();
    }

    public boolean ValidateToken(String token, UserDetails userDetails){
        return userDetails.getUsername().equals(extractUsername(token))
                && !isTokenExpired(token);
    }

    private Claims getClaims(String token){
        return Jwts.parser().setSigningKey(KEY).parseClaimsJws(token).getBody();
    }

    public String extractUsername(String token){
        return getClaims(token).getSubject();
    }

    public boolean isTokenExpired(String token){
        return getClaims(token).getExpiration().before(new Date());
    }
}
