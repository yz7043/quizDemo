package com.andy.project1.dao;

import com.andy.project1.dao.rowmapper.UserRowMapper;
import com.andy.project1.domain.User;
import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.In;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDao {
    private final JdbcTemplate jdbcTemplate;
    private final UserRowMapper userRowMapper;

    @Autowired
    public UserDao(JdbcTemplate jdbcTemplate, UserRowMapper userRowMapper){
        this.jdbcTemplate = jdbcTemplate;
        this.userRowMapper = userRowMapper;
    }

    public User getUserById(Integer id){
        String query = "SELECT * FROM Users WHERE user_id = ?";
        List<User> result = jdbcTemplate.query(query, userRowMapper, id);
        return result.size() == 0 ? null : result.get(0);
    }

    public User getUserByEmail(String email){
        String query = "SELECT * FROM Users WHERE email = ?";
        List<User> result = jdbcTemplate.query(query, userRowMapper, email);
        return result.size() == 0 ? null : result.get(0);
    }

    public int addUser(User user){
        String query = "INSERT INTO Users (email, password, firstname, lastname, is_active, is_admin)" +
                "VALUES (?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(query,
                user.getEmail(),
                user.getPassword(),
                user.getFirstname(),
                user.getLastname(),
                user.getIs_active(),
                user.getIs_admin()
        );
    }

    public int updateUser(User user){
        String query = "UPDATE Users SET password = ?, lastname = ?, firstname = ?, is_active = ?, is_admin = ? " +
                "WHERE user_id = ?";
        return jdbcTemplate.update(query,
                user.getPassword(),
                user.getLastname(),
                user.getFirstname(),
                user.getIs_active(),
                user.getIs_admin(),
                user.getUser_id()
        );
    }

    public int deleteUserById(Integer id){
        String query = "DELETE FROM Users WHERE user_id = ?";
        return jdbcTemplate.update(query, id);
    }

    public int deleteUserByEmail(String email){
        String query = "DELETE FROM Users WHERE email = ?";
        return jdbcTemplate.update(query, email);
    }
}
