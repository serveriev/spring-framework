package io.lenur.spring.blog.security.filter;

import java.io.IOException;
import java.util.Objects;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.jsonwebtoken.JwtException;
import io.lenur.spring.blog.exception.ErrorHandler;
import io.lenur.spring.blog.security.AuthenticationFacade;
import io.lenur.spring.blog.security.JwtTokenProvider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {
    private static final String AUTH_HEADER_KEY = "Authorization";
    private static final String AUTH_VALUE_START_WITH = "Bearer ";

    private static final Logger LOGGER = LogManager.getLogger(ErrorHandler.class);

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationFacade authenticationFacade;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain chain
    ) throws ServletException, IOException {
        final String requestTokenHeader = request.getHeader(AUTH_HEADER_KEY);
        String username = null;
        String jwtToken = null;

        if (requestTokenHeader != null && requestTokenHeader.startsWith(AUTH_VALUE_START_WITH)) {
            jwtToken = requestTokenHeader.substring(7);
            try {
                username = tokenProvider.getUsernameFromToken(jwtToken);
            } catch (IllegalArgumentException | JwtException e) {
                LOGGER.warn(e.getMessage());
            }
        } else {
            LOGGER.warn("JWT Token does not begin with Bearer String");
        }

        if (username != null && authenticationFacade.getAuthentication() == null) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            if (tokenProvider.validateToken(jwtToken)
                    && Objects.equals(userDetails.getUsername(), username)
            ) {
                UsernamePasswordAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                WebAuthenticationDetails authenticationDetails =
                        new WebAuthenticationDetailsSource().buildDetails(request);
                authenticationToken.setDetails(authenticationDetails);
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }
        chain.doFilter(request, response);
    }
}