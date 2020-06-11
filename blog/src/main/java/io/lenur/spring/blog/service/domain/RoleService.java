package io.lenur.spring.blog.service.domain;

import io.lenur.spring.blog.domain.Role;
import io.lenur.spring.blog.domain.RoleName;

public interface RoleService {
    Role getByName(RoleName name);
}
