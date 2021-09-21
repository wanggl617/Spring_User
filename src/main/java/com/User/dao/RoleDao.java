package com.User.dao;

import com.User.domain.Role;
import com.User.domain.User;

import java.util.List;

public interface RoleDao {

    List<Role> findAll();

    void save(Role role);
    List<Role> findRolesByuserID(User user);

    void delete(Long roleId);

    void deleteRoleAndUser(Long roleId);
}
