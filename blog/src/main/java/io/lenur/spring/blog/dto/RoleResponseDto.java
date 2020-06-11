package io.lenur.spring.blog.dto;

import io.lenur.spring.blog.domain.RoleName;

public class RoleResponseDto {
    private RoleName name;

    public RoleName getName() {
        return name;
    }

    public void setName(RoleName name) {
        this.name = name;
    }
}
