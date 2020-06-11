package io.lenur.spring.blog.security.impl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.lenur.spring.blog.security.JwtTokenProvider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.stream.Collectors;

@Component
@PropertySource({"/WEB-INF/classes/security.properties"})
public class JwtTokenProviderImpl implements JwtTokenProvider {
    private static final String SECRET_KEY = "jwt.secret";
    private static final String SECRET_EXPIRE = "jwt.expire";
    private static final Logger LOGGER = LogManager.getLogger(JwtTokenProviderImpl.class);

    @Autowired
    private Environment environment;

    @Override
    public String generateToken(UserDetails userDetails) {
        String authorities = userDetails
                .getAuthorities()
                .stream()
                .map(Object::toString)
                .collect(Collectors.joining(","));

        Date now = new Date();
        long expire = Long.parseLong(environment.getProperty(SECRET_EXPIRE));
        Date expiryDate = new Date(now.getTime() + expire);

        return Jwts.builder()
                .setAudience(authorities)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, environment.getProperty(SECRET_KEY))
                .compact();
    }

    @Override
    public boolean validateToken(String authToken) {
        try {
            Jwts.parser()
                    .setSigningKey(environment.getProperty(SECRET_KEY))
                    .parseClaimsJws(authToken);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            LOGGER.error(e.getMessage());
        }

        return false;
    }

    @Override
    public String getUsernameFromToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(environment.getProperty(SECRET_KEY))
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }
}
