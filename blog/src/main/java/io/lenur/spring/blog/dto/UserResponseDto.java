package io.lenur.spring.blog.dto;

import java.util.HashSet;
import java.util.Set;

public class UserResponseDto {
    private Long Id;
    private String name;
    private Set<RoleResponseDto> roles = new HashSet<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public Set<RoleResponseDto> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleResponseDto> roles) {
        this.roles = roles;
    }

    public void addRole(RoleResponseDto role) {
        this.roles.add(role);
    }
}
