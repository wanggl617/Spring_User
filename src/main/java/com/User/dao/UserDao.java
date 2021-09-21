package com.User.dao;

import com.User.domain.User;

import java.util.List;

public interface UserDao {
    List<User> findAll();


    void save(Long id,Long[] rolesID);

    Long save(User user);

    void delUserAndRole(Long userId);

    void delete(Long userId);

    User findUserAndPwd(String username, String password);
}
