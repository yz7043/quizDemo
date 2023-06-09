package com.andy.project1.dao.rowmapper;

import com.andy.project1.domain.User;
import org.springframework.stereotype.Component;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class UserRowMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        Integer user_id = rs.getInt("user_id");
        String email = rs.getString("email");
        String password = rs.getString("password");
        String firstname = rs.getString("firstname");
        String lastname = rs.getString("lastname");
        Boolean is_active = rs.getBoolean("is_active");
        Boolean is_admin = rs.getBoolean("is_admin");
        User user = new User(
                user_id,
                email,
                password,
                firstname,
                lastname,
                is_active,
                is_admin
        );
        return user;
    }
}
