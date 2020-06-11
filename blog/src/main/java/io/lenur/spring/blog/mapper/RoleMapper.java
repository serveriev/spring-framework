package io.lenur.spring.blog.mapper;

import io.lenur.spring.blog.domain.Role;
import io.lenur.spring.blog.dto.RoleResponseDto;

public class RoleMapper {
    public static RoleResponseDto toResponse(Role role) {
        RoleResponseDto dto = new RoleResponseDto();
        dto.setName(role.getName());

        return dto;
    }
}
