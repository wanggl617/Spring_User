package com.User.service.Impl;

import com.User.dao.RoleDao;
import com.User.dao.UserDao;
import com.User.domain.User;
import com.User.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao dao;
    @Autowired
    private RoleDao roledao;
    @Override
    public List<User> list() {
        List<User> userList = dao.findAll();
        for(User user:userList){
            user.setRoles(roledao.findRolesByuserID(user));
        }

        return userList;
    }

    @Override
    public void save(User user,Long[] rolesID) {
        //将user用户信息插入 用户表，并返回数据库自动生成的主键 id
        Long userId = dao.save(user);
        //userId 对应的 roleId 插入中间关系表
        dao.save(userId,rolesID);
    }

    @Override
    public void del(Long userId) {
        //需要执行两个操作
        //先删除 sys_user_role 关系表
        dao.delUserAndRole(userId);
        //删除 sys_role 表
        dao.delete(userId);
    }

    @Override
    public User login(String username, String password) {
        User user = dao.findUserAndPwd(username,password);
        System.out.println("user:  null ");
        return user;
    }
}
