package io.lenur.spring.blog.security;

import org.springframework.security.core.userdetails.UserDetails;

public interface JwtTokenProvider {
    String generateToken(UserDetails userDetails);

    boolean validateToken(String authToken);

    String getUsernameFromToken(String token);
}
