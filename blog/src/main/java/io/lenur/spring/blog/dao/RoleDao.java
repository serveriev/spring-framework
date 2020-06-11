package io.lenur.spring.blog.dao;

import io.lenur.spring.blog.domain.Role;
import io.lenur.spring.blog.domain.RoleName;

import java.util.Optional;

public interface RoleDao {
    Optional<Role> findByName(RoleName name);

}
