package io.lenur.spring.blog.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

public interface AuthenticationFacade {
    UserDetails getUser();

    Authentication getAuthentication();
}
