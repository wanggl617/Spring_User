package com.User.dao.Impl;

import com.User.dao.UserDao;
import com.User.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
@Repository("userDao")
public class UserDaoImpl implements UserDao {
    @Autowired
    private JdbcTemplate template;
    @Override
    public List<User> findAll() {
        String sql = "select * from sys_user";
        List<User> userList = template.query(sql, new BeanPropertyRowMapper<User>(User.class));
        return userList;
    }

    @Override
    public void save(Long userid, Long[] rolesID) {
        for (Long roleid : rolesID) {
            String sql = "insert  into  sys_user_role values (?,?)";
            template.update(sql,userid,roleid);
        }

    }

    @Override
    public Long save(User user) {
        String sql = "insert into sys_user values(null,?,?,?,?)";
//        template.update(sql,user.getUsername(),user.getEmail(),user.getPassword(),user.getPhoneNum());

        //Jdbctemplate  模板有API可以直接得到，数据库自动生成的 id 值，比较复杂
        PreparedStatementCreator preparedStatementCreator = new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
                preparedStatement.setString(1, user.getUsername());
                preparedStatement.setString(2, user.getEmail());
                preparedStatement.setString(3, user.getPassword());
                preparedStatement.setString(4, user.getPhoneNum());
                return preparedStatement;
            }
        };
        //创建 keyHolder
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        template.update(preparedStatementCreator,keyHolder);
        //获取生成的主键
        long userID = keyHolder.getKey().longValue();
        //MyBatis 中的 API 更简洁

        return userID; //这个ID是 数据库自动生成的id。
    }

    @Override
    public void delUserAndRole(Long userId) {
        String sql = "delete from sys_user_role where userId = ?";
        template.update(sql,userId);
    }

    @Override
    public void delete(Long userId) {
        String sql  ="delete from sys_user where id = ?";
        template.update(sql,userId);
    }

    @Override
    public User findUserAndPwd(String username, String password) {
        try{
            String sql = "select * from sys_user where username = ? and password = ?";
            User user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), username, password);
            return user;
        }catch (EmptyResultDataAccessException e){
            System.out.println("select_null");
            return null;
        }

    }

}
