package io.lenur.spring.blog.dto;

import javax.validation.constraints.NotEmpty;

public class AuthenticationDto {
    @NotEmpty
    private String username;
    @NotEmpty
    private String password;

    public AuthenticationDto() {
    }

    public AuthenticationDto(String username, String password) {
        this.setUsername(username);
        this.setPassword(password);
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}