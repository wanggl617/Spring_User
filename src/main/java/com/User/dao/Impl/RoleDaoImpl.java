package com.User.dao.Impl;

import com.User.dao.RoleDao;
import com.User.domain.Role;
import com.User.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository("roleDao")
public class RoleDaoImpl implements RoleDao {
    @Autowired
    private JdbcTemplate template;
    @Override
    public List<Role> findAll() {
        String sql = "select * from sys_role";
        List<Role> roleList = template.query(sql, new BeanPropertyRowMapper<Role>(Role.class));
        return roleList;
    }

    @Override
    public void save(Role role) {
        String sql = "insert into sys_role values (null ,?,?)";
        template.update(sql,role.getRoleName(),role.getRoleDesc());
    }
    @Override
    public List<Role> findRolesByuserID(User user) {
        String sql = "select * from sys_user_role ur, sys_role r where ur.userID = ? and ur.roleId = r.id";
        List<Role> roleList = template.query(sql, new BeanPropertyRowMapper<Role>(Role.class), user.getId());
        return roleList;
    }

    @Override
    public void delete(Long roleId) {
        String sql = "delete from sys_role where id = ?";
        template.update(sql,roleId);
    }

    @Override
    public void deleteRoleAndUser(Long roleId) {
        String sql = "delete from sys_user_role where roleId = ?";
        template.update(sql,roleId);

    }
}
