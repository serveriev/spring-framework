package io.lenur.spring.blog.controller;

import io.lenur.spring.blog.dto.AuthenticationDto;
import io.lenur.spring.blog.dto.AuthenticationResponseDto;
import io.lenur.spring.blog.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@CrossOrigin
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @PostMapping(value = "/authenticate")
    public ResponseEntity<?> authenticate(
            @Valid @RequestBody AuthenticationDto authenticationRequest
    ) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getUsername(),
                        authenticationRequest.getPassword()
                )
        );

        final UserDetails userDetails = (UserDetails)authentication.getPrincipal();
        final String token = tokenProvider.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponseDto(token));
    }
}
