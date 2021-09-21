package com.User.service;

import com.User.domain.Role;

import java.util.List;

public interface RoleService {
    List<Role> list();

    void save(Role role);

    void del(Long roleId);
}
