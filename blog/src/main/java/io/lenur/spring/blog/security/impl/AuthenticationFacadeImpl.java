package io.lenur.spring.blog.security.impl;

import io.lenur.spring.blog.security.AuthenticationFacade;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationFacadeImpl implements AuthenticationFacade {
    @Override
    public UserDetails getUser() {
        Authentication authentication = getAuthentication();

        return (UserDetails) authentication.getPrincipal();
    }

    @Override
    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }
}
