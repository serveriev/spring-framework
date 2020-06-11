package io.lenur.spring.blog.service.domain.impl;

import io.lenur.spring.blog.dao.RoleDao;
import io.lenur.spring.blog.domain.Role;
import io.lenur.spring.blog.domain.RoleName;
import io.lenur.spring.blog.exception.ModelNotFoundException;
import io.lenur.spring.blog.service.domain.RoleService;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleDao roleDao;

    public RoleServiceImpl(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public Role getByName(RoleName name) {
        return roleDao
                .findByName(name)
                .orElseThrow(() -> new ModelNotFoundException("Role is not found!"));
    }
}
