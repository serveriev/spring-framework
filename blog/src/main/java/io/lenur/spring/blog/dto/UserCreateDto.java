package io.lenur.spring.blog.dto;

import io.lenur.spring.blog.domain.RoleName;

import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.Set;

public class UserCreateDto {
    @NotEmpty
    private String name;

    @NotEmpty
    private String password;

    @NotEmpty
    private Set<RoleName> roles = new HashSet<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<RoleName> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleName> roles) {
        this.roles = roles;
    }
}
