package com.andy.project1.dao;

import com.andy.project1.dao.rowmapper.UserRowMapper;
import com.andy.project1.domain.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.InstanceOfAssertFactories.spliterator;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
public class UserDaoTest {
    @Autowired
    private UserDao userDao;
    @Autowired
    private UserRowMapper userRowMapper;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    User user1;
    User user2;
    @BeforeEach
    public void setup(){
        String sql = "INSERT INTO Users (email, password, firstname, lastname, is_active, is_admin) VALUES " +
                "(?, ?, ?, ?, ?, ?)";
        KeyHolder keyHolder1 = new GeneratedKeyHolder();
        user1 = new User();
        user1.setEmail("user1@test.com");
        user1.setPassword("user1pwd");
        user1.setFirstname("user1First");
        user1.setLastname("user1Last");
        user1.setIs_active(true);
        user1.setIs_admin(false);
        jdbcTemplate.update(
                con -> {
                    PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                    ps.setString(1, user1.getEmail());
                    ps.setString(2, user1.getPassword());
                    ps.setString(3, user1.getFirstname());
                    ps.setString(4, user1.getLastname());
                    ps.setBoolean(5, user1.getIs_active());
                    ps.setBoolean(6, user1.getIs_admin());
                    return ps;
                }, keyHolder1
        );
        user1.setUser_id(keyHolder1.getKey().intValue());
        KeyHolder keyHolder2 = new GeneratedKeyHolder();
        user2 = new User();
        user2.setEmail("user2@test.com");
        user2.setPassword("user2pwd");
        user2.setFirstname("user2First");
        user2.setLastname("user2Last");
        user2.setIs_active(false);
        user2.setIs_admin(true);
        jdbcTemplate.update(
                con -> {
                    PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                    ps.setString(1, user2.getEmail());
                    ps.setString(2, user2.getPassword());
                    ps.setString(3, user2.getFirstname());
                    ps.setString(4, user2.getLastname());
                    ps.setBoolean(5, user2.getIs_active());
                    ps.setBoolean(6, user2.getIs_admin());
                    return ps;
                }, keyHolder2
        );
        user2.setUser_id(keyHolder2.getKey().intValue());
    }

    @Test
    public void getUserByIdTest(){
        User result = userDao.getUserById(user1.getUser_id());
        assertThat(result.toString()).isEqualTo(user1.toString());
        User resultNull = userDao.getUserById(-1);
        assertThat(resultNull).isNull();
    }

    @Test
    public void getUserByEmailTest(){
        User result = userDao.getUserByEmail(user2.getEmail());
        assertThat(result.toString()).isEqualTo(user2.toString());
        User resultNull = userDao.getUserByEmail("notvalidemail");
        assertThat(resultNull).isNull();
    }

    @Test
    public void addUserTest(){
        User added = new User(null, "testEmail@GG.com", "testPwd", "testFirst",
                "testLast", true, true);
        int resCode = userDao.addUser(added);
        assertThat(resCode).isEqualTo(1);
        List<User> users = jdbcTemplate.query("SELECT * FROM Users", userRowMapper);
        assertThat(users).extracting(User::getPassword).containsExactlyInAnyOrder(
                user1.getPassword(), user2.getPassword(), added.getPassword()
        );
        assertThat(users).extracting(User::getEmail).containsExactlyInAnyOrder(
                user1.getEmail(), user2.getEmail(), added.getEmail()
        );
        assertThat(users).extracting(User::getFirstname).containsExactlyInAnyOrder(
                user1.getFirstname(), user2.getFirstname(), added.getFirstname()
        );
        assertThat(users).extracting(User::getLastname).containsExactlyInAnyOrder(
                user1.getLastname(), user2.getLastname(), added.getLastname()
        );
        assertThat(users).extracting(User::getIs_active).containsExactlyInAnyOrder(
                user1.getIs_active(), user2.getIs_active(), added.getIs_active()
        );
        assertThat(users).extracting(User::getIs_admin).containsExactlyInAnyOrder(
                user1.getIs_admin(), user2.getIs_admin(), added.getIs_admin()
        );

        User user2 = new User(null, "testEmail@GG.com", "testPwd", "testFirst",
                "testLast", true, true);
        assertThrows(DuplicateKeyException.class, () -> userDao.addUser(user2));
    }

    @Test
    public void updateUserTest(){
        user1.setFirstname("sdasdaFirst");
        userDao.updateUser(user1);
        User result = jdbcTemplate.query("SELECT * FROM Users WHERE user_id = ?",
                userRowMapper, user1.getUser_id()).get(0);
        assertThat(result.toString()).isEqualTo(user1.toString());
    }

    @Test
    public void deleteUserByIdTest(){
        int resNull = userDao.deleteUserById(-1);
        assertThat(resNull).isEqualTo(0);
        int result = userDao.deleteUserById(user1.getUser_id());
        assertThat(result).isEqualTo(1);
        List<User> users = jdbcTemplate.query("SELECT * FROM Users", userRowMapper);
        assertThat(users).extracting(User::getUser_id).doesNotContain(user1.getUser_id());
    }

    @Test
    public void deleteUserByEmail(){
        int resNull = userDao.deleteUserByEmail("ndsahkjdhkasEamil");
        assertThat(resNull).isEqualTo(0);
        int result = userDao.deleteUserByEmail(user1.getEmail());
        assertThat(result).isEqualTo(1);
        List<User> users = jdbcTemplate.query("SELECT * FROM Users", userRowMapper);
        assertThat(users).extracting(User::getUser_id).doesNotContain(user1.getUser_id());
    }

    @AfterEach
    public void after(){
        String sql = "DELETE FROM Users WHERE user_id = ?";
        jdbcTemplate.update(sql, user1.getUser_id());
        jdbcTemplate.update(sql, user2.getUser_id());
    }
}
