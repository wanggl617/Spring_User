package com.User.service;

import com.User.domain.User;

import java.util.List;

public interface UserService {
    List<User> list();

    void save(User user,Long[] rolesID);
    void del(Long roleId);

    User login(String username, String password);
}
