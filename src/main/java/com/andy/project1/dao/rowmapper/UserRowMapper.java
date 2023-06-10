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
        User user = new User();
        user.setUser_id(rs.getInt("user_id"));
        user.setEmail(rs.getString("email"));
        user.setPassword(rs.getString("password"));
        user.setFirstname(rs.getString("firstname"));
        user.setLastname( rs.getString("lastname"));
        user.setIs_active(rs.getBoolean("is_active"));
        user.setIs_admin(rs.getBoolean("is_admin"));
        return user;
    }
}
