package com.User.service.Impl;

import com.User.dao.RoleDao;
import com.User.domain.Role;
import com.User.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service("roleService")
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleDao dao;
    @Override
    public List<Role> list() {
        List<Role> roleList = dao.findAll();
        return roleList;
    }

    @Override
    public void save(Role role) {
        dao.save(role);
    }

    @Override
    public void del(Long roleId) {
        dao.deleteRoleAndUser(roleId);
        dao.delete(roleId);
    }


}
